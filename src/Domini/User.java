package Domini;

import java.util.ArrayList;
import java.util.List;

public class User 
{
	private static String[] forbiddenCharacters = { "\\", "/", ".", ";", ":", "\"", "*", "?",
													"<", ">", "|", "*"};
	
    String name;
    int age;
    ArrayList<Reproduction> reproductions;

	public User()
    {
    	reproductions = new ArrayList<Reproduction>();
    }
    
    public User(String name, int age, ArrayList<Reproduction> r) throws Exception
    {
    	CheckUsername(name);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
        reproductions.addAll(r);
    }
    
    public User(String name, int age) throws Exception
    {
    	CheckUsername(name);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
    }
    
    public void AddReproduction(Reproduction r)
    {
    	reproductions.add(r);
    }
    
    public void AddReproductions(List<Reproduction> r)
    {
    	reproductions.addAll(r);
    }
    
    public void SetAge(int a) { age = a; }
    public void SetName(String n) throws Exception
    { 
    	CheckUsername(name);
    	name = n; 
    } 
	public void SetReproductions(ArrayList<Reproduction> reproductions) 
	{
		this.reproductions = reproductions;
	}
	
    public int GetAge() { return age; }
    public String GetName() { return name; }
    public ArrayList<Reproduction> GetReproductions() 
    {
		return reproductions;
	}
    
    public void Print()
    {
    	System.out.println("--- User ----------");
    	System.out.println("Name: " + name);
    	System.out.println("Age: " + age);
    	System.out.println("Reproductions of " + name + ":");
		System.out.println("_____");
    	for(Reproduction r : reproductions)
    	{
    		r.Print();
    		System.out.println("_____");
    	}
    	System.out.println("------");
    	System.out.println("");
    }
    
    private void CheckUsername(String name) throws Exception
    {
    	for(String c : forbiddenCharacters)
		if(name.contains(c)) 
		{
			String characters = ""; 
			for(String c2 : forbiddenCharacters) 
				characters += "\"" + c2 + "\",  ";
			throw new Exception("A user can't contain any of these characters: " + characters);
		}
    }
    
    public boolean equals(Object obj)
    {
    	User u = (User) obj;
    	return GetName().equals(u.GetName()) && GetAge() == u.GetAge() && GetReproductions().equals(u.GetReproductions());
    }
}
