
function GetXmlHttpObject()
{
    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        xmlHttp = new window.XMLHttpRequest;
    } else {
        try {
            xmlHttp = new ActiveXObject("MSXML2.XMLHTTP.3.0");
        } catch (ex) {
            return null;
        }
    }
    return xmlHttp;
}//GetXmlHttpObject

var selectedLevel = "";
function newShowZones(id)
{
    alert("in show zones = " + id)
    selectedLevel = "discom";
    if (id != "NULL")
    {
       
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "getZones_1.jsp";
        url = url + "?discom_id=" + id;
//         alert("show zones url = "+url);
        xmlHttp.onreadystatechange = showZonesAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
}
function showZonesAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No Zones are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            for (var j = document.getElementById("zone").options.length - 1; j >= 0; j--)
            {
                document.getElementById("zone").remove(j);
            }
            var combo = document.getElementById("zone");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }

}
function zoneLevel(zone_id)
{
//    alert("in zoneLevel = "+id)
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading1').innerHTML;
    showCircles(zone_id);
    if (zone_id > 0) {
        showGraphts('all', zone_id);
    } else {
        selectedLevel = "discom";
        selectedLevelId = document.getElementById("discom").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';
}

function newShowCircles(zone_id)
{
    //alert("in circles "+zone_id);
    selectedLevel = "zone";
    if (zone_id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getCircles.jsp";
        url = url + "?zone_id=" + zone_id;
//        alert(url);
        xmlHttp.onreadystatechange = showCirclesAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previous circle names        
        for (var j = document.getElementById("circleId").options.length - 1; j > 0; j--)
        {
            document.getElementById("circleId").remove(j);
        }
        //to delete previous division names
        if ($('#divisionId').length) {
            for (var j = document.getElementById("divisionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("divisionId").remove(j);
            }
        }
        //to delete previous subdivision names
        if ($('#subdivisionId').length) {
            for (var j = document.forms[0].subdivisionname.options.length - 1; j > 0; j--)
            {
                document.forms[0].subdivisionname.remove(j);
            }
        }
        //to delete previuos mandal names
        if ($('#mandalId').length) {
            for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
            {
                document.getElementById("mandalId").remove(j);
            }
        }
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }

    }
}
function showCirclesAction()
{

    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
//        alert("showdata = "+showdata);
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No Circles are available");
        } else if (strar.length > 1)
        {

            var strname = strar[1];
            for (var j = document.getElementById("circleId").options.length; j >= 0; j--)
            {
                document.getElementById("circleId").remove(j);
            }
            //to delete previous division names
//            if ($('#divisionId').length > 1) {
                for (var j = document.getElementById("divisionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("divisionId").remove(j);
                }
//            }
            //to delete previous subdivision names
//            if ($('#subdivisionId').length > 1) {
//                for (var j = document.forms[0].subdivisionname.options.length - 1; j > 0; j--)
//                {
//                    document.forms[0].subdivisionname.remove(j);
//                }
                for (var j = document.getElementById("subdivisionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("subdivisionId").remove(j);
                }             
//            }
            //to delete previuos mandal names
//            if ($('#mandalId').length > 1) {
                for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("mandalId").remove(j);
                }
//            }
            //to delete previous section names
//            if (!$('#sectionId').length > 1) {
                for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("sectionId").remove(j);
                }
//            }
            //to delete previous substation names
//            if ($('#ssId').length > 1) {
                for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("ssId").remove(j);
                }
//            }
            var combo = document.getElementById("circleId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function circleLevel(circle_id)
{
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    showDivisions(circle_id);
    if (circle_id > 0) {
        showGraphts('all', circle_id);
    } else {
        selectedLevel = "zone";
        selectedLevelId = document.getElementById("zone").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';
}
function newShowDivisions(id)
{
    selectedLevel = "circle";
    if (id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getDivisions.jsp";
        url = url + "?circle_id=" + id;
//        alert(url);
        xmlHttp.onreadystatechange = showDivisionsAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previous division names
        for (var j = document.getElementById("divisionId").options.length - 1; j > 0; j--)
        {
            document.getElementById("divisionId").remove(j);
        }
        //to delete previous subdivision names
        if ($('#subdivisionId').length) {
            for (var j = document.forms[0].subdivisionname.options.length - 1; j > 0; j--)
            {
                document.forms[0].subdivisionname.remove(j);
            }
        }
        //to delete previuos mandal names
        if ($('#mandalId').length) {
            for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
            {
                document.getElementById("mandalId").remove(j);
            }
        }
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }
}
function showDivisionsAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
//        alert("showdata = "+showdata);
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No Divisions are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            for (var j = document.getElementById("divisionId").options.length - 1; j >= 0; j--)
            {
                document.getElementById("divisionId").remove(j);
            }
            //to delete previous subdivision names
//            if ($('#subdivisionId').length > 1) {
                for (var j = document.getElementById("subdivisionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("subdivisionId").remove(j);
                }  
//            }//to delete previuos mandal names
//            if ($('#mandalId').length > 1) {
                for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("mandalId").remove(j);
                }
//            }
            //to delete previous section names
//            if ($('#sectionId').length > 1) {
                for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("sectionId").remove(j);
                }
//            }
            //to delete previous substation names
//            if ($('#ssId').length > 1) {
                for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("ssId").remove(j);
                }
//            }

            var combo = document.getElementById("divisionId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function divisionLevel(division_id)
{
   
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    //showMandals_divWise(division_id);
    newShowSubDivisions(division_id);
    if (division_id > 0)
        showGraphts('all', division_id);
    else {
        selectedLevel = "circle";
        selectedLevelId = document.getElementById("circleId").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';
}
function newShowSubDivisions(emp_value)
{
    if (emp_value > 0)
    {
        //alert("emp value is"+emp_value);
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getSubDivisions.jsp";
        url = url + "?emp_id=" + emp_value;

        xmlHttp.onreadystatechange = showSubDivisionsStateChanged;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);

    } else
    {
        //to delete previous subdivision names
        for (var j = document.forms[0].subdivisionname.options.length - 1; j > 0; j--)
        {
            document.forms[0].subdivisionname.remove(j);
        }
        //to delete previuos mandal names
        if ($('#mandalId').length) {
            for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
            {
                document.getElementById("mandalId").remove(j);
            }
        }
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }

}
function showSubDivisionsStateChanged()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        //alert("show"+showdata);
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            //  alert("Please Select Employee Id");
        } else if (strar.length > 1)
        {
            var strname = strar[1];
            for (var j = document.getElementById("subdivisionId").options.length - 1; j >= 0; j--)
            {
                document.getElementById("subdivisionId").remove(j);
            }
            //to delete previuos mandal names
//            if ($('#mandalId').length > 1) {
                for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("mandalId").remove(j);
                }
//            }
            //to delete previous section names
//            if ($('#sectionId').length > 1) {
                for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("sectionId").remove(j);
                }
