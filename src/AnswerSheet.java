import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private ArrayList<String> answers;
	
	public AnswerSheet() {
		answers = new ArrayList<String>();
	}
	
	public void addAnswer(int val) {
		String answer = "";
		if (val==1) { answer = "a"; }
		if (val==2) { answer = "b"; }
		if (val==3) { answer = "c"; }
		if (val==4) { answer = "d"; }
		if (val==5) { answer = "e"; }
		answers.add(answer);
	}
	
	public void printAnswers() {
		for (String answer : answers) {
			System.out.println(answer + ", " + " index is : " + PRINT THE INDEX);
		}
		System.out.println("size of answer list: " + answers.size());
		System.out.println("finished grading");
	}
	
}
