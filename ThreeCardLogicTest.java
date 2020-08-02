import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ThreeCardLogicTest {

	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> dealersHand = new ArrayList<Card>();
	
	@Test
	void checkSorted() {
		hand.add(new Card('S', 14));
		hand.add(new Card('H', 2));
		hand.add(new Card('D', 6));
		
		Collections.sort(hand, new SortCard());
		
		boolean sorted = false;
		Card card1 = hand.get(0);
		Card card2 = hand.get(1);
		Card card3 = hand.get(2);
		if (card1.getValue() < card2.getValue() && (card2.getValue() < card3.getValue())) {
			sorted = true;
		}
		
		assertTrue(sorted, "Hand not sorted properly");
	}
	
	@Nested
	class testEvalHand {
		
		@Test
		void checkHigh() {
			hand.add(new Card('S', 2));
			hand.add(new Card('D', 7));
			hand.add(new Card('C', 9));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(0, val, "Hand not evaluated properly: straight flush not evaluated correctly");
		}
		
		@Test
		void checkStraightFlush() {
			hand.add(new Card('S', 2));
			hand.add(new Card('S', 4));
			hand.add(new Card('S', 3));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(1, val, "Hand not evaluated properly: straight flush not evaluated correctly");
		}
		
		@Test
		void checkThreeOfAKind() {
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 2));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(2, val, "Hand not evaluated properly: three of a kind not evaluated correctly");
		}
		
		@Test
		void checkStraight() {
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 4));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(3, val, "Hand not evaluated properly: straight not evaluated correctly");
		}
		
		@Test
		void checkFlush() {
			hand.add(new Card('S', 2));
			hand.add(new Card('S', 6));
			hand.add(new Card('S', 3));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(4, val, "Hand not evaluated properly: flush not evaluated correctly");
		}
		
		@Test
		void checkPair() {
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.evalHand(hand);
			assertEquals(5, val, "Hand not evaluated properly: pair not evaluated correctly");
		}
		
	}
	
	@Nested
	class testCompareHand {
		
		// Dealer has straight flush and player has three of a kind --> DEALER WINS
		@Test
		void checkStraightFlushVSThreeOfAKind() {
			// Dealer's hand (straight flush)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('S', 3));
			dealersHand.add(new Card('S', 4));
			
			// Player's hand (three of a kind)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 2));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}	
		
		// Dealer has straight flush and player has straight --> DEALER WINS
		@Test
		void checkStraightFlushVSStraight() {
			// Dealer's hand (straight flush)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('S', 3));
			dealersHand.add(new Card('S', 4));
			
			// Player's hand (straight)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 4));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}		
		
		// Dealer has straight flush and player has flush --> DEALER WINS
		@Test
		void checkStraightFlushVSFlush() {
			// Dealer's hand (straight flush)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('S', 3));
			dealersHand.add(new Card('S', 4));
			
			// Player's hand (pair)
			hand.add(new Card('S', 2));
			hand.add(new Card('S', 6));
			hand.add(new Card('S', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has straight flush and player has pair --> DEALER WINS
		@Test
		void checkStraightFlushVSPair() {
			// Dealer's hand (straight flush)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('S', 3));
			dealersHand.add(new Card('S', 4));
			
			// Player's hand (pair)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has three of a kind and player has straight --> DEALER WINS
		@Test
		void checkThreeOfAKindVSStraight() {
			// Dealer's hand (three of a kind)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 2));
			dealersHand.add(new Card('D', 2));
			
			// Player's hand (straight)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 4));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has three of a kind and player has flush --> DEALER WINS
		@Test
		void checkThreeOfAKindVSFlush() {
			// Dealer's hand (three of a kind)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 2));
			dealersHand.add(new Card('D', 2));
			
			// Player's hand (flush)
			hand.add(new Card('S', 2));
			hand.add(new Card('S', 6));
			hand.add(new Card('S', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has three of a kind and player has pair --> DEALER WINS
		@Test
		void checkThreeOfAKindVSPair() {
			// Dealer's hand (three of a kind)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 2));
			dealersHand.add(new Card('D', 2));
			
			// Player's hand (pair)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has straight and player has flush --> DEALER WINS
		@Test
		void checkStraightVSFlush() {
			// Dealer's hand (straight)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 4));
			dealersHand.add(new Card('D', 3));
			
			// Player's hand (flush)
			hand.add(new Card('S', 2));
			hand.add(new Card('S', 6));
			hand.add(new Card('S', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has straight and player has pair --> DEALER WINS
		@Test
		void checkStraightVSPair() {
			// Dealer's hand (straight)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 4));
			dealersHand.add(new Card('D', 3));
			
			// Player's hand (pair)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		// Dealer has flush and player has pair --> DEALER WINS
		@Test
		void checkFlushVSPair() {
			// Dealer's hand (flush)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('S', 6));
			dealersHand.add(new Card('S', 3));
			
			// Player's hand (pair)
			hand.add(new Card('S', 2));
			hand.add(new Card('H', 2));
			hand.add(new Card('D', 3));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not compared properly: dealer is supposed to win");
		}
		
		
		// Dealer has pair and player has high card --> DEALER WINS
		@Test
		void checkHighCard1() {
			// Dealer's hand (pair)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('H', 2));
			dealersHand.add(new Card('D', 3));
			
			// Player's hand (high card)
			hand.add(new Card('S', 2));
			hand.add(new Card('D', 7));
			hand.add(new Card('C', 9));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not evaluated properly: dealer is supposed to win");
		}
		
		// Dealer and player both have high card
		// Dealer's 3rd card is higher than player's 3rd card --> DEALER WINS
		@Test
		void checkHighCard2() {
			// Dealer's hand (pair)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('D', 3));
			dealersHand.add(new Card('C', 10));
			
			// Player's hand (high card)
			hand.add(new Card('S', 2));
			hand.add(new Card('D', 7));
			hand.add(new Card('C', 9));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not evaluated properly: dealer is supposed to win");
		}
		
		// Dealer and player both have high card
		// Dealer's 3rd card == player's 3rd card, but Dealer's 2nd card is higher than player's 2nd card --> DEALER WINS
		@Test
		void checkHighCard3() {
			// Dealer's hand (pair)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('D', 7));
			dealersHand.add(new Card('H', 10));
			
			// Player's hand (high card)
			hand.add(new Card('S', 2));
			hand.add(new Card('D', 3));
			hand.add(new Card('C', 10));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(1, val, "Hand not evaluated properly: dealer is supposed to win");
		}
		
		// Dealer and player both have high card
		// Dealer's card ranks == player's card ranks --> NO ONE WINS (impossible because there are only 4 suits and 6 cards are needed)
		@Test
		void checkHighCard4() {
			// Dealer's hand (pair)
			dealersHand.add(new Card('S', 2));
			dealersHand.add(new Card('D', 3));
			dealersHand.add(new Card('C', 10));
			
			// Player's hand (high card)
			hand.add(new Card('S', 2));
			hand.add(new Card('D', 3));
			hand.add(new Card('C', 10));
			
			int val = ThreeCardLogic.compareHands(dealersHand, hand);
			assertEquals(0, val, "Hand not evaluated properly: dealer is supposed to win");
		}
		
		
		
	}

}
