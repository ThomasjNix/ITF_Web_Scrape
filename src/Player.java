/*
 * Written by Thomas Nix
 * For the purposes of the T1 ITF Tennis project
 * In ITCS-3162 Section 001 at UNC Charlotte
 * 
 * Project partners: Jesus Garcia and Seth Morris
 */

import java.util.HashMap;

public class Player {
	
	//Create variables to hold player information
	public String playerName;
	public int playerYearOfBirth;
	HashMap<String,String[]> scorecard = new HashMap<String, String[]>();
	
	//Constructor to set player name and year of birth
	public Player(String name, int yearOfBirth){
		playerName = name;
		playerYearOfBirth = yearOfBirth;
	}
	
	//Updates the player scorecard
	//(Necessary for looped update in crawl.java)
	public void updateScorecard(HashMap<String,String[]> inVals){
		scorecard = inVals;
	}
	
	//Replaces missing values with ? and X
	//Necessary for data mining, players without wins 
	//In any grade will have ? marked for that grade.
	public void fillInScoreCard(){
		String[] allGradeValues = {"5","4","3","2","1","A","B","C"};
		
		for (String grade : allGradeValues){
			if (!scorecard.containsKey(grade)){
				scorecard.put(grade, new String[]{"?","X"});
			}
		}
	}
	
	//Creates the printable string once all data has been gathered.
	public String createPrintableInstance(){
		fillInScoreCard();
		String return_string = "";
		return_string += playerName + ",";
		return_string += playerYearOfBirth + ",";
		for (String grade : scorecard.keySet()){
			return_string += scorecard.get(grade)[0]+",";
		}

		return return_string;
	}
}
