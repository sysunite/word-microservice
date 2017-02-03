package com.sysunite.microservice.word.controllers;


import com.google.gson.Gson;
import com.sysunite.microservice.word.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

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

        DataJSONInvoice dataJSONInvoice = new Gson().fromJson(req.body(), DataJSONInvoice.class);

        logger.debug(dataJSONInvoice.toString());

        logger.debug("The data: " + data);
        logger.debug("The filename: " + fileName);


        return process(res, dataJSONInvoice, fileName);
    };

    public static Route json = (Request req, Response res) -> {

        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

        String fileName = req.queryParams("fileName");

        String jsonStr = req.body().toString();

        return process(res, jsonStr, fileName);
    };


    private static HttpServletResponse process(Response res, DataJSONInvoice dataJSONInvoice, String fileName) throws IOException {

        Invoice2Doc invoice2Doc = new Invoice2Doc();

        String tempPath = invoice2Doc.createDocx(dataJSONInvoice, fileName);

        res.raw().setContentType("application/octet-stream");
        res.raw().setHeader("Content-Disposition", "attachment; filename=" + fileName);

        byte[] bytes = Files.readAllBytes(Paths.get(tempPath));
        HttpServletResponse raw = res.raw();

        raw.getOutputStream().write(bytes);
        raw.getOutputStream().flush();
        raw.getOutputStream().close();

        return res.raw();

    }


    private static HttpServletResponse process(Response res, String dataJSONString, String fileName) throws IOException {

        CreateDocX createDocX = new CreateDocX();

        String tempPath;

        HttpServletResponse raw = res.raw();

        try {

            tempPath = createDocX.createDocx(fileName, dataJSONString);

            res.raw().setContentType("application/octet-stream");
            res.raw().setHeader("Content-Disposition", "attachment; filename=" + fileName);

            byte[] bytes = Files.readAllBytes(Paths.get(tempPath));


            raw.getOutputStream().write(bytes);
            raw.getOutputStream().flush();
            raw.getOutputStream().close();

            return res.raw();

        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());

            raw.sendError(400, e.getLocalizedMessage());

            return res.raw();
        }

    }

}