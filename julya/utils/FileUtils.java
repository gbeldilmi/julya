package julya.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
  public static String getFileExtension(String path) {
    int index;
    if (path == null) {
      return null;
    }
    index = path.lastIndexOf('.');
    if (index == -1 || (index == 0 && path.startsWith("./"))) {
      return "";
    } else {
      return path.substring(index + 1);
    }
  }

  public static String readFile(String path) {
    File file;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String line;
    if (path == null) {
      return null;
    }
    file = new File(path);
    if (!file.exists()) {
      return null;
    }
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    stringBuilder = new StringBuilder();
    try {
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      bufferedReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }

  public static void writeFile(String fileName, String content, boolean append) {
    File file;
    FileWriter fw;
    try {
      file = new File(fileName);
      if (!file.exists()) {
        file.createNewFile();
      }
      fw = new FileWriter(file, append);
      fw.write(content);
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
