package com.sysunite.microservice.word;

import com.sysunite.microservice.word.util.Props;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohamad Alamili
 */
public class ApplicationControllerTest extends BaseTest {

  @Test
  public void get_version() {
    final String VERSION = Props.getInstance().getProperty("application.version");
    get("/application/version").then().body(is(VERSION));
  }
}