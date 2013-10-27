Meteor.saveFile = (blob, name, id, type, callback) ->
  console.log 'in save file...'
  fileReader = new FileReader()
  encoding = 'binary'
  type = 'binary'
  switch type
    when 'text'
      method = 'readAsText'
      encoding = 'utf8'
    when 'binary'
      method = 'readAsBinaryString'
      encoding = 'binary'
    else
      method = 'readAsBinaryString'
      encoding = 'binary'

  fileReader.onload = (file)->
    Meteor.call('saveFile', file.srcElement.result, name, id, encoding, callback)
  
  fileReader[method](blob)
