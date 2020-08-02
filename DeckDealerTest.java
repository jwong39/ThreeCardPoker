import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckDealerTest {

	Deck theDeck = new Deck();    
	Dealer theDealer = new Dealer();
	
	// Test cases (5) for Deck class
	@Nested
	class checkDeck {
		
		int checkSuit(char suit) {
			int num = 0;
			
			// Counts how many cards in the deck match the given suit
			for (int i = 0; i < 52; i++) {
				char s = theDeck.getDeck().get(i).getSuit();
				if (s == suit)
					num++;
			}
			
			return num;
		}
		
		// TEST 1: Check if the deck creates 52 cards
		@Test
		void check52() {
			assertEquals(52, theDeck.getDeck().size(), "Deck not created properly: 52 cards not made");
		}

		// TEST 2: Check if deck has 13 Spades
		@Test
		void checkSpades() {
			int num = checkSuit('S');
			assertEquals(13, num, "Deck not created properly: 13 spades not made");
		}
		
		// TEST 3: Check if deck has 13 Diamonds
		@Test
		void checkDiamonds() {
			int num = checkSuit('D');
			assertEquals(13, num, "Deck not created properly: 13 diamonds not made");
		}

		// TEST 4: Check if deck has 13 Clubs
		@Test
		void checkClubs() {
			int num = checkSuit('C');
			assertEquals(13, num, "Deck not created properly: 13 clubs not made");
		}
		
		// TEST 5: Check if deck has 13 Diamonds
		@Test
		void checkHearts() {
			int num = checkSuit('H');
			assertEquals(13, num, "Deck not created properly: 13 hearts not made");
		}
		
	}
	
	// Test cases (5) for Dealer class
	@Nested
	class checkDealer {
		
		// Test 6: Check if deck was made properly from within dealer class
		@Test
		void checkDeckinDealer() {
			assertEquals(52, theDealer.getDealersDeck().size(), "Dealer did not create deck properly: 52 cards were not created");
		}
		
		// Test 7: Check if dealer can deal 3 cards
		@Test
		void checkDeal3() {
			assertEquals(3, theDealer.dealHand().size(), "Dealer did not deal cards properly: 3 cards were not dealt");
		}
		
		// Test 8: Set dealer's hand to some ArrayList of cards
		@Test
		void checkSetAndGet() {
			ArrayList<Card> newHand = new ArrayList();
			newHand.add(new Card('S', 2));
			newHand.add(new Card('S', 3));
			newHand.add(new Card('S', 4));
			
			theDealer.setDealersHand(newHand);
			
			for (int i = 0; i < 3; i++) {
				assertEquals('S', newHand.get(i).getSuit(), "Dealer's hand not set properly: card set is not a Spade");
				assertEquals((i + 2), newHand.get(i).getValue(), "Dealer's hand not set properly: card set does not have the value of " + (i + 2));
			}
		}
		
		// Test 9: Remove 18 cards to see if a new deck is created if the deck reaches 34 cards
		@Test
		void checkStart1() {
			for (int i = 0; i < 18; i++) {
				theDealer.getDealersDeck().remove(0);
			}
			
			theDealer.startWith52();
			
			assertEquals(52, theDealer.getDealersDeck().size(), "Dealer did not check the deck at the start of the game properly: 52 cards were not created");
		}
		
		// Test 10: Remove 17 cards and see if a new deck does not get created since the deck would have > 34 cards
		@Test
		void checkStart2() {
			for (int i = 0; i < 17; i++) {
				theDealer.getDealersDeck().remove(0);
			}
						
			theDealer.startWith52();
			
			assertEquals(35, theDealer.getDealersDeck().size(), "Dealer did not check the deck at the start of the game properly: 52 cards were not supposed to be created");			
		}
		
	}

}
