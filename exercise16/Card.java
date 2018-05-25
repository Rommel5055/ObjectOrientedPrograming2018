package exercise16;

/**
 * A standard playing card.
 */
public class Card {

	//12.1
	public Card[] makeDeck(){
		Card[] cards = new Card[52];
		int index = 0;
		for (int suit = 0; suit <= 3; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				cards[index] = new Card(rank, suit);
				index++;
			}
		}
		return cards;
	}
	
    public static final String[] RANKS = {
        null, "Ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "Jack", "Queen", "King"};

    public static final String[] SUITS = {
        "Clubs", "Diamonds", "Hearts", "Spades"};

    private final int rank;

    private final int suit;

    /**
     * Constructs a card of the given rank and suit.
     */
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns a negative integer if this card comes before
     * the given card, zero if the two cards are equal, or
     * a positive integer if this card comes after the card.
     */
    
    //12.2
    public int compareTo(Card that) {
        if (this.suit < that.suit) {
            return -1;
        }
        if (this.suit > that.suit) {
            return 1;
        }
        if (this.rank == 1 && that.rank != 1){
        	return 1;
        }
        else if (this.rank != 1 && that.rank == 1){
        	return -1;
        }
        else if (this.rank < that.rank) {
            return -1;
        }
        else if (this.rank > that.rank) {
            return 1;
        }
        return 0;
    }
    
    //12.3.1
    public int[] suitHist(Card[] cards){
    	int[] counter = new int[4];
    	for (int i = 0; i < cards.length; i++){
    		counter[cards[i].getSuit()] += 1;
    		}
    	return counter;
    }
    
    //12.3.2
    public boolean hasFlush(Card[] hand){
    	int[] counter = new int[4];
    	counter = suitHist(hand);
    	for (int i = 0; i < 4; i++){
    		if (counter[i] >= 5){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Returns true if the given card has the same
     * rank AND same suit; otherwise returns false.
     */
    public boolean equals(Card that) {
        return this.rank == that.rank
            && this.suit == that.suit;
    }

    /**
     * Gets the card's rank.
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Gets the card's suit.
     */
    public int getSuit() {
        return this.suit;
    }

    /**
     * Returns the card's index in a sorted deck of 52 cards.
     */
    public int position() {
        return this.suit * 13 + this.rank - 1;
    }

    /**
     * Returns a string representation of the card.
     */
    public String toString() {
        return RANKS[this.rank] + " of " + SUITS[this.suit];
    }

}
