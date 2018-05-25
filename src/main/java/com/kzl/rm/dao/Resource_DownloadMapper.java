package com.kzl.rm.dao;

import com.kzl.rm.bean.Resource_Download;
import com.kzl.rm.bean.Resource_DownloadExample;
import com.kzl.rm.bean.Resource_Upload;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Resource_DownloadMapper {
    long countByExample(Resource_DownloadExample example);

    int deleteByExample(Resource_DownloadExample example);

    int deleteByPrimaryKey(Long resourceId);

    int insert(Resource_Download record);

    int insertSelective(Resource_Download record);

    List<Resource_Download> selectByExample(Resource_DownloadExample example);

    Resource_Download selectByPrimaryKey(Long resourceId);

    int updateByExampleSelective(@Param("record") Resource_Download record, @Param("example") Resource_DownloadExample example);

    int updateByExample(@Param("record") Resource_Download record, @Param("example") Resource_DownloadExample example);

    int updateByPrimaryKeySelective(Resource_Download record);

    int updateByPrimaryKey(Resource_Download record);

	
}