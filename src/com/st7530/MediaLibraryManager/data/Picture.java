package com.st7530.MediaLibraryManager.data;

public class Picture extends Resource {
    private String nation; // 出口国籍
    private int length; // 长
    private int width; // 宽

    public Picture(int id, String title, String author, String rate, String nation, int length, int width) {
        super(id, title, author, rate);
        this.nation = nation;
        this.length = length;
        this.width = width;
    }

    public Picture() {
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
