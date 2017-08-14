package com.huhang.process;

import com.huhang.model.PostMetaData;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanna on 5/20/17.
 */
public class MarkdownCreator {

  public static void create(String parent, String name, List<String> outputLines,
      String formatedDate)
      throws IOException {

    Path outFilePath = Paths.get(parent + formatedDate + "-" + name);
    Files.write(outFilePath, outputLines, Charset.forName("UTF-8"));
  }


}
