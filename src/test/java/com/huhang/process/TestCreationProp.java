package com.huhang.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.junit.Test;

/**
 * Created by joanna on 5/21/17.
 */
public class TestCreationProp {

  @Test
  public void test() {
    File folder = new File(Configuration.INSTANCE.getProperties().getProperty("sourceDirectory"));
    List<File> allFiles = new LinkedList<>();
    FileInformationManager.getMKDFilesRecursivelyWithoutDate(folder, allFiles);
    HashMap<String, String> dateMap = new HashMap<>();
    for (File file : allFiles) {
      String sourceDirectory = Configuration.INSTANCE.getProperties()
          .getProperty("sourceDirectory");
      String mediumPath = file.getParent().replace(sourceDirectory, "") + "/";
      String name = file.getName();
      String creationDate = getCreationDate(sourceDirectory + mediumPath, name);
      dateMap.put(name, creationDate);

    }
    saveMaps(dateMap);
  }

  public void saveMaps(HashMap<String, String> map) {
    Properties properties = new Properties();
    properties.putAll(map);
    FileOutputStream output = null;
    try {
      output = new FileOutputStream(
          Thread.currentThread().getContextClassLoader().getResource("creationDate.properties")
              .getFile());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      properties.store(output, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getCreationDate(String path, String name) {
    FileTime creationTime = null;
    try {
      creationTime = Files.readAttributes(Paths.get(path + name), BasicFileAttributes.class)
          .creationTime();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new SimpleDateFormat("yyyy-MM-dd").format(creationTime.toMillis());
  }
}
