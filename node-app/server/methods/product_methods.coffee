
Meteor.methods
  addProduct: (productParams)->
    console.log 'Adding product [method]: ', productParams

  uploadFile: (file, options)->
    console.log 'uploading file: ', file.name, options

  saveFile: (file, name, id, encoding)->
    console.log 'got file: ', name, id
    fs = Npm.require('fs')
    if name == 'thumbnail'
      fs.writeFile '/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/images/' + id + '.jpg', file, encoding, (err)->
        if err
          console.log err
          throw (new Meteor.Error 'cant upload file: ' + id, err)
        else
          console.log 'Added image file: ', id
    else
      fs.writeFile '/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/videos/' + id + '.mp4', file, encoding, (err)->
        if err
          throw (new Meteor.Error 'cant upload file: ' + id, err)
        else
          console.log 'File is added : ', id, 'encoding: ', encoding
      new VideoProcessor().processVideo(id)