package ftp;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        String server;
        String protocol;
        int port = 0;
        String username;
        String password;
        String targetPath;
        String resourcePath;

        String zipName;

        ArrayList<String> infos;

        FileNameChanger fileNameChanger = new FileNameChanger();
        filePath = fileNameChanger.change(filePath);

        FileReader fileReader = new FileReader(filePath);
        infos = fileReader.read();
        server = infos.get(0);
        protocol = infos.get(1).toUpperCase();
        if (protocol.equals("SFTP")) {
            port = 22;
        } else if (protocol.equals("FTP"))
            port= 21;
        username = infos.get(2);
        password = infos.get(3);
        targetPath = infos.get(4);
        resourcePath = infos.get(5);

        targetPath = fileNameChanger.change(targetPath);
        resourcePath = fileNameChanger.change(resourcePath);

        zipName = targetPath.substring(targetPath.lastIndexOf("/")+1) + ".zip";
        String createCommand = "zip -r " + zipName + " " + resourcePath;

        SSHConnection create = new SSHConnection(server, username, password, createCommand, port);
        create.sshConnection();

        if (port == 22) {
            SFTPDownload sftpDownload = new SFTPDownload(server, port, username, password, targetPath, zipName);
            sftpDownload.sftpDownload();
        } else if (port == 21) {
            FTPDownload ftpDownload = new FTPDownload(server, port, username, password, targetPath, zipName);
            ftpDownload.ftpDownload();
        }

        String deleteCommand = "rm -r " + zipName;

        SSHConnection delete = new SSHConnection(server, username, password, deleteCommand, port);
        delete.sshConnection();

    }
}
