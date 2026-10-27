<div class="panel panel-bd">
  <div class="panel-heading">
      <div class="panel-title">
<div class="col-md-4" >                           
		<select id="pfNpowerParam" onchange="getpfNpowersStatusValues(this.value)" data-placeholder="Choose a Category"
			tabindex="1">
			<option value="pf">PF</option>
			<option value="mw">KW</option>
			<option value="mva">KVA</option>
			<option value="mvar">KVAR</option>
		</select>
	</div> 
<div class="col-md-4" >                           
		<select id="pfNpowerCondition" onchange="getpfNpowersStatusData()" data-placeholder="Choose a Category"
			tabindex="1">
			<option value="lt"><</option>
			<option value="gt">></option>
<!-- 			<option value="bw">Between</option> -->
		</select>
	</div>
<div class="col-md-4" >                           
		<select id="pfNpowerValues" onchange="getpfNpowersStatusData()" data-placeholder="Choose a Category"
			tabindex="1">
			<option value="0.95">0.95</option>
			<option value="0.9">0.9</option>
			<option value="0.8">0.8</option>
			<option value="0.7">0.7</option>
			<option value="0.6">0.6</option>
		</select>
</div>		         
      </div>                                 
      </div>
<div class="panel-body" style="padding:1px !important;">
<div id="pfNpowersStatusdata">
</div>
</div>
</div>      