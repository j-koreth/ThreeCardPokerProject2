import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.Random;

public class ThreeCardPokerGame extends Application {
	Player playerOne;
	Player playerTwo;

	Dealer theDealer;
	Character dealer, playerOneCharacter, playerTwoCharacter;

	VBox playerOneStuff, playerTwoStuff, betsOne, betsTwo, buttonStuff;
	HBox dealerContainer;
	HBox playerOneAll, playerTwoAll;
	Button deal, bet, fold;
	TextField anteOne, ppwOne, anteTwo, ppwTwo;
	MenuBar menuBar;
	Menu options;
	MenuItem freshStart, newLook, exit;
	Label ante1, ppw1, wager1, ante2, ppw2, wager2, winningOne, winningTwo, message;
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


		dealer = new Character("player_avatars/han-enter-the-dragon (2).jpg","JPEG/Yellow_back.jpg", "Dealer");

		dealerContainer = new HBox(30, dealer.name, dealer.characterAvatar, dealer.cardContainer);

		dealerContainer.setAlignment(Pos.CENTER_LEFT);


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

		playerOneCharacter = new Character("player_avatars/bruce-lee.jpg","JPEG/Yellow_back.jpg", "Dealer");

		playerOneStuff = new VBox(10, playerOneCharacter.name, playerOneCharacter.characterAvatar, winningOne);

		playerOneStuff.setAlignment(Pos.CENTER);

		ante1 = new Label("Ante");
		anteOne = new TextField("0");
		ppw1 = new Label("PPW");
		ppwOne = new TextField("0");
		wager1 = new Label("Wager: $" + playerOne.playBet);

		betsOne = new VBox(10, ante1, anteOne, ppw1, ppwOne, wager1,playerOneCharacter.cardContainer);
		playerOneAll = new HBox(10, betsOne, playerOneStuff);

		/*----------------------Design aspects for Player2-------------------------------*/
		playerTwo = new Player();
		winningTwo = new Label("Winnings: $" + playerTwo.totalWinnings);
		winningTwo.getStyleClass().add("headerLabels");
		playerTwoCharacter = new Character("player_avatars/jim-kelly-enter-the-dragon (2).jpg","JPEG/Yellow_back.jpg", "Dealer");


		playerTwoStuff = new VBox(10, playerTwoCharacter.name, playerTwoCharacter.characterAvatar, winningTwo);
		playerTwoStuff.setAlignment(Pos.CENTER);

		ante2 = new Label("Ante");
		anteTwo = new TextField("0");
		anteTwo.setDisable(true);
		ppw2 = new Label("PPW");
		ppwTwo = new TextField("0");
		ppwTwo.setDisable(true);
		wager2 = new Label("Wager: $" + playerTwo.playBet);

		betsTwo = new VBox(10, ante2, anteTwo, ppw2, ppwTwo, wager2, playerTwoCharacter.cardContainer);
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

		HBox paneTop = new HBox(200, menuBar, dealerContainer);

		/*----------------------Set pane for screen layout-------------------------------*/
		pane.setTop(paneTop);
		pane.setCenter(buttonStuff);
		pane.setRight(playerOneAll);
		pane.setLeft(playerTwoAll);
		pane.setBottom(message);
		Scene scene = new Scene(pane, 1150,700);
		scene.getStylesheets().add("css/main.css");
		scene.getStylesheets().add("css/default.css");

		primaryStage.setScene(scene);
		primaryStage.show();

		try{
			Media dbz = new Media(getClass().getResource("music/sleeping.mp3").toURI().toString());
			MediaPlayer player = new MediaPlayer(dbz);
			player.play();
		}//end of try
		catch(IllegalArgumentException e){
			System.out.println(getClass().getResource("music/sleeping.mp3"));
		}//end of catch

