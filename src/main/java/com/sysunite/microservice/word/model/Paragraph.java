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
    public static void create(
            XWPFDocument doc,
            String paragraph,
            String align,
            Boolean bold,
            Boolean italic,
            String font
    ) throws Exception {

        if (paragraph.isEmpty()) {
            throw new Exception("The paragraph is empty");
        }

//        try {
        XWPFParagraph paragraphDoc = doc.createParagraph();
//        paragraphDoc.setAlignment(align.equals("r") ? ParagraphAlignment.RIGHT : ParagraphAlignment.LEFT);

        switch (align) {
            case "right":
                paragraphDoc.setAlignment(ParagraphAlignment.RIGHT);
                break;
            case "center":
                paragraphDoc.setAlignment(ParagraphAlignment.CENTER);
                break;
            case "left":
                paragraphDoc.setAlignment(ParagraphAlignment.LEFT);
                break;
        }

        XWPFRun rGeneral = paragraphDoc.createRun();
        rGeneral.setBold(bold);
        rGeneral.setItalic(italic);
        if (font != null) {
            rGeneral.setFontFamily(font);
        }
        rGeneral.setText(paragraph);
//        } catch (Exception e) {
//            logger.error(e.getLocalizedMessage());
//        }

    }

}
