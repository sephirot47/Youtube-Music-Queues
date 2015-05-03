package Persistencia;

////IMPORTS /////////////////////////////////////
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import Domini.Pair;
import Domini.Reproduction;
import Domini.Solution;
import Domini.Song;
import Domini.SongGraph;
import Domini.SongRelation;
import Domini.User;

public class FileManager
{
	//// METODES ///////////////////////////////////

    //path apunta a un .txt a la carpeta del projecte
    // es retorna una llista d'strings, cada element es una linia del fitxer de text

    public static ArrayList<String> LoadData(String path) throws IOException
    {
        ArrayList<String> list = new ArrayList<String>(); //Creem array
        
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        FileReader fReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fReader); //Creem un buffer on desar cada l
        
        String l = "";
        while ((l = reader.readLine()) != null){
            list.add(l); //Afegim a la llista
        }
        
        reader.close();
		
        return list;   
    }
    
    // path es el nom.txt del fitxer a guardar
    // Es sobreescriu el fitxer amb el contingut de la llista
    public static void SaveData(String path, ArrayList<String> list) throws IOException
    {
        Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        
        File file = new File(path);
        if(!file.exists()) file.getParentFile().mkdirs(); //Not sure al 100% que fa eso, pero pels puestos posava que era per crear el susodicho fitxer
        
        FileWriter writer = new FileWriter(path);
        
        for (int i = 0; i < list.size(); ++i)
        {
            writer.write(list.get(i));
            writer.write("\r\n");
        }
        writer.close();    
    }
    
    public static void SaveEntradaSolution(String filedir, SongGraph entrada) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	Set<Song> songs = entrada.GetAllNodes();
    	ArrayList<Song> songsArray = new ArrayList<Song>();
    	for(Song s : songs) //Nodes pels que esta format el graph
    	{
    		String line = "(" + s.GetAuthor() + "," + s.GetTitle() + ")";
    		lines.add(line);
    		
    		songsArray.add(s);
    	}
    	
    	
    	Set<SongRelation> edges = entrada.GetAllEdges();
    	for(SongRelation e : edges)
    	{
    		Pair<Song, Song> songSong = entrada.GetNodesConnectedBy(e);
    		Song s1 = songSong.GetFirst(), s2 = songSong.GetSecond();
    		
    		String line = songsArray.indexOf(s1) + ";" + songsArray.indexOf(s2) + ";" + e.GetWeight();
    		lines.add(line);
    	}
    	
    	SaveData(filedir + "entrada.txt",  lines);
    	
    	//Se guardara, por ejemplo:
    	/*
    	 * (victor, cuando sarpa el hamor)
    	 * (jfons, tramboliko)
    	 * (aina, mesigualno?)
    	 * 0;1;0.5    //del 0 al 1, con peso 0.5
    	 * 1;2;1.3	  //del 1 al 2, con peso 1.3
    	 * (hi ha un edge de la canco del victor al jfons, i del jfons a l'aina)
    	 */
    }

    public static void SaveCommunitiesSolution(String filedir, ArrayList<Set<Song>> songCommunities) throws IOException
    {
    	ArrayList<String> lines = new ArrayList<String>();
    	
    	int i = 0;
    	for(Set<Song> songs : songCommunities)
    	{
    		lines.add( String.valueOf(i++) );
	    	for(Song s : songs)
	    	{
	    		String line = "(" + s.GetAuthor() + ", " + s.GetTitle() + ")";
	    		lines.add(line);
	    	}
    	}
		SaveData(filedir + "comunitats.txt",  lines);
    }
    
    //path es un path a un fitxer existent
    //path deixa d'existir
    public static boolean EraseData(String path) 
    {
    	  Path fileToLoad = Paths.get(path); //generem un path amb l'string
          fileToLoad = fileToLoad.toAbsolutePath();
          path = fileToLoad.toString();
          File file = new File(path);
          return file.delete();
    }
    
    //path es un path no nul
    //retorna cert si path existeix
    public static boolean Exists(String path)
    {
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        File file = new File(path);
        file.getParentFile().mkdirs();
        if(file.exists()) return true; return false;
    	
    }
    
    //path es un cami a un fitxer existent, newLine conte una linia de text
    //newLine queda concatenat a la ultima linia del fitxer de path
    public static void AddData(String path, String newLine) throws IOException
    {
    	Path fileToLoad = Paths.get(path); //generem un path amb l'string
        fileToLoad = fileToLoad.toAbsolutePath();
        path = fileToLoad.toString();
        FileWriter w = new FileWriter(path, true);
        w.write(newLine);
        w.write("\r\n");
        w.close();
    	
    }    
    
    //SAVE DATA FUNCTIONS (User, Song, Reproductions, etc)
    
    public static void SaveUser(String filepath, User u) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	String songLine = u.GetName() + ";" + u.GetAge();
    	
    	if(Exists(filepath))
    	{
    		fileLines = LoadData(filepath);
    		
	    	boolean existedInFile = false;
	    	for(String line : fileLines)
	    	{
	    		if(line.startsWith(u.GetName()))
	    		{
	    			fileLines.set(fileLines.indexOf(line), songLine);
	    			existedInFile = true;
	    			break;
	    		}
	    	}
	    	
	    	if(existedInFile)
	    	{
	    		EraseData(filepath);
	    		SaveData(filepath, fileLines);
	    	}
	    	else AddData(filepath, songLine); //Append to the eof
    	}
    	else
    	{
    		fileLines.add(songLine);
    		SaveData(filepath, fileLines);
    	}
    }
    
    public static void SaveUsers(String filepath, ArrayList<User> songs) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	for(User u : songs)
    	{
    		String songLine = u.GetName() + ";" + u.GetAge();
    		fileLines.add(songLine);
    	}
		SaveData(filepath, fileLines);
    }
    
    public static void SaveSong(String filepath, Song s) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	
    	String songLine = s.GetAuthor() + ";" + s.GetTitle() + ";" + s.GetYear() + ";";
    	for(String style : s.GetStyles()) songLine += style + ";";
    	for(int i = s.GetStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
    	songLine += s.GetDuration();
    	
    	if(Exists(filepath))
    	{
    		fileLines = LoadData(filepath);
    		
	    	boolean existedInFile = false;
	    	for(String line : fileLines)
	    	{
	    		if(line.startsWith(s.GetAuthor() + ";" + s.GetTitle()))
	    		{
	    			fileLines.set(fileLines.indexOf(line), songLine);
	    			existedInFile = true;
	    			break;
	    		}
	    	}
	    	
	    	if(existedInFile) //replace the line
	    	{
	    		EraseData(filepath);
	    		SaveData(filepath, fileLines);
	    	}
	    	else AddData(filepath, songLine); //Append to the eof
    	}
    	else
    	{
    		fileLines.add(songLine);
    		SaveData(filepath, fileLines);
    	}
    }
    
    public static void SaveSongs(String filepath, ArrayList<Song> songs) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	for(Song s : songs)
    	{
        	String songLine = s.GetAuthor() + ";" + s.GetTitle() + ";" + s.GetYear() + ";";
        	for(String style : s.GetStyles()) songLine += style + ";";
        	for(int i = s.GetStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
        	songLine += s.GetDuration();
        	
    		fileLines.add(songLine);
    	}
		SaveData(filepath, fileLines);
    }
    
    
    public static void SaveReproduction(String filepath, Reproduction r) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	
    	String reproductionLine = r.GetSongAuthor() + ";" + r.GetSongTitle() + ";" + r.GetTime();
    	
    	if(Exists(filepath))
    	{
	    	AddData(filepath, reproductionLine); //Append to the eof
    	}
    	else
    	{
    		fileLines.add(reproductionLine);
    		SaveData(filepath, fileLines);
    	}
    }
    
    public static void SaveReproductions(String filepath, ArrayList<Reproduction> reproductions) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	for(Reproduction r : reproductions)
    	{
    		String reproductionLine = r.GetSongAuthor() + ";" + r.GetSongTitle() + ";" + r.GetTime();
    		fileLines.add(reproductionLine);
    	}
		SaveData(filepath, fileLines);
    }
    
    //REMOVE AND ERASE
    
    public static void RemoveUser(String filepath, String username) throws IOException
    {
    	String search = username;
	
    	ArrayList<String> lines = LoadData(filepath);
    	for(int i = 0; i < lines.size(); ++i)
    	{
    		String line = lines.get(i);
    		if(line.startsWith(search)) lines.remove(i);
    	}
    	
    	SaveData(filepath, lines);
    }
    
    public static void RemoveSong(String filepath, String Author, String Title) throws IOException
    {
    	String search = Author+";"+Title;
	
    	ArrayList<String> lines = LoadData(filepath);
    	for(int i = 0; i < lines.size(); ++i)
    	{
    		String line = lines.get(i);
    		if(line.startsWith(search)) lines.remove(i);
    	}
    	
    	SaveData(filepath, lines);
    }
    
    
    public static void RemoveReproductions(String filedir, String username, long time) throws IOException
    {
    	String search = String.valueOf(time);
	
		File tempFile = new File(filedir + username + "Reproductions2.txt");
    	BufferedReader br;
		br = new BufferedReader(new FileReader(filedir + username + "Reproductions.txt"));
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
        
        String line = "";
        while ((line = br.readLine()) != null) {
        	
            if (!line.contains(search)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();
        
        File repros = new File(filedir + username + "Reproductions.txt");
        repros.delete();
   
        tempFile.renameTo(repros);
    }

}