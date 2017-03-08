import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class VisualTester extends PApplet {
	ArrayList<PImage> images;
	PImage current_image;
	int currentImageIndex = 0;
	int w = 1200;
	int h = 900;

	public void setup() {
		size(w, h);
		images = PDFHelper.getPImagesFromPdf("/omrtest.pdf");
	}

	public void draw() {
		background(255);
		if (images.size() > 0) {
			current_image = images.get(currentImageIndex);
			image(current_image, 0, 0); // display image i
			// rect(120, 460, 185, 38);
			// rect(120, 498, 185, 38);
			text(mouseX + " " + mouseY, 30, 30);
			for (int x = 120; x < (285 * 4); x += 285) {
				for (int y = 460; y < 120 + (25 * 38); y += 38) {
					rect(x, y, 185, 38);
					noFill();
				}
			}

		}
	}

	public void mouseReleased() {
		currentImageIndex = (currentImageIndex + 1) % images.size(); // increment
																		// current
																		// image
	}
}
