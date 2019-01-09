package com.system.service;

import com.system.po.Images;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
     //上传图片到服务器
     void uploadImage(CommonsMultipartFile file, Images images, String realUploadPath) throws IOException;
     //插入图片信息到数据库
     void save (Images images );
     //删除图片信息
     void removeImageByName (String filename) ;
     //根据stuid查询图片
     List<Images> findImageByStuID (Integer stuid) throws Exception;

     List<Images> findUnsignImage(Integer stuid);

     int getCountUnsignImage(Integer stuid);

     void changeImageSign(Integer stuid);
}
