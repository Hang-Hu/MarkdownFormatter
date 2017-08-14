package com.huhang.process;

import com.huhang.model.MarkdownBean;
import java.io.IOException;
import java.util.List;

/**
 * Created by joanna on 5/21/17.
 */
public class WriteDispatcher {


  public void dispatch(MarkdownBean markdownBean) {
    try {
      MarkdownCreator.create(markdownBean.getTargetDirectory() + markdownBean.getMediumPath(),
          markdownBean.getName(), markdownBean.getOutputLines(), markdownBean.getCreationDate());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
