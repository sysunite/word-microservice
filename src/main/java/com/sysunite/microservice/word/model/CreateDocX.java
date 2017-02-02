package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by char on 02/02/17.
 */
public class CreateDocX {


    static Logger logger = LoggerFactory.getLogger(CreateDocX.class);

    public String createDocx(String fileName) {

        String tempPath;

        try {

            XWPFDocument doc = new XWPFDocument();



            File temp = File.createTempFile(UUID.randomUUID().toString().concat("-").concat(fileName),".docx");
            tempPath = temp.getAbsolutePath();
            logger.debug(tempPath);
            FileOutputStream out = new FileOutputStream(temp);

            doc.write(out);
            out.close();
            doc.close();

            return tempPath;

        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());
            return null;

        }
    }

}
