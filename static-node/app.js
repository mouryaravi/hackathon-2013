var nstatic = require('node-static');

//
// Create a node-static server instance to serve the './public' folder
//
var file = new nstatic.Server('/Users/ravi/cf/stack/video-processor-tomcat-7.0.47/videos/out/');

require('http').createServer(function (request, response) {
    request.addListener('end', function () {
        file.serve(request, response);
    }).resume();
}).listen(8090);

console.log('listening on 8090');
