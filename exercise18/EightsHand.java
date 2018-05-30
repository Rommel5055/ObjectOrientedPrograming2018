package exercise18;

public class EightsHand extends Hand{

	public EightsHand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	
	public int scoreHand(){
		int score = 0;
		for (int i = 0; i < size(); i++){
			score += getECard(i).scoreCard();
		}
		return score;
	}
}
