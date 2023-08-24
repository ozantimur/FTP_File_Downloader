# FTP File Downloader
The FTP File Downloader is a Java application that empowers users to effortlessly download files from remote servers using FTP and SFTP protocols. This repository offers a comprehensive solution for setting up secure connections, transferring files, and aggregating them into a single zip folder for streamlined retrieval.

## Table of Contents
* Features
* Usage
    * Prerequisites
    * Configuration
    * Running the Application
* Dependencies

## Features
* Supports both FTP and SFTP protocols for secure and efficient file transfers.
* Reads configuration information from a user-provided text file, simplifying setup.
* Downloads multiple files from remote servers and aggregates them into a zip folder.
* Provides clear and concise code structure, promoting ease of understanding and customization.

## Usage
### Prerequisites
* Java Development Kit (JDK) 11 or higher installed.
* Access to remote servers via FTP or SFTP protocols.

### Configuration
1. Create a text file with the following format, specifying server and authentication details:
   ```plaintext
   IP Address: 192.168.1.100
   Protocol: FTP
   Username: user
   Password: password
   Target Path: /remote/files/
   Resource Paths: (each path on a new line)
   - /local/files/file1.txt
   - /local/files/file2.jpg
   - /local/files/folder/file3.pdf

2. Make sure to have the necessary dependencies listed in your pom.xml file, including commons-net and jsch libraries.

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/ozantimur/FTP_File_Downloader.git
2. Navigate to the project directory:
   ```bash
   cd FTP_File_Downloader
3. Build the project using Maven:
   ```bash
   mvn clean package
4. Run the application, providing the absolute path to the configuration text file:
   ```bash
   java -cp target/FTP-1.0-SNAPSHOT.jar ftp.Main /absolute/path/to/config.txt

## Dependencies
* commons-net (Apache Commons Net library) for FTP functionality.
* jsch (Java Secure Channel) library for SFTP functionality.
* junit for testing (optional).
<br>
<br>
Feel free to customize and expand the provided README to match your project's details and features. This comprehensive README provides step-by-step instructions on configuration, usage, and dependencies, making it easy for users to understand and get started with your FTP File Downloader project.
