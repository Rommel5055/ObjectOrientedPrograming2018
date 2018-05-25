package exercise17;
import java.util.Arrays;
import java.util.Random;

import exercise16.Card;

/**
 * A deck of playing cards (of fixed size).
 */
public class Deck {

    private Card[] cards;
    private Random rand = new Random();

    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck() {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                this.cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }

    /**
     * Constructs a deck of n cards (null).
     */
    public Deck(int n) {
        this.cards = new Card[n];
    }

    /**
     * Gets the internal cards array.
     */
    public Card[] getCards() {
        return this.cards;
    }

    /**
     * Displays each of the cards in the deck.
     */
    public void print() {
        for (int i = 0; i < this.cards.length; i++) {
            System.out.println(this.cards[i]);
        }
    }

    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < this.cards.length; i++) {
           string = string + Card.RANKS[cards[i].getRank()] + " of " + Card.SUITS[cards[i].getSuit()] + "\n";
        }
        return string;
        
     }

    /**
     * Chooses a random number between low and high, including both.
     */
    public int randomInt(int low, int high) {
        int randomN = rand.nextInt(high + 1) + low;
    	return randomN;
    }

    /**
     * Swaps the cards at indexes i and j.
     */
    public void swapCards(int i, int j) {
    	Card card = this.cards[i];
    	this.cards[i] = this.cards[j];
    	this.cards[j] = card;
    }

    /**
     * Randomly permutes the array of cards.
     */
    public void shuffle() {
    	for (int i = 0; i < 51; i++){
    		int random1 = randomInt(0, 51);
    		int random2 = randomInt(0, 51);
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
     * Finds the index of the lowest card
     * between low and high inclusive.
     */
    public int indexLowest(int low, int high) {
        int index = 0;
        int value = 1000;
    	for (int i = low; i <= high; i++){
        	if(cards[i].getRank() < value){
        		index = i;
        		value = cards[i].getRank();
        	}
        }
    	return index;
    }

    /**
     * Sorts the cards (in place) using selection sort.
     */
    public void selectionSort() {
    	int index;
    	for (int i = 0; i < this.cards.length - 1; i++){
    		index = indexLowest(i, this.cards.length - 1);
    		if (index != i){
    			swapCards(index, i);
    		}
    	}
    }

    /**
     * Returns a subset of the cards in the deck.
     */
    public Deck subdeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }

    /**
     * Combines two previously sorted subdecks.
     */
    public static Deck merge(Deck d1, Deck d2) {
        Random rand = new Random();
    	Card[] c1 = d1.getCards();
        Card[] c2 = d2.getCards();
        int l1 = d1.getCards().length;
        int l2 = d2.getCards().length;
        Card[] mergedDeck = new Card[l1 + l2];
        Deck merged = new Deck(l1+l2);
        if (l1 == 0){
        	mergedDeck = c2;
        }
        else if (l2 == 0){
        	mergedDeck = c1;
        }
        else{
	        int counter1 = 0;
	        int counter2 = 0;
	        for (int i = 0; i < l1+l2; i++){
	        	if (counter1 < l1 && counter2 < l2){
		        	if ((c1[counter1].getRank() < c2[counter2].getRank() && counter1 < c1.length) || counter2 >= c2.length){
		        		mergedDeck[i] = c1[counter1];
		        		counter1 ++;
		        	}
		        	else if ((c2[counter2].getRank() < c1[counter1].getRank() && counter2 < c2.length) || counter1 >= c1.length){
		        		mergedDeck[i] = c2[counter2];
		        		counter2++;
		        	}
		        	else{
		        		int chance = rand.nextInt(2);
		        		if (chance == 0){
		        			mergedDeck[i] = c2[counter2];
			        		counter2++;
		        		}
		        		else{
		        			mergedDeck[i] = c1[counter1];
			        		counter1 ++;
		        		}
	        		}
	        	}
	        	else if(counter1 == l1){
	        		mergedDeck[i] = c2[counter2];
	        		counter2++;
	        	}
	        	else if(l2 == counter2){
	        		mergedDeck[i] = c1[counter1];
	        		counter1++;
	        	}
	        }
	    }
        merged.cards = mergedDeck;
    	return merged;
    }

    /**
     * Returns a sorted copy of the deck using merge sort.
     */
    public Deck mergeSortwSelectionSort() {
    	int mid = getCards().length / 2;
    	Card[] c0 = getCards();
     	Deck d1 = new Deck(mid);
    	Card[] c1 = new Card[mid];
    	Deck d2 = new Deck(getCards().length - mid);
    	Card[] c2 = new Card[getCards().length - mid];
    	for (int i = 0; i < mid; i ++){
    		c1[i] = c0[i];
    	}
    	for (int i = 0; i < getCards().length - mid; i++){
    		c2[i] = c0[i + getCards().length - mid];
    	}
    	d1.cards = c1;
    	d2.cards = c2;
    	d1.selectionSort();
    	d2.selectionSort();
    	Deck merged = new Deck(getCards().length);
    	merged = merge(d1, d2);
    	return merged;
    }

    public Deck mergeSort(){
    	if (this.cards.length == 1 || this.cards.length == 0){
    		return this;
    	}
    	else{
    		Deck d1 = subdeck(0, this.cards.length/2 - 1).mergeSort();
    		Deck d2 = subdeck(this.cards.length/2, this.cards.length -1).mergeSort();
    		return merge(d1, d2);
    	}
    }
    /**
     * Reorders the cards (in place) using insertion sort.
     */
    public void insertionSort() {
    	for (int i = 1; i < this.cards.length; i++){
    		for (int j = i; j > 0; j--){
    			if (this.cards[j-1].getRank() > this.cards[j].getRank()){
    				swapCards(j-1,j);
    			}
    			else{
    				break;
    			}
    		}
    	}
    }
    
    public static void main(String[] args) {
    	Deck deck = new Deck();
    	deck.shuffle();
    	deck.print();
    	System.out.println("-------------------------");
    	deck.selectionSort();
    	deck.print();
    	deck.shuffle();
    	deck.print();
    	System.out.println("-------------------------");
    	Deck merged = new Deck(deck.getCards().length);
    	merged = deck.mergeSortwSelectionSort();
    	merged.print();
    	System.out.println("-------------------------");
    	System.out.println("-------------------------");
    	deck.shuffle();
    	deck.print();
    	deck.insertionSort();
    	deck.print();
    	System.out.println("-------------------------");
    	System.out.println(deck.toString());
    }
}