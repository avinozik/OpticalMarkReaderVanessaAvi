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
		// Optional:  add a saveResults() method to save answers to a csv file
	}

	/***
	 * Score all pages in list, using index 0 as the key.
	 * 
	 * NOTE:  YOU MAY CHANGE THE RETURN TYPE SO YOU RETURN SOMETHING IF YOU'D LIKE
	 * 
	 * @param images List of images corresponding to each page of original pdf
	 */
	private static void scoreAllPages(ArrayList<PImage> images) {
		ArrayList<AnswerSheet> scoredSheets = new ArrayList<AnswerSheet>();

		// Score the first page as the key
		AnswerSheet key = markReader.processPageImage(images.get(0), 120, 460, 285*4, 120 + (25 * 38));

		for (int i = 1; i < images.size(); i++) {
			PImage image = images.get(i);

			AnswerSheet answers = markReader.processPageImage(image, 125, 464, 30, 185);


			for (int index = 0; index < answers.getSize(); index++) {
				if (key.getAnswerAtIndex(index).equals(answers.getAnswerAtIndex(index))) {
					answers.addCorrectAnswer();
				} else {
					answers.addIncorrectAnswer();
				}
			}
			answers.calculatePercentCorrect();
			answers.calculatePercentIncorrect();
			
		}
		
		key.printAnswers();
	}
}