package ftp;

import java.io.File;
import java.util.*;

public class FileReader {

    String filePath;
    private String resourcePaths = "";
    ArrayList<String> texts = new ArrayList<>();
    ArrayList<String> infos = new ArrayList<>();

    String[][] matrix = new String[5][2];

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> read() {

        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                texts.add(scanner.nextLine());
            }

            matrix[0][0] = "IP Address:";
            matrix[0][1] = "11";

            matrix[1][0] = "Protocol:";
            matrix[1][1] = "9";

            matrix[2][0] = "Username:";
            matrix[2][1] = "9";

            matrix[3][0] = "Password:";
            matrix[3][1] = "9";

            matrix[4][0] = "Target Path:";
            matrix[4][1] = "12";

            scanner.close();


            for (int i = 0 ; i<5; i++) {
                for (String text : texts) {
                    if (text.indexOf(matrix[i][0]) == 0) {
                        String info = text.substring(Integer.parseInt(matrix[i][1]));
                        info = info.replaceAll(" ", "");
                        System.out.println(info);
                        infos.add(info);
                        texts.remove(text);
                        break;
                    }
                }
            }

            texts.remove("Resource Paths:");

            for (String path : texts) {
                String info = path.replaceAll(" ", "");
                info = info.replaceAll("-", "");
                resourcePaths += info + " ";
                System.out.println(info);
            }

            infos.add(resourcePaths);




        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            return infos;
        }
    }
}
