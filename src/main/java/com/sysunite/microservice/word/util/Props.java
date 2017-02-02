package com.sysunite.microservice.word.util;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Mohamad Alamili
 */
public class Props extends Properties {
  private static Props instance;
  private static Logger logger = LoggerFactory.getLogger(Props.class);
  
  protected Props() {
    try {
      load(Resources.getResource("main.properties").openStream());
      load(Resources.getResource("word.parts").openStream());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
  
  public static Props getInstance() {
    if(instance == null) {
      instance = new Props();
    }
    
    return instance;
  }
}