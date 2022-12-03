package test;

import java.util.Random;

public abstract class Stats {
	final static  int LARGEST_LEVEL = 6;
	final static int LOWEST_LEVEL = -6;
	Random rand = new Random();
	
	double modifier;
	int level = 0;
	int magnitude;
	int individualValue;
	int base;
	int ev = 0;
	
	public Stats(int base, Pokemon p) {
		this.setModifier(level); 
		this.calculateIV(p);
		this.base = base;
		
		
	}
	
	void setModifier(int level){
		if(level > LARGEST_LEVEL) level = LARGEST_LEVEL;
		if (level < LOWEST_LEVEL) level = LOWEST_LEVEL;
		int numerator = 0;
		int denominator = 0;
		if(level > 0) {
			numerator = level + 2;
			denominator = 2;
		}
		if(level < 0) {
			numerator = 2;
			denominator = level - 2;
		}
		if(level == 0) {
			numerator = 2;
			denominator = 2;
		}
		
		this.modifier = numerator/ denominator;
		
	}
	
	void increment(int number) {
		level += number;
		this.setModifier(level);
	}
	
	void decrement(int number) {
		level -= number;
		this.setModifier(level);
	}
	
	void unmodify() {
		this.setModifier(1);
	}
	
	 void calculateIV(Pokemon p) {

		 if(p instanceof SuperRare) {
			 individualValue = rand.nextInt(31) + 5;
			 System.out.println("im at super raree");
		 }
		 if(p instanceof Rare) {
			 individualValue = rand.nextInt(31) + 3;
			 System.out.println("im at raree");
		 }
		 if(p instanceof Common) {
			 individualValue = rand.nextInt(31) + 1;
			 System.out.println("im at super common");
		 }
	}
	 
	 void calculateMagnitude(Pokemon p) {
		double effortValue =  Math.sqrt(65025);
		double innerNum =(2 * base + individualValue + (effortValue / 4)) * p.level;
		this.magnitude = (int) (((innerNum / 100) + 5) * 1.10);
	 }
	 
	 public int returnBasePoint() {
		 return this.base;
	 }
	 
	 public void addEffortPoints(Stats s) {
		 if(!(Math.sqrt(this.ev)>= 255))
			 this.ev += s.returnBasePoint();
		 
	 }
	 
	 

}
