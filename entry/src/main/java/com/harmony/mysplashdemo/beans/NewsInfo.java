package com.harmony.mysplashdemo.beans;

import java.io.Serializable;

/**
 * @author: jun.xiong
 * @date: 2021/2/18 10:25
 * @description:
 */
public class NewsInfo implements Serializable {

    private String title;
    private String description;
    private String imgUrl;
    private String publishTime;
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
