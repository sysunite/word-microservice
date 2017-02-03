package com.sysunite.microservice.word.model;

import com.sysunite.microservice.word.util.Props;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.UUID;

/**
 * Created by char on 02/02/17.
 */
public class CreateDocX {


    static Logger logger = LoggerFactory.getLogger(CreateDocX.class);

    public String createDocx(String fileName, String jsonStr) throws Exception {

        String tempPath;

        checkJSON(jsonStr);

        XWPFDocument doc = new XWPFDocument();


        jsonStr = jsonStr.substring(2, jsonStr.length() - 2);

        jsonStr = jsonStr.replaceAll("\n", "").replaceAll("\t", "");

        String[] item = jsonStr.split("\",\"");

        // Split on comma for each key-value pair
        for (int i = 0; i < item.length; i++) {

            String it = item[i];

            // Get the individual key-value pair
            String[] keyValue = it.split("\":\"");

//                String key = keyValue[0];
            String key = i == 0 ? keyValue[0].substring(1, keyValue[0].length()) : keyValue[0];

            String value = i == item.length - 1 ? keyValue[1].substring(0, keyValue[1].length() - 1) : keyValue[1];

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
                    Paragraph.create(doc, value, "left", false, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-r"))) {
                try {
                    Paragraph.create(doc, value, "right", false, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-c"))) {
                try {
                    Paragraph.create(doc, value, "center", false, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-c-b"))) {
                try {
                    Paragraph.create(doc, value, "center", true, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-c-i"))) {
                try {
                    Paragraph.create(doc, value, "center", false, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-c-b-i"))) {
                try {
                    Paragraph.create(doc, value, "center", true, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-i"))) {
                try {
                    Paragraph.create(doc, value, "left", false, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-r-i"))) {
                try {
                    Paragraph.create(doc, value, "right", false, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-b"))) {
                try {
                    Paragraph.create(doc, value, "left", true, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-b-i")) ||
                    key.equals(Props.getInstance().getProperty("word.parts.paragraph-i-b"))    ) {
                try {
                    Paragraph.create(doc, value, "left", true, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-r-b"))) {
                try {
                    Paragraph.create(doc, value, "right", true, false, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.paragraph-r-b-i"))) {
                try {
                    Paragraph.create(doc, value, "right", true, true, null);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

            if (key.equals(Props.getInstance().getProperty("word.parts.title-2"))) {
                try {
                    Title.create(doc, value, 2);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (key.equals(Props.getInstance().getProperty("word.parts.title-3"))) {
                try {
                    Title.create(doc, value, 3);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (key.equals(Props.getInstance().getProperty("word.parts.title-4"))) {
                try {
                    Title.create(doc, value, 4);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (key.equals(Props.getInstance().getProperty("word.parts.title-5"))) {
                try {
                    Title.create(doc, value, 5);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (key.equals(Props.getInstance().getProperty("word.parts.title-6"))) {
                try {
                    Title.create(doc, value, 6);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }

        }


        File temp = File.createTempFile(UUID.randomUUID().toString().concat("-").concat(fileName), ".docx");
        tempPath = temp.getAbsolutePath();
        logger.debug(tempPath);
        FileOutputStream out = new FileOutputStream(temp);

        doc.write(out);
        out.close();
        doc.close();

        return tempPath;

    }

    private void checkJSON(String jsonStr) throws JsonParsingException {

        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        reader.read();

    }

}
