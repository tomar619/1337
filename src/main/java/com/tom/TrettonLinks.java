package com.tom;

public class TrettonLinks {

    private String linkTitle;
    private String linkUrl;

    public TrettonLinks() {
    }

    public TrettonLinks(String linkTitle, String linkUrl) {
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
