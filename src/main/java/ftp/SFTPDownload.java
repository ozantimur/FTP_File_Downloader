package ftp;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.Vector;

public class SFTPDownload {

    String server;
    int port;
    String username;
    String password;
    String targetPath;

    public SFTPDownload(String server, int port, String username, String password, String targetPath) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.password = password;
        this.targetPath = targetPath;
    }

    public void sftpDownload() {

        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = null;



        String resourcePath = "zipFolder.zip";

        try {
            session = jsch.getSession(username, server, port);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();


            Vector<ChannelSftp.LsEntry> fileList = sftpChannel.ls(resourcePath);
            for (ChannelSftp.LsEntry entry : fileList) {
                if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
                    String remoteFilePath = resourcePath;
                    String localFilePath = targetPath + "/" + entry.getFilename();
                    downloadFile(sftpChannel, remoteFilePath, localFilePath);
                }
            }



            sftpChannel.exit();
            session.disconnect();

            System.out.println("Download completed!");
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadFile(ChannelSftp sftpChannel, String remoteFile, String localFile)
            throws SftpException, IOException {
        File local = new File(localFile);
        File parentDir = local.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(local)) {
            sftpChannel.get(remoteFile, fileOutputStream);
        }
    }
}