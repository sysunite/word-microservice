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