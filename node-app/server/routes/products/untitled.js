Router.map(function() {
  this.route('getProduct', {
    where: 'server',
    path: '/product/:product',
    controller: 'ProductController'
  });
  return this.route('addProduct', {
    where: 'server',
    path: '/products',
    controller: 'AddProductController'
  });
});
