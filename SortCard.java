import java.util.Comparator;

public class SortCard implements Comparator<Card>{

	@Override
	public int compare(Card c1, Card c2) {
		return c1.getValue() - c2.getValue();
	}
	
}
