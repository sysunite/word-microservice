module.exports = ->

  beforeEach( ->
  )

  # Init
  global.Promise = require('bluebird')
  chai = require('chai')
  chaiAsPromised = require('chai-as-promised')

  chai.use(chaiAsPromised);
  # You need to call chai.should() before being able to wrap everything with should
  chai.should(); 
  
  # Make Chai global (within all tests)
  global.expect = chai.expect
  global.assert = chai.assert
  global.should = chai.should