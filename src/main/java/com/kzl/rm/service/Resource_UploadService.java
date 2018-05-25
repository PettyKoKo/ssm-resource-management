package com.kzl.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kzl.rm.bean.Article;
import com.kzl.rm.bean.Resource_Upload;
import com.kzl.rm.dao.Resource_UploadMapper;

@Service
public class Resource_UploadService {

	@Autowired
	Resource_UploadMapper resource_UploadMapper;

	/**
	 * 
	 * @Title: saveUploadResource
	 * @Description: 保存上传的资源
	 * @return boolean 返回类型
	 */
	public boolean saveUploadResource(Resource_Upload upload_resource) {
		int count = 0;
		if (upload_resource != null)
			count = resource_UploadMapper.insertSelective(upload_resource);
		return count == 1;
	}

	public List<Resource_Upload> getAllByAccount(Integer pn, String account) {
		PageHelper.startPage(pn, 8);
		List<Resource_Upload> results = resource_UploadMapper.getAllByAccount(account);
		return results;
	}

	public List<Resource_Upload> getALL(Integer pn) {
		PageHelper.startPage(pn, 8);
		List<Resource_Upload> results = resource_UploadMapper.getALL();
		return results;
	}

}
