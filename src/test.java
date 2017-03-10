
public class test {

  public static void main(String[] args) {
    String message = "hi im paul";

    FileIO.writeDataToFile("C:/Users/Avi/Documents/new.txt", message);

    String fileContent = FileIO
        .readFileAsString("C:/Users/Avi/Documents/new.txt");
    System.out.println(fileContent);
  }

}
