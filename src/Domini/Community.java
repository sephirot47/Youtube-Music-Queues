package Domini;

import java.util.ArrayList;
import java.util.HashMap;


// Stub de la classe Comunity
public class Community
{
	private HashMap<String, Node> com;

	// Pre: true
	// Post: Creates an empty community 
	public Community() 
	{
		com = new HashMap<String, Node>();
	} 

	// Adds a node to a community
	// Pre: true
	// Post: the community now contains the Node n (has one more Node)
	public void addNode(Node n) 
	{
		com.put(n.getId(), n);
	}

	// Delete a Node from the community
	// Pre: true
	// Post: if the node with identifier ‘id’ is in the community it is deleted, otherwise the //community is not changed.
	public void deleteNode(String id) 
	{
		com.remove(id);
	}

	// Checks whether or not a Node is in the community
	// Pre: true
	// Post: returns true if a Node with identifier ‘id’ is in the community, else returns false
	public boolean checkNode(String id) 
	{
		return com.containsKey(id);
	}

	//Returns the collection of Nodes that belong to the community
	// Pre: true
	// Post: returns an ArrayList of all the Nodes that form the community
	public ArrayList<Node> getCommunity() 
	{
		return new ArrayList<Node>(com.values());
	}

	// Returns the size (number of Nodes) of the community
	// Pre: true
	// Post: return the number of Nodes of the community. (0 if it is empty)
	public int getNumberOfNodes() 
	{
		return com.size();
	}
}
