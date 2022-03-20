package com.tom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper1337 {

    public static void main(String[] args) {
        String url = "https://tretton37.com";
        try {
            // Create a document object and fetch the website using JSoup
            System.out.println("Making Connection to the URL: " + url);
            Document doc = Jsoup.connect(url).get();

            // Get the document title JSoup's title() method
            System.out.printf("Website Title: %s\n", doc.title());

            System.out.println("Starting Navigation Link scraping from: " + url);
            scrapeNavLinks(doc);
            System.out.println("Navigation Link scraping completed !! ");

            // If any IO error, write to the console
        } catch (IOException e) {
            System.out.println("Connection failed to the URL: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void scrapeNavLinks(Document doc){
        List<TrettonLinks> trettonLinks = new ArrayList<>();
        trettonLinks.add(new TrettonLinks("Link Title", "Link URL"));

        Elements navLinkItems = doc.getElementsByTag("li");

        for(Element navLinkItem: navLinkItems){
            Elements linkItems = navLinkItem.getElementsByTag("a");
            String linkItemUrl = linkItems.attr("abs:href");
            if(linkItems.text().length() > 0)
                System.out.println(linkItems.text() + " : " + linkItemUrl);
                trettonLinks.add(new TrettonLinks(linkItems.text(), linkItemUrl));
        }
        System.out.println("Successfully extracted " + trettonLinks.size() + " navigation Links.");
    }

}
