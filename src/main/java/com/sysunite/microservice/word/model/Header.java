package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by char on 02/02/17.
 */
public class Header {

    static Logger logger = LoggerFactory.getLogger(Header.class);

    /**
     * Creates a Header
     */
    public static void create(XWPFDocument doc, String header) throws Exception {

        if (header.isEmpty()) {
            throw new Exception("The header is empty");
        }

        try {
            XWPFHeaderFooterPolicy headerAndFooter = doc.createHeaderFooterPolicy();

            XWPFHeader headerDoc = headerAndFooter.createHeader(STHdrFtr.DEFAULT);
            headerDoc.getParagraphArray(0).createRun().setText(header);
            
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

    }

}
