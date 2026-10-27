<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <script>
  $(".test").keydown(function(event) { 
	  alert("alert");
	    return false;
	});

  
  </script>
  
  
  <script>
  
  function tab1(){
	  var date = document.getElementsByClassName('date').value;
	  alert("date"+date);
  }
function tab2(){
	alert("tab2");
	var element = document.getElementsByClassName("nav-list")[0];
	element.addEventListener("click", myFunction);
	function myFunction(e) {
			var elems = document.querySelector(".active");
	    if(elems !=null) {
	      elems.classList.remove("active");
	    }
	    e.target.className = "active";
		}
	
  }
function tab3(){
	 alert("tab3");	  
}
function tab4(){
	 alert("tab4");  
}
  </script>
  <style>
  	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	header {
		width: 100%;
		height: auto;
		max-width: 600px;
		margin: 0 auto;
	}
	nav {
		width: 100%;
		height: 40px;
		background-color: cornflowerblue;
	}
	ul {
		list-style-type: none;
	}
	li {
		display: inline-block;
	}
	a {
		text-decoration: none;
		padding: 8px 15px;
		display: block;
		text-transform: capitalize;
    	background-color: darkgray;
    	color: #fff;
	}
	a.active {
		background-color: cornflowerblue;
	}
  </style>
</head>
<body>

<header>
	<nav>
		<ul class="nav-list">
			<li><a href="javascript:tab1();">home</a></li>
			<li><a href="javascript:tab2();">about</a></li>
			<li><a href="javascript:tab3();">service</a></li>
			<li><a href="javascript:tab4();">profile</a></li>
			
		</ul>
	</nav>
</header>
<input type="text" class="test" id="date" value="10">
</body>
</html>
