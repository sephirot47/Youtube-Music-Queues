package Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Persistencia.FileManager;

public class SongManager 
{
	private static Set<Song> songs = new HashSet<Song>();
    
    public static void loadSongsFromDisk() throws Exception
    {
    	SongManager.getSongs("data/songs/songs.txt");
    }
    
	public static Set<Song> getSongs(String filepath) throws Exception
	{
		songs.clear();
		
		if(songs.size() == 0)
		{
			ArrayList<Song> songsArray = new ArrayList<Song>();
			songsArray = SongManager.getSongsFromString(filepath);
			for(Song s : songsArray) songs.add(s);
		}
		return songs;
	}
	
	public static void saveSongToDisk(String filepath, Song s) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	
    	String songLine = s.getAuthor() + ";" + s.getTitle() + ";" + s.getYear() + ";";
    	for(String style : s.getStyles()) songLine += style + ";";
    	for(int i = s.getStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
    	songLine += s.getDuration();
    	
    	if(FileManager.exists(filepath))
    	{
    		fileLines = FileManager.loadData(filepath);
    		
	    	boolean existedInFile = false;
	    	for(String line : fileLines)
	    	{
	    		if(line.startsWith(s.getAuthor() + ";" + s.getTitle()))
	    		{
	    			fileLines.set(fileLines.indexOf(line), songLine);
	    			existedInFile = true;
	    			break;
	    		}
	    	}
	    	
	    	if(existedInFile) //replace the line
	    	{
	    		FileManager.eraseData(filepath);
	    		FileManager.saveData(filepath, fileLines);
	    	}
	    	else FileManager.addData(filepath, songLine); //Append to the eof
    	}
    	else
    	{
    		fileLines.add(songLine);
    		FileManager.saveData(filepath, fileLines);
    	}
    }
    
    public static void saveSongsToDisk(String filepath, ArrayList<Song> songs) throws IOException
    {
    	ArrayList<String> fileLines = new ArrayList<String>();
    	for(Song s : songs)
    	{
        	String songLine = s.getAuthor() + ";" + s.getTitle() + ";" + s.getYear() + ";";
        	for(String style : s.getStyles()) songLine += style + ";";
        	for(int i = s.getStyles().size(); i < 3; ++i) songLine += "-;"; //Resta d'estils buits
        	songLine += s.getDuration();
        	
    		fileLines.add(songLine);
    	}
		FileManager.saveData(filepath, fileLines);
    }

    public static void removeSongFromDisk(String filepath, String Author, String Title) throws IOException
    {
    	String search = Author+";"+Title;
	
    	ArrayList<String> lines = FileManager.loadData(filepath);
    	for(int i = 0; i < lines.size(); ++i)
    	{
    		String line = lines.get(i);
    		if(line.startsWith(search)) lines.remove(i);
    	}
    	
    	FileManager.saveData(filepath, lines);
    }
    
    public static ArrayList<String> getStylesFromSong(String songAuthor, String songTitle)
    {
    	ArrayList<String> res = new ArrayList<String>();
    	Song s = getSongFromAuthorTitle(songAuthor, songTitle);
    	if(s != null) res.addAll(s.getStyles());
    	return res;
    }
    
    private static Song getSongFromAuthorTitle(String songAuthor, String songTitle)
    {
    	for(Song s : songs)
    		if(s.getAuthor().equals(songAuthor) && s.getTitle().equals(songTitle)) return s;
    	return null;
    }
    
    public static ArrayList<Pair<String, String>> getSongsAuthorsAndTitles()
	{
    	ArrayList<Pair<String, String>> res = new ArrayList<Pair<String, String>>();
    	for(Song s : songs)
    	{
    		res.add( new Pair(s.getAuthor(), s.getTitle()) );
    	}
    	
    	return res;
	}

	public static int getSongYear(String author, String title)
	{
		Song s = getSongFromAuthorTitle(author, title);
		if(s == null) return 0;
		else return s.getYear();
	}

	public static int getSongDuration(String author, String title)
	{
		Song s = getSongFromAuthorTitle(author, title);
		if(s == null) return 0;
		else return s.getDuration();
	}

    private static Song getSongFromString(String line) throws Exception
    {
    	String fields[] = line.split(";");
    	if(fields.length < 7) return null; 
    	
    	String author = fields[0];
    	String title = fields[1];
    	int year = Integer.parseInt(fields[2]);
    	
    	ArrayList<String> styles = new ArrayList<String>();
    	for(int i = 3; i <= 5; ++i) if(!fields[i].equals("-")) styles.add(fields[i]);
    	
    	int duration = Integer.parseInt(fields[6]);
    	
    	return new Song(author, title, year, styles, duration);
    }

    private static ArrayList<Song> getSongsFromString(String filepath) throws Exception
	{
		ArrayList<Song> songs = new ArrayList<Song>();
		
		ArrayList<String> fileLines = FileManager.loadData(filepath);
		for(String line : fileLines)
		{
			songs.add(SongManager.getSongFromString(line));
		}
		
		return songs;
	}
}
