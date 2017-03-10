# This file resides in the git repository but do not track it in git by using the following command:
# git update-index --assume-unchanged config/local.coffee
#
# Use it for custom local configuration here to override default configurations
module.exports =
  application:
    sounds:
      muteAll: false
  logging:
    console: 'silly'