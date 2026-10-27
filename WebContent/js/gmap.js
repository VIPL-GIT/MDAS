

function getDevicesMapStatus(){
	clear();
	hideDashboardDiv();
	$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');  
	$.ajax( {
        url:'gmap/mapHome.epx',
        beforeSend:function(){
        	document.getElementById("datapart").innerHTML=document.getElementById("loader").innerHTML;
        },
        success:function(data) {
           $('#datapart').html(data);
        }
     });
}

function getMapDetails(){
	
}

var map;
function initialize(){
	 map = null;
	if( map == null){
    	
	 map = new google.maps.Map(document.getElementById('gmap'), {                    
         zoom: 6,
         center: {lat: 23.9627141 , lng:76.1783224}
         //minZoom: 4,
         //center: vijayawada,
         /*panControl: false,
          mapTypeId:google.maps.MapTypeId.ROADMAP,
          zoomControl: true,
          scaleControl: false,
          zoomControlOptions: {
          style: google.maps.ZoomControlStyle.LARGE,
          position: google.maps.ControlPosition.LEFT_BOTTOM
          }*/
     });
	}
	google.maps.event.trigger(map, 'resize');
	 map.data.loadGeoJson('gmap/kml/MP.json');
	 var featureStyle = {
            // fillColor: 'lightblue',
             //fillColor: '#FFFF00',
             fillOpacity: '0',
             strokeColor: '#5a8ccd',
             //strokeColor:'#6A5ACD',
             strokeWeight: 2
         };
         map.data.setStyle(featureStyle);

         var image = 'gmap/img/marker.png'; 
         var infoWindow = new google.maps.InfoWindow();
	 /*var myParser = new geoXML3.parser({map: map});
     var ctaLayer = new google.maps.KmlLayer({
         url: myParser.parse('gmap/kml/MP.kml'),
         map: map
     });*/
         
     var markers=loadCircleLatLons();
    // console.log("Markers : "+markers);
     var marker = [];
     for (i = 0; i < markers.length; i++) {
         var data = markers[i];
         
         var myLatlng = new google.maps.LatLng(data.lat, data.lng);

         var marker_pres = new google.maps.Marker({
             position: myLatlng,
             map: map,
             icon: image,
             labelContent: "$425K",
             animation: google.maps.Animation.DROP
         });
         (function(marker_pres, data) {

             // Attaching a click event to the current marker
             google.maps.event.addListener(marker_pres, "mouseover", function(e) {
                 infoWindow.setContent(data.title);
                 infoWindow.open(map, marker_pres);
             });
             google.maps.event.addListener(marker_pres, "mouseout", function(e) {
                 infoWindow.close();
             });
             google.maps.event.addListener(marker_pres, "click", function(e) {
                 map.setZoom(9);
                 map.setCenter(marker_pres.getPosition());
                // remove_Markers(data.id);
                 getSubstationsList(map,data.title);                
             });
         })(marker_pres, data);
         marker[i] = marker_pres;
     }
}
function loadCircleLatLons(){
	var jdata;	
	$.ajax({
        'async': false,
        'url': "gmap/kml/circles.json",
        'success': function (data) {
        	jdata = data;
        }
    });
	console.log("jdata  : "+jdata);
	return jdata;
}
var clusterStyles = [
                     {
                       textColor: 'white',
                       url: 'gmap/img/m1.png',
                       height: 50,
                       width: 50
                     },
                    {
                       textColor: 'white',
                       url: 'gmap/img/m2.png',
                       height: 50,
                       width: 50
                     },
                    {
                       textColor: 'white',
                       url: 'gmap/img/m3.png',
                       height: 50,
                       width: 50
                     }
                   ];
function getSubstationsList(map,circlename){
	var image = 'gmap/img/substation_green.png'; 
    var infoWindow = new google.maps.InfoWindow();
    
   
    var container = L.DomUtil.get('map');
    if(container==null){
	    latlng = L.latLng(22.46646667,79.43022167);
	    map = L.map('gmap', {center: latlng, zoom: 10});
	    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	    	maxZoom: 13,
	    	minZoom:2,
	    	attribution: 'OpenStreetMap'
	    	}).addTo(map);
    }
    
	$.ajax({
        'async': false,
       // 'url': "gmap/kml/ss_details.json",
        'url': "gmap/ssLatLons.epx?circlename="+circlename,
        'success': function (data) {
        	var markers=JSON.parse(data);
        	var markersVar = L.markerClusterGroup();
        	console.log(markers[0].meters);
        	for (i = 0; i < markers.length; i++) {
                var data = markers[i].meters;
                //console.log("----"+i+" : "+data.length);
               for(m=0;m<data.length;m++){
            	   var title = "Location : "+data[m].location+"<br>"+
            	               "Meter    : "+data[m].meter+"<br>"+
            	               "Last Comm.Date : "+data[m].lastCommDate+"<br>"+
            	               "Rph-v : "+data[m].rph_v+"<br>"+
            	               "Yph-v : "+data[m].yph_v+"<br>"+
            	               "Bph-v : "+data[m].bph_v+"<br>"+
            	               "Rph-c : "+data[m].rph_c+"<br>"+
            	               "Yph-c : "+data[m].yph_c+"<br>"+
            	               "Bph-c : "+data[m].bph_c;
            	               
       			var marker = L.marker(new L.LatLng(data[m].lat, data[m].lng), { title: title });
       			marker.bindPopup(title);
       			markersVar.addLayer(marker);
               }
        	}//for
        	map.addLayer(markersVar); 
        }
    });
	
}