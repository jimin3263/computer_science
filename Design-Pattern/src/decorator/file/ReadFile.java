package decorator.file;

import java.io.*;

public class ReadFile {
    public static void main(String[] args) {
        try {
            LineNumberReader readme
                    = new LineNumberReader(
                    new FileReader("readme.txt"));
            int b = readme.read();
            String readLine = readme.readLine();
            System.out.println("b= " + readLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
