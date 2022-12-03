package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.*;
import java.io.IOException;

public abstract class Pokemon implements TypeOfP {
	
	//based on defender's perspevter
	static Map<String, Map<String, Double>> weaknesses = new HashMap<>();
	static Map<String, Map<String, String>> allAtkMap = generateMapForAttacks();
	Map<String, Integer> baseStats;
	Map<String, Map<String, Image>> views;
	String [] attackList;
	String type;// expected to use static variable from TypeOfP interface
	final double  EXPERIENCE_TO_100 = 1.15 *  Math.pow(100, 3);
	int level;
	int totalExperience;
	//PokeAttack attackP;
	String name;
	Attack atk;
	Defense def;
	HP hp;
	SpAttack sa;
	SpDefense df;
	Speed s;
	
	//level will not any input on base stats... base stats are calculations assuming each pokemon is in level 1
	public Pokemon(String name, int level, String type) throws WrongTypeException, IOException {
		baseStats = RequestInfo.getBaseStats(name);
		this.setType(type);
		this.name = name;
		this.level = level;
		this.atk = new Attack(baseStats.get("Attack"), this);
		this.def = new Defense(baseStats.get("Defense"), this);
		this.hp = new HP(baseStats.get("HP"), this);
		this.sa = new SpAttack(baseStats.get("Sp. Atk"), this);
		this.df = new SpDefense(baseStats.get("Sp. Def"), this);
		this.s = new Speed(baseStats.get("Speed"), this);
		
		
	}
	//meant to be called outside of class before games start
	
