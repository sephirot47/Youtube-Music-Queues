package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class GraphTest extends TestCase 
{
	public GraphTest()
	{
		super("GraphTest");
	}
	
	class N extends Node
	{
		String id;
		public N(String idd) { id = idd; }
		public String getId() { return id; }
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	public void testAddNode() 
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node");
		
		Set<N> p = new HashSet<N>(Arrays.asList(n));
		
		g.addNode(n);
		
		assertEquals(p,g.getAllNodes());
		
	}
	public void testGetAdjacentNodesTo()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node");
		N n1 = new N("node2");
		N n2 = new N("node3");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		e.setWeight(3.14f);
		e1.setWeight(3.14f);
		
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addEdge(n, n1, e);
		g.addEdge(n2, n1, e1);
		g.getAdjacentNodesTo(n1);
		
		Set<Node> p = g.getAdjacentNodesTo(n1);
		assertEquals((p.contains(n2)&&(p.contains(n))),true);
	}
	public void testGetNodesConnectedBy()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node3");
		N n1 = new N("node2");
		Edge e = new Edge();
		
		g.addNode(n);
		g.addNode(n1);
		g.addEdge(n, n1, e);
		e.setWeight(3.14f);
		
		Pair<Node, Node> p = g.getNodesConnectedBy(e);
		
		assertEquals((n == p.getFirst()  || n1 == p.getFirst()) && (n1 == p.getSecond() || n == p.getSecond()), true); //L'ordre en que surt es indeterminat
	
	}
	public void testAddEdge()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		
		Edge e = new Edge();
		e.setWeight(3.14f);
		g.addNode(n);
		g.addNode(n1);
		
		Set<Edge> p = new HashSet<Edge>(Arrays.asList(e));
		g.addEdge(n, n1, e);
		
		assertEquals(p,g.getAllEdges());
	}
	public void testGetAllNodes()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Set<N> p = new HashSet<N>(Arrays.asList(n,n1,n2,n3,n4));
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		assertEquals(p,g.getAllNodes());
		
	}
	
	public void testRemoveNode()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Set<N> p = new HashSet<N>(Arrays.asList(n1,n2,n3,n4));
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		g.removeNode(n);
		
		assertEquals(p,g.getAllNodes());
		
	}
	public void testGetEdge()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		
		Edge e = new Edge();
		e.setWeight(3.14f);
		
		g.addNode(n);
		g.addNode(n1);
		g.addEdge(n, n1, e);
		
		
		assertEquals(e,g.getEdge(n, n1));
	}
	public void testGetAllEdges()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		g.addEdge(n, n1, e);
		g.addEdge(n1, n2, e1);
		g.addEdge(n2, n3, e2);
		g.addEdge(n3, n4, e3);
		g.addEdge(n4, n, e4);
		
		Set<Edge> p = new HashSet<Edge>(Arrays.asList(e1,e2,e3));
		
		g.removeNode(n);
		
		assertEquals(p, g.getAllEdges());		
	}
	
	public void testRemoveEdge()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		g.addEdge(n, n1, e);
		g.addEdge(n1, n2, e1);
		g.addEdge(n2, n3, e2);
		g.addEdge(n3, n4, e3);
		g.addEdge(n4, n, e4);
		
		g.removeEdge(e4);
		Set<Edge> p = g.getAllEdges();
		assertEquals(p.contains(e4),false);
		
	}
	
	public void testRemoveAllEdges()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		g.addEdge(n, n1, e);
		g.addEdge(n1, n2, e1);
		g.addEdge(n2, n3, e2);
		g.addEdge(n3, n4, e3);
		g.addEdge(n4, n, e4);
		
		g.removeAllEdges();
		
		Edge e5 = new Edge();
		e4.setWeight(5f);
		g.addEdge(n4, n, e5);
		
		Set<Edge> p = new HashSet<Edge>(Arrays.asList(e5));
		assertEquals(p,g.getAllEdges());
	}
	
	public void testGetConnectedComponents()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		Set<N> p = new HashSet<N>(Arrays.asList(n,n1,n2));
		Set<N> p2 = new HashSet<N>(Arrays.asList(n3,n4));
		
		ArrayList< Set<N> > result = new ArrayList< Set<N> >();
		
		result.add(p);
		result.add(p2);
		
		ArrayList< Set<N> > result2 = new ArrayList< Set<N> >();
		
		result2.add(p2);
		result2.add(p);
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		g.addEdge(n, n1, e);
		g.addEdge(n1, n2, e1);
		g.addEdge(n3, n4, e3);
		
		ArrayList< Set<N> > realResult =	new ArrayList< Set<N> >();
		for(Community com : g.getConnectedComponents())
		{
			Set<N> set = new HashSet<N>();
			for(Node nodo : com.getCommunity())
			{
				set.add((N)nodo);
			}
			realResult.add(set);
		}
		
		assertTrue(result.containsAll(realResult) && realResult.containsAll(result));
	}
	
	public void testPrint()
	{
		Graph<Node> g = new Graph<Node>();
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		
		g.addEdge(n, n1, e);
		g.addEdge(n1, n2, e1);
		g.addEdge(n2, n3, e2);
		g.addEdge(n3, n4, e3);
		g.addEdge(n4, n, e4);
	}
	
	public void testEquals() 
	{
		N n = new N("node1");
		N n1 = new N("node2");
		N n2 = new N("node3");
		N n3 = new N("node4");
		N n4 = new N("node5");
		
		Edge e = new Edge();
		Edge e1 = new Edge();
		Edge e2 = new Edge();
		Edge e3 = new Edge();
		Edge e4 = new Edge();
		
		e.setWeight(3.1f);
		e1.setWeight(3.14f);
		e2.setWeight(3.141f);
		e3.setWeight(3.1415f);
		e4.setWeight(3.14159f);
		
		Graph<N> g1 = new Graph<N>();
		g1.addNode(n);
		g1.addNode(n1);
		g1.addNode(n2);
		g1.addNode(n3);
		g1.addNode(n4);
		
		g1.addEdge(n, n1, e);
		g1.addEdge(n1, n2, e1);
		g1.addEdge(n2, n3, e2);
		g1.addEdge(n3, n4, e3);
		g1.addEdge(n4, n, e4);
		
		Graph<N> g2 = new Graph<N>();
		//ELS AFEGIM EN DESORDRE
		g2.addNode(n4);
		g2.addNode(n2);
		g2.addNode(n);
		g2.addNode(n3);
		g2.addNode(n1);
 
		//ELS AFEGIM EN DESORDRE
		g2.addEdge(n3, n4, e3);
		g2.addEdge(n1, n2, e1);
		g2.addEdge(n4, n, e4);
		g2.addEdge(n, n1, e);
		g2.addEdge(n2, n3, e2);
		
		//MORE TESTS
		Graph<N> g3 = new Graph<N>();
		g3.addNode(n);
		g3.addNode(n1);
		g3.addNode(n2);
		g3.addNode(n3);
		g3.addNode(n4);
		
		//A LOT OF EDGES
		g3.addEdge(n, n1, new Edge(1.0f));
		g3.addEdge(n, n2, new Edge(1.1f));
		g3.addEdge(n, n3, new Edge(1.2f));
		g3.addEdge(n, n4, new Edge(1.3f));
		g3.addEdge(n2, n3, new Edge(1.4f));
		g3.addEdge(n3, n4, new Edge(1.5f));
		g3.addEdge(n4, n, new Edge(1.6f));
		g3.addEdge(n4, n2, e);
		
		Graph<N> g4 = new Graph<N>();
		//ELS AFEGIM EN DESORDRE
		g4.addNode(n4);
		g4.addNode(n2);
		g4.addNode(n);
		g4.addNode(n3);
		g4.addNode(n1);
 
		//ELS AFEGIM EN DESORDRE
		g4.addEdge(n, n1, new Edge(1.0f));
		g4.addEdge(n2, n3, new Edge(1.4f));
		g4.addEdge(n, n3, new Edge(1.2f));
		g4.addEdge(n, n4, new Edge(1.3f));
		g4.addEdge(n4, n, new Edge(1.6f));
		g4.addEdge(n, n2, new Edge(1.1f));
		g4.addEdge(n3, n4, new Edge(1.5f));

		//Hauria de donar que son diferents, ja que a g4 li falta un edge (e)
		assertFalse(g3.equals(g4));

		g4.addEdge(n4, n2, e);
		
		assertTrue(g3.equals(g4)); //Ara si son iguals

		g4.removeEdge(e);
		assertFalse(g3.equals(g4));
	
		g3.removeEdge(e);
		assertTrue(g4.equals(g3));
		
		
		//MOOOOOOOOORE TESTS (just nodes now)
		Graph<N> g5 = new Graph<N>();
		Graph<N> g6 = new Graph<N>();
		
		g5.addNode(n);
		g6.addNode(n);
		assertTrue(g5.equals(g6));
		
		g6.addNode(n2);
		assertFalse(g5.equals(g6));
		
		g5.addNode(n3);
		assertFalse(g5.equals(g6));
		
		g6.addNode(n3);
		assertFalse(g5.equals(g6));

		g5.addNode(n2);
		assertTrue(g5.equals(g6)); //Ara si son iguals ^^
	}
}
