

class @VideoProcessor

  processVideo: (id)->
    HTTP.post 'http://localhost:8080/' + id + "/video.json", {}, (err, res)->
      console.log err, res
  
