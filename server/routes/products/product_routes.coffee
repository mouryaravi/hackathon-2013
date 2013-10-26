Router.map ()->
  @route 'getProduct',
    where: 'server'
    path: '/product/:product'
    controller: 'ProductController'
