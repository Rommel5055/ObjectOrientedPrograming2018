package exercise18;

import java.util.ArrayList;

public class Genius extends Player{
	private CardCollection posiblePlays;
	private ArrayList<Integer> handIndex;
	
	public Genius(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		posiblePlays = new CardCollection("PosiblePlays");
		handIndex = new ArrayList<Integer>();
	}
	
	public void getPlays(Card prev){
		posiblePlays = new CardCollection("PosiblePlays");
		handIndex = new ArrayList<Integer>();
		for (int i = 0; i < getHand().size(); i++) {
            Card card = getHand().getCard(i);
            if ((cardMatches(card, prev))) {
            	posiblePlays.addCard(card);
            	handIndex.add(i);
            }
		}
	}
	
	public Card checkPossibilities(){
		if (posiblePlays.size() == 0){
			return null;
		}
		else{
			int index = 0;
			int maxVal = 0;
			for (int j = 0; j < posiblePlays.size()-1; j++){
				if (posiblePlays.getCard(j).getRank() == 8){
					index = j;
					maxVal = 20;
				}
				else if (posiblePlays.getCard(j).getRank() > maxVal){
					index = j;
					maxVal = posiblePlays.getCard(j).getRank();
				}
			}
			Card playCard = posiblePlays.getCard(index);
			hand.popCard(handIndex.get(index));
			posiblePlays = new CardCollection("PossiblePlays");
			return playCard;
		}
	}
	
	public Card play(Eights eights, Card prev){
		getPlays(prev);
		Card playingCard = checkPossibilities();
		if (playingCard == null){
			playingCard = super.drawForMatch(eights, prev);
		}
		posiblePlays = new CardCollection("PosiblePlays");
		return playingCard;
	}
}