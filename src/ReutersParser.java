/**
 * Created by girl-hamster on 06.02.2018.
 */
import java.io.*;
import java.util.ArrayList;


import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ReutersParser {
    public static void main(String args[]){
        ArrayList linksToPrint = new ArrayList<String>();
        try {


            for (int i = 0; i < 50; i++) {
                String pageAddress = "https://www.reuters.com/news/archive?view=page&page=" + (i + 1) + "&pageSize=10";
                System.out.println("Parsing page number " + (i + 1));
                Document archivePage;
                archivePage = Jsoup.connect(pageAddress).get();
                Elements links = archivePage.select("div.story-content > a[href]");
                System.out.println(links.size() + " links found");
                for (Element link:links){
                    String clearLink = "https://www.reuters.com" + StringUtils.substringBetween(link.toString(), "<a href=\"", "\">" );
                    linksToPrint.add(clearLink);
                }
                }
            FileOutputStream fout=new FileOutputStream("reuters_500_links.csv");
            PrintStream csv=new PrintStream(fout);
            for (Object linkToPrint: linksToPrint) {
                csv.println(linkToPrint);
            }
            fout.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
