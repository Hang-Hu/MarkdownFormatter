package com.huhang.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by joanna on 5/21/17.
 */
public enum Configuration {
  INSTANCE;
  private Properties properties;
  private Properties dates;

  public Properties getDates() {
    return dates;
  }

  Configuration() {
    read();
  }

  private void read() {

    this.properties = FileInformationManager.readProperties("app.properties");
    this.dates=FileInformationManager.readProperties("creationDate.properties");
  }


  public Properties getProperties() {
    return properties;
  }
}
