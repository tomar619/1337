package com.tom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Scraper1337 {

    public static void main(String[] args) {
        String url = "https://tretton37.com";
        try {
            // Create a document object and fetch the website using JSoup
            Document doc = Jsoup.connect(url).get();

            // Get the document title JSoup's title() method
            System.out.printf("Title: %s\n", doc.title());

            // If any IO error, write to the console
        } catch (IOException e) {
            System.out.println("Connection failed to the URL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
