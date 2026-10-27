
//this is for number (5,2) format validation
function validate_NumberFormat(e,str) 
{
    var keynum;
    var keychar;
    var numcheck;

    var unicode=e.charCode? e.charCode : e.keyCode;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }

    keychar = String.fromCharCode(keynum);
    var str2 = str + keychar;
    var myString = /^[-+]?\d{1,5}(\.\d{1,2})?$/
    numcheck = new RegExp(myString).test(str2);
    if(numcheck || keynum==46 || keynum==8 || unicode==38 || unicode==39 || unicode==40 || unicode==9 || unicode==46 || unicode==36)
    {
        return true;
    }
    else
    {
        var st1 = str.substr(0,str.length-1);
        return false;
    }
}

//this is for entering only alphabets in a text field
function validate_AlphabetOnlyText(e,str)
{
    var keynum;
    var keychar;
    var numcheck;

    var unicode=e.charCode? e.charCode : e.keyCode;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }

    keychar = String.fromCharCode(keynum);
    var str2 = str + keychar;
    var myString = /[a-zA-Z]$/
    numcheck = new RegExp(myString).test(str2);
    if(numcheck || keynum==46 || keynum==8 || unicode==39 || unicode==9)
    {
        return true;
    }
    else
    {
        var st1 = str.substr(0,str.length-1);
        return false;
    }
}

//this is for entering only numbers in a text field
function validate_NumberText(e,str)
{
    var keynum;
    var keychar;
    var numcheck;

    var unicode=e.charCode? e.charCode : e.keyCode;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }

    keychar = String.fromCharCode(keynum);
    var str2 = str + keychar;
    var myString = /[0-9]$/
    numcheck = new RegExp(myString).test(str2);
    if(numcheck || keynum==46 || keynum==8 || unicode==39 || unicode==9)
    {
        return true;
    }
    else
    {
        var st1 = str.substr(0,str.length-1);
        return false;
    }
}

//this is for entering only alphanumeric  in a text field
function validate_AlphaNumericText(e,str)
{
    var keynum;
    var keychar;
    var numcheck;

    var unicode=e.charCode? e.charCode : e.keyCode;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }

    keychar = String.fromCharCode(keynum);
    var str2 = str + keychar;
    var myString = /[a-zA-Z0-9]$/
    numcheck = new RegExp(myString).test(str2);
    if(numcheck || keynum==46 || keynum==8 || unicode==39 || unicode==9)
    {
        return true;
    }
    else
    {
        var st1 = str.substr(0,str.length-1);
        return false;
    }
}

//this is for entering only special characters  in a text field
function validate_SpecialCharText(e,str)
{
    var keynum;
    var keychar;
    var numcheck;

    var unicode=e.charCode? e.charCode : e.keyCode;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }

    keychar = String.fromCharCode(keynum);
    var str2 = str + keychar;
    var myString = /[^a-zA-Z0-9]$/
    numcheck = new RegExp(myString).test(str2);
    if(numcheck || keynum==46 || keynum==8 || unicode==39 || unicode==9)
    {
        return true;
    }
    else
    {
        var st1 = str.substr(0,str.length-1);
        return false;
    }
}


var pattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;
x = 46;
function validateIP1(e) {
    if (e.which != 8 && e.which != 0 && e.which != x && (e.which < 48 || e.which > 57)) {
        console.log(e.which);
        return false;
    }
}
function validateIP2(obj,disp_id,alert_msg){
    var this1 = $(obj);
    if (!pattern.test(this1.val())) {
        if(""==alert_msg){
             $('#validate_ip_'+disp_id).text('Not Valid IP');
        }else{
            $('#validate_ip_'+disp_id).text(alert_msg);
        }
        //alert()
        while (this1.val().indexOf("..") !== -1) {
            this1.val(this1.val().replace('..', '.'));
        }
        x = 46;
    } else {
        x = 0;
        var lastChar = this1.val().substr(this1.val().length - 1);
        if (lastChar == '.') {
            this1.val(this1.val().slice(0, -1));
        }
        var ip = this1.val().split('.');
        if (ip.length == 4) {
            $('#validate_ip_'+disp_id).text(''); 
        }
    }
}
//only for numbers
function NumericValidation(eventObj)
{
    var keycode;

    if(eventObj.keyCode) //For IE
        keycode = eventObj.keyCode;
    else if(eventObj.Which)
        keycode = eventObj.Which;  // For FireFox
    else
        keycode = eventObj.charCode; // Other Browser

    if (keycode!=8) //if the key is the backspace key
    {
        if (keycode<48||keycode>57) //if not a number
            return false; // disable key press
        else
            return true; // enable key press
    }

}

//For Float values and numeric values

function extractNumber(obj, decimalPlaces, allowNegative){
	var temp = obj.value;	
	// avoid changing things if already formatted correctly
	var reg0Str = '[0-9]*';
	if (decimalPlaces > 0) {
		reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
	} else if (decimalPlaces < 0) {
		reg0Str += '\\.?[0-9]*';
	}
	reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
	reg0Str = reg0Str + '$';
	var reg0 = new RegExp(reg0Str);
	if (reg0.test(temp)) return true;

	// first replace all non numbers
	var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
	var reg1 = new RegExp(reg1Str, 'g');
	temp = temp.replace(reg1, '');

	if (allowNegative) {
		// replace extra negative
		var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
		var reg2 = /-/g;
		temp = temp.replace(reg2, '');
		if (hasNegative) temp = '-' + temp;
	}
	
	if (decimalPlaces != 0) {
		var reg3 = /\./g;
		var reg3Array = reg3.exec(temp);
		if (reg3Array != null) {
			// keep only first occurrence of .
			//  and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
			var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
			reg3Right = reg3Right.replace(reg3, '');
			reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
			temp = temp.substring(0,reg3Array.index) + '.' + reg3Right;
		}
	}	
	obj.value = temp;
}
function blockNonNumbers(obj, e, allowDecimal, allowNegative)
{
	var key;
	var isCtrl = false;
	var keychar;
	var reg;
		
	if(window.event) {
		key = e.keyCode;
		isCtrl = window.event.ctrlKey
	}
	else if(e.which) {
		key = e.which;
		isCtrl = e.ctrlKey;
	}	
	if (isNaN(key)) return true;	
	keychar = String.fromCharCode(key);	
	// check for backspace or delete, or if Ctrl was pressed
	if (key == 8 || isCtrl)	{
		return true;
	}
	reg = /\d/;
	var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
	var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;	
	return isFirstN || isFirstD || reg.test(keychar);
}
 

