import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawl {

	public static void main(String args[]) throws IOException{
		/*
		 * Instantiate document object instance and loop through folder containing HTML pages
		 */
		
		Document playerProfilePage;
		final File readDirectory = new File("Player_DOMs");
		Player[] playerArray = new Player[readDirectory.listFiles().length];
		int playerCounter = 0;
		for (File single_file : readDirectory.listFiles()){
			playerProfilePage = Jsoup.parse(single_file, "UTF-8");
			
			/*
			 * Getting the player information
			 */
			String playerName = playerProfilePage.select("#PlayerDiv > div.pn-in.clfx > div > div.playerDetails.fl > ul > li:nth-child(1) > strong").text();
			String fullDOB = playerProfilePage.select("#liDOB > strong").text();
			String yearDOB = fullDOB.substring(fullDOB.length() - 4, fullDOB.length());
			int playerYearOfBirth = Integer.parseInt(yearDOB);
			if (playerYearOfBirth == 1999 || playerYearOfBirth == 2000 || playerYearOfBirth == 2001){
				System.out.println("Player name: " + playerName + "\t Player birth year: " + playerYearOfBirth + "\n====================");
				/*
				 * Here I gather all the information from the players table data.
				 * I break it down into the specific tables that are for singles only,
				 * then I parse each row that does not contain missing values 
				 * and break the table data for the row into the important pieces
				 * 
				 * To filter out what contains missing values, I saw every missing value table row
				 * contained the content "BYE" in the td.td-120 table data, so I excluded all rows that contained
				 * the phrase "BYE".
				 */
	
				int numGamesTotal = playerProfilePage.select("li.pn-Gradient").size();
				Elements grade = null;
				String highest_level = "";
				String highest_level_win_loss = "";
				HashMap<Integer, String> playerData = new HashMap<Integer, String>();
				for (Element currentElm : playerProfilePage.select("li.pn-Gradient")){
					for (Element matchData : currentElm.select("ul:nth-of-type(2)")){
						if (matchData.select("li").text().charAt(0) == 'S'){
							grade=matchData.select("li:nth-of-type(2)");
							
							for (Element tableRow : currentElm.select("ul:nth-of-type(2) + table tbody tr:nth-of-type(n+3)")){
								String round = null;
								String round_score = null;
								
								if (!(tableRow.select("td").text().replaceAll(" ", "").contains("BYE")) &&
										!((tableRow.select("td.td-20").text().length() == 0) || (tableRow.select("td.td-30").text().length() == 0))){
									round = tableRow.select("td.td-20").text();
									round_score = tableRow.select("td.td-30").text();
									System.out.println("Grade: " + grade.text() + "\tRound:" + round + "\tRound Score: " + round_score);
									
									/*
									 * Implement highest level / win
									 */
								}
								
							}	
						}
					}
				}
				System.out.println("====================\n\n");
			}
		}
		

		
	}
}




