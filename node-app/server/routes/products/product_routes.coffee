Router.map ()->
  @route 'getProduct',
    where: 'server'
    path: '/product/:product'
    controller: 'ProductController'

  @route 'addProduct',
    where: 'server'
    path: '/products'
    controller: 'AddProductController'

  @route 'getVideo',
    where: 'server',
    path: '/videos/out/:videoId'
    controller: 'StaticVideoController'