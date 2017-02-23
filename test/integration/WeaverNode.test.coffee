require("./../test-suite")
supertest = require('supertest')
should    = require('should')
config    = require('./../config/test')

weaverServer = supertest.agent("http://#{config.server.ip}:#{config.server.port}")

describe 'WeaverNode rest-API test', ->

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
  
  it 'should create a node', ->
    ###
     this endpoint is answering with empty array
    ###
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"ciz5imq7p0000quxxbk3z4eaq"}],"target":"$SYSTEM"}')
    .expect(200)
    .then((res) ->
      res.text.should.equal('[]')
    ).then(  ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].should.have.property('nodeId','ciz5imq7p0000quxxbk3z4eaq')
      res.body[0].attributes.should.have.property('createdOn')
    )
    
  
  it 'should add an attribute', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"name","value":"Foo"}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].attributes.should.have.property('name','Foo')
    )
  
  it 'should set a new boolean attribute', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"isBar","value":false}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].attributes.should.have.property('isBar',false)
    )
  
  it 'should set a new number attribute', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"length","value":3}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res, err) ->
      res.body[0].attributes.should.have.property('length',3)
    )
  
  it 'should set a new number double attribute', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"high","value":165.33}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].attributes.should.have.property('high',165.33)
    )
  
  it 'should set an existing attribute with new value', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"name","value":"Bar"}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].attributes.should.have.property('name','Bar')
    )
  
  
  it 'should unset an attribute', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"remove-attribute","id":"ciz5imq7p0000quxxbk3z4eaq","key":"name"}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["ciz5imq7p0000quxxbk3z4eaq"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].attributes.should.not.have.property('name')
    )
  
  it 'should add a new relation', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"one"},{"action":"create-node","id":"two"}, {"action":"create-relation","from":"one","key":"comesBefore","to":"two"}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["one"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].should.have.property('nodeId','one')
      res.body[0].should.have.property('relations')
      res.body[0].relations.should.have.property('comesBefore')
      res.body[0].relations.comesBefore[0].should.have.property('nodeId','two')
    )
  
  it 'should remove a new relation', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"remove-relation","from":"one","key":"comesBefore","to":"two"}],"target":"$SYSTEM"}')
    .then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["one"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    ).then((res) ->
      res.body[0].should.have.property('nodeId','one')
      res.body[0].should.have.property('relations')
      res.body[0].relations.should.not.have.property('comesBefore')
    )
  
  # TODO: do not get the error
  it 'should give an error if node already exists', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"ciz5imq7p0000quxxbk3z4eaq"}],"target":"$SYSTEM"}')
    .then((res, err) ->
    )
  
  # TODO: do not get the error
  it 'should give an error if node does not exists', ->
    weaverServer
    .post('/query')
    .type('json')
    .send('{"query":{"_restrict":["lol"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
    .then((res) ->
      # console.log res
    )
    .catch((err) ->
      # console.log err
    )
  
  it 'should not blow up when saving in circular chain', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"a"},{"action":"create-node","id":"b"},{"action":"create-node","id":"c"},{"action":"create-relation","from":"a","key":"to","to":"b"},{"action":"create-relation","from":"c","key":"to","to":"a"},{"action":"create-relation","from":"b","key":"to","to":"c"},{"action":"create-relation","from":"a","key":"to","to":"b"}],"target":"$SYSTEM"}')
    .then((res, err) ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["a"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res, err) ->
        res.body[0].should.have.property('nodeId','a')
        res.body[0].should.have.property('relations')
        res.body[0].relations.should.have.property('to')
        res.body[0].relations.to[0].should.have.property('nodeId','b')
      )
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["b"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res, err) ->
        res.body[0].should.have.property('nodeId','b')
        res.body[0].should.have.property('relations')
        res.body[0].relations.should.have.property('to')
        res.body[0].relations.to[0].should.have.property('nodeId','c')
      )
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["c"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res, err) ->
        res.body[0].should.have.property('nodeId','c')
        res.body[0].should.have.property('relations')
        res.body[0].relations.should.have.property('to')
        res.body[0].relations.to[0].should.have.property('nodeId','a')
      )
    )
    
  it 'should remove a node', ->
    weaverServer
    .post('/write')
    .type('json')
    .send('{"operations":[{"action":"create-node","id":"norberto"}],"target":"$SYSTEM"}')
    .expect(200)
    .then((res) ->
      res.text.should.equal('[]')
    ).then( ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["norberto"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res) ->
        res.body[0].should.have.property('nodeId','norberto')
        res.body[0].attributes.should.have.property('createdOn')
      )
    ).then( ->
      weaverServer
      .post('/write')
      .type('json')
      .send('{"operations":[{"action":"remove-node","id":"norberto"}],"target":"$SYSTEM"}')
      .expect(200)
    ).then(  ->
      weaverServer
      .post('/query')
      .type('json')
      .send('{"query":{"_restrict":["norberto"],"_equals":{},"_orQueries":[],"_conditions":{},"_include":[],"_select":[],"_count":false,"_hollow":false,"_limit":1,"_skip":0,"_order":[],"_ascending":true},"target":"$SYSTEM"}')
      .then((res) ->
        res.body.should.empty
      )
    )
    