	static void loadTypes() {
		
		Map<String, Double> normalHash = new HashMap<>();
		normalHash.put(FT, 2.0);
		normalHash.put(GH,0.0);
		
		Map<String, Double> fireHash = new HashMap<>();
		fireHash.put(F, 0.5);
		fireHash.put(W,2.0);
		fireHash.put(G, 0.5);
		fireHash.put(I,0.5);
		fireHash.put(S,0.5);
		
		Map<String, Double> waterHash = new HashMap<>();
		waterHash.put(F, 0.5);
		waterHash.put(W,0.5);
		waterHash.put(G, 2.0);
		waterHash.put(I,0.5);
		waterHash.put(E,2.0);
		waterHash.put(S,0.5);
		
		Map<String, Double> grassHash = new HashMap<>();
		grassHash.put(F, 2.0);
		grassHash.put(W,0.5);
		grassHash.put(G, 2.0);
		grassHash.put(I,2.);
		grassHash.put(E, .5);
		grassHash.put(GN,0.5);
		grassHash.put(P,2.);
		grassHash.put(FL, 2.);
		grassHash.put(B,2.);
		
		Map<String, Double> electricrHash = new HashMap<>();
		electricrHash.put(E, 0.5);
		electricrHash.put(FL,0.5);
		electricrHash.put(GN, 2.0);
		electricrHash.put(S,0.5);
		
		Map<String, Double> iceHash = new HashMap<>();
		iceHash.put(F, 2.);
		iceHash.put(I,.5);
		iceHash.put(FT, 2.);
		iceHash.put(R,2.);
		iceHash.put(S,2.);
		
		Map<String, Double> fightingHash = new HashMap<>();
		fightingHash.put(FL, 2.);
		fightingHash.put(PY, 2.);
		fightingHash.put(B, .5);
		fightingHash.put(R,.5);
		fightingHash.put(FA,2.0);
		fightingHash.put(D,0.5);
		
		Map<String, Double> poisonHash = new HashMap<>();
		poisonHash.put(G, .5);
		poisonHash.put(FT, .5);
		poisonHash.put(P, .5);
		poisonHash.put(GN,2.);
		poisonHash.put(PY,2.0);
		poisonHash.put(B,0.5);
		poisonHash.put(FA,0.5);
		
		Map<String, Double> groundHash = new HashMap<>();
		groundHash.put(P, 0.5);
		groundHash.put(W,2.);
		groundHash.put(G, 2.0);
		groundHash.put(I,2.);
		groundHash.put(E,0.);
		groundHash.put(R,0.5);
		
		Map<String, Double> flyingHash = new HashMap<>();
		flyingHash.put(G, .5);
		flyingHash.put(E, 2.);
		flyingHash.put(I, 2.);
		flyingHash.put(FT,.5);
		flyingHash.put(GN,0.);
		flyingHash.put(B,0.5);
		flyingHash.put(R,2.);
		
		Map<String, Double> psychicHash = new HashMap<>();
		psychicHash.put(PY, .5);
		psychicHash.put(B, 2.);
		psychicHash.put(GH, 2.);
		psychicHash.put(F,.5);
		psychicHash.put(D,2.);
		
		Map<String, Double> bugHash = new HashMap<>();
		bugHash.put(F, 2.);
		bugHash.put(FT,.5);
		bugHash.put(G, .5);
		bugHash.put(FL,2.);
		bugHash.put(R,2.);
		bugHash.put(GN,0.5);
		
		Map<String, Double> rockHash = new HashMap<>();
		rockHash.put(F, .5);
		rockHash.put(W,2.);
		rockHash.put(G, 2.0);
		rockHash.put(FT,2.);
		rockHash.put(S, 2.);
		rockHash.put(GN,2.);
		rockHash.put(P,.5);
		rockHash.put(FL, .5);
		rockHash.put(N,.5);
		
		Map<String, Double> ghostHash = new HashMap<>();
		ghostHash.put(N, 0.);
		ghostHash.put(FT,0.);
		ghostHash.put(P, .5);
		ghostHash.put(D,2.);
		ghostHash.put(GH,2.);
		ghostHash.put(B,0.5);
		
		Map<String, Double> dragonHash = new HashMap<>();
		dragonHash.put(F, .5);
		dragonHash.put(W,.5);
		dragonHash.put(G, .5);
		dragonHash.put(I,2.);
		dragonHash.put(DR, 2.);
		dragonHash.put(FA,2.);
		dragonHash.put(E,.5);
		
		Map<String, Double> darkHash = new HashMap<>();
		darkHash.put(B, 2.);
		darkHash.put(FT,2.);
		darkHash.put(PY, 0.);
		darkHash.put(FA,2.);
		darkHash.put(GH,.5);
		darkHash.put(S,0.5);
		
		Map<String, Double> steelHash = new HashMap<>();
		steelHash.put(N, .5);
		steelHash.put(F,2.);
		steelHash.put(G, .5);
		steelHash.put(I,.5);
		steelHash.put(FT, 2.);
		steelHash.put(P,0.);
		steelHash.put(GN,2.);
		steelHash.put(FL, .5);
		steelHash.put(PY,.5);
		steelHash.put(B,.5);
		steelHash.put(R,.5);
		steelHash.put(DR, .5);
		steelHash.put(S,.5);
		steelHash.put(FA,.5);
		
		Map<String, Double> fairyHash = new HashMap<>();
		fairyHash.put(P, 2.);
		fairyHash.put(FT,.5);
		fairyHash.put(DR, 0.);
		fairyHash.put(S,2.);
		fairyHash.put(B,.5);
		fairyHash.put(D,0.5);
		
		loadInnerTypes(normalHash, N);
		loadInnerTypes(fireHash, F);
		loadInnerTypes(waterHash, W);
		loadInnerTypes(grassHash, G);
		loadInnerTypes(electricrHash, E);
		loadInnerTypes(iceHash, I);
		loadInnerTypes(fightingHash, FT);
		loadInnerTypes(poisonHash, P);
		loadInnerTypes(groundHash, GN);
		loadInnerTypes(flyingHash, FL);
		loadInnerTypes(psychicHash, PY);
		loadInnerTypes(bugHash, B);
		loadInnerTypes(rockHash, R);
		loadInnerTypes(ghostHash, GH);
		loadInnerTypes(dragonHash, DR);
		loadInnerTypes(darkHash, D);
		loadInnerTypes(steelHash, S);
		loadInnerTypes(fairyHash, FA);
	}
	
