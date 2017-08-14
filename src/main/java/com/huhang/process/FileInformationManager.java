package com.huhang.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

/**
 * Created by joanna on 5/20/17.
 */
public class FileInformationManager {

  //return only markdown files and unformated files
  public static void getMKDFilesRecursivelyWithoutDate(final File folder, List<File> files) {
    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        getMKDFilesRecursivelyWithoutDate(file, files);
      } else {
        if ((file.getName().endsWith(".mkd") && (startsWithDate(file.getName()) == false))) {
          files.add(file);
        }
      }
    }
  }

  public static void getFilesRecursivelyNoLimit(final File folder, List<File> files) {

    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        getFilesRecursivelyNoLimit(file, files);
      } else {
        files.add(file);
      }
    }
  }

  private static boolean startsWithDate(String str) {
    if (str.length() < 10)//doesn't start with date
    {
      return false;
    }
    try {
      new SimpleDateFormat("yyyy-MM-dd").parse(str.substring(0, 10));
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  public static List<String> getNoteCategoryNames(File file) throws IOException {
    String canonicalPath = file.getCanonicalPath();
    List<String> strings = new LinkedList(Arrays.asList(canonicalPath.split("/")));

    for (ListIterator<String> i = strings.listIterator(); i.hasNext(); ) {
      String s = i.next();
      if ((s.contains("Notes")) && (s.equals("Tech-Notes") == false) && (s.equals("Notes")
          == false)) {
        i.set(s.substring(0, s.lastIndexOf('-')));
      } else {
        i.remove();
      }
    }
    return strings;
  }

  public static String getCreationDate(String path, String name) {

    Properties properties = Configuration.INSTANCE.getDates();
    // if the date is available in the createDate.properties, use this one
    if (properties.get(name) != null) {
      return (String) properties.get(name);
    } else {
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

  public static Properties readProperties(String name) {
    Properties properties = new Properties();
    try {
      properties.load(
          new FileInputStream(
              Thread.currentThread().getContextClassLoader().getResource(name)
                  .getFile()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }
}
