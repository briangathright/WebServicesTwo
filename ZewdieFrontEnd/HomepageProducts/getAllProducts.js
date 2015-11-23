// $('#AllProducts').on('click', function(e){
// 	$.get("https://damp-reef-8180.herokuapp.com/services/productservice/product", function(data){
// 	alert("Data Loaded: " + data);
// 	console.log(data);
// });
// });

$('#AllProducts').on('click', function(e){
	
			var xmlhttp = new XMLHttpRequest();
			var url = "https://damp-reef-8180.herokuapp.com/services/productservice/productD";

			xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			   var myArr = JSON.parse(xmlhttp.responseText);
			   myFunction(myArr);
			   }
			};

			xmlhttp.open("GET", url, true);
			xmlhttp.send();
	});
});


