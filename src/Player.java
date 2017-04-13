
public class Player {
	
	public String playerName;
	public int playerYearOfBirth;
	public String[][] scores;
	public Player(String name, int yearOfBirth){
		playerName = name;
		playerYearOfBirth = yearOfBirth;
	}
	
	public void setSinglesScores(String[][] inScores){
		scores = inScores;
	}
}
