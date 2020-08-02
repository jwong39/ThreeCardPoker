public class Card {
	
	private char suit;   // ‘C’, ‘D’, ’S’, ‘H’
	private int value;   // integer between 2-14
	
	// Constructor to create a card with a suit and value
	// Jack: 11, Queen: 12, King: 13, Ace: 14,
	Card(char suit, int value) {
		this.suit = suit;
		this.value = value;
	}
	
	// Returns suit of card
	char getSuit() {
		return this.suit;
	}
	
	// Returns value of card
	int getValue() {
		return this.value;
	}
	
}
