package com.huhang.process;

import com.huhang.model.MarkdownBean;
import java.lang.Character.UnicodeScript;
import java.util.logging.Logger;

/**
 * Created by joanna on 8/12/17.
 */
public class ChineseCharChecker {

  private Logger logger = Logger.getLogger(ChineseCharChecker.class.getName());

  public void check(MarkdownBean markdownBean) {
    for (String s : markdownBean.getOutputLines()) {
      if (containsHanChar(s) == true) {
        logger.severe("File " + markdownBean.getTargetDirectory() + markdownBean.getMediumPath()
            + markdownBean.getName()+" contains Chinese characters!");
      }
    }
  }

  public boolean containsHanChar(String s) {
    return s.codePoints().anyMatch(
        codepoint -> Character.UnicodeScript.of(codepoint) == UnicodeScript.HAN);
  }
}
