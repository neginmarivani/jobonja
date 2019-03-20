<html>
<head >
	<title>Projects list</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<ul id="list">
		
	</ul> 
	<button type="button" onClick="retreiveData()">Click Me!</button>
	<script type="text/javascript">
		function retreiveData() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				
				if (this.readyState == 4 && this.status == 200) {
					
					var obj = JSON.parse(this.responseText);
					for(var i=0;i<obj.length ;i++){
						var ul = document.getElementById("list");
						  var li1 = document.createElement("li");
						  li1.appendChild(document.createTextNode("title : " +obj[i].title));
						  ul.appendChild(li1);
						  var li2 = document.createElement("li");
						  li2.appendChild(document.createTextNode("id : " +obj[i].id));
						  ul.appendChild(li2);
						  var li3 = document.createElement("li");
						  li3.appendChild(document.createTextNode("description : " +obj[i].description));
						  ul.appendChild(li3);
						  var x = document.createElement("IMG");
						  x.setAttribute("src", obj[i].imageUrl);
						  x.setAttribute("width", "304");
						  x.setAttribute("height", "228");
						  x.setAttribute("alt", "project picture");
						  ul.appendChild(x);
						  
						  var li5 = document.createElement("li");
						  li5.appendChild(document.createTextNode("deadline : " +obj[i].deadline));
						  ul.appendChild(li5);
						  
						  var li4 = document.createElement("li");
						  li4.appendChild(document.createTextNode("prerequisites : "));
						  ul.appendChild(li4);
						  
						  var ul2 = document.createElement("ul");
						  for(var j=0;j<obj[i].prerequisites.length ;j++){
						 	 var li6 = document.createElement("li");
						 	 li6.appendChild(document.createTextNode("name: "+obj[i].prerequisites[j].name));
						 	 ul2.appendChild(li6);
						 	var li7 = document.createElement("li");
						 	 li7.appendChild(document.createTextNode("point: "+obj[i].prerequisites[j].point));
						 	 ul2.appendChild(li7);
						  }
						  ul.appendChild(ul2);
						  
						  
					}
				}
			};
			xhttp.open("GET", "http://localhost:8080/Phase-2/ProjectsController", true);
			xhttp.send();		
		}
	</script>
</body>
</html>