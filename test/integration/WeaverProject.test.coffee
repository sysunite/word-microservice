require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')

weaverServer = supertest.agent("http://#{config.server.ip}:#{config.server.port}")

describe 'WeaverProject rest-API test', ->

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
    
    
  it 'should create a new project', ->
    this.timeout(4000)
    weaverServer
    .post('/project/create')
    .type('json')
    .send('{"id":"mzx5lindj0000y3xx7l4z10s1","target":"$SYSTEM"}')
    .expect(200)
    .then((res) ->
      res.status.should.equal(200)
    )
  
  
  
  it 'should checks if the new project is ready', ->
    ###
     This endpoint has some inconsistency sometimes the answering is
     '{"ready":false}' and others is '{"ready":true}'
    ###
  
    weaverServer
    .post('/project/ready')
    .type('json')
    .send('{"id":"mzx5lindj0000y3xx7l4z10s1","target":"$SYSTEM"}')
    .expect(200)
    .then((res) ->
      res.status.should.equal(200)
    )

  it 'should list all the projects', ->
    weaverServer
    .get('/project')
    .expect(200)
    .then((res) ->
      res.body[0].should.have.property('id','mzx5lindj0000y3xx7l4z10s1')
    )
  
  it 'should deletes the project', ->
    weaverServer
    .post('/project/delete')
    .type('json')
    .send('{"id":"mzx5lindj0000y3xx7l4z10s1"}')
    .expect(200)
  