package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by char on 02/02/17.
 */
public class Paragraph {

    static Logger logger = LoggerFactory.getLogger(Paragraph.class);

    /**
     * Creates a Header
     */
    public static void create(XWPFDocument doc, String paragraph, String align) throws Exception {

        if (paragraph.isEmpty()) {
            throw new Exception("The paragraph is empty");
        }

        try {
            logger.debug(paragraph);
            XWPFParagraph paragraphDoc = doc.createParagraph();
            paragraphDoc.setAlignment(align.equals("r")?ParagraphAlignment.RIGHT:ParagraphAlignment.LEFT);

            XWPFRun rGeneral = paragraphDoc.createRun();
            rGeneral.setText(paragraph);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }

}
