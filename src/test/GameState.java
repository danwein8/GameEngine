package test;

public class GameState {
	
	/**
	 *  these will be the two participants of the battle that make up any GameState.
	 *  TODO: change all variable values to be relative to these variables, like 
	 *  instead of reservePoke, do self.reservePoke etc.
	 */
	public PlayerTrainer enemy;
	public Enemy self;
	/**
	 * reserve pokemon that the AI trainer has, if AI is a wild pokemon this == 0
	 */
	public int reservePoke = self.reservePoke;
	/**
	 * reserve pokemon that the player trainer has
	 */
	public int enemyReservePoke;
	/**
	 * percentage of health that the AI controlled pokemon has, this value will be scaled down
	 * as to not effect the decision more than any other variable
	 */
	public int pokeHealth = 100;
	/**
	 * percentage of health the players pokemon has, this value will be scaled down as to not
	 * effect the decision more than any other variable
	 */
	public int enemyPokeHealth;
	/**
	 * AI controlled pokemon type, used to check match-up against player pokemon
	 */
	public String pokeType = self.currentPokemon().type;
	/**
	 * player controlled pokemon type, used to check match-up against AI pokemon
	 */
	public String enemyPokeType;
	/**
	 * AI controlled pokemon level, used to check match-up against player pokemon
	 */
	public int pokeLevel = self.currentPokemon().getLevel();
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
	public GameState(PlayerTrainer enemy, Enemy self)
	{
		// if wild pokemon, reservePoke = 0
		enemyReservePoke = enemy.reservePoke;
		pokeHealth = enemy.pokeHealth;
		enemyPokeType = enemy.currentPokemon().type;
		enemyPokeLevel = enemy.currentPokemon().getLevel();
		
		// Gotta do the same thing for the player class
	}
	
	/**
	 * need a rating system, going to get Jonathans help on this because of the way the Stats work.
	 * basically I'm just going to compare a bunch of things and rate the GameState based on who's
	 * in a better position, then I can use that rating to do A* path-finding and battle AI
	 * @return
	 */
	public int hRating()
	{
		double heuristic = (reservePoke - enemyReservePoke) + (enemyPokeHealth / 100) + (pokeLevel - enemyPokeLevel);
		if (pokeType > enemyPokeType) return (int)(2.0 * heuristic);
		if (pokeType < enemyPokeType) return (int)(0.5 * heuristic);
		else return (int)heuristic;
	}
}
