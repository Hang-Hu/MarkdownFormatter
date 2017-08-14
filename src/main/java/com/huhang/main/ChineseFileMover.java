package com.huhang.main;

import com.huhang.process.ChineseCharChecker;
import com.huhang.process.ChineseFileFinder;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by joanna on 8/12/17.
 */
public class ChineseFileMover {

  public static final String DEST = "/home/joanna/Documents/gitRepository/Hang-Hu.github.com/_posts/chinese/";

  public static void main(String[] args) {
    run();
  }

  private static void run() {
    Logger logger=Logger.getLogger(ChineseFileMover.class.getName());
    List<File> chineseFiles = new ChineseFileFinder().find();
    for (File file : chineseFiles) {
      file.renameTo(new File(DEST + file.getName()));
      logger.info("Move file "+file.getAbsolutePath());
    }
  }
}