	static void loadInnerTypes(Map<String, Double> defenderChart, String defender) {
		weaknesses.put(defender, defenderChart);
		for(int i = 0; i < typesList.length; i++) {
			if(weaknesses.get(defender).get(typesList[i]) == null) {
				weaknesses.get(defender).put(typesList[i], 1.0);
			}
		}
	}
	
	
	void setType(String type) throws WrongTypeException{
		if(type == "" )
			throw new WrongTypeException("must use a proper fellasmon type");
		for(int i = 0; i < typesList.length; i++) {
			if(type == typesList[i]) {
				type = "";
				break;
			}
		}
		if(type != "" )
			throw new WrongTypeException("must use a proper fellasmon type");
	}
	
	void gatherExperience(Pokemon enemy) {
		this.totalExperience += (enemy.getExperience() * enemy.getLevel());
		this.calculateLevel();
	}
	
	void calculateLevel() {
		while(this.totalExperience > (1.25 * Math.pow(this.level + 1, 3))) {
			level++;
		}
	}
	
	public int getExperience() {
		return this.totalExperience;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public Map<String, String> getAttackData(int attckArrayIndex){
		return allAtkMap.get(this.attackList[attckArrayIndex]);
	}
	
	public String[] getAttackList()
	{
		return attackList;
	}
	
	private boolean attackListFull() {
		int counter = 0;
		for(int i =0; i < 4; i++) {
			if(this.attackList != null) counter++;
		}
		return (counter == 4);
	}
	
	private boolean doesLearn() {
		try {
			return (RequestInfo.getAttacks(this.name).get(level) != null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void LearnAttack() {
		if(this.doesLearn()) {
			if(attackListFull()) {
				Scanner numberToDelete = new Scanner(System.in);
				int i;
				i = numberToDelete.nextInt();
				if(i >= 0 || i <= 3) {
					try {
						this.attackList[i] = RequestInfo.getAttacks(this.name).get(level);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				for(int i =0; i < 4; i++) {
					if(this.attackList != null)
						try {
							this.attackList[i] = this.attackList[i] = RequestInfo.getAttacks(this.name).get(level);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} ;
				}
			}
		}
	}
	
	public 
	
	static  Map<String, Map<String, String>>  generateMapForAttacks() {
		 try {
			return RequestInfo.getAllAttacks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	public void printMyStats() {
		System.out.println(this.atk.base);
		System.out.println(this.def.base);
		System.out.println(this.s.base);
		System.out.println(this.df.base);
		System.out.println(this.sa.base);
		System.out.println(this.hp.base);
		System.out.println("-------------------------------------------------");
		System.out.println(this.atk.individualValue);
		System.out.println(this.def.individualValue);
		System.out.println(this.s.individualValue);
		System.out.println(this.df.individualValue);
		System.out.println(this.sa.individualValue);
		System.out.println(this.hp.individualValue);
		System.out.println("-------------------------------------------------");
		this.atk.calculateMagnitude(this);
		this.def.calculateMagnitude(this);
		this.s.calculateMagnitude(this);
		this.df.calculateMagnitude(this);
		this.sa.calculateMagnitude(this);
		this.hp.calculateMagnitude(this);
		System.out.println(this.atk.magnitude);
		System.out.println(this.def.magnitude);
		System.out.println(this.s.magnitude);
		System.out.println(this.df.magnitude);
		System.out.println(this.sa.magnitude);
		System.out.println(this.hp.magnitude);
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		Pokemon.loadTypes();
		Map<String, Double> currentMap;
		for(int i = 0; i < Pokemon.typesList.length; i++) {
			currentMap = Pokemon.weaknesses.get(typesList[i]);
			System.out.println("------------------- " + typesList[i] + " --------------------");
			currentMap.entrySet().stream().forEach(input ->
				System.out.println(input.getKey() + " : "
							 + input.getValue())
				);
		}
		
		
	}
	
	

}


