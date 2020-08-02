import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;   // Current hand of a player
	private int anteBet;            // Player's ante bet
	private int playBet;            // Player's play bet
	private int pairPlusBet;        // Player's pair plus bet
	private int totalWinnings;      // Player's total winnings across multiple games
	
	// Constructor to create and initialize a player in the game
	Player() {
		this.setAnteBet(0);
		this.setPlayBet(0);
		this.setPairPlusBet(0);
		this.setTotalWinnings(1000);
		this.hand = new ArrayList<Card>();   // Use setHand function to 
	}
	
	// Sets player's hand as the 3 cards dealt by the dealer
	void setHand(ArrayList<Card> cards) {
		this.hand = cards;
	}
	
	// Returns player's hand
	ArrayList<Card> getHand() {
		return this.hand;
	}
	
	// Sets ante bet to a given bet amount
	void setAnteBet(int bet) {
		this.anteBet = bet;
	}
	
	// Returns player's ante bed
	int getAnteBet() {
		return this.anteBet;
	}
	
	// Sets play bet to a given bet amount
	void setPlayBet(int bet) {
		this.playBet = bet;
	}
	
	// Returns player's play bet
	int getPlayBet() {
		return this.playBet;
	}
	
	// Sets pair plus bet to a given bet amount
	void setPairPlusBet(int bet) {
		this.pairPlusBet = bet;
	}
	
	// Returns player's pair plus bet
	int getPairPlusBet() {
		return this.pairPlusBet;
	}
	
	// Sets total winnings to a given winning amount (can be negative)
	void setTotalWinnings(int winnings) {
		this.totalWinnings = winnings;
	}
	
	// Returns player's total winnings
	int getTotalWinnings() {
		return this.totalWinnings;
	}
	
}
