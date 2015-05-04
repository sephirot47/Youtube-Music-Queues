package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import Domini.GraphTest.E;
import Domini.GraphTest.N;
import junit.framework.TestCase;

public class SongGraphTest extends TestCase 
{
	public SongGraphTest()
	{
		super("SongGraphTest");
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	
	
	class N extends Node
	{
		public String getId() { return "potato"; }
	}
	
	class E extends Edge
	{
		float weight;
		public float getWeight() { return weight;}
		public void setWeight(float weight) { this.weight = weight; }
	}
	public void testGenerateEdges()
	{
		try {
		Ponderations p = new Ponderations();
		
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("Rock");
		
		Song s = new Song("AC/DC","Thunderstruck",1990,style,292);
		Song s1 = new Song("AC/DC","Highway to Hell",1979,style,284);
		Song s2 = new Song("AC/DC","You Shook Me All Night Long",1980,style,212);
		Song s3 = new Song("AC/DC","Hells Bells",1980,style,312);
		Song s4 = new Song("AC/DC","T.N.T",1975,style,214);

		p.setAuthor(9);
		p.setDuration(8);
		p.setStyle(10);
		p.setUserAge(1);
		p.setNearbyReproductions(0);
		
		sg.addNode(s);
		sg.addNode(s1);
		//sg.AddNode(s2);
		//sg.AddNode(s3);
		//sg.AddNode(s4);
		
		Set<Song> songs = new HashSet<Song>();
		songs.add(s);
		songs.add(s1);
		ArrayList<Set<Song>> ss = new ArrayList<Set<Song>>();
		ss.add(0, songs);
		
		
		sg.generateEdges(p);
		ArrayList<Community> Cjs = sg.getConnectedComponents();
		ArrayList<Set<Song>> CjsSet = new ArrayList<Set<Song>>();

		for(Community com : Cjs)
		{
			Set<Song> set = new HashSet<Song>();
			for(Node n : com.getCommunity())
			{
				set.add((Song)n);
			}
			CjsSet.add(set);
		}
		
		sg.getAllEdges();
		
		assertEquals(CjsSet,ss);
		} catch(Exception e) {}
	}
	
	
	public void testGetNearbyReproductionsAportation()
	{
		try {
		SongGraph sg = new SongGraph();
		
		ArrayList<String> style = new ArrayList<String>();		
		style.add("flamenquillo del weno");
		
		Song s = new Song("victor","cuando sarpa el hamor",2015,style,180);
		style.remove(0);
		style.add("ioroYOLO");
		Song s1 = new Song("jfons","tramboliko",1867,style,45);
		
		assertEquals(sg.getNearbyReproductionsAportation(s, s1),0.5f);
		} catch(Exception e) {}
	}
}