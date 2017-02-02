
package com.sysunite.microservice.word.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataJSONInvoice {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("generalText")
    @Expose
    private String generalText;
    @SerializedName("invoiceDetailText")
    @Expose
    private String invoiceDetailText;
    @SerializedName("totalText")
    @Expose
    private String totalText;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("footer")
    @Expose
    private String footer;
    @SerializedName("totalTextGross")
    @Expose
    private String totalTextGross;
    @SerializedName("totalAmountGross")
    @Expose
    private Double totalAmountGross;
    @SerializedName("taxesName")
    @Expose
    private String taxesName;
    @SerializedName("taxPercent")
    @Expose
    private String taxPercent;
    @SerializedName("taxAmount")
    @Expose
    private Double taxAmount;
    @SerializedName("totalTextAfterTaxes")
    @Expose
    private String totalTextAfterTaxes;
    @SerializedName("totalAmountAfterTaxes")
    @Expose
    private Double totalAmountAfterTaxes;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("dataInvoiceEmitter")
    @Expose
    private DataInvoiceEmitter dataInvoiceEmitter;
    @SerializedName("dataInvoiceReceiver")
    @Expose
    private DataInvoiceReceiver dataInvoiceReceiver;
    @SerializedName("invoiceDetail")
    @Expose
    private List<InvoiceDetail> invoiceDetail = null;
    @SerializedName("localize")
    @Expose
    private Localize_ localize;
    @SerializedName("image")
    @Expose
    private Image image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getTotalTextGross() {
        return totalTextGross;
    }

    public void setTotalTextGross(String totalTextGross) {
        this.totalTextGross = totalTextGross;
    }

    public Double getTotalAmountGross() {
        return totalAmountGross;
    }

    public void setTotalAmountGross(Double totalAmountGross) {
        this.totalAmountGross = totalAmountGross;
    }

    public String getTaxesName() {
        return taxesName;
    }

    public void setTaxesName(String taxesName) {
        this.taxesName = taxesName;
    }

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTotalTextAfterTaxes() {
        return totalTextAfterTaxes;
    }

    public void setTotalTextAfterTaxes(String totalTextAfterTaxes) {
        this.totalTextAfterTaxes = totalTextAfterTaxes;
    }

    public Double getTotalAmountAfterTaxes() {
        return totalAmountAfterTaxes;
    }

    public void setTotalAmountAfterTaxes(Double totalAmountAfterTaxes) {
        this.totalAmountAfterTaxes = totalAmountAfterTaxes;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public DataInvoiceEmitter getDataInvoiceEmitter() {
        return dataInvoiceEmitter;
    }

    public void setDataInvoiceEmitter(DataInvoiceEmitter dataInvoiceEmitter) {
        this.dataInvoiceEmitter = dataInvoiceEmitter;
    }

    public DataInvoiceReceiver getDataInvoiceReceiver() {
        return dataInvoiceReceiver;
    }

    public void setDataInvoiceReceiver(DataInvoiceReceiver dataInvoiceReceiver) {
        this.dataInvoiceReceiver = dataInvoiceReceiver;
    }

    public List<InvoiceDetail> getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    public Localize_ getLocalize() {
        return localize;
    }

    public void setLocalize(Localize_ localize) {
        this.localize = localize;
    }

    public String getGeneralText() {
        return generalText;
    }

    public void setGeneralText(String generalText) {
        this.generalText = generalText;
    }

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public String getInvoiceDetailText() {
        return invoiceDetailText;
    }

    public void setInvoiceDetailText(String invoiceDetailText) {
        this.invoiceDetailText = invoiceDetailText;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DataJSONInvoice{" +
                "title='" + title + '\'' +
                ", generalText='" + generalText + '\'' +
                ", invoiceDetailText='" + invoiceDetailText + '\'' +
                ", totalText='" + totalText + '\'' +
                ", header='" + header + '\'' +
                ", footer='" + footer + '\'' +
                ", totalTextGross='" + totalTextGross + '\'' +
                ", totalAmountGross=" + totalAmountGross +
                ", taxesName='" + taxesName + '\'' +
                ", taxPercent='" + taxPercent + '\'' +
                ", taxAmount=" + taxAmount +
                ", totalTextAfterTaxes='" + totalTextAfterTaxes + '\'' +
                ", totalAmountAfterTaxes=" + totalAmountAfterTaxes +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", date='" + date + '\'' +
                ", currency='" + currency + '\'' +
                ", dataInvoiceEmitter=" + dataInvoiceEmitter +
                ", dataInvoiceReceiver=" + dataInvoiceReceiver +
                ", invoiceDetail=" + invoiceDetail +
                ", localize=" + localize +
                ", image=" + image +
                '}';
    }
}


/**

 {
 "title":"Title Invoice",
 "header":"Header for the Invoice",
 "footer":"Footer for the Invoice",
 "totalText":"Total",
 "totalTextGross":"This is the total",
 "totalAmountGross":1254.08,
 "taxesName":"Name for the tax",
 "taxPercent":"21%",
 "taxAmount":213.52,
 "totalTextAfterTaxes":"Amount after taxes",
 "totalAmountAfterTaxes":1517.44,
 "invoiceNumber":"45845877-inv-c1",
 "documentNumber":"ABCD-55555-5555",
 "date":"2017-01-25",
 "currency":"€",
 "generalText":"this is the general text, to write some good things",
 "dataInvoiceEmitter":
 {
 "name":"Name of the emitter",
 "address":
 {
 "street":"name of the street",
 "streetNumber":"256a",
 "zipCode":"5555 AA",
 "city":"The One City"
 },
 "contact":
 {
 "telephone":"+35 555 555 555",
 "mobile":"+31 555 555 555",
 "email":"invoice@mailinator.com",
 "web":"www.invoice.com",
 "localize":
 {
 "telephone":"teléfono",
 "mobile":"móvil",
 "email":"correo",
 "web":"webería"
 }
 },
 "bankData":
 {
 "IBAN":"NL555555555555A55",
 "BIC":"ABCDFG456",
 "KvK":"Name for the bank",
 "BTW":"NL555.55.555.A.55"
 }
 },
 "dataInvoiceReceiver":
 {
 "name":"Name of the Receiver",
 "address":
 {
 "street":"name of the street",
 "streetNumber":"256a",
 "zipCode":"5555 AA",
 "city":"The One City"
 }
 },
 "invoiceDetailText":"Details",
 "invoiceDetail":
 [
 {
 "detail":"The description of the detail",
 "amount":114.54
 },
 {
 "detail":"Other description for the detail",
 "amount":14.54
 }
 ],
 "localize":
 {
 "invoiceNumber":"Número de factura",
 "documentNumber":"Número de documento",
 "date":"fecha"
 }
 }

 */
