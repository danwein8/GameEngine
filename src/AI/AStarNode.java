package AI;

import java.util.List;

/**
 * The AStarNode class, along with the AStarSearch class, implements
 * a generic A* search algorithm. The AStarNode class should be subclassed
 * to provide searching capability
 * @author danielweiner
 *
 */
public abstract class AStarNode implements Comparable {
	
	AStarNode pathParent;
	float costFromStart;
	float estimatedCostToGoal;
	
	public float getCost()
	{
		return costFromStart + estimatedCostToGoal;
	}
	
	public int compareTo(Object other)
	{
		float otherValue = ((AStarNode)other).getCost();
		float thisValue = this.getCost();
		
		return MoreMath.sign(thisValue - otherValue);
	}
	
	/**
	 * Gets the cost between this node and the specified adjacent
	 * (aka "neighbor" or "child") node.
	 * @param node - specified node to get the cost between implicit
	 * parameter node
	 * @return - cost between nodes, depending on what you want cost
	 * to be
	 */
	public abstract float getCost(AStarNode node);
	
	/**
	 * Gets the estimated cost between this node and the specified
	 * node. The estimated cost should never exceed the true cost.
	 * The better the estimate, the more efficient the search.
	 * @param node - specified node to get estimated cost to from
	 * the implicit parameter node
	 * @return - estimation of cost between current node and 
	 * explicit parameter "goal" node, depending on what you want
	 * cost to be
	 */
	public abstract float getEstimatedCost(AStarNode node);
	
	/**
	 * Gets the children (aka "neighbors" or "adjacent nodes") of
	 * this node.
	 * @return - a list of nodes that are the direct children of 
	 * this node
	 */
	public abstract List getNeighbors();
	
}
