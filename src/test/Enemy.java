package test;

public class Enemy extends Entity {
	
	public int skillLevel;
	public Bag enemyBag;
	
	public Enemy(Pokemon[] pokemonIn, int amountOfPoke, int skill)
	{
		super(pokemonIn, amountOfPoke);
		this.skillLevel = skill;
	}
	
}
