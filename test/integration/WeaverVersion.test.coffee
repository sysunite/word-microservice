require("./../test-suite")
supertest = require('supertest')
should    = require('should')
pckjson   = require('./../../package.json')
config    = require('./../config/test')

weaverServer = supertest.agent("http://#{config.server.ip}:#{config.server.port}")

describe 'Weaver Version rest-API test', ->
  
  it 'should get the weaver-server version', ->
    weaverServer
    .get('/application/version')
    .expect("Content-type",/text/)
    .expect(200)
    .then((res) ->
      res.status.should.equal(200)
      res.text.should.equal(pckjson.version)
    )
    