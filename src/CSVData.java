import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***
 * Class to read and write comma numerical CSV files and allow easy access
 *
 * @author AviNozik
 *
 */
public class CSVData {
  private double[][] data;
  private String[] columnNames;
  private int numRows;
  private String filePathToCSV;

  public static CSVData readCSVfile(String filename, int numLinestToIgnore,
      String[] columnNames) {
    String file = readFileAsString(filename);

    CSVData output = new CSVData(filename, columnNames, numLinestToIgnore);
    output.columnNames = columnNames;

    for (int row = 0; row < file.length(); row++) {
      String[] hold = file.split(",");

    }

    return output;
  }

  public CSVData(String filepath, String[] columnNames, int startRow) {
    this.filePathToCSV = filepath;

    String dataString = readFileAsString(filepath);
    String[] lines = dataString.split("\n");

    // number of data points
    int n = lines.length - startRow;
    this.numRows = n;
    int numColumns = columnNames.length;

    // create storage for column names
    this.columnNames = columnNames;

    // create storage for data
    this.data = new double[n][numColumns];
    for (int i = 0; i < lines.length - startRow; i++) {
      String line = lines[startRow + i];
      String[] coords = line.split(",");
      for (int j = 0; j < numColumns; j++) {
        if (coords[j].endsWith("#"))
          coords[j] = coords[j].substring(0, coords[j].length() - 1);
        double val = Double.parseDouble(coords[j]);
        data[i][j] = val;
      }
    }
  }

  private static String readFileAsString(String filepath) {
    StringBuilder output = new StringBuilder();
    try (Scanner scanner = new Scanner(new File(filepath))) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        output.append(line + System.getProperty("line.separator"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return output.toString();
  }

  /***
   * Returns a new CSVData file ignoring the lines at the top. It uses the first
   * row as the column name, All other data is stared as doubles.
   *
   * @param filename
   *          the file to read
   * @param numLinesToIgnore
   *          number of lines at the top to ignore
   * @return a CSVData object for that file.
   */
  public static CSVData readCSVfile(String filename, int numLinesToIgnore) {
    return null;
  }

  /***
   * gets a row
   *
   * @param rowIndex
   *          the index of a row
   * @return the row
   */
  public double[] getRow(int rowIndex) {
    double[] output = new double[data[0].length];// amount of columns
    for (int col = 0; col < data.length; col++) {
      output[col] = data[rowIndex][col];
    }
    return output;
  }

  /***
   * gets a column
   *
   * @param columnIndex
   *          the index of a column
   * @return the column
   */
  public double[] getColumn(int columnIndex) {
    double[] output = new double[data.length];
    for (int i = 0; i < data.length; i++) {
      output[i] = data[i][columnIndex];
    }
    return output;
  }

  /***
   * gets a column(overloaded)
   *
   * @param name
   *          name of the column
   * @return the column
   */
  public double[] getColumn(String name) {
    double[] output = new double[data[0].length];
    String[] titles = getTitles();
    int index = 0;
    for (int i = 0; i < titles.length; i++) {
      if (titles[i].equals(name)) {
        index = i;
      }
    }
    int counter = 0;
    for (int row = 0; row < data.length; row++) {
      output[counter] = data[row][index];
      counter++;
    }
    return output;
  }

  /***
   * gets multiple rows
   *
   * @param startRowIndex
   *          first needed rows index
   * @param endRowIndex
   *          last needed rows index
   * @return the rows
   */
  public double[][] getMultipleRows(int startRowIndex, int endRowIndex) {
    double[][] output = new double[endRowIndex - startRowIndex][data[0].length];
    for (int row = startRowIndex; row < endRowIndex; row++) {// all the rows
      int counter = 0;
      for (int col = 0; col < data[0].length; col++) {
        output[counter][col] = data[row][col];
      }
      counter++;
    }
    return output;
  }

  /***
   * returns multiple rows
   *
   * @param rowIndexes
   *          an array of row indexes
   * @return the rows
   */
  public double[][] getMultipleRows(int[] rowIndices) {
    double[][] output = new double[rowIndices.length][data[0].length];
    for (int i = 0; i < rowIndices.length; i++) {// through diff row indexs
      for (int j = 0; j < data[0].length; j++) {
        output[i][j] = data[rowIndices[i]][j];
      }
    }
    return output;
  }

  /***
   * gets multiple columns
   *
   * @param startColumnIndex
   *          first needed columns index
   * @param endColumnIndex
   *          last needed columns index
   * @return the columns
   */
  public double[][] getMultipleColumns(int startColumnIndex,
      int endColumnIndex) {
    double[][] output = new double[data.length][endColumnIndex
        - startColumnIndex];
    for (int row = 0; row < data.length; row++) {
      for (int col = startColumnIndex; col < endColumnIndex; col++) {
        int counter = 0;
        output[row][counter] = data[row][col];
        counter++;
      }
    }
    return output;
  }

  /***
   * gets multiple columns
   *
   * @param ColumnIndexes
   *          an array of column indexes
   * @return the columns
   */
  public double[][] getMultipleColumns(int[] columnIndices) {
    double[][] output = new double[data.length][columnIndices.length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < columnIndices.length; j++) {
        output[i][j] = data[i][columnIndices[j]];// ok
      }
    }
    return output;
  }

  /***
   * gets multiple columns
   *
   * @param ColumnNames
   *          an array of column names
   * @return the columns
   */
  public double[][] getMultipleColumns(String[] ColumnNames) {
    return null;
  }

  /***
   * gets titles of columns
   *
   * @return the titles
   */
  public String[] getTitles() {
    String[] output = new String[data[0].length];
    for (int i = 0; i < output.length; i++) {
      double x = data[0][i];
      String y = Double.toString(x);
      output[i] = y;
    }
    return output;
  }

  /***
   *
   * @param rowIndex
   * @param columnIndex
   * @param newValue
   */
  public void SetValues(int rowIndex, int columnIndex, double newValue) {
    data[rowIndex][columnIndex] = newValue;
  }

  /***
   * saves the state of the file
   *
   * @param filename
   *          the file we are saving into
   */
  public void SaveState(String filename) {

  }

}
