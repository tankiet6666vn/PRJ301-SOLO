package model;

import java.sql.Date;

public class Book {
    private int bid;
    private String title;
    private Date publisheddate;
    private int cid;
    private String createdby;
    private String categoryName; 

    public Book() {}

    public Book(int bid, String title, Date publisheddate, int cid, String createdby) {
        this.bid = bid;
        this.title = title;
        this.publisheddate = publisheddate;
        this.cid = cid;
        this.createdby = createdby;
    }

    // Getters/setters
    public int getBid() { return bid; }
    public void setBid(int bid) { this.bid = bid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Date getPublisheddate() { return publisheddate; }
    public void setPublisheddate(Date publisheddate) { this.publisheddate = publisheddate; }

    public int getCid() { return cid; }
    public void setCid(int cid) { this.cid = cid; }

    public String getCreatedby() { return createdby; }
    public void setCreatedby(String createdby) { this.createdby = createdby; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
