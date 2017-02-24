require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')
img      = require('./data/imgBufferData')
path = require('path')
pckjson   = require('./../../package.json')


wordMicroservice = supertest.agent("http://#{config.server.ip}:#{config.server.port}")
fileId = ''

describe 'word-microservice rest-API test', ->
  
  it 'should returns the about for the word-microservice', ->
    wordMicroservice
    .get('/about')
    .expect(200)
    .then((res) ->
      res.text.should.eql(pckjson.name + ' - ' + pckjson.version)
    )
      

  it 'should create a new template', ->
    testFile = path.join(__dirname,'./data/test.docx')
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
    testFile = path.join(__dirname,'./data/imgBufferData.coffee')
    wordMicroservice
    .post('/template/add')
    .field('fileName', 'test.dox')
    .attach('template', testFile)
    .expect(400)
    .then((res) ->
      res.text.should.eql('The only valid extension and format for templates are docx')
    )
    
  it 'should retrieve a previous template by the Id', ->
    wordMicroservice
    .get("/template/get?templateId=#{fileId}&fileName=templateDownloaded.docx")
    .expect(200)
    .then((res) ->
      console.log res
    )
      
  it 'should fails retrieve a previous template by incorrect Id', ->
    wordMicroservice
    .get("/template/get?templateId=55555&fileName=templateDownloaded.docx")
    .expect(400)
    .then((res) ->
      res.text.should.eql("The requested templateId #{req.query.templateId} does not exits")
    )
    
    
  it 'should inject data for the template and download the result', ->
    wordMicroservice
    .post('/docx/inject?templateId=#{fileId}&fileName=newDocumentDownloaded.docx')
    .type('json')
    .send('{jsonData}')
    .expect(200)
    .then((res) ->
      console.log res
    )
    
  it 'should fails the inject data for the template and download the result, because the file does not exits', ->
    wordMicroservice
    .post("/docx/inject?templateId=555555&fileName=newDocumentDownloaded.docx")
    .type('json')
    .send('{jsonData}')
    .expect(200)
    .then((res) ->
      console.log res
    )
      
  it 'should fails the inject data for the template and download the result, because there is no data send to fill the template', ->
    wordMicroservice
    .post("/docx/inject?templateId=555555&fileName=newDocumentDownloaded.docx")
    .type('json')
    .expect(200)
    .then((res) ->
      console.log res
    )
    