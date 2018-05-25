package com.kzl.rm.dao;

import com.kzl.rm.bean.Person_Image;
import com.kzl.rm.bean.Person_ImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Person_ImageMapper {
    long countByExample(Person_ImageExample example);

    int deleteByExample(Person_ImageExample example);

    int deleteByPrimaryKey(Long imageId);

    int insert(Person_Image record);

    int insertSelective(Person_Image record);

    List<Person_Image> selectByExample(Person_ImageExample example);

    Person_Image selectByPrimaryKey(Long imageId);

    int updateByExampleSelective(@Param("record") Person_Image record, @Param("example") Person_ImageExample example);

    int updateByExample(@Param("record") Person_Image record, @Param("example") Person_ImageExample example);

    int updateByPrimaryKeySelective(Person_Image record);

    int updateByPrimaryKey(Person_Image record);

	Person_Image findImageByName(String imageName);
}