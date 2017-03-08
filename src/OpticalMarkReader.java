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
	public AnswerSheet processPageImage(PImage image, int startX, int startY, int width, int height) {
		// start at a certain x, y
		// loop through each questions' answers individually, determine answer
		// compare the answer to the correct answer, do whatever supposed to do
		// save the students answers into an "AnswerSheet"
		image.filter(PImage.GRAY);
		AnswerSheet answerSheet = new AnswerSheet();
		for (int col = startX; col < width; col += 300) {
			for (int row = startY-9; row < height; row += 48) {
				int answer = determineBubble(row, col, 185, 48, 5, image);
				answerSheet.addAnswer(answer);
			}
		}
		return answerSheet;
	}

	public int getPixelAt(int row, int col, PImage image) {
		image.loadPixels();
		int index = row * image.width + col;
		return image.pixels[index] & 255;
	}

	public int determineBubble(int r, int c, int width, int height, int numBubbles, PImage image) {
		int boxWidth = width / numBubbles, max = 255, maxBubble = 0;
		for (int i = 0; i < numBubbles; i++) {
			int value = getSumValue(r, c + i * boxWidth, width, height, image);
			if (value < max) {
				max = value;
				maxBubble = i;
			}
		}
		return maxBubble;
	}

	public int[][] getAnswerRegion(int r, int c, int[][] pixels) {
		int[][] output = new int[30][185];
		int outputRow = 0;
		int outputCol = 0;
		for (int row = r; row < r + 30; row++) {
			for (int col = c; col < c + 185; c++) {
				output[outputRow][outputCol] = pixels[row][col];
				outputCol++;
				if (outputCol == output[0].length)
					outputCol = 0;
			}
			outputRow++;
			if (outputRow == output.length)
				outputRow = 0;
		}
		return output;
	}

	public int getSumValue(int r, int c, int width, int height, PImage image) {
		int sum = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				sum = +getPixelAt(r + i, c + j, image);
			}
		}
		return sum;
	}
}
