package com.kzl.rm.dao;

import com.kzl.rm.bean.Resource_Upload;
import com.kzl.rm.bean.Resource_UploadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Resource_UploadMapper {
    long countByExample(Resource_UploadExample example);

    int deleteByExample(Resource_UploadExample example);

    int deleteByPrimaryKey(Long resourceId);

    int insert(Resource_Upload record);

    int insertSelective(Resource_Upload record);

    List<Resource_Upload> selectByExample(Resource_UploadExample example);

    Resource_Upload selectByPrimaryKey(Long resourceId);

    int updateByExampleSelective(@Param("record") Resource_Upload record, @Param("example") Resource_UploadExample example);

    int updateByExample(@Param("record") Resource_Upload record, @Param("example") Resource_UploadExample example);

    int updateByPrimaryKeySelective(Resource_Upload record);

    int updateByPrimaryKey(Resource_Upload record);
}