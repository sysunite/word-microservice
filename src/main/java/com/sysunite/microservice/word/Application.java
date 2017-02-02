package com.sysunite.microservice.word;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sysunite.microservice.word.controllers.ApplicationController;
import com.sysunite.microservice.word.controllers.WordController;
import com.sysunite.microservice.word.util.CORS;
import com.sysunite.microservice.word.util.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

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

//    String jsonStr = new String("{\"LCD\": \"Samsung\",\"MOUSE\": \"HP, the best\",\"MOUSE\": \"DELL\",\"LCD\": \"Apple\",\"LCD\": \"DELL\",\"DRINK\": \"Coke\",\"LCD\": \"Lenovo\",\"DRINK\": \"Pepsi\",\"KEYBOARD\": \"Lenovo\"}");
//
//    // Removing the first and last braces
//    jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
//
//    // Create a Guava Multimap
//    Multimap<String, String> myMap = ArrayListMultimap.create();
//
//    // Split on comma for each key-value pair
//    for(String item: jsonStr.split(",")) {
//      // Get the individual key-value pair
//      String[] keyValue = item.split(":");
//
//      // Get the values between the the inverted commas
//      String key = keyValue[0].substring(keyValue[0].indexOf("\"") + 1, keyValue[0].lastIndexOf("\""));
//      String value = keyValue[1].substring(keyValue[1].indexOf("\"") + 1, keyValue[1].lastIndexOf("\""));
//
//      // Add to map
//      myMap.put(key, value);
//    }
//
//    // Print to check
//    for(Map.Entry<String, String> entry: myMap.entries()) {
//      System.out.println(entry.getKey() + ": " + entry.getValue());
//    }


    new Application();
  }
}