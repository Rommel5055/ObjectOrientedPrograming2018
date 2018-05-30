package exercise18;

public class EightsCard extends Card{

	public EightsCard(int rank, int suit) {
		super(rank, suit);
		// TODO Auto-generated constructor stub
		
	}

	public boolean match(Card card){
		if (getSuit() == card.getSuit()) {
	        return true;
	    }
	    if (getRank() == card.getRank()) {
	        return true;
	    }
	    if (getRank() == 8) {
	        return true;
	    }
	    return false;
	}

	public int scoreCard(){
		if(getRank() == 8){
			return 20;
		}
		else if(getRank() > 10){
			return 10;
		}
		else{
			return getRank();
		}
	}
	
}
