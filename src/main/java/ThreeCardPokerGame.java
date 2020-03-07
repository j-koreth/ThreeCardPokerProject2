import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ThreeCardPokerGame extends Application {
	Player playerOne;
	Player playerTwo;
	Dealer theDealer;
	VBox playerOneStuff, dealerStuff, playerTwoStuff, betsOne, betsTwo, buttonStuff;
	HBox playerOneAll, playerTwoAll, playerONeCards, playerTwoCards, dealerCards;
	Button bet, fold;
	TextField anteOne, ppwOne, wagerOne, anteTwo, ppwTwo, wagerTwo;
	MenuBar menuBar;
	Menu options;
	MenuItem freshStart, newLook, exit;
	Label dealerLabel, playerLabelOne, playerLabelTwo, ante1, ppw1, wager1, ante2, ppw2, wager2, winningOne, winningTwo, message;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: gold;");

		dealerLabel = new Label("Dealer");
		Label dealerTemp = new Label("Image will go here");
		dealerStuff = new VBox(20, dealerLabel, dealerTemp);
		dealerStuff.setAlignment(Pos.TOP_CENTER);

		bet = new Button("BET");
		fold = new Button("Fold");
		buttonStuff = new VBox(30, bet, fold);
		buttonStuff.setAlignment(Pos.CENTER);

		playerOne = new Player();
		winningOne = new Label("Winnings: $" + playerOne.totalWinnings + ".00");
		playerLabelOne = new Label("Player 1");
		Label playerOneTemp = new Label("Image will go here");
		playerOneStuff = new VBox(30, playerLabelOne, winningOne, playerOneTemp);
		ante1 = new Label("Ante");
		anteOne = new TextField();
		ppw1 = new Label("PPW");
		ppwOne = new TextField();
		wager1 = new Label("Wager");
		wagerOne = new TextField();
		betsOne = new VBox(10, ante1, anteOne, ppw1, ppwOne, wager1, wagerOne);
		playerOneAll = new HBox(10, betsOne, playerOneStuff);

		playerTwo = new Player();
		winningTwo = new Label("Winnings: $" + playerTwo.totalWinnings + ".00");
		playerLabelTwo = new Label("Player 2");
		Label playerTwoTemp = new Label("Image will go here");
		playerTwoStuff = new VBox(30, playerLabelTwo, winningTwo, playerTwoTemp);
		ante2 = new Label("Ante");
		anteTwo = new TextField();
		ppw2 = new Label("PPW");
		ppwTwo = new TextField();
		wager2 = new Label("Wager");
		wagerTwo = new TextField();
		betsTwo = new VBox(10, ante2, anteTwo, ppw2, ppwTwo, wager2, wagerTwo);
		playerTwoAll = new HBox(10, playerTwoStuff, betsTwo);

		message = new Label();
		message.setText("Place ante and/or pair plus wager (PPW) between $5-25. Click bet to deal cards!");
		message.setAlignment(Pos.CENTER);

		freshStart = new MenuItem("Fresh Start");
		newLook = new MenuItem("New Look");
		exit = new MenuItem("Exit");
		options = new Menu("Options");
		options.getItems().addAll(freshStart, newLook, exit);
		menuBar = new MenuBar();
		menuBar.getMenus().add(options);
		HBox paneTop = new HBox(300, menuBar, dealerStuff);

		pane.setTop(paneTop);
		pane.setCenter(buttonStuff);
		pane.setRight(playerOneAll);
		pane.setLeft(playerTwoAll);
		pane.setBottom(message);
		Scene scene = new Scene(pane, 1050,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
