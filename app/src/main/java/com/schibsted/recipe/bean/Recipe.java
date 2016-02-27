package com.schibsted.recipe.bean;

public class Recipe implements Bean {
    private String publisher;
    private String f2f_url;
    private String publisher_url;
    private String title;
    private String source_url;
    private int page;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String newVal) {
        publisher = newVal;
    }

    public String getF2f_url() {
        return f2f_url;
    }

    public void setF2f_url(String newVal) {
        f2f_url = newVal;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(String newVal) {
        publisher_url = newVal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newVal) {
        title = newVal;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String newVal) {
        source_url = newVal;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int newVal) {
        page = newVal;
    }

}