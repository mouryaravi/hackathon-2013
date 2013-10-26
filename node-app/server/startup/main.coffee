Meteor.startup ()->
  sampleProducts = [_id: 'GALS4', basic: 
      'name': 'Samsung Galaxy S4'
      'videoUrl': 'http://www.youtube.com/watch?v=oUynugn9AYs&feature=player_detailpage'
      'thumbnail': 'http://www.samsung.com/global/microsite/galaxys4/mobile/images/img_gallery_w1.jpg'
      'images': [
        'http://img7.flixcart.com/image/mobile/g/p/z/samsung-galaxy-s4-i9500-400x400-imadkbmdrjx5rxvg.jpeg'
        'http://img7.flixcart.com/image/mobile/g/p/z/samsung-galaxy-s4-i9500-400x400-imadkbmdphgzywsf.jpeg'
        'http://img7a.flixcart.com/image/mobile/g/p/z/samsung-galaxy-s4-i9500-100x100-imadkbmd8hkjhzum.jpeg'
      ]
      'price': '32000'
      'buyUrl': 'http://www.mysmartprice.com/mobile/samsung-galaxy-s4-msp2439'


    _id: 'GALN3', basic:
      'name': 'Samsung Galaxy Note 3'
      'videoUrl': 'https://www.youtube.com/watch?feature=player_embedded&v=-Fo5x7ZIPCM'
      'thumbnail': 'http://img7a.flixcart.com/image/mobile/f/b/x/samsung-galaxy-note-3-galaxy-note-3-n9000-400x400-imadzrekbmswhyxa.jpeg'
      'images': [
        'http://img7.flixcart.com/image/mobile/f/b/x/samsung-galaxy-note-3-galaxy-note-3-n9000-400x400-imadzr6qzpdtss8z.jpeg'
        'http://img7.flixcart.com/image/mobile/f/b/x/samsung-galaxy-note-3-galaxy-note-3-n9000-400x400-imadzrekykx8zcjr.jpeg'
        'http://img7.flixcart.com/image/mobile/f/b/x/samsung-galaxy-note-3-galaxy-note-3-n9000-400x400-imadzr5brydyvd5d.jpeg'
      ]
      'price': '46000'
      'buyUrl': 'http://www.mysmartprice.com/mobile/samsung-galaxy-note-3-msp2587'
  ]

  console.log 'Initializing...'

  products = Products.find()
  if products.count() == 0
    console.log 'Adding new products data...'
    _.each sampleProducts, (product)->
      Products.insert product
