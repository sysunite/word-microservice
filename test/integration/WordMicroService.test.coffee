require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')
img      = require('./data/imgBufferData')
path = require('path')
pckjson   = require('./../../package.json')
fs = require('fs')


wordMicroservice = supertest.agent("http://#{config.server.ip}:#{config.server.port}")
fileId = ''
testFile = path.join(__dirname,'./data/test.docx')

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
    .field('fileName', 'test.dox')
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
    .field('fileName', 'test.dox')
    .attach('template', testDoomyFile)
    .expect(400)
    .then((res) ->
      res.text.should.eql('The only valid extension and format for templates are docx')
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
    wordMicroservice
    .post('/docx/inject?templateId=#{fileId}&fileName=newDocumentDownloaded.docx')
    .type('json')
    .send('{jsonData}')
    .expect(200)
    .then((res) ->
      lol(false)
    )
  
  it 'should fails the inject data for the template and download the result, because the file does not exits', ->
    wordMicroservice
    .post("/docx/inject?templateId=555555&fileName=newDocumentDownloaded.docx")
    .type('json')
    .send('{jsonData}')
    .expect(200)
    .then((res) ->
      lol(false)
    )
  
  it 'should fails the inject data for the template and download the result, because there is no data send to fill the template', ->
    wordMicroservice
    .post("/docx/inject?templateId=555555&fileName=newDocumentDownloaded.docx")
    .type('json')
    .expect(200)
    .then((res) ->
      lol(false)
    )
    