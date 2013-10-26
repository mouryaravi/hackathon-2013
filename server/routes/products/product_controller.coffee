class @ProductController extends RouteController
  template: 'getProduct'
  action: ()->
    console.log 'Searching for product: ', @params.product, 'User: ', @params.userId
    product = Products.findOne {_id: @params.product}
    if product
      console.log 'Found product: ', product
    @response.write JSON.stringify product

