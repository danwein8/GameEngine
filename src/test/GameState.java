package test;

public class GameState {
	
	/**
	 * reserve pokemon that the AI trainer has, if AI is a wild pokemon this == 0
	 */
	public int reservePoke = 0;
	/**
	 * reserve pokemon that the player trainer has
	 */
	public int enemyReservePoke = 0;
	/**
	 * percentage of health that the AI controlled pokemon has, this value will be scaled down
	 * as to not effect the decision more than any other variable
	 */
	public int pokeHealth = 100;
	/**
	 * percentage of health the players pokemon has, this value will be scaled down as to not
	 * effect the decision more than any other variable
	 */
	public int enemyPokeHealth = 100;
	/**
	 * AI controlled pokemon type, used to check match-up against player pokemon
	 */
	public String pokeType;
	/**
	 * player controlled pokemon type, used to check match-up against AI pokemon
	 */
	public String enemyPokeType;
	/**
	 * AI controlled pokemon level, used to check match-up against player pokemon
	 */
	public int pokeLevel;
	/**
	 * player controlled pokemon level, used to check match-up against AI pokemon
	 */
	public int enemyPokeLevel;
	
	/**
	 * The class that will have all the information for the pathfinding algorithm to make an
	 * informed decision for the AIs next move based on the current state of the battle.
	 * Measures several factors based on the level of the AI trainer, meaning more factors will
	 * be taken into account the higer the level of the AI trainer. If a wild pokemon is
	 * the AI, the factors will essentially be ignored and a random attack will be chosen
	 * since its supposed to be "wild"
	 * @param enemy - the enemy that is encountered
	 */
	public GameState(Enemy enemy)
	{
		// if wild pokemon, reservePoke = 0
		reservePoke = enemy.reserve;
		pokeHealth = enemy.pokeHealth;
		pokeType = enemy.currentPokemon().type;
		pokeLevel = enemy.currentPokemon().level;
		
		// Gotta do the same thing for the player class
	}
	
	public int hRating()
	{
		double heuristic = (reservePoke - enemyReservePoke) + (enemyPokeHealth / 100) + (pokeLevel - enemyPokeLevel);
		if (pokeType > enemyPokeType) return (int)(2.0 * heuristic);
		if (pokeType < enemyPokeType) return (int)(0.5 * heuristic);
		else return (int)heuristic;
	}
}
