class @StaticVideoController extends RouteController
  template: 'getVideo'
  action: ()->
    console.log 'Searching for video: ', @params.videoId
    webroot = '/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/videos/out/'

    fs = Npm.require 'fs'
    @response.writeHead 200,
      'Content-Type': 'video/mp4',
      'connection': 'close'
      'accept': '*/*'

    fs.readFile webroot + @params.videoId, (err, data)=>
      console.log 'data length', data.length
      @response.end data, 'binary'



