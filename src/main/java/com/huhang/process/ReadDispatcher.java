package com.huhang.process;

import com.huhang.model.MarkdownBean;
import com.huhang.model.PostMetaData;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanna on 5/21/17.
 */
public class ReadDispatcher {


  public void dispatch(MarkdownBean markdownBean) {
    PostMetaData postMetaData = getPostMetaData(markdownBean);
    List<String> outputLines = new LinkedList<>(postMetaData.toStringList());
    try {
      outputLines.addAll(MarkdownFormator
          .formatMkd(markdownBean.getSourceDirectory() + markdownBean.getMediumPath(),
              markdownBean.getName()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    markdownBean.setOutputLines(outputLines);
    String creationDate = FileInformationManager
        .getCreationDate(markdownBean.getSourceDirectory() + markdownBean.getMediumPath(),
            markdownBean.getName());
    markdownBean.setCreationDate(creationDate);
    String formatedName=format(markdownBean.getName());
    markdownBean.setName(formatedName);
  }

  private String format(String name) {
    return name.replace("# ", "").replace(" ", "-");
  }

  public PostMetaData getPostMetaData(MarkdownBean markdownBean) {
    File file = new File(
        markdownBean.getSourceDirectory() + markdownBean.getMediumPath() + markdownBean.getName());
    List<String> categoryNames = null;
    try {
      categoryNames = FileInformationManager.getNoteCategoryNames(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new PostMetaData(
        Configuration.INSTANCE.getProperties().getProperty("layout"),
        Configuration.INSTANCE.getProperties().getProperty("author"),
        categoryNames.get(categoryNames.size() - 1).toLowerCase(), categoryNames,
        "");
  }

}
