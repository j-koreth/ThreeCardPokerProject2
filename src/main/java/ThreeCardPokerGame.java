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
	boolean newLookInUse = false;

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
		/*----------------------Design aspects for dealer-------------------------------*/
		theDealer = new Dealer();
		dealerLabel = new Label("Dealer");
		dealerLabel.getStyleClass().add("headerLabels");
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
		bet = new Button("Bet");
		bet.setDisable(true);
		fold = new Button("Fold");
		fold.setDisable(true);
		buttonStuff = new VBox(30, deal, bet, fold);
		buttonStuff.setAlignment(Pos.CENTER);

		/*----------------------Design aspects for Player1-------------------------------*/
		playerOne = new Player();
		winningOne = new Label("Winnings: $" + playerOne.totalWinnings);
		winningOne.getStyleClass().add("headerLabels");
		playerLabelOne = new Label("Player 1");
		playerLabelOne.getStyleClass().add("headerLabels");
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
		winningTwo.getStyleClass().add("headerLabels");
		playerLabelTwo = new Label("Player 2");
		playerLabelTwo.getStyleClass().add("headerLabels");
		Image jim = new Image("player_avatars/jim-kelly-enter-the-dragon (2).jpg");
		ImageView playerTwoPic = new ImageView(jim);
		playerTwoPic.setFitHeight(175);
		playerTwoPic.setFitWidth(150);
		playerTwoPic.setPreserveRatio(true);

		VBox playerTwoPicContainer = new VBox();
		playerTwoPicContainer.getChildren().add(playerTwoPic);
		playerTwoPicContainer.getStyleClass().add("avatar");
		playerTwoStuff = new VBox(30, playerLabelTwo, playerTwoPicContainer, winningTwo);
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
		scene.getStylesheets().add("css/default.css");

		primaryStage.setScene(scene);
		primaryStage.show();

		/*----------------------Menu item's actions-------------------------------*/
		freshStart.setOnAction(e -> {
			playerOne.totalWinnings = 0;
			playerTwo.totalWinnings = 0;
			winningOne.setText("Winnings: $" + playerOne.totalWinnings);
			winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);
		});

		newLook.setOnAction(e->{
			if (!newLookInUse) {
				scene.getStylesheets().remove("css/main.css");
				scene.getStylesheets().add("css/alternative.css");
			}
			else{
				scene.getStylesheets().remove("css/alternative.css");
				scene.getStylesheets().add("css/main.css");
			}

			newLookInUse = !newLookInUse;
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

					Label dealMessage = new Label("Click Bet to bet wager or click Fold to stop playing.");
					pane.setBottom(dealMessage);

					playerOne.hand = theDealer.dealHand();
					playerTwo.hand = theDealer.dealHand();
					theDealer.dealersHand = theDealer.dealHand();

				}
		});

		/*----------------------Fold's actions-------------------------------*/
		fold.setOnAction(e-> {

			playerOne.totalWinnings -= playerOne.anteBet;
			playerOne.totalWinnings = returnPpw(playerOne);

			playerTwo.totalWinnings -= playerTwo.anteBet;
			playerTwo.totalWinnings = returnPpw(playerTwo);

			winningOne.setText("Winnings: $" + playerOne.totalWinnings);
			winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);
			deal.setDisable(false);
			fold.setDisable(true);
			bet.setDisable(true);
			anteOne.setDisable(false);
			ppwOne.setDisable(false);
			pane.setBottom(message);
		});

		/*----------------------Bet's actions-------------------------------*/
		bet.setOnAction(e-> {

			playerOne.totalWinnings = playerVDealer(theDealer, playerOne);
			playerTwo.totalWinnings = playerVDealer(theDealer, playerTwo);

			winningOne.setText("Winnings: $" + playerOne.totalWinnings);
			winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);
			deal.setDisable(false);
			fold.setDisable(true);
			bet.setDisable(true);
			anteOne.setDisable(false);
			ppwOne.setDisable(false);
			pane.setBottom(message);

		});

	}

	//Returns what the player won or lost from the pair plus wager
	public int returnPpw (Player player) {
		if (ThreeCardLogic.evalHand(player.hand) == 0) {
			player.totalWinnings -= player.pairPlusBet;
		}
		else {
			player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
		}
		return player.totalWinnings;
	}

	//Returns what the player won or lost from the wager bet
	public int playerVDealer (Dealer dealer, Player player) {
		if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 1) {
			player.totalWinnings -= (player.anteBet * 2);
			player.totalWinnings = returnPpw(player);
		}
		else if (ThreeCardLogic.compareHands(dealer.dealersHand, player.hand) == 2) {
			player.totalWinnings += (player.anteBet * 4);
			player.totalWinnings = returnPpw(player);
		}
		else {
			player.totalWinnings = returnPpw(player);
		}
		return player.totalWinnings;
	}
}