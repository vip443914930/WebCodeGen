package com.codegen.util;

import com.codegen.ShellRunner;
import sun.net.www.protocol.file.FileURLConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * Created by chenxiaojun on 2017/8/17.
 */
public class JarSourceUtil {

    private Class sourceClass;
    private String destPath;

    public JarSourceUtil(Class sourceClass, String destPath) {
        super();
        this.sourceClass = sourceClass;
        this.destPath = destPath;
    }

    public void loadRecourseFromJarByFolder(String sourcePath) throws IOException {
        URL url = ShellRunner.class.getResource(sourcePath);
        URLConnection urlConnection = url.openConnection();
        if(urlConnection instanceof FileURLConnection){
            copyFileResources(url,sourcePath);
        }else if(urlConnection instanceof JarURLConnection){
            copyJarResources((JarURLConnection)urlConnection);
        }
    }

    /**
     * 当前运行环境资源文件是在文件里面的
     * @param url
     * @param folderPath
     * @throws IOException
     */
    private void copyFileResources(URL url, String folderPath) throws IOException{
        File root = new File(url.getPath());
        if(root.isDirectory()){
            File[] files = root.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    loadRecourseFromJarByFolder(folderPath+"/"+file.getName());
                } else {
                    loadRecourseFromJar(folderPath+"/"+file.getName());
                }
            }
        }
    }

    /**
     * 当前运行环境资源文件是在jar里面的
     * @param jarURLConnection
     * @throws IOException
     */
    private void copyJarResources(JarURLConnection jarURLConnection) throws IOException{
        JarFile jarFile = jarURLConnection.getJarFile();
        Enumeration<JarEntry> entrys = jarFile.entries();
        while(entrys.hasMoreElements()){
            JarEntry entry = entrys.nextElement();
            if (entry.getName().startsWith(jarURLConnection.getEntryName()) && !entry.getName().endsWith("/")) {
                loadRecourseFromJar("/"+entry.getName());
            }
        }
        jarFile.close();
    }

    public void loadRecourseFromJar(String path) throws IOException {
        if (path.startsWith("/")) {
           throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }
        if(path.endsWith("/")){
            throw new IllegalArgumentException("The path has to be absolute (cat not end with '/').");
        }
        int index = path.lastIndexOf('/');
        String filename = path.substring(index + 1);
        String folderPath = destPath + path.substring(0, index + 1);
        // If the folder does not exist yet, it will be created. If the folder
        // exists already, it will be ignored
        File dir = new File(folderPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // If the file does not exist yet, it will be created. If the file
        // exists already, it will be ignored
        filename = folderPath + filename;
        File file = new File(filename);

        if (!file.exists() && !file.createNewFile()) {
            System.out.println("create file :{"+ filename+"} failed");
            return;
        }

        // Prepare buffer for data copying
        byte[] buffer = new byte[1024];
        int readBytes;

        // Open and check input stream
        URL url = sourceClass.getResource(path);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();

        if (is == null) {
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        // Open output stream and copy data between source file in JAR and the
        // temporary file
        OutputStream os = new FileOutputStream(file);
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            // If read/write fails, close streams safely before throwing an
            // exception
            os.close();
            is.close();
        }

    }
}