//            }
            //to delete previous substation names
//            if ($('#ssId').length > 1) {
                for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("ssId").remove(j);
                }
//            }
            var combo = document.getElementById("subdivisionId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for

        }

    }
}
function newShowMandals(id){
    selectedLevel = "division";
    if (id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getMandals_master.jsp";
        url = url + "?subdivid=" + id;
        // alert(url);
        xmlHttp.onreadystatechange = showMandalsAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previuos mandal names
        if ($('#mandalId').length) {
            for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
            {
                document.getElementById("mandalId").remove(j);
            }
        }
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }
}
function showMandals_divWise(id)
{
    selectedLevel = "division";
    if (id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getMandals.jsp";
        url = url + "?divid=" + id;
        // alert(url);
        xmlHttp.onreadystatechange = showMandalsAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previuos mandal names
        if ($('#mandalId').length) {
            for (var j = document.getElementById("mandalId").options.length - 1; j > 0; j--)
            {
                document.getElementById("mandalId").remove(j);
            }
        }
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }

}
function showMandalsAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        var strar = showdata.split(":");
//        alert('strar '+strar);
        if (strar.length == 1)
        {
            alert("No Mandals are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            //to delete previuos mandal names
//            if ($('#mandalId').length) {
                for (var j = document.getElementById("mandalId").options.length - 1; j >= 0; j--)
                {
                    document.getElementById("mandalId").remove(j);
                }
//            }
            //to delete previous section names
//            if ($('#sectionId').length > 1) {
                for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("sectionId").remove(j);
                }
//            }
            //to delete previous substation names
//            if ($('#ssId').length > 1) {
                for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("ssId").remove(j);
                }
