package com.rtdas.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.beans.ClientBean;
import com.rtdas.common.models.Client;
import com.rtdas.common.service.ClientService;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	@Autowired
	private ClientService clientService;
	public String actionId;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.GET)
	public ModelAndView addClient() throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		// System.out.println("--------"+clientService.listModules());
		model.put("modules", clientService.listModules());
		model.put("actionId", getActionId());
		return new ModelAndView("client/addClient", model);
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public ModelAndView saveClient(@ModelAttribute("client") ClientBean clientBean, MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception{
		int gen_id = 0;
		if (clientBean.getClientId() == 0)
			gen_id = clientService.addClient(prepareModel(clientBean), clientBean.getSubmodules());
		if (clientBean.getClientId() != 0 && request.getFile("client_img_file") == null) {
			String clientImg = clientService.getClientImageUrl(clientBean.getClientId());
			clientBean.setClientImg(clientImg);
			gen_id = clientService.addClient(prepareModel(clientBean), clientBean.getSubmodules());
		} if(clientBean.getClientId() != 0 && request.getFile("client_img_file") != null) {
			gen_id = clientService.addClient(prepareModel(clientBean), clientBean.getSubmodules());
		}
		// System.out.println("----->"+clientBean.getSubmodules());
		try {
			String clientId = new UtilityClass().addZeros(gen_id);
			if (request.getFile("client_img_file") != null) {
				MultipartFile multipartFile = request.getFile("client_img_file");
				String fileName = multipartFile.getOriginalFilename();
				String file_ext = fileName.substring(fileName.lastIndexOf("."));
				String sep=System.getProperty("file.separator");
				System.out.println(sep+"--->" + clientId + "\t" + fileName);

				File f = new File(request.getRealPath(sep+"img") + sep+"clients"+sep+"client_" + clientId + file_ext);
				System.out.println("File is saved at ---->" +request.getRealPath(sep+"img") + sep+"clients"+sep+"client_" + clientId + file_ext);
				// if(!f.exists()) f.mkdirs();
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(multipartFile.getBytes());
				fos.flush();
				fos.close();
				clientBean.setClientImg(f.getAbsolutePath());
				clientBean.setClientId(gen_id);
				clientService.addClient(prepareModel(clientBean), clientBean.getSubmodules());
			}
		} catch (Exception e) {
           e.printStackTrace();
		}
		Map<String, Object> model = new HashMap();
		model.put("clients", prepareListofBean(clientService.listClients()));
		model.put("actionId", getActionId());
		return new ModelAndView("client/viewClient", model);
	}

	@RequestMapping(value = "/deleteClient")
	public ModelAndView deleteClient(@RequestParam("clientId") String clientId) throws Exception{
		ClientBean cb = new ClientBean();
		cb.setClientId(Integer.parseInt(clientId));
		// deleting the client image also--------------------------------------
		File f = new File(clientService.getClientImageUrl(Integer.parseInt(clientId)));
		if (f.exists())
			f.delete();
		clientService.deleteClient(prepareModel(cb));
		Map<String, Object> model = new HashMap();
		model.put("clients", prepareListofBean(clientService.listClients()));
		model.put("actionId", getActionId());
		return new ModelAndView("client/viewClient", model);
	}

	@RequestMapping(value = "/editClient", method = RequestMethod.GET)
	public ModelAndView editModem(@ModelAttribute("modem") ClientBean clientBean) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("client", prepareModemBean(clientService.getClient(clientBean.getClientId())));
		model.put("modules", clientService.listModules());
		model.put("mappedModules", clientService.getMappedModules(clientBean.getClientId()));
		model.put("actionId", getActionId());
		return new ModelAndView("client/editClient", model);

	}

	@RequestMapping(value = "/viewClient", method = RequestMethod.GET)
	public ModelAndView viewClients(@RequestParam("actionId") String actionId) throws Exception{
		// System.out.println("-----------in viewClinet---->"+actionId);
		Map<String, Object> model = new HashMap();
		model.put("clients", prepareListofBean(clientService.listClients()));
		setActionId(actionId);
		model.put("actionId", actionId);
		return new ModelAndView("client/viewClient", model);
	}

	
	private Client prepareModel(ClientBean clientBean) {
		Client client = new Client();
		client.setClient_id(clientBean.getClientId());
		client.setClient_name(clientBean.getClientName());
		client.setClient_add1(clientBean.getAdd1());
		client.setClient_add2(clientBean.getAdd2());
		client.setAbout_client(clientBean.getAboutClient());
		client.setEmail(clientBean.getEmail());
		client.setMobile(clientBean.getMobile());
		client.setContact_person(clientBean.getContactPerson());
		client.setActive_flg(clientBean.getActiveStatus());
		client.setClient_logo(clientBean.getClientImg());
		return client;
	}

	private List<ClientBean> prepareListofBean(List<Client> clients) {
		List<ClientBean> beans = null;
		if (clients != null && !clients.isEmpty()) {
			beans = new ArrayList<ClientBean>();
			ClientBean bean = null;
			for (Client client : clients) {
				bean = new ClientBean();
				bean.setClientId(client.getClient_id());
				bean.setClientName(client.getClient_name());
				bean.setAdd1(client.getClient_add1());
				bean.setAdd2(client.getClient_add2());
				bean.setAboutClient(client.getAbout_client());
				bean.setEmail(client.getEmail());
				bean.setMobile(client.getMobile());
				bean.setContactPerson(client.getContact_person());
				bean.setActiveStatus(client.getActive_flg());
				String logo = client.getClient_logo();
				logo = (logo == null) ? "" : logo.substring(logo.indexOf("img"));
				bean.setClientImg(logo);
				beans.add(bean);
			}
		}
		return beans;
	}

	private ClientBean prepareModemBean(Client client) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		ClientBean bean = new ClientBean();
		bean.setClientId(client.getClient_id());
		bean.setClientName(client.getClient_name());
		bean.setAdd1(client.getClient_add1());
		bean.setAdd2(client.getClient_add2());
		bean.setAboutClient(client.getAbout_client());
		bean.setEmail(client.getEmail());
		bean.setMobile(client.getMobile());
		bean.setContactPerson(client.getContact_person());
		bean.setActiveStatus(client.getActive_flg());
		String logo = client.getClient_logo();
		logo = (logo == null) ? "" : logo.substring(logo.indexOf("img"));
		bean.setClientImg(logo);
		return bean;
	}
	
}
