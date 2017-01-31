package com.sysunite.microservice.word.controllers;

import com.sysunite.microservice.word.util.Props;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * @author Mohamad Alamili
 * @author char
 */
public class ApplicationController {
  
  public static Route index = (Request req, Response res) ->
    Props.getInstance().getProperty("application.name") + " v " + Props.getInstance().getProperty("application.version");
    
  public static Route version = (Request req, Response res) -> 
    Props.getInstance().getProperty("application.version");

  public static Route notFound = (Request req, Response res) ->
    "not found";
}

