$('#AllProducts').on('click', function(e){
	$.get("https://damp-reef-8180.herokuapp.com/services/productservice/product", function(data){
	alert("Data Loaded: " + data);
	console.log(data);
});
});

