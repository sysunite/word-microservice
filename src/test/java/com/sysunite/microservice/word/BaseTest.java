package com.sysunite.microservice.word;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * @author Mohamad Alamili
 */
public class BaseTest {
  
  static Application application;
  
  @BeforeClass
  public static void setup(){
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 9267;
    
    if(application == null) {
      application = new Application();
    }
  }
}