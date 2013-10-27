

class @VideoProcessor

  processVideo: (id)->
    HTTP.post 'http://localhost:8080/' + id + "/video.json", {}, (err, res)->
      console.log err, res
      if res and res.statusCode == 406
        Products.insert {
          _id: id
          basic: {
            'videoUrl': '/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/videos/out/' + id + '.mp4'
          }
        }
      else if err
        throw (new Meteor.Meteor.Error 500, 'Error 500: Huh, We screwed something', err)
