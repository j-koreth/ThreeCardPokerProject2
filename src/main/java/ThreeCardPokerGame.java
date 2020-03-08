import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class ThreeCardPokerGame extends Application {
	Player playerOne;
	Player playerTwo;
	Dealer theDealer;
	VBox playerOneStuff, dealerStuff, playerTwoStuff, betsOne, betsTwo, buttonStuff;
	HBox playerOneAll, playerTwoAll, playerOneCards, playerTwoCards, dealerCards;
	Button deal, bet, fold;
	TextField anteOne, ppwOne, anteTwo, ppwTwo;
	MenuBar menuBar;
	Menu options;
	MenuItem freshStart, newLook, exit;
	Label dealerLabel, playerLabelOne, playerLabelTwo, ante1, ppw1, wager1, ante2, ppw2, wager2, winningOne, winningTwo, message;
	PauseTransition pause = new PauseTransition(Duration.seconds(2.0));

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
		pane.setStyle("-fx-background-color: gold;" + "-fx-border-color: black;"+
				"-fx-border-width: 10;");

		/*----------------------Design aspects for dealer-------------------------------*/
		dealerLabel = new Label("Dealer");
		dealerLabel.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: firebrick;");
		Image pic = new Image("player_avatars/han-enter-the-dragon (2).jpg");
		ImageView dealerPic = new ImageView(pic);
		dealerPic.setFitHeight(175);
		dealerPic.setFitWidth(150);
		dealerPic.setPreserveRatio(true);

		VBox dealerPicContainer = new VBox();
		dealerPicContainer.getChildren().add(dealerPic);
		dealerPicContainer.getStyleClass().add("avatar");

		dealerStuff = new VBox(5, dealerLabel, dealerPicContainer);
		dealerStuff.setAlignment(Pos.TOP_CENTER);

		/*----------------------Design aspects for buttons-------------------------------*/
		deal = new Button("Deal");
		deal.setStyle("-fx-background-color: firebrick;");
		bet = new Button("BET");
		bet.setStyle("-fx-background-color: firebrick;");
		bet.setDisable(true);
		fold = new Button("Fold");
		fold.setStyle("-fx-background-color: firebrick;");
		fold.setDisable(true);
		buttonStuff = new VBox(30, deal, bet, fold);
		buttonStuff.setAlignment(Pos.CENTER);

		/*----------------------Design aspects for Player1-------------------------------*/
		playerOne = new Player();
		winningOne = new Label("Winnings: $" + playerOne.totalWinnings);
		winningOne.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: firebrick;");
		playerLabelOne = new Label("Player 1");
		playerLabelOne.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: firebrick;");
		Image bruce = new Image("player_avatars/bruce-lee.jpg");
		ImageView playerOnePic = new ImageView(bruce);
		playerOnePic.setFitHeight(175);
		playerOnePic.setFitWidth(150);
		playerOnePic.setPreserveRatio(true);

		VBox playerOnePicContainer = new VBox();
		playerOnePicContainer.getChildren().add(playerOnePic);
		playerOnePicContainer.getStyleClass().add("avatar");

		playerOneStuff = new VBox(30, playerLabelOne, playerOnePicContainer, winningOne);
		ante1 = new Label("Ante");
		anteOne = new TextField("0");
		ppw1 = new Label("PPW");
		ppwOne = new TextField("0");
		wager1 = new Label("Wager: $" + playerOne.playBet);
		betsOne = new VBox(10, ante1, anteOne, ppw1, ppwOne, wager1);
		playerOneAll = new HBox(10, betsOne, playerOneStuff);

		/*----------------------Design aspects for Player2-------------------------------*/
		playerTwo = new Player();
		winningTwo = new Label("Winnings: $" + playerTwo.totalWinnings);
		winningTwo.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: firebrick;");
		playerLabelTwo = new Label("Player 2");
		playerLabelTwo.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: firebrick;");
		Label playerTwoTemp = new Label("Image will go here");
		playerTwoStuff = new VBox(30, playerLabelTwo, playerTwoTemp, winningTwo);
		ante2 = new Label("Ante");
		anteTwo = new TextField("0");
		anteTwo.setDisable(true);
		ppw2 = new Label("PPW");
		ppwTwo = new TextField("0");
		ppwTwo.setDisable(true);
		wager2 = new Label("Wager: $" + playerTwo.playBet);
		betsTwo = new VBox(10, ante2, anteTwo, ppw2, ppwTwo, wager2);
		playerTwoAll = new HBox(10, playerTwoStuff, betsTwo);

		/*----------------------Design aspects for message-------------------------------*/
		message = new Label();
		message.setText("Place ante and/or optional pair plus wager (PPW) between $5-25. Click bet to deal cards!");
		message.setStyle("-fx-font-size: 16;" );
		message.setAlignment(Pos.CENTER);

		/*----------------------Design aspects for menu-------------------------------*/
		freshStart = new MenuItem("Fresh Start");
		newLook = new MenuItem("New Look");
		exit = new MenuItem("Exit");
		options = new Menu("Options");
		options.getItems().addAll(freshStart, newLook, exit);
		menuBar = new MenuBar();
		menuBar.setStyle("-fx-background-color: firebrick;");
		menuBar.getMenus().add(options);
		HBox paneTop = new HBox(300, menuBar, dealerStuff);

		/*----------------------Set pane for screen layout-------------------------------*/
		pane.setTop(paneTop);
		pane.setCenter(buttonStuff);
		pane.setRight(playerOneAll);
		pane.setLeft(playerTwoAll);
		pane.setBottom(message);
		Scene scene = new Scene(pane, 1050,700);
		scene.getStylesheets().add("css/main.css");
		primaryStage.setScene(scene);
		primaryStage.show();

		/*----------------------Menu item's actions-------------------------------*/
		freshStart.setOnAction(e -> {playerOne.totalWinnings = 0; playerTwo.totalWinnings = 0;});

		newLook.setOnAction(e->{pane.setStyle("-fx-background-color: DARKOLIVEGREEN;" + "-fx-border-color: DARKMAGENTA;"+
				"-fx-border-width: 10;");
				dealerLabel.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: DARKORANGE");
				playerLabelOne.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" +"-fx-border-color: DARKORANGE");
				winningOne.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: DARKORANGE;");
				playerLabelTwo.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" +"-fx-border-color: DARKORANGE");
				winningTwo.setStyle("-fx-font-size: 20;" + "-fx-border-size: 40;" + "-fx-border-color: DARKORANGE;");
				deal.setStyle("-fx-background-color: DARKORANGE");
				bet.setStyle("-fx-background-color: DARKORANGE");
				fold.setStyle("-fx-background-color: DARKORANGE");
				menuBar.setStyle("-fx-background-color: DARKORANGE");
		});

		exit.setOnAction(e -> {primaryStage.close();});

		/*----------------------Deal's actions-------------------------------*/
		deal.setOnAction(e-> {
			int playerAnte1 = Integer.parseInt(anteOne.getText());
			int playerPpw1 = Integer.parseInt(ppwOne.getText());
			Random r = new Random();
			int playerAnte2 = r.nextInt((25 - 5) + 1) + 5;
			anteTwo.setText("" + playerAnte2);
			int playerPpw2 = r.nextInt((25 - 5) + 1) + 5;
			ppwTwo.setText("" + playerPpw2);
			pause.play();

			if ((playerAnte1 < 5 || playerAnte1 > 25) || ((playerPpw1 < 5 || playerPpw1 >25) && (playerPpw1 != 0 ))) {
				Label anteMessage = new Label("Please place a bet and/or optional PPW between $5-25.");
				pane.setBottom(anteMessage);
			}
			else {
					if (playerPpw1 >= 5 && playerPpw1 <= 25) {
						playerOne.pairPlusBet = playerPpw1;
					}
					playerTwo.pairPlusBet = playerPpw2;
					ppwOne.setDisable(true);

					playerOne.anteBet = playerAnte1;
					playerTwo.anteBet = playerAnte2;
					anteOne.setDisable(true);

					playerOne.playBet = playerAnte1;
					playerTwo.playBet = playerAnte2;
					wager1.setText("Wager: $" + playerOne.playBet);
					wager2.setText("Wager: $" + playerTwo.playBet);

					deal.setDisable(true);
					bet.setDisable(false);
					fold.setDisable(false);
					playerOne.hand = theDealer.dealHand();
					playerTwo.hand = theDealer.dealHand();
					theDealer.dealersHand = theDealer.dealHand();
				}
		});

//		/*----------------------Fold's actions-------------------------------*/
//		fold.setOnAction(e-> {
//			playerOne.totalWinnings -= (playerOne.anteBet + playerOne.playBet);
//
//		});


	}

}
