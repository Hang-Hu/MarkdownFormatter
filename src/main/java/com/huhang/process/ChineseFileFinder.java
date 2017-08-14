package com.huhang.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by joanna on 8/12/17.
 */
public class ChineseFileFinder {

  private Logger logger = Logger.getLogger(ChineseFileFinder.class.getName());

  public List<File> find() {
    List<File> chineseFiles = new LinkedList<>();
    File targetDirectory = new File(
        Configuration.INSTANCE.getProperties().getProperty("targetDirectory"));
    List<File> allFiles = new LinkedList<>();
    FileInformationManager.getFilesRecursivelyNoLimit(targetDirectory, allFiles);
    BufferedReader reader = null;
    ChineseCharChecker chineseCharChecker = new ChineseCharChecker();
    for (File file : allFiles) {
      try {
        checkFile(chineseFiles, chineseCharChecker, file);
      } catch (IOException e) {
        logger.severe("Fail to read file " + file.getAbsolutePath());
      }
    }
    return chineseFiles;
  }

  private void checkFile(List<File> chineseFiles, ChineseCharChecker chineseCharChecker, File file)
      throws IOException {
    BufferedReader reader;
    reader = new BufferedReader(new FileReader(file));
    String line = "";
    while ((line = reader.readLine()) != null) {
      if (chineseCharChecker.containsHanChar(line) == true){
        chineseFiles.add(file);
        break;
      }
    }
  }
}
