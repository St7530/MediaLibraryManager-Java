package com.st7530.MediaLibraryManager.data;

import java.util.Vector;

public abstract class Resource {
    private int id; // 编号
    private String title; // 标题
    private String author; // 作者
    private String rate; // 评级

    public Resource(int id, String title, String author, String rate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rate = rate;
    }

    public abstract Vector<Object> show();

    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
