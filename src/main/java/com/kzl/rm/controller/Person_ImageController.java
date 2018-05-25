package com.kzl.rm.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kzl.rm.bean.Person_Image;
import com.kzl.rm.service.Person_ImageService;
import com.kzl.rm.service.UserService;

/**
 * 
 * @ClassName: Person_ImageController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author kezeli
 * @date 2018年5月19日 下午4:59:04
 *
 */

@Controller
public class Person_ImageController {

	private static final long serialVersionUID = 1L;

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "static\\images";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@Autowired
	private Person_ImageService person_ImageService;
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @Title: upload_image
	 * @Description: 实现更换头像功能
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/upload_image")
	public String upload_image(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("account");
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

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						//查询数库中有没有这张图片
						Person_Image image = person_ImageService.findImageByName(fileName);
						//如果没有
						if(image==null){
							image = new Person_Image();
							image.setImageName(fileName);
							image.setImageUrl(UPLOAD_DIRECTORY+"\\"+fileName);
							//保存图片到数据库
							 person_ImageService.saveImage(image);
							image = person_ImageService.findImageByName(fileName);
						}
						//修改用户中的图片Id
						userService.updateImageId(user_account,image.getImageId());
					}
				}
			}
		} catch (Exception ex) {
			return "error";
		}
		
		return "redirect:/userInfo_management";

	}
}
