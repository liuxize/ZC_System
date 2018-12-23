package com.system.mapper;

import com.system.po.Images;

import java.util.List;

public interface ImageMapper {
    //根据学生id查找图片
    List<Images> selectImageByStuID (Integer stuid);
    //插入图片
    int insertImage (Images images);
    //删除图片
    int deleteImageByName (String filename);
}
