require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')
img      = require('./data/imgBufferData')
path = require('path')
pckjson   = require('./../../package.json')
fs = require('fs')
bufferSplice = require('buffer-splice')


wordMicroservice = supertest.agent("http://#{config.server.ip}:#{config.server.port}")
fileId = ''
testFile = path.join(__dirname,'./data/testTemplate.docx')

describe 'word-microservice rest-API test', ->
  
  it 'should returns the about for the word-microservice', ->
    wordMicroservice
    .get('/about')
    .expect(200)
    .then((res) ->
      res.text.should.eql(pckjson.name + ' - ' + pckjson.version)
    )
  
  
  it 'should create a new template', ->
    wordMicroservice
    .post('/template/add')
    .attach('template', testFile)
    .expect(200)
    .then((res) ->
      fileId = res.text
      res.text.should.type('string')
    )
  
  
  it 'should fails because the template add endpoint only accpets *.docx files format', ->
    testDoomyFile = path.join(__dirname,'./data/imgBufferData.coffee')
    wordMicroservice
    .post('/template/add')
    .attach('template', testDoomyFile)
    .expect(400)
    .then((res) ->
      res.text.should.eql('The only valid extension and format for templates are docx')
    )
  
  it 'should fails because the name for the attached file is not template', ->
    testDoomyFile = path.join(__dirname,'./data/imgBufferData.coffee')
    wordMicroservice
    .post('/template/add')
    .attach('filename', testDoomyFile)
    .expect(500)
    .then((res) ->
      res.error.text.should.startWith('Error: Unexpected field')
    )
  
  it 'should retrieve a previous template by the Id', ->
    wordMicroservice
    .get("/template/get?templateId=#{fileId}&fileName=#{fileId}")
    .expect(200)
    .then((res) ->
      fs.readFile(testFile, (err, data) ->
        data.should.eql(res.body)
      )
    )
  
  it 'should fails retrieve a previous template by an incorrect Id', ->
    wordMicroservice
    .get("/template/get?templateId=55555&fileName=templateDownloaded.docx")
    .expect(400)
    .then((res) ->
      res.text.should.eql("The requested templateId 55555 does not exits")
    )
  
  it 'should fails retrieve a previous template because there is no templateId param provide', ->
    wordMicroservice
    .get("/template/get?fileName=templateDownloaded.docx")
    .expect(400)
    .then((res) ->
      res.text.should.eql('The templateId and fileName params are mandatory on the query')
    )
  
  it 'should fails retrieve a previous template because there is no fileName param provide', ->
    wordMicroservice
    .get("/template/get?templateId=55555")
    .expect(400)
    .then((res) ->
      res.text.should.eql('The templateId and fileName params are mandatory on the query')
    )
    
  

  it 'should inject data for the template and download the result', ->
    this.timeout(4000)
    wordMicroservice
    .post("/word/inject?templateId=#{fileId}&fileName=newDocumentDownloaded.docx")
    .type('json')
    .send('{"name": "Davinel Lulinvega","test": "Foo","normal_something": "bar","isNumber": true,"number": 5,"artists": [{"name": "Beck", "band": "Beck"},{"name": "Jim Morrison", "band": "The Doors"},{"name": "Thorston Moore", "band": "Sonic Youth"}],"parts": [{"name":"screen", "price":56.45, "possition":"top"},{"name":"battery", "price":25, "possition":"inside"}]}')
    .expect(200)
    .then((res) ->
      testDocx = path.join(__dirname,'./data/wordOutput.docx')
      # Deleting some differences from the Buffer
      fileraw = fs.readFileSync(testDocx)
      aBuffer = bufferSplice(fileraw, 1929,2)
      bBuffer = bufferSplice(res.body, 1929, 2)
      aBuffer = bufferSplice(aBuffer, 1931,4)
      bBuffer = bufferSplice(bBuffer, 1931, 4)
      aBuffer = bufferSplice(aBuffer, 3532,4)
      bBuffer = bufferSplice(bBuffer, 3532, 4)
      aBuffer = bufferSplice(aBuffer, 29556,8)
      bBuffer = bufferSplice(bBuffer, 29556, 8)
      aBuffer.should.eql(bBuffer)
    )
  
  it 'should fails the inject data for the template and download the result, because there is no data send to fill the template', ->
    wordMicroservice
    .post("/word/inject?templateId=#{fileId}&fileName=newDocumentDownloaded.docx")
    .type('json')
    .expect(400)
    .then((res) ->
      res.text.should.eql('The templateId and fileName and data for the template are mandatory on the query')
    )
  
  it 'should fails the inject data for the template and download the result, because the file does not exits', ->
    wordMicroservice
    .post("/word/inject?templateId=555555&fileName=newDocumentDownloaded.docx")
    .type('json')
    .send('{"name": "Davinel Lulinvega","test": "Foo","normal_something": "bar","isNumber": true,"number": 5,"artists": [{"name": "Beck", "band": "Beck"},{"name": "Jim Morrison", "band": "The Doors"},{"name": "Thorston Moore", "band": "Sonic Youth"}],"parts": [{"name":"screen", "price":56.45, "possition":"top"},{"name":"battery", "price":25, "possition":"inside"}]}')
    .expect(400)
    .then((res) ->
      res.text.should.eql("The requested templateId 555555 does not exits")
    )
    