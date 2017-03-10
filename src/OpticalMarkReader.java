import processing.core.PImage;

/***
 * Class to perform image processing for optical mark reading
 * 
 */
public class OpticalMarkReader {

	/***
	 * Method to do optical mark reading on page image. Return an AnswerSheet
	 * object representing the page answers.
	 * 
	 * @param image
	 * @return
	 */
	public AnswerSheet processPageImage(PImage image) {
		// start at a certain x, y
		// loop through each questions' answers individually, determine answer
		// compare the answer to the correct answer, do whatever supposed to do
		// save the students answers into an "AnswerSheet"
		image.filter(PImage.GRAY);
		AnswerSheet answerSheet = new AnswerSheet();
		for (int x = 120; x < (285 * 4); x += 285) {
			for (int y = 460; y < 120 + (25 * 38); y += 38) {//[x,y] is the top left corner of each box
				int answer = determineBubble(x, y, 185, 38, 5, image);
				answerSheet.addAnswer(answer);
			}
		}
		return answerSheet;
	}

	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		int index = row * image.width + col;
		//System.out.println(image.pixels[index]);//val of pixel
		return image.pixels[index] & 255;
	}

	
	/*
	 * returns answer for 1 question at r,c for a box width,height for numbubbles
	 */
	public int determineBubble(int r, int c, int width, int height, int numBubbles, PImage image) {
		int boxWidth = width / numBubbles, max = Integer.MAX_VALUE, maxBubble = 0;
		for (int i = 0; i < numBubbles; i++) {
			int value = getSumValue(r, c + i * boxWidth, width, height, image);
			if (value < max) {
				max = value;
				maxBubble = i;
			}
		}
		return maxBubble;
	}

	

	public int getSumValue(int r, int c, int width, int height, PImage image) {
		int sum = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sum += getPixelAt(r + i, c + j, image);
			}
		}
		return sum;
	}
}
