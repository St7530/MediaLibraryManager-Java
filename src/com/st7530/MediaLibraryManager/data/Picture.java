package com.st7530.MediaLibraryManager.data;

import java.util.Vector;

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

    @Override
    public Vector<Object> show() {
        Vector<Object> row = new Vector<>();
        row.add(this.getId());
        row.add("图画");
        row.add(this.getTitle());
        row.add(this.getAuthor());
        row.add(this.getRate());
        row.add(this.getNation());
        row.add(this.getLength());
        row.add(this.getWidth());
        return row;
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
