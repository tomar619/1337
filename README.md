# 1337
Project for scraping https://tretton37.com/

I tried to find out the approach for scraping the website and came up with Jsoup library as the efficient way.

With Jsoup entire website code can be converted into a Document in a single command
Document doc = Jsoup.connect(url).get();

Later I tried to extract certain sections from the document and create csv files with the extracted data.

Added method scrapeNavLinks(doc) to extract Navigation links

Added method scrapeJobLinks(doc) to extraact list of Job openings

Added method scrapeSocialLinks(doc) to extract social meadia links for tretton37

Optionally complete HTML can be extracted from the document with doc.outerHtml()


