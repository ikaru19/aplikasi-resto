package com.syafrizal.my_geer.Model;

public class Notification {
    private String title;
    private String body;
    private String image;

    public Notification() {

    }
    public Notification(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Notification(String title, String body, String image) {
        this.title = title;
        this.body = body;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
