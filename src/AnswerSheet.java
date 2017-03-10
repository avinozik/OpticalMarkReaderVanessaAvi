import java.util.ArrayList;

/***
 * A class to represent a set of answers from a page
 */
public class AnswerSheet {
	private ArrayList<String> answers;
	private int numCorrect, numIncorrect;
	private double percentCorrect, percentIncorrect;
	
	public AnswerSheet() {
		answers = new ArrayList<String>();
		numCorrect = 0; 
		numIncorrect = 0; 
		percentCorrect = 0;
		percentIncorrect = 0;
		
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
	
	public void addCorrectAnswer() {
		numCorrect++;
	}
	public void addIncorrectAnswer() {
		numIncorrect++;
	}
	
	public void calculatePercentCorrect() {
		percentCorrect = numCorrect/100;
	}
	
	public void calculatePercentIncorrect() {
		percentIncorrect = numIncorrect/100;
	}
	
	public int getNumCorrect() {
		return numCorrect;
	}

	public int getNumIncorrect() {
		return numIncorrect;
	}

	public double getPercentCorrect() {
		return percentCorrect;
	}

	public double getPercentIncorrect() {
		return percentIncorrect;
	}

	public int getSize() {
		return answers.size();
	}
	
	public String getAnswerAtIndex(int index) {
		return answers.get(index);
	}
	
	public void printAnswers() {
		for (int i = 0; i < answers.size(); i++) {
			String answer = answers.get(i);
			System.out.println("The answer is: " + answer + ", " + " The question is : " + (i +1));
		}
		System.out.println("size of answer list: " + answers.size());
		System.out.println("finished grading");
	}
	
}
