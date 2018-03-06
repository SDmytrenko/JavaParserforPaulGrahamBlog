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


public class PaulGrahamBlogParser {
    public static void main(String args[]){
        ArrayList linksToPrint = new ArrayList<String>();
        try {



                String pageAddress = "http://paulgraham.com/articles.html";
                Document archivePage;
                archivePage = Jsoup.connect(pageAddress).get();
                Elements links = archivePage.select("tr > td[width=435] > font > a[href]");
                System.out.println(links.size() + " links found");
                for (Element link:links){
                    String clearLink = "http://paulgraham.com/" + StringUtils.substringBetween(link.toString(), "<a href=\"", "\">" );
                    linksToPrint.add(clearLink);
                }

            FileOutputStream fout=new FileOutputStream("paul_graham.csv");
            PrintStream csv=new PrintStream(fout);
            for (Object linkToPrint: linksToPrint) {
                csv.println(linkToPrint);
            }
            fout.close();        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