//            }
            var combo = document.getElementById("mandalId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function mandalLevel(mandalid)
{
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    showSections(mandalid);
    if (mandalid > 0)
        showGraphts('all', mandalid);
    else {
        selectedLevel = "division";
        selectedLevelId = document.getElementById("divisionId").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';
}
function newShowSections(id)
{
    selectedLevel = "mandal";
    if (id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getSections.jsp";
        url = url + "?mandalid=" + id;
        xmlHttp.onreadystatechange = showSectionsAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previous section names
        if ($('#sectionId').length) {
            for (var j = document.getElementById("sectionId").options.length - 1; j > 0; j--)
            {
                document.getElementById("sectionId").remove(j);
            }
        }
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }
}
function showSectionsAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No sections are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            //to delete previous section names
//            if ($('#sectionId').length) {
                for (var j = document.getElementById("sectionId").options.length - 1; j >= 0; j--)
                {
                    document.getElementById("sectionId").remove(j);
                }
//            }
            //to delete previous substation names
//            if ($('#ssId').length > 1) {
                for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
                {
                    document.getElementById("ssId").remove(j);
                }
//            }
            var combo = document.getElementById("sectionId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function sectionLevel(section_id) {
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    showSubStations(section_id);
    if (section_id > 0)
        showGraphts('all', section_id);
    else {
        selectedLevel = "mandal";
        selectedLevelId = document.getElementById("mandalId").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';
}
function newShowSubStations(id)
{
    selectedLevel = "section";
    if (id > 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getSubstations.jsp";
        url = url + "?section_id=" + id;
        // alert(url);
        xmlHttp.onreadystatechange = showSubstationsAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    } else {
        //to delete previous substation names
        if ($('#ssId').length) {
            for (var j = document.getElementById("ssId").options.length - 1; j > 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
        }
    }
}
function showSubstationsAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No SubStations are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            for (var j = document.getElementById("ssId").options.length - 1; j >= 0; j--)
            {
                document.getElementById("ssId").remove(j);
            }
            var combo = document.getElementById("ssId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function substationLevel(substation_id)
{
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    if (substation_id > 0) {
        showFeeders(substation_id);
        showGraphts('all', substation_id);
    } else {
        selectedLevel = "section";
        selectedLevelId = document.getElementById("sectionId").value;
        showGraphts('all', selectedLevelId);
    }
    document.getElementById('meterType').value = 'all';

}
function showFeeders(id)
{
    selectedLevel = "substation";
    if (id != 0)
    {
        xmlHttp = GetXmlHttpObject();
        if (xmlHttp == null)
        {
            alert("Browser does not support HTTP Request");
            return;
        }
        var url = "MasterData/getFeeders.jsp";
        url = url + "?substation_id=" + id;
        //alert(url);
        xmlHttp.onreadystatechange = showFeedersAction;
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
    }
}
function showFeedersAction()
{
    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
    {
        var showdata = xmlHttp.responseText;
        var strar = showdata.split(":");
        if (strar.length == 1)
        {
            alert("No Feeders are available");
        } else if (strar.length > 1)
        {
            var strname = strar[1];

            for (var j = document.getElementById("feederId").options.length - 1; j >= 0; j--)
            {
                document.getElementById("feederId").remove(j);
            }
            var combo = document.getElementById("feederId");
            for (var i = 0; i < strar.length; i++)
            {
                var option = document.createElement("option");
                option.text = strar[i].substring(strar[i].indexOf("_") + 1);//textb.value;
                option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;
                try {
                    combo.add(option, null); //Standard
                } catch (error) {
                    combo.add(option); // IE only
                }//try
            }//for
        }
    }
}
function feederLevel(feederId) {
    selectedLevel = "Feeder";
    document.getElementById('pi_chart').innerHTML = document.getElementById('loading').innerHTML;
    showGraphts('all', feederId);
    document.getElementById('meterType').value = 'all';

}

function oldSss()
    {

        ssSel=document.getElementById("ssId");
        var ssid=ssSel.options[ssSel.selectedIndex].value;
        var ssName=ssSel.options[ssSel.selectedIndex].text;
//        document.getElementById("sstwo").style.visibility="visible";
//        document.getElementById("newsid").value=ssid;
//        document.getElementById("ssname").value=ssName;
        mdSel=document.getElementById("mandalId");
        var mdid=mdSel.options[mdSel.selectedIndex].value;
        xmlHttp=GetXmlHttpObject();

        if (xmlHttp==null)
        {
            alert ("Browser does not support HTTP Request");
            return;
        }
        var url="MasterData/getSections.jsp?mandalid="+escape(mdid);
//         alert("url is "+url);

        xmlHttp.onreadystatechange=oldSsAction;
        xmlHttp.open("GET",url,true);
        xmlHttp.send(null);

    }
    function oldSsAction()
    {
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        {

            var showdata = xmlHttp.responseText;
//            alert("showdata = "+showdata);
            var strar = showdata.split(":");
            if(strar.length==1)
            {
                //  alert("Please Select Employee Id");
            }
            else if(strar.length>1)
            {
                var strname = strar[1];

                //----- Values inserting into Combo Box Dynamicaly------
                for(var j=document.forms[0].newsection.options.length-1;j>=0;j--)
                {
                    document.forms[0].newsection.remove(j);
                }
                var combo = document.getElementById("newsection");
                for(var i=0;i<strar.length;i++)
                {
                    var option = document.createElement("option");
                    option.text = strar[i].substring(strar[i].indexOf("_")+1);//textb.value;
                    option.value = strar[i].substring(0, strar[i].indexOf("_"));//textb.value;

                    try {
                        combo.add(option, null); //Standard
                    }catch(error) {
                        combo.add(option); // IE only
                    }//try
                }//for

            }
        }
    }



    function searchForMeterss() {
//      alert('=searchForMeterss()=');
  //alert("Hello");
  document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML;//Kumar
      document.getElementById("dtr").innerHTML = "";
      
      
      
      //document.getElementById("DataPart").innerHTML = document.getElementById("dcu_loading").innerHTML; 
//      alert("in admin hierarchy js")
      var searchText = document.getElementById("searchText").value;
//      alert("searchText = "+searchText);
      var cat = document.getElementById("cat").value;
//      alert("category = "+cat);
      var discomSel = document.getElementById("discom");
//      alert("discomSel = "+discomSel)
      var zoneSel = document.getElementById("zone");
      var circleSel = document.getElementById("circleId");
      var divisionSel = document.getElementById("divisionId");
      var sdSel = document.getElementById("subdivisionId");
      var mandalSel = document.getElementById("mandalId");
      var sectionSel = document.getElementById("sectionId");
      var substationSel = document.getElementById("ssId");
      var searchSel = document.getElementById("search");
//      alert("searchSel = "+searchSel);

      var discomVal = discomSel.options[discomSel.selectedIndex].value;
//      alert("discom val = "+discomVal);
      var zoneVal = zoneSel.options[zoneSel.selectedIndex].value;
      var circleVal = circleSel.options[circleSel.selectedIndex].value;
      var divisionVal = divisionSel.options[divisionSel.selectedIndex].value;
      var sdVal = sdSel.options[sdSel.selectedIndex].value;
      var mandalVal = mandalSel.options[mandalSel.selectedIndex].value;
      var sectionVal = sectionSel.options[sectionSel.selectedIndex].value;
      var ssVal = substationSel.options[substationSel.selectedIndex].value;
//      alert("ssVal = "+ssVal);
      var searchVal = searchSel.options[searchSel.selectedIndex].value;
      //alert("searchVal = "+searchVal);

      var searchType = 0;
      var optVal = 0;
      if (discomVal == "-1") {
          alert("Please Select Discom");
          return 0;
      }
      if (cat == "0") {
          alert("Please Select Category");
          return 0;
      }
      if (searchVal != 0) {
          if (searchText == "" && searchVal != "unmapped") {
              alert("Please enter data to search");
              document.body.style.cursor = 'default';
              return 0;
          } else {
              if (searchVal == "location") {
                  searchType = 8;
                  optVal = searchText;
              } else if (searchVal == "meter") {
                  searchType = 9;
                  optVal = searchText;
              } else if (searchVal == "unmapped") {
                  searchType = 10;
                  optVal = searchText;
              }
          }//else
      } else {

          if (ssVal > 0) {
              searchType = 7;
              optVal = ssVal;
          } else if (sectionVal > 0) {
              searchType = 6;
              optVal = sectionVal;
          } else if (mandalVal > 0) {
              searchType = 5;
              optVal = mandalVal;
          } else if (sdVal > 0) {
              searchType = 4;
              optVal = sdVal;
          } else if (divisionVal > 0) {
              searchType = 3;
              optVal = divisionVal;
          } else if (circleVal > 0) {
              searchType = 2;
              optVal = circleVal;
          } else if (zoneVal > 0) {
              searchType = 1;
              optVal = zoneVal;
          } else if (discomVal > 0) {
              searchType = 11;
              optVal = discomVal;
          } else {
          }//end else
      }
     
      searchXmlHttp = GetXmlHttpObject();
      if (searchXmlHttp == null)
      {
          alert("Browser does not support HTTP Request");
          return;
      }
      //alert("getsearchmeter");
      var url = "getSearchMeters.jsp?meterId=" + escape(searchText) + "&opt=" + escape(optVal) + "&searchType=" + escape(searchType) + "&meterLoc=" + escape(cat);
//       alert(url);

      //------------Removing the previous values---------------------
//      for (var j = document.forms[0].dtr.options.length - 1; j >= 0; j--) {
//          document.forms[0].dtr.remove(j);
//      }//end for
      if(document.getElementById("instant1"))//Kumar - throws error in system management page
      {
          document.getElementById("instant1").checked=true;//KUMAR
          $("#cont-1-1").load(location.href + " #cont-1-1>*", "");//KUMAR
      }
     
      
          
      searchXmlHttp.onreadystatechange = state_DTR_Changed;
      searchXmlHttp.open("GET", url, true);
      searchXmlHttp.send(null);

  }//end method

    
    
    function state_DTR_Changed(){
        
        if (searchXmlHttp.readyState == 4 || searchXmlHttp.readyState == "complete")
        {
            
            document.getElementById("DataPart").innerHTML ="";
            
            if(document.getElementById("instant1"))
            {
                //alert("exists");
                document.getElementById("instant1").checked=true;//KUMAR
                $("#cont-1-1").load(location.href + " #cont-1-1>*", "");//KUMAR
            }
           
            
            var showdata = searchXmlHttp.responseText;
           // alert("showdata = "+showdata);
            showdata = showdata.toString().replace("\n", "");
//            alert("showdata = "+showdata);
            var strar = showdata.split(":");
//            alert("strar = "+strar);
            if (strar.length == 1)
            {
            } else if (strar.length > 1)
            {
                var strname = strar[1];
                //----- Values inserting into Combo Box Dynamicaly------
//                for (var j = document.forms[0].dtr.options.length - 1; j >= 0; j--)
//                {
//                    document.forms[0].dtr.remove(j);
//                }
                var combo = document.getElementById("dtr");
                for (var i = strar.length - 1; i >= 0; i--)
                {
                    var option = document.createElement("option");
                    try {
                        //option.text = strar[i].trim().substring(strar[i].indexOf("_")+1,strar[i].indexOf("-"));

                        option.text = strar[i].substring(strar[i].indexOf("-") + 1);
                        option.title = strar[i].substring(strar[i].indexOf("_") + 1, strar[i].indexOf("-"));
                        option.value = strar[i].substring(0, strar[i].indexOf("_"));

                        combo.add(option, null); //Standard
                    } catch (error) {

                    }//try
                }//for
            }
        }
    }//state_DTR_Changed
    