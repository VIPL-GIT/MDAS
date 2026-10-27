package com.rtdas.common.beans;

public class ModuleSubModuleBean {
 
	
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}
	public String getModule_title() {
		return module_title;
	}
	public void setModule_title(String module_title) {
		this.module_title = module_title;
	}
	public int getSubmodule_id() {
		return submodule_id;
	}
	public void setSubmodule_id(int submodule_id) {
		this.submodule_id = submodule_id;
	}
	public String getSubmodule_title() {
		return submodule_title;
	}
	public void setSubmodule_title(String submodule_title) {
		this.submodule_title = submodule_title;
	}
	
	public String getSubmodule_url() {
		return submodule_url;
	}
	public void setSubmodule_url(String submodule_url) {
		this.submodule_url = submodule_url;
	}

	public String getModule_img() {
		return module_img;
	}
	public void setModule_img(String module_img) {
		this.module_img = module_img;
	}

	private int module_id;
	private String module_title;
	private int submodule_id;
	private String submodule_title;
	private String submodule_url;
	private String  module_img;
}
