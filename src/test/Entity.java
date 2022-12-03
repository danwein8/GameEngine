package test;

import java.awt.*;

/**
 * Entity class will be inherited by any enemy trainer, the player character, and even wild Pokemon.
 * This class is essentially a framework for us to be able to put all the trainer/enemy information
 * in one place so we can reference it all right here during battle so we don't need to access the 
 * raw Pokemon class or raw item class directly.
 * @author danielweiner
 *
 */
public abstract class Entity {
	
	/**
	 * amount of reserve pokemon available, cannot be > 5 (1 out and 5 in the backpack)
	 */
	public int reservePoke = 0;
	/**
	 * the total pokemon this entity has, wild pokemon only have 1 (themselves), trainers
	 * can not have > 6
	 */
	public int totalPoke = 0;
	/**
	 * the index of the currently fighting pokemon
	 */
	public int currentPoke = 0;
	/**
	 * an array of Pokemon objects representing all of the 
	 */
	public Pokemon[] pokemon = new Pokemon[totalPoke];
	/**
	 * a percentage representation of the health of the pokemon, needed for the rendering
	 * of the health bars
	 */
	public int pokeHealth = 100;
	
	
	public Entity(Pokemon[] pokemonIn, int amountOfPoke)
	{
		this.totalPoke = amountOfPoke; 
		for (int i = 0; i < totalPoke; i++)
			pokemon[i] = pokemonIn[i];
	}
	
	public void changePokemon(int newPokeIndex)
	{
		currentPoke = newPokeIndex;
	}
	
	public Pokemon currentPokemon()
	{
		return pokemon[currentPoke];
	}
	
	public void changePokeHealth(int change)
	{
		if (pokemon[currentPoke].hp + change > pokemon[currentPoke].maxHP)
		{
			pokemon[currentPoke].hp = pokemon[currentPoke].maxHP;
			pokeHealth = 100;
		}
		if (pokemon[currentPoke].hp + change < 0)
		{
			pokemon[currentPoke].hp = 0;
			pokeHealth = 0;
		}
		else
		{
			pokemon[currentPoke].hp += change;
			pokeHealth = (pokemon[currentPoke].hp / pokemon[currentPoke].maxHP) * 100;
		}
	}
	
	/**
	 * All these entities need to be able to be drawn
	 * @param g - the Graphics object
	 */
	public void draw(Graphics g)
	{
		
	}
	
}
