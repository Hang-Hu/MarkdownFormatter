package com.huhang.process;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by joanna on 8/12/17.
 */
public class Deletor {
  Logger logger=Logger.getLogger(Deletor.class.getName());

  public void delete(File file) {
    boolean isDelete = file.delete();
    if(isDelete==false){
      logger.severe("Fail to delete file "+ file.getAbsolutePath());
    }
  }
}
