package com.kzl.rm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.kzl.rm.bean.Resource_Download;
import com.kzl.rm.bean.Resource_Upload;
import com.kzl.rm.service.Resource_DownloadService;
import com.kzl.rm.service.Resource_UploadService;

@Controller
public class Resource_DownloadController {

	@Autowired
	Resource_DownloadService resource_DownloadService;
	
	@Autowired
	Resource_UploadService resource_UploadService;
	
	@RequestMapping(value="/resource_download")
	public String download_resource(HttpServletRequest request,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		if(account==null)
			return "login";
		
		List<Resource_Upload> results = resource_UploadService.getALL(pn);

		// 使用PageInfo包装查询后的结果
		PageInfo page = new PageInfo(results);
		model.addAttribute("pageInfo", page);
		return "download_resource";
	}
}
