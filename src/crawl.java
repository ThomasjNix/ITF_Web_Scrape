/*
 * Written by Thomas Nix
 * For the purposes of the T1 ITF Tennis project
 * In ITCS-3162 Section 001 at UNC Charlotte
 * 
 * Project partners: Jesus Garcia and Seth Morris
 */

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class crawl {

	/*
	 * Main driver class to load in data, organize it, distinguish it, and export it to a file.
	 */
	public static void main(String args[]) throws IOException {
		//Creating file writer for later 
		//and writing the header information
		File stats_file = new File("PlayerStats.txt");
		FileWriter write_file = new FileWriter(stats_file);
		write_file.write("player_name,player_yob,grade_1,grade_a,grade_2,grade_b,grade_3,grade_c,grade_4,grade_5\n");
		
		// Reading in all files from the directory
		Document playerProfilePage;
		final File readDirectory = new File("Player_DOMs");
		Player[] playerArray = new Player[readDirectory.listFiles().length];
		int playerCounter = 0;
		
		// Looping through each file and gathering player data
		for (File single_file : readDirectory.listFiles()) {
			playerProfilePage = Jsoup.parse(single_file, "UTF-8");
			String playerName = playerProfilePage
					.select("#PlayerDiv > div.pn-in.clfx > div > div.playerDetails.fl > ul > li:nth-child(1) > strong")
					.text();
			String fullDOB = playerProfilePage.select("#liDOB > strong").text();
			String yearDOB = fullDOB.substring(fullDOB.length() - 4, fullDOB.length());
			int playerYearOfBirth = Integer.parseInt(yearDOB);
			if (playerYearOfBirth == 1999 || playerYearOfBirth == 2000 || playerYearOfBirth == 2001) {
				
				// Creating the new player
				playerArray[playerCounter] = new Player(playerName, playerYearOfBirth);
				String grade = null;
				HashMap<String, String[]> playerScoreCard = new HashMap<String, String[]>();
				
				// Looping through each match and gathering data on it
				for (Element currentElm : playerProfilePage.select("li.pn-Gradient")) {
					for (Element matchData : currentElm.select("ul:nth-of-type(2)")) {
						if (matchData.select("li").text().charAt(0) == 'S') {
							grade = matchData.select("li:nth-of-type(2)").text();

							// Normalizing grade values
							NormalizeGrade ng = new NormalizeGrade(grade);
							grade = ng.newGrade;

							// Getting specific rounds and scores
							for (Element tableRow : currentElm
									.select("ul:nth-of-type(2) + table tbody tr:nth-of-type(n+3)")) {
								String round = null;
								String round_score = null;

								// Separating each round into identifiable parts
								if (!(tableRow.select("td").text().replaceAll(" ", "").contains("BYE"))
										&& !((tableRow.select("td.td-20").text().length() == 0)
												|| (tableRow.select("td.td-30").text().length() == 0))) {
									round = tableRow.select("td.td-20").text();
									round_score = tableRow.select("td.td-30").text();


									// Updating the player scorecard
									/*
									 * For each grade
									 *  --update the highest round played ONLY if: 
									 * ===1: The round is the same or higher AND 
									 * ===2: The higher round is a WIN
									 * -----
									 * (ex1: old: Grade 3, round 64, W new: Grade 3, round 32, W) -> take new
									 * (ex2: old: Grade 3, round 64, L new: Grade 3, round 64, W) -> take new
									 * (ex3: old: Grade 3, round 32, W new: Grade 3, round 32, L) -> leave old
									 * (ex4: old: Grade 3, round 64, L new: Grade 3, round 32, L) -> leave old
									 */

									double temporaryRoundScore = 99; // for use with non-numeric rounds
									double temporaryRoundScore2 = 99; // for use with non-numeric rounds
										
									if (round_score.contains("W") && (!round.contains("Q1") && !round.contains("Q2")
											&& !round.contains("Q3"))) {
										if (playerScoreCard.get(grade) == null) {
											playerScoreCard.put(grade, new String[] { round, round_score });
										} else {
											try {
												int previousRoundScore = Integer
														.parseInt(playerScoreCard.get(grade)[0]);
												try {

													int currentRoundScore = Integer.parseInt(round);
													if (currentRoundScore <= previousRoundScore) {
														playerScoreCard.put(grade, new String[] { round, round_score });
													}
												} catch (Exception e) {

													if (round == "QF") {
														temporaryRoundScore = 3;
													} else if (round == "SF") {
														temporaryRoundScore = 2;
													} else if (round == "FR") {
														temporaryRoundScore = 1;
													} else {
														// Throw away invalid
														// data that doesn't
														// match
														// 64/32/16/QF/SF/FR
														temporaryRoundScore = 99;
													}

													if (temporaryRoundScore <= previousRoundScore) {
														playerScoreCard.put(grade, new String[] { round, round_score });
													}
												}
											} catch (Exception e) {
												if (playerScoreCard.get(grade)[0] == "QF") {
													temporaryRoundScore = 3;
												} else if (playerScoreCard.get(grade)[0] == "SF") {
													temporaryRoundScore = 2;
												} else if (playerScoreCard.get(grade)[0] == "FR") {
													temporaryRoundScore = 1;
												} else {
													// Throw away invalid data
													// that doesn't match
													// 64/32/16/QF/SF/FR
													temporaryRoundScore = 4;
												}
												
												//No try/catch block necessary here since we know that the previous round is non-numeric 
												//and we only care about the current round if it is also non-numeric
												if (round == "QF") {
													temporaryRoundScore2 = 3;
												} else if (round == "SF") {
													temporaryRoundScore2 = 2;
												} else if (round == "FR") {
													temporaryRoundScore2 = 1;
												} else {
													// Throw away invalid
													// data that doesn't
													// match
													// 64/32/16/QF/SF/FR
													temporaryRoundScore2 = 5;
												}
												if (temporaryRoundScore2 <= temporaryRoundScore) {
													playerScoreCard.put(grade, new String[] { round, round_score });
												}	
											}
										}

									}
									playerArray[playerCounter].updateScorecard(playerScoreCard);
								}
							}
						}
					}
				}
			}
			
			//Sets up the printable string and adds a new line so the file writer can write by line
			String line_to_write = playerArray[playerCounter].createPrintableInstance().substring(0, playerArray[playerCounter].createPrintableInstance().length()-1);
			line_to_write += "\n";
			write_file.write(line_to_write);
			playerCounter++;
		}
		//Closing the file writer once all files have been processed.
		write_file.close();

	}
}
