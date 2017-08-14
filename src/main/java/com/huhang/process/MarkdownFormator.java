package com.huhang.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanna on 5/20/17.
 */
public class MarkdownFormator {


  public static List<String> formatMkd(String parent, String name) throws IOException {
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(new File(parent + name));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    String line = "";
    List<String> output = new LinkedList<>();
    boolean isPreviousLineContainsSharp = false;
    String codeBlockType = "";
    while ((line = bufferedReader.readLine()) != null) {
      // add empty line if last line contains # and this line is not empty line.
      // Or
      // if last line is the right part of code block ``` and this line is not empty
      if (((isPreviousLineContainsSharp == true) && (line.equals("") == false)) || (
          (codeBlockType.equals("Right")) && (line.equals("") == false))) {
        output.add("");//add a empty line after the # line
      }
      if (line.contains("```")) {
        if (codeBlockType.equals("") || codeBlockType.equals("Right")) {
          codeBlockType = "Left";//this code block is the left one (, add one line before it if no empty line before
          if (output.get(output.size() - 1).equals("") == false) {
            output.add("");// add a empty line before adding this line
          }
        } else {
          codeBlockType = "Right";//this code block is the right one ), add one empty line after it if no empty line after
        }
      }
      if (line.contains("#")) {
        isPreviousLineContainsSharp = true;
        int lastIndex = line.lastIndexOf('#');
        // add space after # is there is no space after #
        if ((line.length() > lastIndex + 1) && (line.charAt(lastIndex + 1) != ' ')) {
          output.add(new StringBuilder(line).insert(lastIndex + 1, ' ')
              .toString());//add this line with space inserted after #
        } else {
          output.add(line);// add this line
        }
      } else {
        isPreviousLineContainsSharp = false;
        output.add(line);// add this line
      }

    }
    deleteTitle(output);
    return output;
  }

  private static void deleteTitle(List<String> output) {
    String firstLine = output.get(0);
    // One # shows it's title
    if (firstLine.lastIndexOf('#') == firstLine.indexOf('#')) {
      output.remove(0);
    }
  }
}
