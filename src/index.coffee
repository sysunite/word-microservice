
Pomise = require('bluebird')
config = require('config')
logger = require('./util/logger')
express = require('express')
pckjsn = require('../package.json')
multer = require('multer')

port = config.get('server.port')

upload = multer({ dest: 'files/', fileFilter: (req, file, cb) ->
  logger.debug(file.originalname.split('.')[1])
  if file.originalname.split('.')[1] isnt 'docx'
    req.fileValidationExtension = 'The only valid extension and format for templates are docx'
    cb(null, false)
  else
    cb(null, true)
})

app = express()

###
 Enabling CORS
###

app.all('*', (req, res, next) ->
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next()
)


app.get('/', (req, res) ->
  res.send("#{pckjsn.name} - #{pckjsn.version}")
)

###
 Accepts a docx as template, save it and returns the id of this new docx template
 for future calls
###

app.post('/template/add', upload.single('fileName'), (req, res, next) ->
  if req.fileValidationExtension
    res.status(400).send(req.fileValidationExtension)
  else
    res.send(req.file.filename)
)

###
 Download a previously uploaded template based on a template ID.
###

app.get('/template/get', (req, res) ->
  res.send('Hi there!')
)

###
 Inject data into docx file according to a previously added template
###

app.post('/docx/inject', (req, res) ->
  res.send('Hi there!')
)

app.listen(port, ->
  logger.info("word-microservice listening at #{port}")
)
