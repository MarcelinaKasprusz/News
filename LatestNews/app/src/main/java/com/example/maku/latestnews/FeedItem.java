package com.example.maku.latestnews;

/**
 * Created by Maku on 2017-09-18.
 */




public class FeedItem {

    String title;
    String link;
    String description;
    String pubDate;
    String thumbnailURL;

    public FeedItem() {
    }

    public FeedItem(String title, String link, String description, String pubDate, String thumbnailUrl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.thumbnailURL = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) { this.thumbnailURL = thumbnailURL; }
}
