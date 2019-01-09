package com.system.service.impl;

import com.system.mapper.ImageMapper;
import com.system.po.Images;
import com.system.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    /*
     * 上传图片并返回图片的相对地址
     */
    public void uploadImage(CommonsMultipartFile  file, Images images, String realUploadPath) throws  IOException
    {
        //如果目录不存在则创建目录
        File uploadFile=new File(realUploadPath+"/uploadImages");
        if(!uploadFile.exists()){
            uploadFile.mkdirs();
        }

        //更改照片名字
        String fileName = file.getOriginalFilename(); //获取文件的名称
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        //获取扩展
        String extName = fileName.substring(fileName.lastIndexOf("."));//.jpg
        //UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //新名称
        String newName = uuid+extName;     //在这里用UUID来生成新的文件夹名字，这样就不会导致重名
        //保存到数据库
        images.setPath(newName);
        imageMapper.insertImage(images);

        //创建输入流
        InputStream inputStream=file.getInputStream();
        //生成输出地址URL
        String outputPath=realUploadPath+"/uploadImages/"+newName;
        //创建输出流
        OutputStream outputStream=new FileOutputStream(outputPath);
        //设置缓冲区
        byte[] buffer=new byte[1024];

        //输入流读入缓冲区，输出流从缓冲区写出
        while((inputStream.read(buffer))>0)
        {
            outputStream.write(buffer);
        }
        outputStream.close();

        //返回原图上传后的相对地址
       // return "uploadImages/"+newName;
    }

    public void save (Images images ){
        imageMapper.insertImage(images);
    }
    //删除图片信息
    public  void removeImageByName (String filename) {
        imageMapper.deleteImageByName(filename);
    }
    //根据stuid查询图片
    public List<Images> findImageByStuID (Integer stuid) throws Exception{
        return imageMapper.selectImageByStuID(stuid);
    }

    public List<Images> findUnsignImage(Integer stuid){
        return imageMapper.selectUnsignImage(stuid);
    }

    public int getCountUnsignImage(Integer stuid){
        return imageMapper.countUnsignImage(stuid);
    }

    public void  changeImageSign(Integer stuid){
        imageMapper.updateImageSign(stuid);
    }

}
