Pomise = require('bluebird')
JSZip = require('jszip')
Docxtemplater = require('docxtemplater')
# ImageModule=require('docxtemplater-image-module')
fs = require('fs')
path = require('path')
logger = require('./util/logger')
cuid = require('cuid')

module.exports =
  generateDocXFromTemplate: (templatePath, jsonData) ->
    new Promise((resolve, reject) =>
      try
        content = fs.readFileSync(templatePath, 'binary')
        zip = new JSZip(content)
        doc = new Docxtemplater()
        doc.loadZip(zip)
        doc.setData(jsonData)
        doc.render()
        buf = doc.getZip().generate({type: 'nodebuffer'})
        uidfp = path.resolve(__dirname, '../filesTmp/'+ cuid() + '.docx')
        fs.writeFile(uidfp, buf, (err) ->
          if !err
            resolve(uidfp)
          else
            reject()
        )
      catch error
        e = {
          message: error.message,
          name: error.name,
          stack: error.stack,
          properties: error.properties
        }
        logger.error({error:e})
        reject({error:e})
    )
    
    
  cleanGeneratedDocX: (filePath) ->
    fs.unlink(filePath, (err) ->
      if err
        logger.error("Error trying to delete #{filePath}")
    )