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
	Button deal, bet, fold;
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
		dealerLabel.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: black;");
		Label dealerTemp = new Label("Image will go here");
		dealerStuff = new VBox(20, dealerLabel, dealerTemp);
		dealerStuff.setAlignment(Pos.TOP_CENTER);

		deal = new Button("Deal");
		bet = new Button("BET");
		bet.setDisable(true);
		fold = new Button("Fold");
		fold.setDisable(true);
		buttonStuff = new VBox(30, deal, bet, fold);
		buttonStuff.setAlignment(Pos.CENTER);

		playerOne = new Player();
		winningOne = new Label("Winnings: $" + playerOne.totalWinnings);
		playerLabelOne = new Label("Player 1");
		playerLabelOne.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: black;");
		Label playerOneTemp = new Label("Image will go here");
		playerOneStuff = new VBox(30, playerLabelOne, playerOneTemp, winningOne);
		ante1 = new Label("Ante");
		anteOne = new TextField("0");
		ppw1 = new Label("PPW");
		ppwOne = new TextField("0");
		wager1 = new Label("Wager");
		wager1.setDisable(true);
		wagerOne = new TextField();
		wagerOne.setDisable(true);
		betsOne = new VBox(10, ante1, anteOne, ppw1, ppwOne, wager1, wagerOne);
		playerOneAll = new HBox(10, betsOne, playerOneStuff);

		playerTwo = new Player();
		winningTwo = new Label("Winnings: $" + playerTwo.totalWinnings);
		playerLabelTwo = new Label("Player 2");
		playerLabelTwo.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: black;");
		Label playerTwoTemp = new Label("Image will go here");
		playerTwoStuff = new VBox(30, playerLabelTwo, playerTwoTemp, winningTwo);
		ante2 = new Label("Ante");
		anteTwo = new TextField("0");
		ppw2 = new Label("PPW");
		ppwTwo = new TextField("0");
		wager2 = new Label("Wager");
		wager2.setDisable(true);
		wagerTwo = new TextField();
		wagerTwo.setDisable(true);
		betsTwo = new VBox(10, ante2, anteTwo, ppw2, ppwTwo, wager2, wagerTwo);
		playerTwoAll = new HBox(10, playerTwoStuff, betsTwo);

		message = new Label();
		message.setText("Place ante and/or pair plus wager (PPW) between $5-25. Click bet to deal cards!");
		message.setStyle("-fx-font-size: 25;" );
		message.setAlignment(Pos.CENTER);

		freshStart = new MenuItem("Fresh Start");
		newLook = new MenuItem("New Look");
		exit = new MenuItem("Exit");
		options = new Menu("Options");
		options.getItems().addAll(freshStart, newLook, exit);
		menuBar = new MenuBar();
		menuBar.getMenus().add(options);
		HBox paneTop = new HBox(300, menuBar, dealerStuff);

//		deal.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				int playerAnte1 = Integer.parseInt(anteOne.getText());
//				int playerPpw1 = Integer.parseInt(ppwOne.getText());
//				int playerAnte2 = Integer.parseInt(anteTwo.getText());
//				int playerPpw2 = Integer.parseInt(ppw2.getText());
//
//				if ((playerAnte1 < 5 || playerAnte1 > 25) || (playerAnte2 < 5 || playerAnte2 > 25)) {
//					message.setText("Please enter an ante between $5-25.");
//				}
//				else {
//					if (playerPpw1 >= 5 && playerPpw1 <= 25) {
//						playerOne.pairPlusBet = playerPpw1;
//					}
//					if (playerPpw2 >= 5 && playerPpw2 <= 25) {
//						playerTwo.pairPlusBet = playerPpw2;
//					}
//					ppw1.setDisable(true);
//					ppwOne.setDisable(true);
//					ppw2.setDisable(true);
//					ppwTwo.setDisable(true);
//
//					playerOne.anteBet = playerAnte1;
//					playerTwo.anteBet = playerAnte2;
//					ante1.setDisable(true);
//					anteOne.setDisable(true);
//					ante2.setDisable(true);
//					anteTwo.setDisable(true);
//
//				}
//			}
//		});

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
