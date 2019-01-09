package com.system.po;

public class Images {
    Integer imageid;
    Integer stuid;  //学生id
    String title;   //图片名称
    String path;    //图片路径
    Integer imagesign;

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getImagesign() {
        return imagesign;
    }

    public void setImagesign(Integer imagesign) {
        this.imagesign = imagesign;
    }
}
