jQuery(document).ready(function(){
								
	///// TRANSFORM CHECKBOX /////							
	jQuery('input:checkbox').uniform();
	
	///// LOGIN FORM SUBMIT /////
	jQuery('#login').submit(function(){
	
		if(jQuery('#user_id').val() == '' && jQuery('#user_password').val() == '') {
			jQuery('.nousername').fadeIn();
			jQuery('.nopassword').hide();
			return false;	
		}
		if(jQuery('#user_id').val() != '' && jQuery('#user_password').val() == '') {
			jQuery('.nopassword').fadeIn().find('.userlogged h4, .userlogged a span').text(jQuery('#user_id').val());
			jQuery('.nousername,.username').hide();
			return false;;
		}
	});
	
	///// ADD PLACEHOLDER /////
	jQuery('#user_id').attr('placeholder','Username');
	jQuery('#user_password').attr('placeholder','Password');
});
