package com.huhang.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanna on 5/20/17.
 */
public class PostMetaData {

  private String layout;
  private String author;
  //More than one categories can be used but better not,
  // otherwise it's meaningless to distinguish tags from category.
  private String categories;
  private List<String> tags;
  private String cover;

  public PostMetaData(String layout, String author, String categories,
      List<String> tags, String cover) {
    this.layout = layout;
    this.author = author;
    this.categories = categories;
    this.tags = tags;
    this.cover = cover;
  }

  public List<String> toStringList() {
    List<String> lines = new LinkedList<>();
    lines.add("---");
    lines.add("layout: " + layout);
    lines.add("author: " + author);
    lines.add("categories: " + categories);
    StringBuilder tagLine=new StringBuilder("tags: ");
    for(String tag:tags){
      tagLine.append(tag+" ");
    }
    lines.add(tagLine.toString());
    lines.add("cover: " + cover);
    lines.add("---");
    return lines;
  }

}
