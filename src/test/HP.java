package test;


public class HP  extends Stats{

	
	public HP(int base, Pokemon p) {
		super(base, p);
	}
	
	@Override
	void calculateMagnitude(Pokemon p){
		double effortValue =  Math.sqrt(65025);
		double innerNum =(2 * base + individualValue + (effortValue / 4)) * p.level;
		this.magnitude = (int) ((innerNum / 100) + 10 + p.level);
	}
}


