/*
 * Written by Thomas Nix
 * For the purposes of the T1 ITF Tennis project
 * In ITCS-3162 Section 001 at UNC Charlotte
 * 
 * Project partners: Jesus Garcia and Seth Morris
 */

public class NormalizeGrade {
	public String newGrade;
	public NormalizeGrade(String grade){
		switch (grade){
		case ("Grade 5"):
			newGrade = "5";
			break;
		case ("Grade 4"):
			newGrade = "4";
			break;
		case ("Grade 3"):
			newGrade = "3";
			break;
		case ("Grade 2"):
			newGrade = "2";
			break;
		case ("Grade 1"):
			newGrade = "1";
			break;
		case ("Grade A"):
			newGrade = "A";
			break;
		case ("Grade B"):
			newGrade = "B";
			break;
		case ("Grade C"):
			newGrade = "C";
			break;
		default:
			if (grade.matches("\\Grade A+")){
				newGrade="A";
			}else if (grade.matches("\\Grade B+")){
				newGrade="B";					
			}else if (grade.matches("\\Grade C+")){
				newGrade="C";
			}
			break;
		}
		
	}
}
