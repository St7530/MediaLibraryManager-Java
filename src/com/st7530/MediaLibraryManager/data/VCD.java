package com.st7530.MediaLibraryManager.data;

import java.util.Vector;

public class VCD extends Resource {
    private String name; // 出品者
    private int year; // 出品年份
    private int period; // 视频时长

    public VCD(int id, String title, String author, String rate, String name, int year, int period) {
        super(id, title, author, rate);
        this.name = name;
        this.year = year;
        this.period = period;
    }

    @Override
    public Vector<Object> show() {
        Vector<Object> row = new Vector<>();
        row.add(this.getId());
        row.add("视频光盘");
        row.add(this.getTitle());
        row.add(this.getAuthor());
        row.add(this.getRate());
        row.add(this.getName());
        row.add(this.getYear());
        row.add(this.getPeriod());
        return row;
    }

    public VCD() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
