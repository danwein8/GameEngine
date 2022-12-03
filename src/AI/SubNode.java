package AI;

import java.util.ArrayList;
import java.util.List;

import test.*;

public class SubNode extends AStarNode 
{
	/**
	 * this goal node needs to be defined somehow, I'm not sure this all works if we
	 * don't have a tangible target node to search for, so i have to find a way to 
	 * define a win
	 */
	SubNode goalNode;
	/**
	 * use the GameState item as the object that holds all the variables that SubNode 
	 * needs to calculate the cost of any node
	 */
	GameState nodeState;
	/**
	 * keep all neighbors in an ArrayList data member so we can add and subtract them as
	 * needed
	 */
	private ArrayList neighbors;
	
	public SubNode(AStarNode parent, GameState currentState)
	{
		if (parent != null)
		{
			pathParent = parent;
			costFromStart = parent.costFromStart + getCost(parent);
		}
		else
		{
			pathParent = null;
			costFromStart = 0;
		}
		/**
		 * set the goal node to zero health for the enemy poke, and zero 
		 * reserve poke, this way we have a tangible goal that we can shoot for.
		 */
		goalNode.nodeState.enemyPokeHealth = 0;
		goalNode.nodeState.enemyReservePoke = 0;
		// bring in goalNode as a data member? have to set it in constructor?
		estimatedCostToGoal = getEstimatedCost(goalNode);
	}
	
	/**
	 * Builds the list of neighbors for the AStarNode representation.
	 * The neighbors are all the possible moves the AI can make from
	 * this current GameState.
	 */
	public void buildNeighborList() {
		/**
		 * with all this information compiled we can take this neighbors list and rate each node as useful
		 * or not useful in our A* algorithm, then we can make a choice based on the highest rated one
		 */
		neighbors = new ArrayList();
		PlayerTrainer tempTrainer;
		Enemy tempAI;
		Pokemon pokeA = nodeState.self.currentPokemon();
		String[] attacks = pokeA.getAttackList();
		// we are going to add all the possible attacks of the current pokemon to the neighbor list
		// these are all attacks we can perform
		for (int i = 0; i < attacks.length; i++)
		{
			// I instantiate a new object as to not change any values of the original
			tempTrainer = nodeState.enemy;
			// I need to be able to get the attacks and their damage values
			// I need to be able to get the health of any pokemon and change it at will
			tempTrainer.currentPokemon().hp -= attacks[i].damage;
			neighbors.add(new SubNode(this, new GameState(tempTrainer, nodeState.self)));
		}
		// we add all of the current items available in the bad to the neighbor list
		// these are all items we can use
		
		for (int i = 0; i < nodeState.self.enemyBag.allItems.length; i++)
		{
			tempAI = nodeState.self;
			tempAI.enemyBag.allItems[i].useItem();
			neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
		}
		// we add all the reserve pokemon into the neighbor list as long as health is > 0 and its not the current pokemon
		// these are all the pokemon we could switch to
		for (int i = 0; i < nodeState.self.pokemon.length; i++)
		{
			tempAI = nodeState.self;
			if (tempAI.pokemon[i].hp > 0)
			{
				if (tempAI.pokemon[i] != tempAI.currentPokemon())
					tempAI.changePokemon(i);
					neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
			}
		}
		
		// Trim to size then remove references to this node.
		// Ensures extra capacity for calls to addNeighbor()
		// without enlarging the array capacity)
		neighbors.trimToSize();
		while(neighbors.remove(this));
	}
	
	public void addNeighbor(AStarNode node) {
		if (neighbors == null) buildNeighborList();
		neighbors.add(node);
	}
	
	public void removeNeighbor(AStarNode node) {
		if (neighbors == null) buildNeighborList();
		neighbors.remove(node);
	}
	
	// ASTARNODE METHODS
	
	@Override
	public float getCost(AStarNode node) {
		/**
		 * This cost gets calculated PER-NODE in the GameState class then this
		 * function calculates the cost between the implicit node and the specified
		 * neighbor node
		 * 
		 * Cost between this node and the specified neighbor node defined as:
		 * 
		 * my pokemon has less health = higher cost
		 * enemy pokemon has less health = lower cost
		 * no change/higher health not taken into account
		 * 
		 * I have less reserve pokemon = higher cost
		 * enemy has less reserve pokemon = lower cost
		 * no change/revived pokemon not taken into account
		 * 
		 * my poke type is strong against enemy poke type = lower cost
		 * enemy poke type is strong against my poke type = higher cost (this should cause
		 * a switch in higher skill trainers if better option available)
		 * no type advantage has no effect on cost
		 * 
		 * my poke is lower level than enemy poke = higher cost (this should cause
		 * a switch in higher skill trainers if better option available)
		 * my poke is higher level than enemy poke = lower cost
		 * poke within 3 or 4 levels will have no effect on cost
		 * 
		 */
		return getEstimatedCost(node);
	}

	@Override
	public float getEstimatedCost(AStarNode node) {
		/**
		 * here we need to calculate the best path to a winning GameState.
		 * We will accomplish that with the comparisons of the AStarNode class
		 * and the A* search method of the AStarSearch class, we just have to
		 * make sure all the necessary information is available for those classes
		 * to function as expected
		 */
		float estimate = 0;
		// if the AStarNode explicit parameter is a SubNode
		if (node instanceof SubNode) {
			// cast it to a SubNode
			SubNode other = (SubNode)node;
			// if the current enemy pokemon has less health after the move, decrement the cost
			if (nodeState.enemy.pokeHealth > other.nodeState.enemy.pokeHealth)
				estimate--;
			// if one of the enemy pokemon gets K.O.ed after the move, decrement the cost by 2
			if (nodeState.enemy.reservePoke > other.nodeState.enemy.reservePoke)
				estimate -= 2;
			// if our pokemon loses health after the move,
			// and if AI skill level is high enough increment the cost
			if (nodeState.self.currentPokemon().hp > other.nodeState.self.currentPokemon().hp)
				if (nodeState.self.skillLevel > 2) estimate++;
			// if one of our pokemon gets K.O.ed after the move,
			// and if AI skill level is high enough increase the cost by 2
			if (nodeState.self.reservePoke > other.nodeState.self.reservePoke)
				if (nodeState.self.skillLevel > 2) estimate+=2;
		}
		return 0;
	}

	@Override
	public List getNeighbors() {
		/**
		 * neighbor nodes are any nodes that are only one move away, so if we 
		 * are at the initial GameState, all the neighbor nodes are:
		 * 1. every attack that can be made 
		 * 2. every item that can be used 
		 * 3. every pokemon that can be switched to
		 * 4. run from the fight
		 * 
		 * its just everything that you can do this exact turn
		 */
		if (neighbors == null) {
            buildNeighborList();
        }
        return neighbors;
	}

}
