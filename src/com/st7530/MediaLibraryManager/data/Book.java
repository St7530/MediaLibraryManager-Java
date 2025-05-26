package com.st7530.MediaLibraryManager.data;

import java.util.Vector;

public class Book extends Resource {
    private String press; // 出版社
    private String isbn; // ISBN 号
    private int page; // 页数

    public Book(int id, String title, String author, String rate, String press, String isbn, int page) {
        super(id, title, author, rate);
        this.press = press;
        this.isbn = isbn;
        this.page = page;
    }

    @Override
    public Vector<Object> show() {
        Vector<Object> row = new Vector<>();
        row.add(this.getId());
        row.add("图书");
        row.add(this.getTitle());
        row.add(this.getAuthor());
        row.add(this.getRate());
        row.add(this.getPress());
        row.add(this.getIsbn());
        row.add(this.getPage());
        return row;
    }

    public Book() {
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Book{" +
                "press='" + press + '\'' +
                ", isbn=" + isbn +
                ", page=" + page +
                '}';
    }
}
