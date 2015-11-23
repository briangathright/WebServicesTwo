function getAllProducts(){
// 	$.get("https://damp-reef-8180.herokuapp.com/services/productservice/product", function(data){
// 	alert("Data Loaded: " + data);
// 	console.log(data);

// });

$.ajax({
        url: "https://damp-reef-8180.herokuapp.com/services/productservice/product",
        method: 'GET',
        // params: {dataType: 'json'}
        // dataType: "json"
      }).done(function(data) {
      		// var y = 0;
      		// x = data.getElementsByTagName("Product")[y];
      		// console.log(x);
      		// while(x!=null){
      		// 	y++;
      		// 	x = data.getElementsByTagName("Product")[y];
      		// }
      		// document.getElementById("div2").innerHTML=y;
      		// var curr = "";
      		// for (var i = y - 1; i >= 0; i--) {
      		// 	table += "<tr><td>" +
      		// 	curr += data.getElementsByTagName("Product")[i].childNodes[0].nodeValue + '</br>';
      		// 	curr += data.getElementsByTagName("Product")[i].childNodes[0].nodeValue + '</br>';

      		// };
      		var i;
      		var table="<tr><th>ID</th><th>Price</th></tr>";
      		var x = data.getElementsByTagName("Product");
      		for (i = 0; i <x.length; i++) { 
			    table = table + "<tr><td>" +
			    x[i].getElementsByTagName("id")[0].childNodes[0].nodeValue +
			    "</td><td>" +
			    x[i].getElementsByTagName("productPrice")[0].childNodes[0].nodeValue +
			    "</td></tr>";
			    console.log(table);

			  }
      		// document.getElementById("div2").innerHTML=curr;
			

			document.getElementById("div2").innerHTML = table;

      	// console.log(data)
      });
}


// function myFunction(data) {
//   var i;
//   var xmlDoc = xml.responseXML;
//   var table="<tr><th>Artist</th><th>Title</th></tr>";
//   var x = xmlDoc.getElementsByTagName("CD");
//   for (i = 0; i <x.length; i++) { 
//     table += "<tr><td>" +
//     x[i].getElementsByTagName("ARTIST")[0].childNodes[0].nodeValue +
//     "</td><td>" +
//     x[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue +
//     "</td></tr>";
//   }
//   document.getElementById("demo").innerHTML = table;
// }

