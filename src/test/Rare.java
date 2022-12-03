package test;

import java.io.IOException;

public class Rare extends Pokemon {

	public Rare(String name,  int level, String type)
			throws WrongTypeException, IOException {
		super(name, level, type);
	
	}
	
	
	public static void main(String[] args) {
		try {
			Pokemon charmander = new Rare("charmander", 2, Pokemon.F);
			charmander.printMyStats();
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}
