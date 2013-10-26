class @ProductController extends RouteController
  template: 'getProduct'
  action: ()->
    console.log 'Searching for product: ', @params.product, 'User: ', @params.userId
    product = (Products.findOne {_id: @params.product}) || {}
    console.log 'Found product: ', product
    @response.writeHead 200,
      'Content-Type': 'application/json'
    @response.end JSON.stringify product

