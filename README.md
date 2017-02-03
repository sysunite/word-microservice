# Excel Microservice
Microservice for Word file manipulations using [Apache POI](https://poi.apache.org/).

WIP


*POST* http://localhost:9268/word/create?data=foo&fileName=bar.docx

```
  {
    "title": "Title Invoice",
    "header": "Header for the Invoice",
    "footer": "Footer for the Invoice",
    "totalText": "Total",
    "totalTextGross": "This is the total",
    "totalAmountGross": 1254.08,
    "taxesName": "Name for the tax",
    "taxPercent": "21%",
    "taxAmount": 213.52,
    "totalTextAfterTaxes": "Amount after taxes",
    "totalAmountAfterTaxes": 1517.44,
    "invoiceNumber": "45845877-inv-c1",
    "documentNumber": "ABCD-55555-5555",
    "date": "2017-01-25",
    "currency": "€",
    "generalText": "this is the general text, to write some good things",
    "dataInvoiceEmitter": {
      "name": "Name of the emitter",
      "address": {
        "street": "name of the street",
        "streetNumber": "256a",
        "zipCode": "5555 AA",
        "city": "The One City"
      },
      "contact": {
        "telephone": "+35 555 555 555",
        "mobile": "+31 555 555 555",
        "email": "invoice@mailinator.com",
        "web": "www.invoice.com",
        "localize": {
          "telephone": "Telephone",
          "mobile": "Mobil",
          "email": "E-main",
          "web": "Web site"
        }
      },
      "bankData": {
        "IBAN": "NL555555555555A55",
        "BIC": "ABCDFG456",
        "KvK": "Name for the bank",
        "BTW": "NL555.55.555.A.55"
      }
    },
    "dataInvoiceReceiver": {
      "name": "Name of the Receiver",
      "address": {
        "street": "name of the street",
        "streetNumber": "256a",
        "zipCode": "5555 AA",
        "city": "The One City"
      }
    },
    "invoiceDetailText": "Details",
    "invoiceDetail": [
      {
        "detail": "The description of the detail",
        "amount": 114.54
      },
      {
        "detail": "Other description for the detail",
        "amount": 14.54
      }
    ],
    "localize": {
      "invoiceNumber": "Invoice Number",
      "documentNumber": "Document Number",
      "date": "Date"
    }
  }
```



*POST* http://localhost:9268/poc/json?fileName=foo

```
{
	"title":"Main title, heading 1",
	"paragraph":"This is a paragraph, a normal one, align to the left is the default.",
	"paragraph":"This is other paragraph.",
	"title-2":"This is heading 2",
	"abecd":"other thing, that never will go to the DocX cose abecd does not means anythig",
	"title-3":"This is heading 3",
	"footer":"This is the Footer",
	"header":"This is a Header",
	"title-4":"This is heading 4",
	"title":"Other heading 1",
	"paragraph-right":"This is a paragraph with right align.",
	"paragraph-right-bold":"This is a bold paragraph with right align.",
	"paragraph-right-italic":"This is an italic paragraph with right align.",
	"paragraph-right-bold-italic":"This is an italic and bold paragraph with right align.",
	"title-5":"This is heading 5",
	"paragraph":"Other paragraph",
	"paragraph-bold":"A bold paragraph",
	"paragraph-italic":"An italic paragraph",
	"paragraph-bold-italic":"A bold and italic paragraph",
	"paragraph-italic-bold":"An italic bold paragraph",
	"title-6":"This is heading 6",
	"paragraph-center":"This is a centered paragraph",
	"paragraph-center-bold":"This is a centered and bold paragraph",
	"paragraph-center-italic":"This is a centered and italic paragraph",
	"paragraph-center-bold-italic":"This is a centered and italic paragraph"
}
```