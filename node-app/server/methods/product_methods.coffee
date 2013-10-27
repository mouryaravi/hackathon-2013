
Meteor.methods
  addProduct: (productParams)->
    console.log 'Adding product: ', productParams

  uploadFile: (file, options)->
    console.log 'uploading file: ', file.name, options

  saveFile: (file, name, id, encoding)->
    console.log 'got file: ', name, id
    if name == 'thumbnail'
      return
    fs = Npm.require('fs')
    fs.writeFile '/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/videos/' + id + '.mp4', file, encoding, (err)->
      if err
        throw new Error 'cant upload file: ' + id
      else
        console.log 'File is added : ', id, 'encoding: ', encoding
    new VideoProcessor().processVideo(id)