Template.addProduct.events = 
  'click .btn': (e, template)->
    e.preventDefault()
    console.log 'Clicked on button'
    name = template.find('#inputName').value
    price = template.find('#inputPrice').value
    id = template.find('#inputId').value
    thumbnail = template.find('#inputThumbnail').files[0]
    video = template.find('#inputVideo').files[0]

    console.log 'data: ', [name, price, id, thumbnail, video]
    Meteor.saveFile thumbnail, 'thumbnail', id
    Meteor.saveFile video, 'video', id
