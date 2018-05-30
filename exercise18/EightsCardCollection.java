package exercise18;

import java.util.ArrayList;
import java.util.Random;

public class EightsCardCollection extends CardCollection{
	private ArrayList<EightsCard> eightCards;
	
	public EightsCardCollection(String label) {
		super(label);
		// TODO Auto-generated constructor stub
		eightCards = new ArrayList<EightsCard>();
	}
    /**
     * Adds the given card to the collection.
     */
    public void addCard(EightsCard card) {
    	eightCards.add(card);
    }

    /**
     * Removes and returns the card with the given index.
     */
    public EightsCard popCard(int i) {
        return eightCards.remove(i);
    }

    /**
     * Removes and returns the last card.
     */
    public EightsCard popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    /**
     * Returns the number of cards.
     */
    public int size() {
        return eightCards.size();
    }

    /**
     * True if the collection is empty, false otherwise.
     */
    public boolean empty() {
        return eightCards.size() == 0;
    }

    /**
     * Moves n cards from this collection to the given collection.
     */
    public void deal(EightsCardCollection that, int n) {
        for (int i = 0; i < n; i++) {
            EightsCard card = popCard();
            that.addCard(card);
        }
    }

    /**
     * Moves all remaining cards to the given collection.
     */
    public void dealAll(EightsCardCollection that) {
        int n = size();
        deal(that, n);
    }

    /**
     * Returns the card with the given index.
     */
    public EightsCard getECard(int i) {
        return eightCards.get(i);
    }

    /**
     * Returns the last card.
     */
    public EightsCard last() {
        int i = size() - 1;
        return eightCards.get(i);
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    public void swapCards(int i, int j) {
        EightsCard temp = eightCards.get(i);
        eightCards.set(i, eightCards.get(j));
        eightCards.set(j, temp);
    }

    /**
     * Randomly permute the cards.
     */
    
    public void shuffle() {
        for (int i = 0; i < eightCards.size(); i++){
    		int random1 = randomInt(0, eightCards.size()-1);
    		int random2 = randomInt(0, eightCards.size()-1);
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
        return label + ": " + eightCards.toString();
    }

}