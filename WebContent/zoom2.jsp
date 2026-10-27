<title>Move mouse on image to Zoom In and Zoom Out</title>
<script type="text/javascript" src="http://demo.mistonline.in/jquery-1.2.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
       $('#img_car').width(500);
       $('#img_car').mouseover(function()
       {
          $(this).css("cursor","pointer");
          $(this).animate({width: "360px"}, 'slow');
       });
     
    $('#img_car').mouseout(function()
      {   
          $(this).animate({width: "200px"}, 'slow');
       });
   });
</script>
<h1>Move mouse on image to Zoom In and Zoom Out</h1>
<br />
<img src="images/ratnapur.png" alt="smile" id="img_car"/>