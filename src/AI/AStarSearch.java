package AI;

import java.util.*;

/**
 * The AStarSearch class, along with the AStarNode class, implements
 * a generic A* search algorithm. The AStarNode class should be subclassed
 * to provide searching capability.
 * @author danielweiner
 *
 */
public class AStarSearch {

	/**
	 * A simple priority list, also called a priority queue. Objects
	 * in the list are ordered by their priority, determined by the 
	 * object's Comparable interface. The highest priority item is
	 * first in the list.
	 * ThePriorityList inner class is a simple LinkedList that adds 
	 * nodes using an insertion sort. In this case, only AStarNode 
	 * instances are added, keeping the list sorted from lowest cost 
	 * to highest. Nodes are removed only from the front of the list 
	 * (lowest cost).
	 * @author danielweiner
	 *
	 */
	public static class PriorityList extends LinkedList {

		public void add(Comparable object) {
			for (int i=0; i<size(); i++) {
				if (object.compareTo(get(i)) <= 0) {
					add(i, object);
					return;
				}
			}
			addLast(object);
		}
	}

	/**
	 * Construct the path from node to start node, not including the start node.
	 * @param node - start node
	 * @return - a list of all the parent nodes from the start node
	 */
	protected List constructPath(AStarNode node) {
		LinkedList path = new LinkedList();
		while (node.pathParent != null) {
			path.addFirst(node);
			node = node.pathParent;
		}
		return path;
	}

	/**
	 * ThefindPath() function is very similar to the breadth-first search 
	 * implementation, except for a couple of changes. The costFromStart and 
	 * estimatedCostToGoal fields are calculated as you go along. Also, a node 
	 * is moved from the closed list to the open list if a shorter path to that 
	 * node is found. Other than that, they're pretty much the same.
	 * @param startNode - start node in the path
	 * @param goalNode - goal node in the path
	 * @return - a List of AStarNodes representing the lowest cost path from
	 * startNode to goalNode based on what was defined for the cost in the 
	 * AStarNode class
	 */
	public List findPath(AStarNode startNode, AStarNode goalNode) {
		PriorityList openList = new PriorityList();
		LinkedList closedList = new LinkedList();

		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
		startNode.pathParent = null;
		openList.add(startNode);

		while (!openList.isEmpty()) {
			AStarNode node = (AStarNode)openList.removeFirst();
			// if we've found the goal
			if (node == goalNode) {
				// construct the path from start to goal
				return constructPath(goalNode);
			}
			// otherwise keep looking for the goal
			// get the neighbors of the current node
			List neighbors = node.getNeighbors();
			// check if the neighbor nodes are in the open list or the closed list
			for (int i = 0; i < neighbors.size(); i++) {
				AStarNode neighborNode = (AStarNode)neighbors.get(i);
				boolean isOpen = openList.contains(neighborNode);
				boolean isClosed = closedList.contains(neighborNode);
				// calculate their cost from start by adding the cost of the neighbor node
				// and the cost from start of the node before the neighbor node
				float costFromStart = node.costFromStart + node.getCost(neighborNode);

				// check if the neighbor node has not been traversed or if a
				// shorter path to this neighbor node is found
				if ((!isOpen && !isClosed) || costFromStart < neighborNode.costFromStart) {
					neighborNode.pathParent = node;
					neighborNode.costFromStart = costFromStart;
					neighborNode.estimatedCostToGoal = neighborNode.getEstimatedCost(goalNode);
					if (isClosed) {
						closedList.remove(neighborNode);
					}
					if (!isOpen) {
						openList.add(neighborNode);
					}
				}
			}
			closedList.add(node);
		}
		// no path found
		return null;
	}
}
