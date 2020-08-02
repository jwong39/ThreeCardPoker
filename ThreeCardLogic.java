import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeCardLogic {
	
	// Returns an integer value representing the value of the hand passed in
	static int evalHand(ArrayList<Card> hand) {
		
		Collections.sort(hand, new SortCard());   // Now hand is in ascending order
		
		// Declare cards to compare
		Card card1 = hand.get(0);
		Card card2 = hand.get(1);
		Card card3 = hand.get(2);
		
		// Declare booleans
		boolean highCard = isHighCard(card1, card2, card3);
		boolean straightFlush = isStraightFlush(card1, card2, card3);
		boolean straight = isStraight(card1, card2, card3);
		boolean flush = isFlush(card1, card2, card3);
		boolean threeOfAKind = isThreeOfAKind(card1, card2, card3);
		boolean pair = isPair(card1, card2, card3);
		
		// Find the value of the hand. The lower the hand, the better (with the exception of 0)
		int val = -1;
		if (highCard) {
			val = 0;
		}
		else if (straightFlush) {
			val = 1;
		}
		else if (threeOfAKind) {
			val = 2;
		}
		else if (straight) {
			val = 3;
		}
		else if (flush) {
			val = 4;
		}
		else if (pair) {
			val = 5;
		}
				
		return val;
	}
	
	//  Returns the amount won for the PairPlus bet
	static int evalPPWinnings(ArrayList<Card> hand, int bet) {
		
		// Evaluate pair plus payout as follows:
		if (evalHand(hand) == 1) {        // Straight flush
			bet = bet + (40 * bet);
		}
		else if (evalHand(hand) == 2) {   // Three of a Kind
			bet = bet + (30 * bet);
		}
		else if (evalHand(hand) == 3) {   // Straight
			bet = bet + (6 * bet);
		}
		else if (evalHand(hand) == 4) {   // Flush
			bet = bet + (3 * bet);
		}
		else if (evalHand(hand) == 5) {   // Pair
			bet = bet + (1 * bet);	
		}
		else {
			bet = bet + (0 * bet);
		}
		
		return bet;
	}
	
	// Compare the two hands passed in and return an integer based on which hand won
	static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
		int dealerVal = evalHand(dealer);
		int playerVal = evalHand(player);
		int compareVal = -1;
		
		// Dealer and player both have high card: compare by highest rank
		if ((dealerVal == 0) && (playerVal == 0)) {
			compareVal = compareHigh(dealer, player);
		}
		// Dealer has high card, but player has a different type of hand --> Player wins
		else if (dealerVal == 0) {
			compareVal = 2;
		}
		// Player has high card, but Dealer has a different type of hand --> Dealer wins
		else if (playerVal == 0) {
			compareVal = 1;
		}
		// Neither dealer or player have high card
		else {
			// Dealer has a better hand
			if (dealerVal < playerVal) {
				compareVal = 1;
			}
			// Player has a better hand
			else if (dealerVal > dealerVal) {
				compareVal = 2;
			}
			// Dealer and player have equal hands
			else if (dealerVal == playerVal) {
				compareVal = compareEqualHands(dealer, player, dealerVal, playerVal);
			}
		}
		
		return compareVal;
	}
		
	// Straight (val = 3): 3 consecutive cards
	static boolean isStraight(Card c1, Card c2, Card c3) {
		boolean straight = false;
		
		if ((c2.getValue() == (c1.getValue() + 1)) && (c3.getValue() == (c2.getValue() + 1))) {
			straight = true;
		}
		
		return straight;
	}
	
	// Flush (val = 4): 3 cards of the same suit
	static boolean isFlush(Card c1, Card c2, Card c3) {
		boolean flush = false;
		
		if ((c1.getSuit() == c2.getSuit()) && (c1.getSuit() == c3.getSuit())) {
			flush = true;
		}
		
		return flush;
	}
	
	// Three of a kind (val = 2): 3 cards of the same value
	static boolean isThreeOfAKind(Card c1, Card c2, Card c3) {
		boolean threeOfAKind = false;
		boolean flush = isFlush(c1, c2, c3);
		
		if ((c1.getValue() == c2.getValue()) && (c1.getValue() == c3.getValue())) {
			threeOfAKind = true;
		} 
		
		return threeOfAKind;
	}

	// Straight flush (val = 1): 3 consecutive cards of the same suit (straight AND flush)
	static boolean isStraightFlush(Card c1, Card c2, Card c3) {
		boolean straightFlush = false;
		boolean straight = isStraight(c1, c2,c3);
		boolean flush = isFlush(c1, c2, c3);
		
		if (straight && flush) {
			straightFlush = true;
		}
		
		return straightFlush;
	}		
	
	// Pair (val = 5): 2 cards cards of the same value, but not including flush
	static boolean isPair(Card c1, Card c2, Card c3) {
		boolean pair = false;
		boolean flush = isFlush(c1, c2, c3);
		
		if (((c1.getValue() == c2.getValue()) || (c1.getValue() == c3.getValue()) || (c2.getValue() == c3.getValue())) && !flush) {
			pair = true;
		}
		
		return pair;
	}
	
	// High card (val = 0)
	static boolean isHighCard(Card c1, Card c2, Card c3) {
		boolean highCard = false;
		boolean straight = isStraight(c1, c2,c3);
		boolean flush = isFlush(c1, c2, c3);
		boolean threeOfAKind = isThreeOfAKind(c1, c2,c3);
		boolean pair = isPair(c1, c2, c3);
		
		if (!straight && !flush && !threeOfAKind && !pair) {
			highCard = true;
		}
		
		return highCard;
	}
	
	// Compares cards in descending order between dealer and player
	static int compareHigh(ArrayList<Card> dealer, ArrayList<Card> player) {
		int val = 0;
		for (int i = 2; i >= 0; i--) {
			int dealerRank = dealer.get(i).getValue();
			int playerRank = player.get(i).getValue();
			
			// Dealer wins with higher rank
			if (dealerRank > playerRank) {
				val = 1;
				return val;
			}
			// Player wins with higher rank
			else if (dealerRank < playerRank) {
				val = 2;
				return val;
			}
		} // end of array has been reached (all ranks have matched)
		
		
		return val;   // No one wins
	}
	
	// If the dealer and player have the same hand, compare for the better hand
	static int compareEqualHands(ArrayList<Card> dealer, ArrayList<Card> player, int dealerVal, int playerVal) {
		assert dealerVal == playerVal;
		int val = 0;
		
		int dealerHigh = dealer.get(2).getValue();
		
		int playerHigh = player.get(2).getValue();
		
		// Straight Flush (1), Three of a Kind (2), Straight (3), Flush (4): compare highest card
		if ((playerVal == 1) && (playerVal == 2) && (playerVal == 3) && (playerVal == 4)){
			if (dealerHigh > playerHigh) {
				val = 1;
			}
			else if (dealerHigh < playerHigh) {
				val = 2;
			}
			else if (dealerHigh == playerHigh) {
				val = 0;
			}
		}
		// Pair: compare pairs
		else if (playerVal == 5) {
			val = compareHigh(dealer, player);
		}

		return val;
	}
	
	// Checks if player/dealer won pair plus
	static boolean isPairPlus(ArrayList<Card> hand) {
		boolean pp = false;
		
		int num = 0;
		for (int i = 0; i < 3; i++) {
			if (hand.get(i).getValue() == 2) {
				num++;
			}
		}
		
		if (num >= 2) {
			pp = true;
		}
		
		return pp;
	}
	
}
