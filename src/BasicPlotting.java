import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

public class BasicPlotting {
	public static void main(String[] args) {
		String manyNames = "time (ms),  accel x,  accel y,  accel z,  gryo x,  gyro y,  gyro z,  linear accel x,  linear accel y,  linear accel z,  orientation x,  orientation y,  orientation z";

		String[] names = manyNames.split(",");
		
		CSVData data = new CSVData("E:\\NoiseSmoothing-master\\data\\WalkingSampleData-out", names , 0 );
		Plot2DPanel plot = new Plot2DPanel();
		
		double[] sample = data.getColumn(1);//x acceleration
		
		
		
		// add a line plot to the PlotPanel
		plot.addLinePlot("Random signal", sample);
		
		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("Results");
		frame.setSize(800, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}

	
}
