<html>
<head >
	<title>add Skill</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body onload="getSkillFromServer()">
	
		<label>مهارت خود را برای افزودن به سرور انتخاب کنید </label>
		<form onsubmit="retreiveData()">
		
			<select name="skills" id ="skills">
			</select>
			
		 <input type="submit" value="Submit">
		</form>
	

	<script type="text/javascript">
	
		function retreiveData() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {	
				if (this.readyState == 4 && this.status == 200) {			
					var obj = JSON.parse(this.responseText);
					alert(this.responseText);
					/*
					var x = document.createElement("P");                        // Create a <p> element
					var t = document.createTextNode(obj.msg);    // Create a text node
					x.appendChild(t);                                           // Append the text to <p>
					document.body.appendChild(x);
					alert(obj.msg);
					*/
					
				}
			};
			var params= 'nameOfwantedSkill=JSON';
			xhttp.open("POST", "http://localhost:8080/Phase-2/AddSkillController", true);
			xhttp.send(params);		
		}
		
		function getSkillFromServer(){
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				
				if (this.readyState == 4 && this.status == 200) {
					var obj = JSON.parse(this.responseText);
					var select = document.getElementById("skills");
					
					for(var i=0;i<obj.length ;i++){
						var option = document.createElement("option");
						option.text = obj[i].name;
					 	select.add(option);
					}
				}
			};
			
			xhttp.open("GET", "http://142.93.134.194:8000/joboonja/skill", true);
			xhttp.send();	
		}

		
	</script>
</body>
</html>