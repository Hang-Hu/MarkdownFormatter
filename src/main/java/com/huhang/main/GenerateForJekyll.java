package com.huhang.main;

import com.huhang.model.MarkdownBean;
import com.huhang.process.ChineseCharChecker;
import com.huhang.process.Configuration;
import com.huhang.process.Deletor;
import com.huhang.process.FileInformationManager;
import com.huhang.process.ReadDispatcher;
import com.huhang.process.WriteDispatcher;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class GenerateForJekyll {

  public static void main(String[] args) throws IOException {
    run();
  }

  public static void run() {
    File folder = new File(Configuration.INSTANCE.getProperties().getProperty("sourceDirectory"));
    List<File> allFiles = new LinkedList<>();
    FileInformationManager.getMKDFilesRecursivelyWithoutDate(folder, allFiles);
    Deletor deletor = new Deletor();
    for (File file : allFiles) {
      String sourceDirectory = Configuration.INSTANCE.getProperties()
          .getProperty("sourceDirectory");
      String targetDirectory = Configuration.INSTANCE.getProperties()
          .getProperty("targetDirectory");
      String mediumPath = file.getParent().replace(sourceDirectory, "") + "/";
      String name = file.getName();
      MarkdownBean markdownBean = new MarkdownBean();
      markdownBean.setMediumPath(mediumPath);
      markdownBean.setName(name);
      markdownBean.setSourceDirectory(sourceDirectory);
      markdownBean.setTargetDirectory(targetDirectory);
      generate(markdownBean);
      // delete the old file
      deletor.delete(file);
    }
  }

  public static void generate(MarkdownBean markdownBean) {

    new ReadDispatcher().dispatch(markdownBean);
    // Check if there is any chinese characters
    new ChineseCharChecker().check(markdownBean);
    new WriteDispatcher().dispatch(markdownBean);

  }


}
