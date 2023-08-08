package ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileOutputStream;
import java.io.IOException;


public class FTPDownload {

    String server;
    int port;
    String username;
    String password;
    String targetPath;

    String resourcePath = "zipFolder.zip";
    public FTPDownload(String server, int port, String username, String password, String targetPath) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.password = password;
        this.targetPath = targetPath;
    }

    public void ftpDownload() {

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            FTPFile[] files = ftpClient.listFiles(resourcePath);
            for (FTPFile file : files) {
                if (!file.isDirectory()) {
                    String remoteFilePath = resourcePath + "/" + file.getName();
                    String localFilePath = targetPath + "/" + file.getName();
                    downloadFile(ftpClient, remoteFilePath, localFilePath);
                }
            }


            ftpClient.logout();
            ftpClient.disconnect();

            System.out.println("Download completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(FTPClient ftpClient, String remoteFile, String localFile)
            throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(localFile)) {
            ftpClient.retrieveFile(remoteFile, fileOutputStream);
        }
    }
}





