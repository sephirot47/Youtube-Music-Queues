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
    	checkUsername(name);
    	checkAge(age);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
        reproductions.addAll(r);
    }
    
    public User(String name, int age) throws Exception
    {
    	checkUsername(name);
    	checkAge(age);
        this.name = name;
        this.age = age;
        reproductions = new ArrayList<Reproduction>();
    }
    
    public void addReproduction(Reproduction r)
    {
    	reproductions.add(r);
    }
    
    public void addReproductions(List<Reproduction> r)
    {
    	reproductions.addAll(r);
    }
    
    public void setAge(int a) { age = a; }
    public void setName(String n) throws Exception
    { 
    	checkUsername(name);
    	name = n; 
    } 
    
	public void setReproductions(ArrayList<Reproduction> reproductions) 
	{
		this.reproductions = reproductions;
	}
	
    public int getAge() { return age; }
    public String getName() { return name; }
    public ArrayList<Reproduction> getReproductions() 
    {
		return reproductions;
	}
    
    
    private void checkUsername(String name) throws Exception
    {
    	if(name.equals(""))
    	{
    		throw new Exception("Un nom d'usuari no pot ser buit");
    	}
    	
    	for(String c : forbiddenCharacters)
		if(name.contains(c)) 
		{
			String characters = ""; 
			for(String c2 : forbiddenCharacters) 
				characters += "\"" + c2 + "\",  ";
			throw new Exception("Un nom d'usuari nomes pot contenir caracters alfanumerics");
		}
    }
    
    private void checkAge(int age) throws Exception
    {
        if(age <= 0)
        {
        	throw new Exception("La edat d'un usuari ha de ser > 0");
        }
    }
    
    public boolean equals(Object obj)
    {
    	User u = (User) obj;
    	return getName().equals(u.getName()) && getAge() == u.getAge() && getReproductions().equals(u.getReproductions());
    }
}
