<script>

$(document).ready(function() {	

	//select all the a tag with name equal to modal
	$('a[name=modal]').click(function(e) {
		//Cancel the link behavior
		e.preventDefault();
		
		//Get the A tag
		var id = $(this).attr('href');
	
		//Get the screen height and width
		var maskHeight = $(document).height();
		var maskWidth = $(window).width();
	
		//Set heigth and width to mask to fill up the whole screen
		$('#mask').css({'width':maskWidth,'height':maskHeight});
		
		//transition effect		
		$('#mask').fadeIn(1000);	
		$('#mask').fadeTo("slow",0.8);	
	
		//Get the window height and width
		var winH = $(window).height();
		var winW = $(window).width();
              
		//Set the popup window to center
		$(id).css('top',  winH/2-$(id).height()/2);
		$(id).css('left', winW/2-$(id).width()/2);
	
		//transition effect
		$(id).fadeIn(2000); 
	
	});
	
	//if close button is clicked
	$('.window .close').click(function (e) {
		//Cancel the link behavior
		e.preventDefault();
		
		$('.window').fadeOut(2000); 
                $('#mask').fadeOut(2000); 
		//$('#mask').hide();
		//$('.window').hide();
	});		
	
			

	$(window).resize(function () {
	 
 		var box = $('#boxes .window');
 
        //Get the screen height and width
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();
      
        //Set height and width to mask to fill up the whole screen
        $('#mask').css({'width':maskWidth,'height':maskHeight});
               
        //Get the window height and width
        var winH = $(window).height();
        var winW = $(window).width();

        //Set the popup window to center
        box.css('top',  winH/2 - box.height()/2);
        box.css('left', winW/2 - box.width()/2);
	 
	});
	
});

function show_model_window()
{
    if (req.readyState==4 || req.readyState=="complete")
    {
        alert("req.responseText  --> "+req.responseText);
        document.getElementById("body_part").innerHTML = req.responseText;
        //document.getElementById("model_a_link").href();
        $('#model_a_link').click();
    }
}
</script>
<style>

#mask {
  position:absolute;
  left:0;
  top:0;
  z-index:9000;
  background-color:#464646;
  display:none;
}
  
#boxes .window {
  position:fixed;
  left:0;
  top:0;
  width:440px;
  height:200px;
  display:none;
  z-index:9999;
  -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.4);
-moz-box-shadow: 0px 1px 6px rgba(23, 69, 88, .5);
-webkit-border-radius: 12px;
-moz-border-radius: 7px;
border-radius: 7px;
}

#boxes #dialog {
  background-color:#ffffff;
  -webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.4);
  box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.4);
-moz-box-shadow: 0px 1px 6px rgba(23, 69, 88, .5);
-webkit-border-radius: 12px;
-moz-border-radius: 7px;
border-radius: 7px;
padding:5px 5px 20px 5px;
}
.lclose {
width: 31px;
height: 35px;
float: right;
display: block;
cursor: pointer;
margin: 2px;
}

.sprites_img {
background: transparent url(images/close-icon.png) no-repeat;

}
.sprites_img:hover {
background: transparent url(images/close_icon.png) no-repeat;

}
#close_part
{
    width:100%;height: 35px;
    background-color: #ABCEEE;
    background-color: #ABCEEE;
    background-image: -o-linear-gradient(top, #ABCEEE, #8AA8CA);
    background-image: -ms-linear-gradient(top, #ABCEEE, #8AA8CA);
    background-image: -moz-linear-gradient(top, #ABCEEE, #8AA8CA);
    background-image: -webkit-gradient(linear, right top, left top, from(#ABCEEE), to(#8AA8CA));
    background-image: -webkit-linear-gradient(top, #ABCEEE, #8AA8CA);
    background-image: linear-gradient(top, #ABCEEE, #8AA8CA);
    -pie-background: linear-gradient(top, #ABCEEE, #8AA8CA);
    -webkit-border-top-left-radius-: 7px;
    -webkit-border-top-right-radius-: 7px;
    -moz-border-radius-topleft: 7px;
    -moz-border-radius-topright: 7px;
    border-top-left-radius: 7px;
    border-top-right-radius: 7px;    
}
#close_part .title
{
   padding-left: 5px;
   padding-top:3px;
   font-weight: bold;
}
#body_part
{
    overflow: auto;-webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.4);
-moz-box-shadow: 0px 1px 6px rgba(23, 69, 88, .5);
-webkit-border-radius: 12px;
-moz-border-radius: 7px;
border-radius: 7px;
}
</style>
    <a href="#dialog" name="modal" id="model_a_link">&nbsp;</a>


<div id="boxes">

<div id="dialog" class="window" style="width:50px;">
    <div id="close_part"><span class="title">AI Value's</span>
	<a href="#" class="close"/><span class="sprites_img lclose"> </span></a>
</div>
<div id="body_part">
	
    
</div>
</div>


<!-- Mask to cover the whole screen -->
  <div id="mask"></div>
</div>
    