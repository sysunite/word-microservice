package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by char on 02/02/17.
 */
public class Title {

    static Logger logger = LoggerFactory.getLogger(Title.class);

    /**
     * Creates a Title KA Heading 1
     */

    public static void create(XWPFDocument doc, String title) throws Exception {

        if (title.isEmpty()) {
            throw new Exception("The Title is empty");
        }

        try {

            XWPFParagraph titleDoc = doc.createParagraph();
            titleDoc.setStyle("Heading 1");
            XWPFRun r0 = titleDoc.createRun();
            r0.setText(title);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }
    public static void create(XWPFDocument doc, String title, int level) throws Exception {

        if (title.isEmpty()) {
            throw new Exception("The Title is empty");
        }

        try {

            XWPFParagraph titleDoc = doc.createParagraph();
            titleDoc.setStyle("Heading ".concat(String.valueOf(level)));
            XWPFRun r0 = titleDoc.createRun();
            r0.setText(title);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }


}
