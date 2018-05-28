package com.kzl.rm.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.kzl.rm.bean.Resource_Upload;
import com.kzl.rm.service.Resource_UploadService;


@Controller
public class Resource_UploadController {
	
	private static final long serialVersionUID = 1L;

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "static\\file";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	@Autowired
	private Resource_UploadService resource_UploadService;
	
	//跳转上传资源页面
	@RequestMapping(value="/resource_upload")
	public String upload_resource(HttpServletRequest request){
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		if(account==null)
			return "login";
		return "upload_resource";
	}

	//跳转我上传的资源页面
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/my_upload_resource")
	public String my_upload_resource(HttpServletRequest request,
			@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model){
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		if(account==null)
			return "login";
		
		List<Resource_Upload> results = resource_UploadService.getAllByAccount(pn, account);

		// 使用PageInfo包装查询后的结果
		PageInfo page = new PageInfo(results);
		model.addAttribute("pageInfo", page);
		return "my_upload_resource";
	}
	@RequestMapping(value="/file_upload")
	public String file_upload(HttpServletRequest request){
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getSession().getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		Resource_Upload upload_resource = new Resource_Upload();
		Map<String,String> maps = new HashMap<String,String>();
		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段，即文件数据
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						upload_resource.setResourceUrl(UPLOAD_DIRECTORY+"\\"+fileName);
					}else{
						String value = new String((item.getString("iso8859-1")).getBytes("iso8859-1"),"utf-8");
						maps.put(item.getFieldName(), value);
						System.out.println(maps.get(item.getFieldName()));
					}
				}
			}
		} catch (Exception ex) {
			return "error";
		}
		
		String resource_name =maps.get("resource_name");
		String resource_type = maps.get("resource_type");
		String resource_describe = maps.get("resource_describe");
		String user_account = maps.get("uploader");
		Date now = new Date();
		upload_resource.setResourceName(resource_name);
		upload_resource.setResourceType(resource_type);
		upload_resource.setResourceDescribe(resource_describe);
		upload_resource.setUploadTime(now);
		upload_resource.setUploaderAccount(user_account);
		
		System.out.println(upload_resource);
		//保存资源到数据库
		boolean result = resource_UploadService.saveUploadResource(upload_resource);
		return "redirect:/";
		
	}
}
