import java.util.ArrayList;

public class Dealer {

	private Deck theDeck;                  // Deck of 52 cards 
	private ArrayList<Card> dealersHand;   // Holds dealer's hand
	
	// Constructor initializes the deck
	Dealer() {
		this.theDeck = new Deck();
	}
	
	// Removes and returns 3 random cards from the deck
	ArrayList<Card> dealHand() {
		
		this.startWith52();
		ArrayList<Card> dealThree = new ArrayList<Card>();
		// Choose and remove 3 random cards from the top of the deck array list (index: 0 - 51)
		for (int i = 0; i < 3; i++) {
			Card randomCard = this.getDealersDeck().get(0);
			dealThree.add(randomCard);
			this.getDealersDeck().remove(randomCard);
		}
		
		return dealThree;
	}
	
	// Check to see if there are more than 34 cards left in the deck. 
	// If not, theDeck must be re-shuffled with a new set of 52 cards in random order
	void startWith52() {
		if (this.getDealersDeck().size() > 34) {
			return;
		}
		else 
			this.theDeck.newDeck();
	}
	
	// Return theDeck from dealer's hand
	ArrayList<Card> getDealersDeck() {
		return this.theDeck.getDeck();
	} 

	// Set the dealer's hand
	void setDealersHand(ArrayList<Card> cards) {
		this.dealersHand = cards;
	}
	
	// Returns the dealer's hand
	ArrayList<Card> getDealersHand() {
		return this.dealersHand;
	}
	
}
