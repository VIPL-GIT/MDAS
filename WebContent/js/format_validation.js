
//this is for checking empty or not
function validate_Empty_Data(txtbx,msg)
{
    var str = txtbx.value;
    if(str=="")
    {
        alert(msg);
        txtbx.focus();
    }
}

//this is for minimum and maximum length check
function validate_Length(txtbx,min_len,msg,error_div)
{
    var str = txtbx.value;
    if(str.length!=0)
    if(str.length<eval(min_len))
    {
        txtbx.style.border = "2px solid red";
        error_div.innerHTML = msg;
        txtbx.focus();
        return false;
    }
    return true;
}

//this is for cheking two input values are equal or not
function validate_Equality(txtbx1,txtbx2,msg)
{
    var str1 = txtbx1.value;
    var str2 = txtbx2.value;
    if(str1.length!=0)
    if(str1!=str2)
    {
        alert(msg);
        return false;
        //txtbx1.focus();
    }
    return true;
}
//this is for email format validation
function validate_Email(email,txtbx)
{
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
    {
        alert("Not a valid email...");
        txtbx.focus();
        return false;
    }
    return true;
}

//this is for url format validation
function validate_URL(url) 
{
        var regexp = /^(((ht|f){1}(tp:[/][/]){1})|((www.){1}))[-a-zA-Z0-9@:%_\+.~#?&//=]+$/;
	return regexp.test(url);
}

//this is for ip address format validation
function validate_IP(ipaddr) 
{
    var re = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
    if (re.test(ipaddr)) 
    {
        var parts = ipaddr.split(".");
        if (parseInt(parseFloat(parts[0])) == 0) {return false;}
        for (var i=0; i<parts.length; i++) 
        {
            if (parseInt(parseFloat(parts[i])) > 255) {return false;}
        }
        return true;
    } 
    else 
    {
        return false;
    }
}

//this is for date format validation
function validate_Date(input)
{
    var validformat=/^\d{2}\/\d{2}\/\d{4}$/; //Basic check for format validity
    var returnval=false;
    if (!validformat.test(input.value))
        alert("Invalid Date Format. Please correct and submit again.");
    else
    { //Detailed check for valid date ranges
        var dayfield=input.value.split("/")[0];
        var monthfield=input.value.split("/")[1];
        var yearfield=input.value.split("/")[2];
        var dayobj = new Date(yearfield, monthfield-1, dayfield);
        if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
            alert("Invalid Day, Month, or Year range detected. Please correct and submit again.");
        else
            returnval=true;
    }
    if (returnval==false) 
        input.select()
    return returnval
}

