package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by char on 02/02/17.
 */
public class Footer {

    static Logger logger = LoggerFactory.getLogger(Footer.class);

    /**
     * Creates a Title KA Heading 1
     */

    public static void create(XWPFDocument doc, String footer) throws Exception {

        if (footer.isEmpty()) {
            throw new Exception("The Footer is empty");
        }

        try {

            XWPFHeaderFooterPolicy headerAndFooter = doc.createHeaderFooterPolicy();

            XWPFFooter footerDoc = headerAndFooter.createFooter(STHdrFtr.DEFAULT);
            footerDoc.getParagraphArray(0).createRun().setText(footer);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }
}
