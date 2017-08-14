package com.huhang.process;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * Created by joanna on 5/20/17.
 */
public class TestPath {

  @Test
  public void test() throws IOException {
    String path =
        "/home/joanna/Documents/gitRepository/centrarium-master/_posts/Tech-Notes/"
            + "Ubuntu-Notes/Environment-Setup-Notes/";

    String name = "install-maven.mkd";
    File file = new File(path + name);
    String canonicalPath = file.getCanonicalPath();
    System.out.println(file.getParent());
    List<String> strings = new LinkedList(Arrays.asList(canonicalPath.split("/")));

    for (Iterator<String> i = strings.listIterator(); i.hasNext(); ) {
      String s = i.next();
      if ((s.contains("Notes")) && (s.equals("Tech-Notes") == false)) {
        //keep s
      } else {
        i.remove();
      }
    }
  }
}
