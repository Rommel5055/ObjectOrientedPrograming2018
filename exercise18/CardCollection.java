package exercise18;

import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of playing cards.
 */
public class CardCollection {

    protected String label;
    protected ArrayList<Card> cards;
    protected Random rand = new Random();

    /**
     * Constructs an empty collection.
     */
    public CardCollection(String label) {
        this.label = label;
        this.cards = new ArrayList<Card>();
    }

    /**
     * Returns the label of the card collection.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Adds the given card to the collection.
     */
    public void addCard(Card card) {
    	cards.add(card);
    }

    /**
     * Removes and returns the card with the given index.
     */
    public Card popCard(int i) {
        return cards.remove(i);
    }

    /**
     * Removes and returns the last card.
     */
    public Card popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    /**
     * Returns the number of cards.
     */
    public int size() {
        return cards.size();
    }

    /**
     * True if the collection is empty, false otherwise.
     */
    public boolean empty() {
        return cards.size() == 0;
    }

    /**
     * Moves n cards from this collection to the given collection.
     */
    public void deal(CardCollection that, int n) {
        for (int i = 0; i < n; i++) {
            Card card = popCard();
            that.addCard(card);
        }
    }

    /**
     * Moves all remaining cards to the given collection.
     */
    public void dealAll(CardCollection that) {
        int n = size();
        deal(that, n);
    }

    /**
     * Returns the card with the given index.
     */
    public Card getCard(int i) {
        return cards.get(i);
    }

    /**
     * Returns the last card.
     */
    public Card last() {
        int i = size() - 1;
        return cards.get(i);
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    public void swapCards(int i, int j) {
        Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }

    /**
     * Randomly permute the cards.
     */
    
    public int randomInt(int low, int high) {
        int randomN = rand.nextInt(high + 1) + low;
    	return randomN;
    }
    
    public void shuffle() {
        for (int i = 0; i < cards.size(); i++){
    		int random1 = randomInt(0, cards.size()-1);
    		int random2 = randomInt(0, cards.size()-1);
    		if (random1 < random2){
    			swapCards(random1, random2);
    		}
    		else if(random1 > random2){
    			swapCards(random2, random1);
    		}
    		else{
    			if (i != 0){
    				i--;
    			}
    		}
    	}
    }

    /**
     * Returns a string representation of the card collection.
     */
    public String toString() {
        return label + ": " + cards.toString();
    }

}
