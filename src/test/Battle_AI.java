package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Battle_AI class that will make all the decisions in any battle based on a
 * number of factors. More or less factors are used in the decision depending on
 * the skill of the enemy the player is facing, this varies from a wild pokemon,
 * in which case all decisions are 100% randomly chosen, to a highly skilled 
 * opponent such as a gym leader, in which case all of the heuristic conditions
 * are taken into consideration and an A* path finding algorithm will search a
 * graph of possible GameStates to find the best path to success.
 * @author danielweiner
 *
 */
public class Battle_AI {
	
	/**
	 * skillLevel is the skill level of the AI opponent which determines how many
	 * of the heuristic conditions are taken into consideration when selecting the
	 * next move.
	 */
	public int skillLevel;
	/**
	 * the start Node in the GameState graph, it represents the initial battle 
	 * conditions when the player is challenged.
	 */
	public Node start;
	/**
	 * the goal Node in the GameState graph, it represents the finished battle where
	 * the AI has defeated the player (players current pokemon has zero health and 
	 * player has no more reserve pokemon)
	 */
	public Node goal;
	
	/**
	 * Node class to be used as the vertices on the GameState graph, they will store
	 * a list of neighbor Nodes (which would be null for a leaf or a goal Node), their
	 * current parent Node (which would be null for the start Node only), and their data
	 * which is the GameState they represent (so if the AI uses attack A we go to the left
	 * Node, but if the AI uses attack B we go to the right Node)
	 * @author danielweiner
	 *
	 */
	public class Node {
		/**
		 * List of all immediate neighbor Nodes
		 */
		List<Node> neighbors;
		/**
		 * Parent Node
		 */
		Node pathParent;
		/**
		 * GameState that this Node represents
		 */
		GameState state;
		
		/**
		 * Node constructor
		 * @param parent - parent Node
		 * @param neighborNodes - list of all immediate neighbor Nodes
		 * @param nodeState - GameState this Node will represent
		 */
		public Node(Node parent, List<Node> neighborNodes, GameState nodeState)
		{
			pathParent = parent;
			neighbors = neighborNodes;
			state = nodeState;
		}
		
		/**
		 * This constructs a path from the goal (or any Node) to the start. This function assumes
		 * that the start node has no parent, this will be the case for the Battle_AI because the 
		 * start Node will be the first GameState
		 * @param node - the goal node, or the node you want to make a path from to the start
		 * @return a path from node to the start
		 */
		public List<Node> constructPath(Node node)
		{
			LinkedList<Node> path = new LinkedList<Node>();
			while (node.pathParent != null)
			{
				path.addFirst(node);
				node = node.pathParent;
			}
			return path;
		}
		
		/**
		 * Simple BFS of the GameState graph
		 * @param startNode - Node to start the BFS at
		 * @param goalNode - Node to end the BFS at
		 * @return - a list of Node objects that represent the path between startNode and goalNode
		 * not including the startNode, if a path can't be found it returns null
		 */
		public List<Node> search(Node startNode, Node goalNode)
		{
			// list of visited Nodes
			LinkedList<Node> closed = new LinkedList<Node>();
			
			// list of Nodes to visit (sorted)
			LinkedList<Node> open = new LinkedList<Node>();
			open.add(startNode);
			startNode.pathParent = null;
			
			while (!open.isEmpty())
			{
				Node node = open.removeFirst();
				if (node == goalNode)
				{
					// path found
					return constructPath(goalNode);
				}
				else
				{
					closed.add(node);
					// add neighbors to the open list
					Iterator<Node> i = node.neighbors.iterator();
					while (i.hasNext())
					{
						Node neighborNode = i.next();
						if (!closed.contains(neighborNode) && !open.contains(neighborNode))
						{
							neighborNode.pathParent = node;
							open.add(neighborNode);
						}
					}
				}
			}
			// no path found
			return null;
		}
	}
	
}
