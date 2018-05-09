package exercise15;

public class Rational {
	private int numerator;
	private int denominator;
	
	public Rational(){
		this.numerator = 0;
		this.denominator = 0;
	}
	public Rational(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	public void printRational(){
		System.out.println(this.numerator + "/" + this.denominator);
	}
	public void setNumerator(int num){
		this.numerator = num;
	}
	public void setDenominator(int den){
		this.denominator = den;
	}
	public int getNumerator(){
		return this.numerator; 
	}
	public int getDenominator(){
		return this.denominator;
	}
	public String toString(){
		return this.numerator + "/" + this.denominator;
	}
	public void negate(){
		this.numerator = -this.numerator;
	}
	public void invert(){
		if (this.numerator > 0){
			int aux = this.numerator;
			this.numerator = this.denominator;
			this.denominator = aux;
		}
		else{
			int aux = -this.numerator;
			this.numerator = -this.denominator;
			this.denominator = aux;
		}
	}
	public double toDouble(){
		if (this.denominator != 0){
		return (double)this.numerator/this.denominator;
		}
		else{return 0.0;}
	}
	public void fixSign(){
		if (this.denominator < 0){
			this.denominator = -this.denominator;
			this.numerator = -this.numerator;
		}
	}
	
	public Rational reduce(){
		Rational reduced = new Rational(this.numerator / gcd(this.numerator, this.denominator),	this.denominator / gcd(this.numerator, this.denominator));
		reduced.fixSign();
		return reduced;
	}
	
	public int gcd(int m, int n) //https://www.geeksforgeeks.org/c-program-find-gcd-hcf-two-numbers/
    {
		if	(m	% n == 0) {
	         return n;
	      } 
	      else {
	         return gcd(n,m	% n);
	      }
	   }
	
	public Rational add(Rational sum){
		if (this.denominator == sum.denominator){
			Rational result = new Rational (this.numerator + sum.numerator, this.denominator);
			return result;
		}
		else{
			int num = (this.denominator*sum.numerator) + (this.numerator*sum.denominator);
			int den = this.denominator * sum.denominator;
			Rational result = new Rational(num, den);
			result.reduce();
			return result;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rational rat = new Rational();
		rat.setDenominator(8);
		rat.setNumerator(32);
		rat.printRational();
		//Everything works fine so far
		System.out.println(rat.toString());
		rat.negate();
		System.out.println(rat.toString());
		rat.invert();
		System.out.println(rat.toString());
		System.out.println(rat.toDouble());
		rat = rat.reduce();
		System.out.println(rat.toString());
		Rational sum = new Rational(4, 7);
		sum = sum.add(rat);
		System.out.println(sum.toString());
	}

}
