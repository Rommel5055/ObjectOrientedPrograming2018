package exercise18;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Simulates a game of Crazy Eights.  See
 * https://en.wikipedia.org/wiki/Crazy_Eights
 * for basic play and scoring rules.
 */
public class Eights {
	private static ArrayList<Player> Players;
    private Hand drawPile;
    private Hand discardPile;
    private static Scanner reader;
    
    private static int nPlayers;

    /**
     * Initializes the state of the game.
     */
    public Eights() {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // deal cards to each player
        int handSize = 5;
        for (int i = 0; i < nPlayers; i++){
        	deck.deal(Players.get(i).getHand(), handSize);
        }

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
       // in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
    	for (int k = 0; k < nPlayers; k++){
    		if (Players.get(k).getHand().empty()){
    			return true;
    		}
    	}
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card draw() {
        if (drawPile.empty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public int nextPlayer(int current) {
    	System.out.println("wea: " + current);
        if (current + 1 < nPlayers) {
        	current++;
        	return current;
        } else {
            return 0;
        }
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for (int i = 0; i < nPlayers; i++){
        	Players.get(i).display();
        	System.out.println("Hand Size: " + Players.get(i).getHand().size());
        }
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
    }

    /**
     * Waits for the user to press enter.
     */
    public void waitForUser() {
        reader.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.last();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        int player = 0;
       
        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            //waitForUser();
            takeTurn(Players.get(player));
            player = nextPlayer(player);
            System.out.println("Player: " + player);
        }

        // display the final score
        System.out.println("-------------------------------\nFinal Scores");
        for (int i = 0; i < nPlayers; i++){
        	System.out.println(Players.get(i).getName() + ": " + Players.get(i).score());
        }
    }

    public static int[] setScores(){
    	int scores[] = new int[nPlayers];
    	for (int j = 0; j < nPlayers; j++){
        	scores[j] = Players.get(j).score();
        }
    	return scores;
    }
    
    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
    	Players = new ArrayList<Player>();
    	System.out.println("Insert the number of players:");
    	reader = new Scanner(System.in);
    	nPlayers = reader.nextInt(); 
    	for (int i = 0; i < nPlayers; i++){
    		System.out.println("Select a kind of player:\n 1 -> Normal Player\n 2 -> Genius\n 3 -> Eight Genius");
    		int type = reader.nextInt();
    		System.out.println("Insert the player's name");
    		String name= reader.next();
    		if(type == 1){
    			Players.add(new Player(name));
    		}
    		else if (type == 2){
    			Players.add(new Genius(name));
    		}
    		else{
    			Players.add(new EightGenius(name));
    		}
    		
    	}
    	int[] victories = new int[nPlayers];

        for (int k = 0; k < 100000; k++){
    	
        	Eights game = new Eights();
        	game.playGame();
        	int scores[] = setScores();
        	System.out.println("K: " + k);
        	for (int i = 0; i < nPlayers; i++){
        		if (scores[i] == 0){
        			victories[i] = victories[i] + 1;
        			break;
        		}
        	}
        }
        
        int maxWins = 0;
        int winsIndex = 0;
        System.out.println("\n--------------------------------\n--------------------------------\nWins:");
        for (int k = 0; k < nPlayers; k++){
        	System.out.println(Players.get(k).getName() + ": " + victories[k] + " wins");
        	if (victories[k] > maxWins){
        		maxWins = victories[k];
        		winsIndex = k;
        	}
        }
        System.out.println(Players.get(winsIndex).getName() + " is the winner!!!!\nCongratulations!!!!!!!!!!");
        reader.close();
        
        /*
        Result after 100000 itterations:
        Wins:
		Normal: 34789 wins
		Genius: 31438 wins
		Eight: 33773 wins
		Normal is the winner!!!!
		Congratulations!!!!!!!!!!
        
        */
    }
}
