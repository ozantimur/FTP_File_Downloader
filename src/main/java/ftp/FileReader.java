package ftp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

    String filePath;
    private String resourcePaths = "";

    ArrayList<String> infos = new ArrayList<>();

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> read() {

        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < 5; i++) {
                infos.add(scanner.nextLine());
            }

            while (scanner.hasNextLine()) {
                resourcePaths += scanner.nextLine() + " ";
            }

            infos.add(resourcePaths);
            scanner.close();


        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            return infos;
        }
    }
}
