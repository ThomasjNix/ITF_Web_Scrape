import java.io.*;
import java.util.*;


public class crawl {

	public static void main(String args[]) throws IOException{
		
		/*
		 * Reading in the file through a buffered reader and counting the number of 
		 * lines for future use
		 * 
		 * Also storing the links in a hash with the line number as the
		 * key
		 */
		File tennisFile = new File("TennisPlayers.txt");
		FileReader readTennisFile = new FileReader(tennisFile);
		BufferedReader tennisFileBR = new BufferedReader(readTennisFile);
		int lineCount = 0; 
		String line = null;
		HashMap<Integer, String> linkStorage = new HashMap<Integer, String>();
		while ((line = tennisFileBR.readLine()) != null){
			linkStorage.put(lineCount, line);
			//System.out.println(lineCount + "\t" + linkStorage.get(lineCount));
			lineCount++;
			
			
		}
		tennisFileBR.close();
		
		/*
		 * Here I am connecting to the individual players and 
		 * originally setting no session info, but then getting the 
		 * session info from the first request and passing it back to each
		 * subsequent request.
		 */
		CookieGenerator genC = new CookieGenerator();
		Map<String,String> session = genC.generateCookie();
		for (int lineKey : linkStorage.keySet()){
			WebGather indivPlayer = new WebGather(linkStorage.get(lineKey), session);
			System.out.println(indivPlayer.returnPlayerName());
		}
		
		
	}
}