		/*----------------------Menu item's actions-------------------------------*/
		/*Resets all the items on the screen to their original values*/
		freshStart.setOnAction(e -> {
			playerOne.totalWinnings = 0;
			playerTwo.totalWinnings = 0;
			playerOne.anteBet = 0;
			playerTwo.anteBet = 0;
			playerOne.pairPlusBet = 0;
			playerTwo.pairPlusBet = 0;
			playerOne.playBet = 0;
			playerTwo.playBet = 0;
			winningOne.setText("Winnings: $" + playerOne.totalWinnings);
			winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);
			anteOne.setText("0");
			anteTwo.setText("0");
			ppwOne.setText("0");
			ppwTwo.setText("0");
			wager1.setText("Wager: $" + playerOne.playBet);
			wager2.setText("Wager: $" + playerTwo.playBet);
			deal.setDisable(false);
			fold.setDisable(true);
			bet.setDisable(true);
			anteOne.setDisable(false);
			ppwOne.setDisable(false);
			Label betMessage = new Label();
			pane.setBottom(message);
			playerOne.hand.clear();
			playerTwo.hand.clear();
			theDealer.dealersHand.clear();
		}); //end of freshStart

		/*Switches the layout of the game*/
		newLook.setOnAction(e->{
			/*Change to DBS Broly*/
			if (!newLookInUse) {
				scene.getStylesheets().remove("css/main.css");
				scene.getStylesheets().add("css/alternative.css");

				dealer.changeTheme("player_avatars/broly.jpeg", "JPEG/Green_back.jpg");

				playerOneCharacter.changeTheme("player_avatars/goku.jpeg", "JPEG/Green_back.jpg");
				playerTwoCharacter.changeTheme("player_avatars/vegeta.jpeg", "JPEG/Green_back.jpg");
				/*Changes music*/
				try{
					Media dbz = new Media(getClass().getResource("music/dbz1.mp3").toURI().toString());
					MediaPlayer player = new MediaPlayer(dbz);
					player.play();
				}//end of try
				catch(URISyntaxException exception){
					System.out.println(getClass().getResource("music/dbz1.mp3"));
				}//end of catch
			}//end of if
			/*Change to Enter the Dragon*/
			else{
				scene.getStylesheets().remove("css/alternative.css");
				scene.getStylesheets().add("css/main.css");
				dealer.changeTheme("player_avatars/han-enter-the-dragon (2).jpg", "JPEG/Yellow_back.jpg");
				playerOneCharacter.changeTheme("player_avatars/bruce-lee.jpg", "JPEG/Yellow_back.jpg");
				playerTwoCharacter.changeTheme("player_avatars/jim-kelly-enter-the-dragon (2).jpg", "JPEG/Yellow_back.jpg");
				/*Changes music*/
				try{
					Media dbz = new Media(getClass().getResource("music/sleeping.mp3").toURI().toString());
					MediaPlayer player = new MediaPlayer(dbz);
					player.play();
				}//end of try
				catch(URISyntaxException exception){
					System.out.println(getClass().getResource("music/sleeping.mp3"));
				}//end of catch
			}
			newLookInUse = !newLookInUse;
		}); //end of newLook

		/*Exits the game*/
		exit.setOnAction(e -> {primaryStage.close();});

		/*Used so playerTwo can play independently of playerOne*/
		Random r = new Random();

		/*----------------------Deal's actions-------------------------------*/
		deal.setOnAction(e-> {
			//Used to check if a new hand has been dealt or not
			//Also sets the bets for the players
			int playerAnte1 = playerOne.anteBet;
			int playerPpw1 = playerOne.pairPlusBet;
			int playerAnte2 = playerTwo.anteBet;
			int playerPpw2 = playerTwo.pairPlusBet;

			/*New hand was dealt, bets need to be updated*/
			if (playerOne.anteBet == 0 && playerTwo.anteBet == 0) {
				playerAnte1 = Integer.parseInt(anteOne.getText());
				playerPpw1 = Integer.parseInt(ppwOne.getText());
				playerAnte2 = r.nextInt((25 - 5) + 1) + 5;
				anteTwo.setText("" + playerAnte2);
				playerPpw2 = r.nextInt((25 - 5) + 1) + 5;
				ppwTwo.setText("" + playerPpw2);
				pause.play();
			}//end of if

			/*Player did not make a valid bet*/
			if ((playerAnte1 < 5 || playerAnte1 > 25) || ((playerPpw1 < 5 || playerPpw1 >25) && (playerPpw1 != 0 ))) {
				Label anteMessage = new Label("Please place a bet and/or optional PPW between $5-25.");
				anteOne.getStyleClass().add("alert");
				pane.setBottom(anteMessage);
			}//end of if
			/*Player made valid bet*/
			else {
				anteOne.getStyleClass().remove("alert");

				/*Save PPW bet*/
				if (playerPpw1 >= 5 && playerPpw1 <= 25) {
						playerOne.pairPlusBet = playerPpw1;
				}//end of if

				//Save player bets
				//Disable buttons and textFields
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

				/*Print new message*/
				Label dealMessage = new Label("Click Bet to bet wager or click Fold to stop playing.");
				pane.setBottom(dealMessage);


				/*Deal hands to players and reveal playerOne's cards*/
				playerOne.hand = theDealer.dealHand();
				playerOneCharacter.setCards(playerOne.hand);
				playerOneCharacter.hidden = false;

				playerTwo.hand = theDealer.dealHand();


				playerTwoCharacter.hideCards();

				dealer.hideCards();
			}//end of else
		}); //end of Deal

		/*----------------------Fold's actions-------------------------------*/
		fold.setOnAction(e-> {
			/*Deal Dealer's cards and check for queen high*/
			theDealer.dealersHand = theDealer.dealHand();
			boolean dealerHand = PlayGame.queenHigh(theDealer);

			/*Update playerOne's totalWinnings*/
			playerOne.totalWinnings -= playerOne.anteBet;
			playerOne.totalWinnings = PlayGame.returnPpw(playerOne);

			/*Reveal playerTwo's and Dealer's hand*/
			pause.play();
			playerTwoCharacter.setCards(playerTwo.hand);
			playerTwoCharacter.hidden = false;
			pause.play();
			dealer.setCards(theDealer.dealersHand);
			dealer.hidden = false;

			/*Used to print message at end of round*/
			int compareOne = 3;
			int compareTwo = playerTwoChoice(playerTwo, r.nextBoolean(), dealerHand);

			/*Update totalWinnings*/
			winningOne.setText("Winnings: $" + playerOne.totalWinnings);
			winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);

			/*Reset buttons and textFields*/
			deal.setDisable(false);
			fold.setDisable(true);
			bet.setDisable(true);
			anteOne.setDisable(false);
			ppwOne.setDisable(false);
			pause.play();

			/*Print out new message*/
			Label foldMessage = new Label();
			String setMessage = PlayGame.messageString(compareOne, compareTwo);
			foldMessage.setText(setMessage);
			pane.setBottom(foldMessage);

			/*Reset playerOne's bets*/
			playerOne.anteBet = 0;
			anteOne.setText("0");
			playerOne.playBet = 0;
			wager1.setText("Wager: $" + playerOne.playBet);

			/*Reset hands*/
			playerOne.hand.clear();
			playerTwo.hand.clear();
			theDealer.dealersHand.clear();
		}); //end of Fold

		/*----------------------Bet's actions-------------------------------*/
		bet.setOnAction(e-> {
			/*Deal Dealer's cards and check for queen high*/
			theDealer.dealersHand = theDealer.dealHand();
			boolean dealerHand = PlayGame.queenHigh(theDealer);

			/*Reveal playerTwo's and Dealer's hand*/
			pause.play();
			playerTwoCharacter.setCards(playerTwo.hand);
			playerTwoCharacter.hidden = false;
			pause.play();
			dealer.setCards(theDealer.dealersHand);
			dealer.hidden = false;

			/*Dealer does not have queen high*/
			if (!dealerHand) {
				/*Use to print message at end of round*/
				int compareOne = 4;
				int compareTwo = playerTwoChoice(playerTwo, r.nextBoolean(), dealerHand);

				/*Open up PPW and print new message*/
				ppwOne.setDisable(false);
				Label betMessage = new Label();
				String setMessage = PlayGame.messageString(compareOne, compareTwo);
				betMessage.setText(setMessage);
				pane.setBottom(betMessage);
			}//end of if
			/*Dealer has queen high*/
			else {
				/*Compare playerOne's hand*/
				playerOne.totalWinnings = PlayGame.playerVDealer(theDealer, playerOne);
				/*Use to print message at end of round*/
				int compareOne = ThreeCardLogic.compareHands(theDealer.dealersHand, playerOne.hand);
				int compareTwo = playerTwoChoice(playerTwo, r.nextBoolean(), dealerHand);

				/*Update totalWinnings*/
				winningOne.setText("Winnings: $" + playerOne.totalWinnings);
				winningTwo.setText("Winnings: $" + playerTwo.totalWinnings);

				/*Reset buttons and textFields*/
				deal.setDisable(false);
				fold.setDisable(true);
				bet.setDisable(true);
				anteOne.setDisable(false);
				ppwOne.setDisable(false);
				pause.play();

				/*Print out new message*/
				Label betMessage = new Label();
				String setMessage = PlayGame.messageString(compareOne, compareTwo);
				betMessage.setText(setMessage);
				pane.setBottom(betMessage);

				/*Reset playerOne's bets*/
				playerOne.anteBet = 0;
				anteOne.setText("0");
				playerOne.playBet = 0;
				wager1.setText("Wager: $" + playerOne.playBet);

				/*Reset hands*/
				playerOne.hand.clear();
				playerTwo.hand.clear();
				theDealer.dealersHand.clear();
			}//end of else
		});//end of Bet

	}

	/*Sets up hot the cards will be displayed on screen*/
	void setUpCards(VBox vbox, ImageView card){
		card.setFitHeight(100);
		card.setFitWidth(65);
		vbox.getChildren().add(card);
		vbox.getStyleClass().add("card");
	}//end of setUpCards

	/*Allows playerTwo to play independently fromm playerOne */
	public int playerTwoChoice (Player player, boolean choice, boolean qH) {
		/*playerTwo chose Bet*/
		if (choice) {
			/*The Dealer does not have queen high*/
			if (!qH) {
				return 4;
			}//end of if

			/*Reset playerTwo's bets*/
			playerTwo.totalWinnings = PlayGame.playerVDealer(theDealer, playerTwo);
			playerTwo.anteBet = 0;
			anteTwo.setText("0");
			playerTwo.playBet = 0;
			wager2.setText("Wager: $" + playerTwo.playBet);
			/*return to compareTwo to print message*/
			return ThreeCardLogic.compareHands(theDealer.dealersHand, playerTwo.hand);
		}//end of if
		/*playerTwo chose Fold*/
		else {
			/*Reset playerOne's bets*/
			playerTwo.totalWinnings -= playerTwo.anteBet;
			playerTwo.totalWinnings = PlayGame.returnPpw(playerTwo);
			playerTwo.anteBet = 0;
			anteTwo.setText("0");
			playerTwo.playBet = 0;
			wager2.setText("Wager: $" + playerTwo.playBet);
			/*return to compareTwo to print message*/
			return 3;
		}//end of else
	}//end of playerTwoChoice

}