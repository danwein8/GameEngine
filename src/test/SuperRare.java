package test;

import java.io.IOException;

public class SuperRare extends Pokemon {

	public SuperRare(String name,  int level, String type)
			throws WrongTypeException, IOException {
		super(name, level, type);
	
	}
	
	public static void main(String[] args) {
		try {
			Pokemon charmander = new SuperRare("charmander", 100, Pokemon.F);
			Pokemon charmanders = new Rare("charmander", 100, Pokemon.F);
			
			charmander.printMyStats();
			charmanders.printMyStats();
			
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
