package ftp;

public class FileNameChanger {

    public FileNameChanger() {

    }

    public String change (String path) {
        path = path.replace("\\","/");
        path = path.replace("\\\\","/");
        return path;
    }
}
