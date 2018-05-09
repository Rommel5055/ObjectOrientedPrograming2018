package exercise15;

public class Tile {
	private char letter;
	private int value;
	
	public Tile(char letter, int value){
		this.letter = letter;
		this.value = value;
	}
	public static void printTile(Tile paramTiles){
		System.out.println("Tile with:\nChar: " + paramTiles.letter + "\nValue: " + paramTiles.value);
	}
	public static Tile testTile(){
		Tile test = new Tile('z', 10);
		printTile(test);
		return test;
	}
	public String toString(){
	    return "Tile with:\nChar: " + this.letter + "\nValue: " + this.value;
	}
	public boolean equals(Tile that){
		return this.letter == that.letter && this.value == that.value;
	}
	public char getLetter(){
      return this.letter;
    }
    public void setLetter(char letter){
      this.letter = letter;
    }
    public int getValue(){
      return this.value;
    }
    public void setValue(int value){
      this.value = value;
    }
	public static void main(String args[]){
		 Tile test = testTile();
	      test.setValue(20);
	      test.setLetter('k');
	      printTile(test);
	      System.out.println(test.equals(testTile())+" "+test.getLetter());	
	}
}
