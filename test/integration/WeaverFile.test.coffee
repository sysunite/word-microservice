require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')
img      = require('./data/imgBufferData')

weaverServer = supertest.agent("http://#{config.server.ip}:#{config.server.port}")
file = ''

describe 'WeaverFile rest-API test', ->

  it 'should wipe the entire SYSTEM', ->
    weaverServer
    .post('/wipe')
    .type('json')
    .send('{"target":"$SYSTEM"}')
    .expect(200)
    .then((res) ->
      res.status.should.equal(200)
      res.text.should.equal('OK')
    )
  
  it 'should create a new file', ->
    weaverServer
    .post('/uploadFile')
    .type('json')
    .send('{"buffer":{"type":"Buffer","data":['+img.data+']},"target":"area51","fileName":"weaverIcon.png"}')
    .expect(200)
    .then((res) ->
      file = res.text
      res.text.should.containEql('-weaverIcon.png')
    )
    
  it 'should retrieve a file by fileName', ->
    weaverServer
    .get('/downloadFile?payload={"fileName":"' + file + '","target":"area51"}')
    .expect(200)
    .then((res) ->
      json = JSON.stringify(res.body)
      dataJson = JSON.parse(json)
      dataJson.data.should.eql(img.data)
    )
  
  
  ###
   TODO: fix this error on server
  ###
  
  it 'should fails retrieving a file, because the file does not exits on server', ->
    weaverServer
    .get('/downloadFile?payload={"fileName":"foo.bar","target":"area51"}')
    .expect(503)
    .then((res) ->
      error = JSON.parse(res.text)
      if res.error
        error.code.should.equal('NoSuchKey')
    )
    
  ###
   TODO: fix this error on server
  ###
  it 'should fails retrieving a file, because the project does not exits on server', ->
    weaverServer
    .get('/downloadFile?payload={"fileName":"' + file + '","target":"area56"}')
    .expect(503)
    .then((res, err) ->
      error = JSON.parse(res.text)
      if res.error
        error.code.should.equal('NoSuchBucket')
    )
  
  it 'should retrieve a file by ID', ->
    fileId = "#{file}".split('-')[0]
    weaverServer
    .get('/downloadFileByID?payload={"id":"' + fileId + '","target":"area51"}')
    .expect(200)
    .then((res) ->
      json = JSON.stringify(res.body)
      dataJson = JSON.parse(json)
      dataJson.data.should.eql(img.data)
    )
    
  ###
   TODO: need to inject error
  ###
  
  
  it 'should fails retrieving a file by ID, because there is no file matching this ID', ->
    weaverServer
    .get('/downloadFileByID?payload={"id":"555","target":"area51"}')
    .expect(503)
    .then((res) ->
      error = JSON.parse(res.text)
      if res.error
        error.code.should.equal(603)
    )
  
  
  it 'should deletes a file by name', ->
    weaverServer
    .post('/deleteFile')
    .type('json')
    .send('{"fileName":"' + file + '","target":"area51"}')
    .expect(200)
    
  
  it 'should deletes a file by id', ->
    weaverServer
    .post('/uploadFile')
    .type('json')
    .send('{"buffer":{"type":"Buffer","data":['+img.data+']},"target":"area51","fileName":"weaverIcon.png"}')
    .expect(200)
    .then((res) ->
      file = res.text
      res.text.should.containEql('-weaverIcon.png')
      fileId = "#{file}".split('-')[0]
      weaverServer
      .post('/deleteFileByID')
      .type('json')
      .send('{"id":"' + fileId + '","target":"area51"}')
      .expect(200)
    )
    
  
  it 'should fails trying to delete a file because the project does not exists', ->
    weaverServer
    .post('/deleteFile')
    .type('json')
    .send('{"fileName":"' + file + '","target":"area56"}')
    .expect(503)
    .then((res) ->
      error = JSON.parse(res.text)
      if res.error
        error.code.should.equal(603)
    )
    
  
  it 'should fails trying to delete a file by ID because the project does not exists', ->
    weaverServer
    .post('/deleteFileByID')
    .type('json')
    .send('{"id":"' + file + '","target":"area56"}')
    .expect(503)
    .then((res) ->
      error = JSON.parse(res.text)
      if res.error
        error.code.should.equal(603)
    )
