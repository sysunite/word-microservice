# Word Microservice
Microservice for Word file manipulations using [docxtemplater](https://github.com/open-xml-templating/docxtemplater).

Swagger API documentation at http://localhost:9268/doc/api

## Running

You can use Docker:

`docker pull sysunite/word-microservice`

## Endpoints

**GET** `/about`
Version of this  microservice

**POST** `/template/add`
Accepts a docx as template, save it and returns the id of this new docx template for future calls

**GET** `/template/get`
Download a previously uploaded template based on a template ID.

**POST** `/word/inject`
Inject data into docx file according to a previously added template.
And returns the generated word file.

The data to fill the template fields in json:
```
{
    "name": "Davinel Lulinvega",
    "test": "Foo",
    "normal_something": "bar",
    "isNumber": true,
    "number": 5,
    "artists":
    [
        {"name": "Beck", "band": "Beck"},
        {"name": "Jim Morrison", "band": "The Doors"},
        {"name": "Thorston Moore", "band": "Sonic Youth"}
    ],
    "parts":
    [
        {"name":"screen", "price":56.45, "possition":"top"},
        {"name":"battery", "price":25, "possition":"inside"}
    ]
}
```