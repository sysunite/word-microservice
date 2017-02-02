package com.sysunite.microservice.word.controllers;


import com.google.gson.Gson;
import com.sysunite.microservice.word.model.*;

import com.sysunite.microservice.word.util.Props;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author Mohamad Alamili
 * @author char
 */
public class WordController {

  static Logger logger = LoggerFactory.getLogger(WordController.class);

  public static Route create = (Request req, Response res) -> {
    // Get params
    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

    String data = req.queryParams("data");
    String fileName = req.queryParams("fileName");

    DataJSONInvoice dataJSONInvoice = new Gson().fromJson(req.body(),DataJSONInvoice.class);

    logger.debug(dataJSONInvoice.toString());

    logger.debug("The data: " + data);
    logger.debug("The filename: " + fileName);


    return process(res, dataJSONInvoice, fileName);
  };

  public static Route json = (Request req, Response res) -> {

    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

    XWPFDocument doc = new XWPFDocument();

    String jsonStr = req.body().toString();

    jsonStr = jsonStr.substring(2, jsonStr.length() - 2);

    jsonStr = jsonStr.replaceAll("\n","").replaceAll("\t","");

    String[] item = jsonStr.split("\",\"");

    // Split on comma for each key-value pair
    for (int i = 0; i < item.length; i++) {


      String it = item[i];

      // Get the individual key-value pair
      String[] keyValue = it.split("\":\"");

      String key = keyValue[0];

      String value = i == item.length - 1 ? keyValue[1].substring(0,keyValue[1].length()-1) : keyValue[1];


      logger.debug(value);

      if (key.equals(Props.getInstance().getProperty("word.parts.title"))) {
        try {
          Title.create(doc, value);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

      if (key.equals(Props.getInstance().getProperty("word.parts.header"))) {
        try {
          Header.create(doc, value);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

      if (key.equals(Props.getInstance().getProperty("word.parts.footer"))) {
        try {
          Footer.create(doc, value);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

      if (key.equals(Props.getInstance().getProperty("word.parts.paragraph"))) {
        try {
          Paragraph.create(doc, value, "l");
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

      if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-r"))) {
        try {
          Paragraph.create(doc, value, "r");
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

      if (key.equals(Props.getInstance().getProperty("word.parts.title-2"))) {
        try {
          Title.create(doc, value,2);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (key.equals(Props.getInstance().getProperty("word.parts.title-3"))) {
        try {
          Title.create(doc, value,3);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (key.equals(Props.getInstance().getProperty("word.parts.title-4"))) {
        try {
          Title.create(doc, value,4);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (key.equals(Props.getInstance().getProperty("word.parts.title-5"))) {
        try {
          Title.create(doc, value,5);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }
      if (key.equals(Props.getInstance().getProperty("word.parts.title-6"))) {
        try {
          Title.create(doc, value,6);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
      }

    }

    String tempPath;

//    File temp = File.createTempFile(UUID.randomUUID().toString().concat("-").concat("poc"),".docx");
    File temp = new File("./poc.docx");
    tempPath = temp.getAbsolutePath();
    logger.debug(tempPath);
    FileOutputStream out = new FileOutputStream(temp);

    doc.write(out);
    out.close();
    doc.close();

    return HttpServletResponse.SC_OK;
  };


  private static HttpServletResponse process(Response res, DataJSONInvoice dataJSONInvoice, String fileName) throws IOException {

    Invoice2Doc invoice2Doc = new Invoice2Doc();
    
    String tempPath = invoice2Doc.createDocx(dataJSONInvoice,fileName);
    
    res.raw().setContentType("application/octet-stream");
    res.raw().setHeader("Content-Disposition","attachment; filename=" + fileName);

    logger.debug("The filename: " + fileName);

    byte[] bytes = Files.readAllBytes(Paths.get(tempPath));
    HttpServletResponse raw = res.raw();

    raw.getOutputStream().write(bytes);
    raw.getOutputStream().flush();
    raw.getOutputStream().close();

    return res.raw();
    
  }

}