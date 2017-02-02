import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.util.Units;

import org.apache.poi.xwpf.usermodel.*;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

import java.math.BigInteger;

public class POC {

    public static void main(String[] args) throws Exception {

        XWPFDocument doc= new XWPFDocument();

        // the body content
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run=paragraph.createRun();
        run.setText("The Body:");

        paragraph = doc.createParagraph();
        run=paragraph.createRun();
        run.setText("Lorem ipsum....");

        // create header start
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);

        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

        paragraph = header.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.RIGHT);
        int twipsPerInch =  1440;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

        run = paragraph.createRun();
        run.setText("The Header:");
        run.addTab();

        run = paragraph.createRun();
        String imgFile="/Library/WebServer/Documents/jslab/pocArea/logo.png";
        XWPFPicture picture = run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(50), Units.toEMU(50));
        System.out.println(picture); //XWPFPicture is added
        System.out.println(picture.getPictureData()); //but without access to XWPFPictureData (no blipID)

        String blipID = "";
        for(XWPFPictureData picturedata : header.getAllPackagePictures()) {
            blipID = header.getRelationId(picturedata);
            System.out.println(blipID); //the XWPFPictureData are already there
        }
        picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID); //now they have a blipID also
        System.out.println(picture.getPictureData());

        // create footer start
        XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

        paragraph = footer.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setText("The Footer:");


        doc.write(new FileOutputStream("test1.docx"));

    }
}