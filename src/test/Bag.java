package test;

public class Bag {
	
	public Item[] allItems;
	
	public Bag(Item[] itemsIn)
	{
		for (int i = 0; i < itemsIn.length; i++)
		{
			allItems[i] = itemsIn[i];
		}
	}
}
