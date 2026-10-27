<style>
    .button_style
    {
        background-color:#EEEEEE;
        border:1px solid #DDDDDD;
        color:#666666;
        padding:3px 15px;
        text-decoration:none;
        float:left;
    }
    .tab-box {
        border-bottom: 1px solid #DDD;
        padding-bottom:5px;
        padding-top:5px;
    }
    .tab-box a {
        border:1px solid #DDD;
        color:#666666;
        padding: 5px 15px;
        text-decoration:none;
        background-color: #eee;
    }
    .tab-box a.activeLink {
        background-color: #fff;
        border-bottom: 0;
        padding: 6px 15px;
        margin-right:5px;
    }
    .tabcontent { border: 0px solid #ddd; border-top: 0;}
    .hide { display: none;}

</style>
<style>
    .line{ border: 0px solid #ddd; border-top: 0;}
</style>
<script type="text/javascript">
   

    $(document).ready(function() {
    	getMeterAssetMgmtHome('${meter}','loadfactor');
        $(".tabLink").each(function(){
            $(this).click(function(){
                tabeId = $(this).attr('id');
                $(".tabLink").removeClass("activeLink");
                $(this).addClass("activeLink");
                $(".tabcontent").addClass("hide");
                $("#"+tabeId+"-1").removeClass("hide")
               
               
                if(tabeId=="cont-1")
                {
                   
                	getMeterAssetMgmtHome('${meter}','loadfactor');
                }

                if(tabeId=="cont-2")
                {
                  
                	getMeterAssetMgmtHome('${meter}','demandfactor');
                }
                
                return false;
            });
        });
    });
    
    
</script>

<div class="tab-box" id="example">
    <a href="javascript:;" class="tabLink activeLink " id="cont-1">Load Factor </a>
    <a href="javascript:;" class="tabLink " id="cont-2">Demand Factor</a>
   
</div>

<div class="col-md-12" id="reports_div" style="margin-top:5px;"></div>