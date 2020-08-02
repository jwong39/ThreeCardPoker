import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThreeCardPokerGame extends Application {
	Player playerOne;
	Player playerTwo;
	Dealer theDealer;
	ArrayList<ImageView> viewOne, viewTwo, viewDealer;
	ArrayList<Card> pOneHand, pTwoHand, DealHand;
	BorderPane pane;
	Button betBtn, playBtn, optionBtn, exitBtn, freshBtn, newLookBtn,
	play2Btn, foldBtn, fold2Btn, dealBtn, resetBtn;
	Text Title, total, total2, Title2;
	TextField AnteText, PPText, AnteText2, PPText2;
	HBox handOne, handTwo, DealerHand;
	MenuBar menuBar;
	boolean fold1Pressed, fold2Pressed = false;
	int theAnteTwo, pairPlusWin, theAnteOne, thePlayOne, thePlayTwo;

	
	public static void main(String[] args) {
				
		launch(args);
		
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();
		
		
		primaryStage.setTitle("Let's Play Three Card Poker!!!");
		pane = new BorderPane();
		pane.setStyle("-fx-background-color: green;");
		
		
		//change the scene per button
		resetBtn = new Button("Reset");
		resetBtn.setDisable(true);
		
		dealBtn = new Button("Deal");
		dealBtn.setDisable(true);
		
		betBtn = new Button("Bet");
		betBtn.setDisable(false);
		
		playBtn = new Button("Play");
		playBtn.setDisable(true);
		
		play2Btn = new Button("Play");
		play2Btn.setDisable(true);
		
		foldBtn = new Button("Fold");
		foldBtn.setDisable(true);
		
		fold2Btn = new Button("Fold");
		fold2Btn.setDisable(true);
		
		// Create menu bar
		menuBar = new MenuBar();
		Menu mOne = new Menu("Options");
		MenuItem iOne = new MenuItem("Exit");
		MenuItem iTwo = new MenuItem("Fresh Start");
		MenuItem iThree = new MenuItem("NewLook");
		
		// exit option
		iOne.setOnAction(e-> {Platform.exit();});
		// reset option
		iTwo.setOnAction(e-> { try {
			start(primaryStage);
		} catch (Exception e1) {
			e1.printStackTrace();
		} });
		// New look option
		iThree.setOnAction(e-> pane.setStyle("-fx-background-color: lightblue;"));
		// adds 
		mOne.getItems().addAll(iOne, iTwo, iThree);
		menuBar.getMenus().addAll(mOne);

		
		//faceDown
		viewDealer = FaceDown();
		viewTwo = FaceDown();
		viewOne = FaceDown();
		
		
		//Title
		Title = new Text("Welcome to Three Card Poker!");
	    Title.setFill(Color.BLACK);
	    Title.setStyle("-fx-font: 36 arial;");
	    
		//Title2 
		Title2 = new Text("");
	    Title2.setFill(Color.BLACK);
	    Title2.setStyle("-fx-font: 36 arial;");

	    
		//player 1
		total = new Text("$" + playerOne.getTotalWinnings());
	    total.setFill(Color.BLACK);
	    total.setStyle("-fx-font: 36 arial;");
		AnteText = new TextField("Ante");
		PPText = new TextField("Pair Plus");
		
		
		//player 2
		total2 = new Text("$" + playerTwo.getTotalWinnings());
	    total2.setFill(Color.BLACK);
	    total2.setStyle("-fx-font: 36 arial;");
		AnteText2 = new TextField("Ante");
		PPText2 = new TextField("Pair Plus");
		
		
		//hands placed into HBox's
		DealerHand = new HBox(10, viewDealer.get(0), viewDealer.get(1), viewDealer.get(2));
		handOne = new HBox(10, viewOne.get(0), viewOne.get(1), viewOne.get(2));
		handTwo = new HBox(10, viewTwo.get(0), viewTwo.get(1), viewTwo.get(2));
		
		
		//Top Pane
		HBox TITLE = new HBox(Title);
		HBox TITLE2 = new HBox(Title2);
		HBox leftBox = new HBox(5,AnteText, PPText);
		HBox rightBox = new HBox(5,AnteText2, PPText2);
		VBox TopBox = new VBox(10, menuBar, TITLE, TITLE2);
		
		
		//Bottom Pane
		HBox pfLeft = new HBox(playBtn, foldBtn);
		HBox pfRight = new HBox(play2Btn, fold2Btn);
		VBox divideRight = new VBox(10, total2, pfRight, rightBox);
		VBox divideLeft = new VBox(10, total, pfLeft, leftBox);
		VBox middleButtons = new VBox(10, dealBtn, betBtn, resetBtn);
		HBox MainBox = new HBox(200,divideLeft, middleButtons, divideRight);
		
		
		//Alignments for everything
		TITLE.setAlignment(Pos.TOP_CENTER);
		TITLE2.setAlignment(Pos.TOP_CENTER);
		handTwo.setAlignment(Pos.BOTTOM_RIGHT);
		handOne.setAlignment(Pos.BOTTOM_LEFT);
		DealerHand.setAlignment(Pos.CENTER);
		
		
		//pane stuff
		pane.setCenter(DealerHand);
		pane.setTop(TopBox);
		pane.setBottom(MainBox);
		pane.setLeft(handOne);
		pane.setRight(handTwo);
			
		
		//scene
		Scene scene = new Scene(pane, 1250, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
		
//		@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ BUTTONS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@		//
		//Start Button Action
		
		//reset all cards to face down
		resetBtn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				
				betBtn.setDisable(false);
				resetBtn.setDisable(true);
				dealBtn.setDisable(true);
				playBtn.setDisable(true);
				play2Btn.setDisable(true);
				foldBtn.setDisable(true);
				fold2Btn.setDisable(true);
//				//setHand
//				playerOne.setHand(theDealer.dealHand());
//				playerTwo.setHand(theDealer.dealHand());
//				theDealer.setDealersHand(theDealer.dealHand());
				
				handOne.getChildren().clear();
				handTwo.getChildren().clear();
				DealerHand.getChildren().clear();
				
				viewDealer = FaceDown();
				viewTwo = FaceDown();
				viewOne = FaceDown();
				
			    handOne.getChildren().addAll(viewOne.get(0), viewOne.get(1), viewOne.get(2));
			    handTwo.getChildren().addAll(viewTwo.get(0), viewTwo.get(1), viewTwo.get(2));
			    DealerHand.getChildren().addAll(viewDealer.get(0), viewDealer.get(1), viewDealer.get(2));
			}
		});
		
		betBtn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				
				//setHand
				playerOne.setHand(theDealer.dealHand());
				playerTwo.setHand(theDealer.dealHand());
				
				//getHand
				pOneHand = playerOne.getHand();
				pTwoHand = playerTwo.getHand();

				
				if (isInteger(AnteText.getText()) && isInteger(AnteText2.getText()) && isInteger(PPText.getText()) && isInteger(PPText2.getText())) {//if integer
					int anteOneInt = Integer.parseInt(AnteText.getText());
					int anteOne = (int)anteOneInt;
					int anteTwoInt = Integer.parseInt(AnteText2.getText());
					int anteTwo = (int)anteTwoInt;
					int PPTextInt = Integer.parseInt(PPText.getText());
					int PPTextOne = (int)PPTextInt;
					int PPText2Int = Integer.parseInt(PPText2.getText());
					int PPTextTwo = (int)PPText2Int;
					if (anteOne >= 5 && anteOne <= 25 && anteTwo >= 5 && anteTwo <= 25 && 
						PPTextOne >= 5 && PPTextOne <= 25 && PPTextTwo >= 5 && PPTextTwo <= 25 ||
						PPTextOne == 0 || PPTextTwo == 0) {//if in range
						pairPlusWin = PPTextTwo;
						upDateTotal(playerOne, anteOne, total, AnteText, PPTextOne, PPText);
						upDateTotal(playerTwo, anteTwo, total2, AnteText2, PPTextTwo, PPText2);
						
						//The values change

						
						//I need to add if button fold is not pressed
						//clears blank cards
						handOne.getChildren().clear();
						handTwo.getChildren().clear();
						
						// FaceUP cards
						viewOne = FaceUp(pOneHand);
						viewTwo = FaceUp(pTwoHand);
						
						//populate the values
					    handOne.getChildren().addAll(viewOne.get(0), viewOne.get(1), viewOne.get(2));
					    handTwo.getChildren().addAll(viewTwo.get(0), viewTwo.get(1), viewTwo.get(2));
					    
						//sets player buttons to off
					    total.setDisable(false);
						playBtn.setDisable(false);
						play2Btn.setDisable(false);
						foldBtn.setDisable(false);
						fold2Btn.setDisable(false);
						betBtn.setDisable(true);
					}
					else {//if not within range
						Title.setText("Please enter in the correct amount");
					}	
				}
				else {//if not integer
					Title.setText("Please enter in the correct amount");
				}
			}
		});

			
		//want to flip and compare dealer in here
		dealBtn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				
				//setHand
				theDealer.setDealersHand(theDealer.dealHand());
				
				//getHand
				DealHand = theDealer.getDealersHand();
				
				//clear 
				DealerHand.getChildren().clear();
				viewDealer = FaceUp(DealHand);
				DealerHand.getChildren().addAll(viewDealer.get(0), viewDealer.get(1), viewDealer.get(2));
				
				
				// Title: Player one results
				int playAnteOne = -1;
				if (fold1Pressed == true) {
					
					// Save ante bet for next turn & Disable textField
					total.setDisable(true);
					playerOne.setAnteBet(theAnteOne);
					
					// If dealer Queen High or Better play wager returned
					if (isQueenHighBetter(theDealer,playerOne.getHand())) {
						playerOne.setTotalWinnings(playerOne.getTotalWinnings() + thePlayOne);
						int QueenOne = playerOne.getTotalWinnings();
						total.setText("$" + QueenOne);	
					}
					Title.setText("Player One Folded");
				}
				else if (fold1Pressed == false) {
					// Play bet
					int compareOne = ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerOne.getHand());
					String resultOne = new String();
					if (compareOne == 2) {
						
						resultOne = "wins";
						
						if (isQueenHighBetter(theDealer,playerOne.getHand())) {
							playAnteOne = 2 * (thePlayOne + theAnteOne);
						}
						else {
							// If playerOne wins hand, playerOne gains the sum of ante and play bets (PLUS their loss)
							playAnteOne = (thePlayOne + theAnteOne);
						}
					} 
					else {
						resultOne = "loses";	
						
						// If playerOne loses hand, playerOne does not gain anything
						playAnteOne = 0;
					}
					// Pair plus bet
					int pairPlusOne = ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet());
					boolean ppOne = ThreeCardLogic.isPairPlus(playerOne.getHand());
					String ppResultOne = new String();
					if (ppOne == true) {//was true
						ppResultOne = "wins";
					}
					else {
						ppResultOne = "loses";
					}
					
					Title.setText("Player One " + resultOne + " against Dealer and " + ppResultOne + " Pair Plus Bet!");
					
					// Update the total title for playerOne
					playerOne.setTotalWinnings(playerOne.getTotalWinnings() + playAnteOne + pairPlusOne);
					int totalOne = playerOne.getTotalWinnings();
					total.setText("$" + totalOne);		 
				}
				
				
				
				// Title2: Player two results
				int playAnteTwo = -1;
				if (fold2Pressed == true) {
					// If dealer Queen High or Better play wager returned
					if (isQueenHighBetter(theDealer,playerTwo.getHand())) {
						
						// Save ante bet for next turn & Disable textField
						total2.setDisable(true);
						playerTwo.setAnteBet(theAnteTwo);
						
						// If dealer Queen High or Better play wager returned
						if (isQueenHighBetter(theDealer,playerTwo.getHand())) {
							playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + thePlayTwo);
							int QueenTwo = playerTwo.getTotalWinnings();
							total2.setText("$" + QueenTwo);	
						}
					}
						
					Title2.setText("Player Two Folded");
					
				}
				else if (fold2Pressed == false) {
					// Play bet
					int compareTwo = ThreeCardLogic.compareHands(theDealer.getDealersHand(), playerTwo.getHand());
					String resultTwo = new String();
					if (compareTwo == 2) {
						resultTwo = "wins";
						if (isQueenHighBetter(theDealer,playerTwo.getHand())) {
							playAnteTwo = 2 * (thePlayTwo + theAnteTwo);
						}
						else {
							// If playerOne wins hand, playerOne gains the sum of ante and play bets (PLUS their loss)
							playAnteTwo = (thePlayTwo + theAnteTwo);
						}	 
					}
					else {
						resultTwo = "loses";
						
						// If playerTwo loses hand, playerTwo does not gain anything
						playAnteTwo = 0;
					}
					// Pair plus bet
					int pairPlusTwo = ThreeCardLogic.evalPPWinnings(playerTwo.getHand(), playerTwo.getPairPlusBet());
					boolean ppTwo = ThreeCardLogic.isPairPlus(playerTwo.getHand());
					String ppResultTwo = new String();
					if (ppTwo == true) {//was true
						ppResultTwo = "wins";
					}
					else {
						ppResultTwo = "loses";
					}
					
					Title2.setText("Player Two " + resultTwo + " against Dealer and " + ppResultTwo + " Pair Plus Bet!");
					
					// Update the total title for playerTwo
					playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + playAnteTwo + pairPlusTwo);
					int totalTwo = playerTwo.getTotalWinnings();
					total2.setText("$" + totalTwo);
				}
				betBtn.setDisable(true);
				dealBtn.setDisable(true);
				resetBtn.setDisable(false);
				fold1Pressed = false;
				fold2Pressed = false;
			}
		});
		
		
		//play button and start to compare 
		//if dealer does not have Queen High or better the play wager is returned
		// to the players who did not fold and the ante bet is push to the next hand
		playBtn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				
				//clear the text here
				int anteOneInt = Integer.parseInt(AnteText.getText());
				int anteOne = (int)anteOneInt;
				playerOne.setPlayBet(anteOne);
				int x = playerOne.getPlayBet();
				
				//when they win what do they win
				theAnteOne = anteOne;
				thePlayOne = x;
				
				playerOne.setTotalWinnings(playerOne.getTotalWinnings()-x);
				int y = playerOne.getTotalWinnings();
				total.setText("$"+y);
				
				
				playBtn.setDisable(true);
				foldBtn.setDisable(true);
				betBtn.setDisable(true);
				if (play2Btn.isDisabled() && fold2Btn.isDisabled()) {
					dealBtn.setDisable(false);
				}
				AnteText.clear();
			}
		});
		
		
		play2Btn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				
				
				int anteTwoInt = Integer.parseInt(AnteText2.getText());
				int anteTwo = (int)anteTwoInt;
				playerTwo.setPlayBet(anteTwo);
				int x = playerTwo.getPlayBet();
				playerTwo.setTotalWinnings(playerTwo.getTotalWinnings()-x);
				int y = playerTwo.getTotalWinnings();
				total2.setText("$"+y);
				
				theAnteTwo = anteTwo;
				thePlayTwo = x;
				
				play2Btn.setDisable(true);
				fold2Btn.setDisable(true);
				betBtn.setDisable(true);
				if (playBtn.isDisabled() && foldBtn.isDisabled()) {
					dealBtn.setDisable(false);
				}
				AnteText2.clear();
			}
		});
		//fold for player 1 compare nothing instantly lose, decrement 
		//fold does not add or subtract anything because when the player compares and loses

		fold2Btn.setOnAction(new EventHandler<ActionEvent> () {
			//lose antewager and pair plus wager
			public void handle(ActionEvent action) {
				fold2Pressed = true;
				play2Btn.setDisable(true);
				fold2Btn.setDisable(true);
				betBtn.setDisable(true);
				if (playBtn.isDisabled() && foldBtn.isDisabled()) {
					dealBtn.setDisable(false);
				}
				
			}
		});
		//fold for player 2, compare nothing instantly lose. but pair plus bet still active 
		foldBtn.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent action) {
				fold1Pressed = true;
				playBtn.setDisable(true);
				foldBtn.setDisable(true);
				betBtn.setDisable(true);
				if (play2Btn.isDisabled() && fold2Btn.isDisabled()) {
					dealBtn.setDisable(false);
				}
			}
		});
		
	}//end of function start
	
	// Check if the dealer is queen or higher
	boolean isQueenHighBetter(Dealer dealer,ArrayList<Card> hand) {
		ArrayList<Card> dealerDeck = dealer.getDealersHand();
		int i;
		for (i = 0;i<3 ;i++) {
			// If there exist a Queen in hand
			if (dealerDeck.get(i).getValue() == 12 || ThreeCardLogic.evalHand(hand) > 0) {
				return true;
			}
		}
		return false;
	}
	
	
	// Check if string is integer
	static boolean isInteger(String s) {
		
		boolean isValidInteger = false;
		try
		{
			Integer.parseInt(s);
			isValidInteger = true;
		}
		catch (NumberFormatException ex)
		{
			
		}
		return isValidInteger;
		
	}
	

	//updates total winnings for player
	void upDateTotal (Player player, int theAnte, Text total, TextField AnteText, int PP, TextField PPText) {
		
		player.setPairPlusBet(PP);					//Updates the pair plus bet to whatever they input
		int currentPP = player.getPairPlusBet();	//creates an integer for the updated Pair Plus input
		
		player.setAnteBet(theAnte);					//Updates the Ante Bet for Player
		int currentAnte = player.getAnteBet();		//creates an integer for the updated Ante Bet
		
		
		//Updates total winnings
		player.setTotalWinnings(player.getTotalWinnings()-currentAnte - currentPP);
		int y = player.getTotalWinnings();
		total.setText("$"+y);	

	}
	
	
	// Sets player's hand to 3 cards dealt by dealer (dealtHand) and returns an array of ImageViews based on the cards dealt
	ArrayList<ImageView> FaceUp(ArrayList<Card> dealtHand) { 

		ArrayList<ImageView> viewArr = new ArrayList<ImageView>();
		
		// Populate viewArr with ImageViews
		for (int i = 0; i < 3; i++) {
			
			// Concatenate strings of card's suit and value to ".jpg"
			String val = String.valueOf(dealtHand.get(i).getValue());
			String suit = String.valueOf(dealtHand.get(i).getSuit());
			String cardString = new String(val + suit + ".jpg");
			
			Image cardIMG = new Image(cardString, 80, 120, true, true);
			ImageView cardView = new ImageView();
			cardView.setImage(cardIMG);
			viewArr.add(cardView);
		}
		return viewArr;
	}
	
	
	// replace cards images with faced down card images
	ArrayList<ImageView> FaceDown() {
		
		// Create an array list to store the ImageViews
		ArrayList<ImageView> viewArr = new ArrayList<ImageView>();
		
		// Populate viewArr with ImageViews
		for (int i = 0; i < 3; i++) {
			// Create faced down images
			Image cardIMG = new Image("Red_back.jpg", 80, 120, true, true);
			ImageView cardView = new ImageView();
			cardView.setImage(cardIMG);
			viewArr.add(cardView);	
		}
		
		return viewArr;
	}
}
