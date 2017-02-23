require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')

weaverServer = supertest.agent("http://#{config.server.ip}:#{config.server.port}")

describe 'WeaverQuery rest-API test', ->

  ###
   wipe
   
   curl --request POST \
    --url http://localhost:9487/wipe \
    --header 'cache-control: no-cache' \
    --header 'content-type: application/json' \
    --data '{"target":"$SYSTEM"}'
   
  ###

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
    
  it 'should restrict to a single node', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"a"}, {"action":"create-node","id":"b"},{"action":"create-node","id":"c"}],"target":"$SYSTEM"}')
    .expect(200)
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["a"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":100,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body.should.lengthOf(1)
      res.body[0].should.have.property('nodeId','a')
    )
    
    
  it 'should restrict to multiples nodes', ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["a", "c"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":100,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res) ->
        res.body.should.lengthOf(2)
        res.body[0].should.have.property('nodeId','a')
        res.body[1].should.have.property('nodeId','c')
      )
    
  it 'should do equalTo a boolean', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"a","key":"isRed","value":true},{"action":"create-attribute","id":"b","key":"isRed","value":false},{"action":"create-attribute","id":"c","key":"isBlue","value":true}],"target":"$SYSTEM"}')
    .expect(200)
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":[],"_equals":{"isRed":true},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":100,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body.should.lengthOf(1)
      res.body[0].should.have.property('nodeId','a')
    )
    
  it 'should do equalTo a string', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"a","key":"name","value":"Project A"},{"action":"create-attribute","id":"b","key":"name","value":"Project B"},{"action":"create-attribute","id":"c","key":"name","value":"Project C"}],"target":"$SYSTEM"}')
    .expect(200)
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":[],"_equals":{"name":"Project B"},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":100,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body.should.lengthOf(1)
      res.body[0].should.have.property('nodeId','b')
    )
    