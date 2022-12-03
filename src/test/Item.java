package test;

import java.awt.*;

public abstract class Item {
	
	String name;
	Image itemImage;
	Entity effectedPokemon;
	
	public Item(String itemName, Image sprite)
	{
		name = itemName;
		itemImage = sprite;
	}
	
	public abstract void useItem();
}
