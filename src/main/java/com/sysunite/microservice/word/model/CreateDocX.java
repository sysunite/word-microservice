package com.sysunite.microservice.word.model;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
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


            CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
            CTPageMar pageMar = sectPr.addNewPgMar();
            pageMar.setLeft(BigInteger.valueOf(620L));
            pageMar.setTop(BigInteger.valueOf(60L));
            pageMar.setRight(BigInteger.valueOf(620L));
            pageMar.setBottom(BigInteger.valueOf(60L));


            /**
             * One line
             */

            XWPFParagraph line = doc.createParagraph();
            XWPFRun rLine = line.createRun();
            rLine.addCarriageReturn();
            rLine.addCarriageReturn();


            /**
             * Data from emitter
             */

            XWPFParagraph emitter = doc.createParagraph();
            emitter.setAlignment(ParagraphAlignment.RIGHT);


            XWPFRun rEmitter = emitter.createRun();
            rEmitter.setBold(true);
            rEmitter.setText(dataJSONInvoice.getDataInvoiceEmitter().getName());
            rEmitter.addCarriageReturn();
            StringBuilder stringAddress = new StringBuilder();
            stringAddress.append(dataJSONInvoice.getDataInvoiceEmitter().getAddress().getStreet())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getAddress().getStreetNumber());
            rEmitter.setText(stringAddress.toString());
            rEmitter.addCarriageReturn();
            StringBuilder stringZipCity = new StringBuilder();
            stringZipCity.append(dataJSONInvoice.getDataInvoiceEmitter().getAddress().getZipCode())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getAddress().getCity());
            rEmitter.setText(stringZipCity.toString());
            rEmitter.addCarriageReturn();
            rEmitter.addCarriageReturn();
            StringBuilder stringContact = new StringBuilder();
            stringContact.append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getLocalize().getTelephone())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getTelephone());
            rEmitter.setText(stringContact.toString());
            rEmitter.addCarriageReturn();
            stringContact = new StringBuilder();
            stringContact.append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getLocalize().getMobile())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getMobile());
            rEmitter.setText(stringContact.toString());
            rEmitter.addCarriageReturn();
            stringContact = new StringBuilder();
            stringContact.append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getLocalize().getEmail())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getEmail());
            rEmitter.setText(stringContact.toString());
            rEmitter.addCarriageReturn();
            stringContact = new StringBuilder();
            stringContact.append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getLocalize().getWeb())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getContact().getWeb());
            rEmitter.setText(stringContact.toString());
            rEmitter.addCarriageReturn();
            rEmitter.addCarriageReturn();
            StringBuilder stringBank = new StringBuilder();
            stringBank.append("IBAN ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getBankData().getIBAN());
            rEmitter.setText(stringBank.toString());
            rEmitter.addCarriageReturn();
            stringBank = new StringBuilder();
            stringBank.append("BIC ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getBankData().getBIC());
            rEmitter.setText(stringBank.toString());
            rEmitter.addCarriageReturn();
            stringBank = new StringBuilder();
            stringBank.append("KvK ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getBankData().getKvK());
            rEmitter.setText(stringBank.toString());
            rEmitter.addCarriageReturn();
            stringBank = new StringBuilder();
            stringBank.append("BTW ")
                    .append(dataJSONInvoice.getDataInvoiceEmitter().getBankData().getBTW());
            rEmitter.setText(stringBank.toString());
            rEmitter.addCarriageReturn();

            rEmitter.setFontSize(9);

            /**
             * Invoice number
             */

            XWPFParagraph invoiceNumber = doc.createParagraph();
            invoiceNumber.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun rInvoceNumber = invoiceNumber.createRun();
            rInvoceNumber.setText(dataJSONInvoice.getLocalize().getInvoiceNumber().concat(": "));
            rInvoceNumber.setText(dataJSONInvoice.getInvoiceNumber());
            rInvoceNumber.addCarriageReturn();
            rInvoceNumber.addCarriageReturn();
            rInvoceNumber.setText(dataJSONInvoice.getLocalize().getDocumentNumber().concat(": "));
            rInvoceNumber.setText(dataJSONInvoice.getDocumentNumber());
            rInvoceNumber.addCarriageReturn();


            /**
             * Data from receiver
             */

            XWPFParagraph receiver = doc.createParagraph();
            receiver.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun rReceiver = receiver.createRun();
            rReceiver.setBold(true);
            rReceiver.setText(dataJSONInvoice.getDataInvoiceReceiver().getName());
            rReceiver.addCarriageReturn();

            StringBuilder stringReceiver = new StringBuilder();
            stringReceiver.append(dataJSONInvoice.getDataInvoiceReceiver().getAddress().getStreet())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceReceiver().getAddress().getStreetNumber());
            rReceiver.setText(stringReceiver.toString());
            rReceiver.addCarriageReturn();
            stringReceiver = new StringBuilder();
            stringReceiver.append(dataJSONInvoice.getDataInvoiceReceiver().getAddress().getZipCode())
                    .append(" ")
                    .append(dataJSONInvoice.getDataInvoiceReceiver().getAddress().getCity());
            rReceiver.setText(stringReceiver.toString());
            rReceiver.addCarriageReturn();
//            rReceiver.addCarriageReturn();


            /**
             * Date
             */

            XWPFParagraph date = doc.createParagraph();
            date.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun rDate = date.createRun();
            rDate.setText(dataJSONInvoice.getLocalize().getDate().concat(": "));
            rDate.setText("Date: ".concat(dataJSONInvoice.getDate()));
            rDate.addCarriageReturn();

            /**
             * title Heading 1
             */

            XWPFParagraph title = doc.createParagraph();
            title.setStyle("Heading 1");
            XWPFRun r0 = title.createRun();
            r0.setText(dataJSONInvoice.getTitle());

            /**
             * General text
             *
             */

            XWPFParagraph general = doc.createParagraph();
            general.setAlignment(ParagraphAlignment.LEFT);

            XWPFRun rGeneral = general.createRun();
            rGeneral.setText(dataJSONInvoice.getGeneralText());


            /**
             * Total section
             *
             */

            //            title

            XWPFParagraph totalTitle = doc.createParagraph();
            totalTitle.setStyle("Heading 2");
            totalTitle.setWordWrapped(true);

            XWPFRun totalTitleRun = totalTitle.createRun();
            totalTitleRun.setText(dataJSONInvoice.getTotalText());

            XWPFParagraph totalInvoice = doc.createParagraph();
            totalInvoice.setAlignment(ParagraphAlignment.DISTRIBUTE);

            XWPFRun rTotalInvoice = totalInvoice.createRun();

            rTotalInvoice.addCarriageReturn();

            rTotalInvoice.setText(dataJSONInvoice.getTotalTextGross()
                    .concat("\t\t\t\t\t\t")
                    .concat(dataJSONInvoice.getCurrency())
                    .concat(" ")
                    .concat(dataJSONInvoice.getTotalAmountGross().toString()));
            rTotalInvoice.addCarriageReturn();
            StringBuilder stringTotal = new StringBuilder();
            stringTotal.append(dataJSONInvoice.getTaxesName())
                    .append(" ")
                    .append("(")
                    .append(dataJSONInvoice.getTaxPercent())
                    .append(")")
                    .append("\t\t\t\t\t\t")
                    .append(dataJSONInvoice.getCurrency())
                    .append(" ")
                    .append(dataJSONInvoice.getTaxAmount().toString());
            rTotalInvoice.setText(stringTotal.toString());
            rTotalInvoice.addCarriageReturn();
            rTotalInvoice.setText(dataJSONInvoice.getTotalTextAfterTaxes()
                    .concat("\t\t\t\t\t\t")
                    .concat(dataJSONInvoice.getCurrency())
                    .concat(" ")
                    .concat(dataJSONInvoice.getTotalAmountAfterTaxes().toString()));
            rTotalInvoice.addCarriageReturn();

            /**
             * Detail section
             *
             */

            XWPFParagraph detailTitle = doc.createParagraph();
            detailTitle.setStyle("Heading 3");

            XWPFRun rDetailTitle = detailTitle.createRun();
            rDetailTitle.setText(dataJSONInvoice.getInvoiceDetailText());

            //            details
            XWPFParagraph details = doc.createParagraph();
            details.setAlignment(ParagraphAlignment.BOTH);

            XWPFRun rDetails = details.createRun();

            for (InvoiceDetail invoiceDetail:dataJSONInvoice.getInvoiceDetail()) {
                rDetails.setText(invoiceDetail.getDetail()
                        .concat("\t\t\t\t\t\t")
                        .concat(dataJSONInvoice.getCurrency())
                        .concat(" ")
                        .concat(invoiceDetail.getAmount().toString()));
                rDetails.addCarriageReturn();
            }

            /**
             * Header and footer
             */

            XWPFHeaderFooterPolicy headerAndFooter = doc.createHeaderFooterPolicy();

            //    HEADER
            XWPFHeader header = headerAndFooter.createHeader(STHdrFtr.DEFAULT);
            header.getParagraphArray(0).createRun().setText(dataJSONInvoice.getHeader());

            //    FOOTER
            XWPFFooter footer = headerAndFooter.createFooter(STHdrFtr.DEFAULT);
            footer.getParagraphArray(0).createRun().setText(dataJSONInvoice.getFooter());


            dataJSONInvoice.getImage().saveTofile();

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
