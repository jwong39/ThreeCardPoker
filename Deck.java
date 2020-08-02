import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card>{
	
	private ArrayList<Card> cardDeck;
	
	// Constructor to create 52 cards in randomized order
	Deck() {
		createDeck();
		Collections.shuffle(this.cardDeck);   // Shuffle deck
	}
	
	// Clears the deck and creates a new deck of 52 cards in randomized order
	void newDeck() {
		this.cardDeck.clear();  // Clear card deck
		createDeck();
		Collections.shuffle(this.cardDeck);   // Shuffle deck
	}
	
	// Creates the full set of a suit (13 cards)
	void createDeck() {
		this.cardDeck = new ArrayList<Card>();
		
		// Create Spades
		for (int i = 2; i <= 14; i++) {
			Card newCard = new Card('S', i);
			this.cardDeck.add(newCard);
		}
		
		// Create Diamonds
		for (int i = 2; i <= 14; i++) {
			Card newCard = new Card('D', i);
			this.cardDeck.add(newCard);
		}
		
		// Create Clubs
		for (int i = 2; i <= 14; i++) {
			Card newCard = new Card('C', i);
			this.cardDeck.add(newCard);
		}
		
		// Create Hearts
		for (int i = 2; i <= 14; i++) {
			Card newCard = new Card('H', i);
			this.cardDeck.add(newCard);
		}
	}
	
	// Sets deck
	void setDeck(ArrayList<Card> cards) {
		this.cardDeck = cards;
	}
	
	// Returns deck
	ArrayList<Card> getDeck() {
		return this.cardDeck;
	}
	
}
