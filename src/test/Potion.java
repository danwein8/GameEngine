package test;

import java.awt.Image;

public class Potion extends Item {
	
	int healthAmt;

	public Potion(String itemName, Image sprite, int power) {
		super(itemName, sprite);
		healthAmt = power;
	}
	
	public void useItem()
	{
		// add health amount to chosen pokemon
		effectedPokemon.changePokeHealth(healthAmt);
	}

}
