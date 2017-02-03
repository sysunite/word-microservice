package com.sysunite.microservice.word;

import com.sysunite.microservice.word.controllers.ApplicationController;
import com.sysunite.microservice.word.controllers.WordController;
import com.sysunite.microservice.word.util.CORS;
import com.sysunite.microservice.word.util.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static spark.Spark.*;

/**
 * @author Mohamad Alamili
 * @author char
 */
public class Application {

  static Logger logger = LoggerFactory.getLogger(Application.class);

  public static int WORD_MICROSERVICE_PORT = System.getenv("WORD_MICROSERVICE_PORT") != null ?
          Integer.valueOf(System.getenv("WORD_MICROSERVICE_PORT")) : Integer.valueOf(Props.getInstance().getProperty("word.micro.service.port"));

  public Application() {


    // HTTP port to listen on
    port(WORD_MICROSERVICE_PORT);

    // Enable CORS on all hosts
    CORS.enable();

    // Wire routes
    get("/",                      ApplicationController.index);
    get("/application/version",   ApplicationController.version);
    post("/word/create",         WordController.create);
    post("/poc/json", WordController.json);
    get("*",                      ApplicationController.notFound);

    // Wait for server to be initialized
    awaitInitialization();
    
    // Running
    final String VERSION = Props.getInstance().getProperty("application.version");
    final String NAME = Props.getInstance().getProperty("application.name");
    logger.info(NAME + " version " + VERSION  + " running on port " + WORD_MICROSERVICE_PORT);
  }

  public static void main(String[] args) throws IOException {

    new Application();
  }
}