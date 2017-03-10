import java.util.ArrayList;

import processing.core.PImage;

public class Main {
	public static final String PDF_PATH = "/omrtest.pdf";
	public static OpticalMarkReader markReader = new OpticalMarkReader();

	public static void main(String[] args) {
		System.out.println("Welcome!  I will now auto-score your pdf!");
		System.out.println("Loading file..." + PDF_PATH);
		ArrayList<PImage> images = PDFHelper.getPImagesFromPdf(PDF_PATH);

		System.out.println("Scoring all pages...");
		scoreAllPages(images);

		System.out.println("Complete!");
		// Optional: add a saveResults() method to save answers to a csv file
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE: YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D
	 * LIKE
	 * 
	 * @param images
	 *            List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();
		
		String dataOne = "";
		String dataTwo = "";
		double[] arr = new double[100];//inside each index, representing 1 question, is the # of incorrect answers
		double numOfStudents = images.size();



		// Score the first page as the key
		AnswerSheet key = markReader.processPageImage(images.get(0));
		key.printAnswers();

		for (int i = 0; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.processPageImage(image);

			for (int index = 0; index < answers.getSize(); index++) {
				if (key.getAnswerAtIndex(index).equals(answers.getAnswerAtIndex(index))) {
					answers.addCorrectAnswer();
				} else {
					answers.addIncorrectAnswer();
					arr[index]++;
				}
			}
			// calculate statistics based on data collected
			answers.calculatePercentCorrect();
			answers.calculatePercentIncorrect();
			

			// do csv stuff

			dataOne += answers.getNumCorrect() + "," + answers.getNumIncorrect() + "," + answers.getPercentCorrect()
					+ "," + answers.getPercentIncorrect();

			dataOne += System.lineSeparator();// new page = new line

			for (int x = 0; x < 100; x++) {
				dataTwo += arr[x] + "," + (arr[x]/numOfStudents);
			}

		}
		String filepath = "E:/newfile";
		FileIO.writeDataToFile(filepath, dataOne);
		
		//String secondFilePath = "C:/Users/Avi/Documents/OpticalMarkReaderCSV/secondFile";
		//FileIO.writeDataToFile(secondFilePath, dataTwo);
		
		String s = FileIO.readFileAsString(filepath);
		System.out.print(s);
		
	}
}