package com.huhang.model;

import java.util.List;

/**
 * Created by joanna on 5/21/17.
 */
public class MarkdownBean {

  private String sourceDirectory;
  private String targetDirectory;
  private String mediumPath;
  private String name;
  private List<String> outputLines;
  private String creationDate;

  public String getSourceDirectory() {
    return sourceDirectory;
  }

  public void setSourceDirectory(String sourceDirectory) {
    this.sourceDirectory = sourceDirectory;
  }

  public String getTargetDirectory() {
    return targetDirectory;
  }

  public void setTargetDirectory(String targetDirectory) {
    this.targetDirectory = targetDirectory;
  }

  public String getMediumPath() {
    return mediumPath;
  }

  public void setMediumPath(String mediumPath) {
    this.mediumPath = mediumPath;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getOutputLines() {
    return outputLines;
  }

  public void setOutputLines(List<String> outputLines) {
    this.outputLines = outputLines;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }
}
