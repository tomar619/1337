package com.tom;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper1337 {

    private static String filePath = "c:\\Training\\Tretton37\\1337\\ScrapedFiles\\";

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

            System.out.println("Starting Job Link scraping from: " + url);
            scrapeJobLinks(doc);

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
        writeToCsv(trettonLinks, "NavLinks.csv");
    }

    public static void scrapeJobLinks(Document doc){
        List<TrettonLinks> trettonJobLinks = new ArrayList<>();
        trettonJobLinks.add(new TrettonLinks("Job Title", "Job URL"));

        Elements jobItems = doc.getElementsByTag("span");
        for(Element jobItem: jobItems){
            Elements jobLinks = jobItem.getElementsByTag("a");
            String jobLinkUrl = jobLinks.attr("abs:href");
            if(jobLinkUrl.length() > 0){
                System.out.println(jobItem.text() + " : " + jobLinkUrl);
                trettonJobLinks.add(new TrettonLinks(jobItem.text(), jobLinkUrl));
            }
        }
        System.out.println("Successfully extracted " + trettonJobLinks.size() + " job Links.");
        writeToCsv(trettonJobLinks, "JobLinks.csv");
    }

    public static void writeToCsv(List<TrettonLinks> extractedList, String fileName){
        System.out.println("Trying to write extracted data in file: " + fileName);
        try (CSVWriter trettonLinkWriter = new CSVWriter(new FileWriter(filePath + fileName))) {
            for(TrettonLinks extractedListItem: extractedList){
                trettonLinkWriter.writeNext(new String[] {extractedListItem.getLinkTitle(), extractedListItem.getLinkUrl()}, false);
            }
            System.out.println("Successfully added extracted data in file: " + fileName);
        } catch (IOException e) {
            System.out.println("Writing data to csv failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
