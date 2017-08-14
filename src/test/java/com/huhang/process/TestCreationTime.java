package com.huhang.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import org.junit.Test;

/**
 * Created by joanna on 5/20/17.
 */
public class TestCreationTime {
  @Test
  public void test(){
    String path =
        "/home/joanna/Documents/gitRepository/centrarium-master/_posts/Tech-Notes/Ubuntu-Notes/"
            + "Environment-Setup-Notes/";
    String name = "install-maven.mkd";
    FileTime creationTime=null;
    try {
      creationTime = Files.readAttributes(Paths.get(path + name), BasicFileAttributes.class)
          .creationTime();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String formatedDate = new SimpleDateFormat("yyyy-MM-dd").format(creationTime.toMillis());
    System.out.println(formatedDate);

  }
}
