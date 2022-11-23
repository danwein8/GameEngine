package test;

public class Enemy {
	public int reserve = 0;
	public int current = 0;
	public Pokemon[] pokemon = new Pokemon[reserve];
	// these data members might be in the Pokemon class, even tho pokeHealth is a percentage
	public int pokeHealth = 100;
	public String pokeType;
	public int pokeLevel;
	
	public Enemy(Pokemon[] pokemonIn)
	{
		for (int i = 0; i < reserve; i++)
		{
			pokemon[i] = pokemonIn[i];
		}
	}
	
	public void changePokemon(int newPokeIndex)
	{
		current = newPokeIndex;
	}
	
	public Pokemon currentPokemon()
	{
		return pokemon[current];
	}
	
	public void changeHealth(int change)
	{
		if (pokemon[current].health + change > pokemon[current].maxHealth)
		{
			pokemon[current].health = pokemon[current].maxHealth;
			pokeHealth = 100;
		}
		if (pokemon[current].health + change < 0)
		{
			pokemon[current].health = 0;
			pokeHealth = 0;
		}
		else
		{
			pokemon[current].health += change;
			pokeHealth = (pokemon[current].health / pokemon[current].maxHealth) * 100;
		}
	}
}
