Router.map ()->
  @route 'getProduct',
    where: 'server'
    path: '/product/:product'
    controller: 'ProductController'

  @route 'addProduct',
    where: 'server'
    path: '/products'
    controller: 'AddProductController'
