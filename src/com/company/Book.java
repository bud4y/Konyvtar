package com.company;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private int borrowID;

    public Book(int bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
    }

    public Book(int bookID, String title, String author, int borrowID) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.borrowID = borrowID;
    }


    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
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

    public int getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(int borrowID) {
        this.borrowID = borrowID;
    }

    @Override
    public String toString() {
        return
                "\nbookID: " + bookID +
                ", title: " + title +
                ", author: " + author +
                ", borrowID: " + borrowID;
    }
}
