/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class GoDb {

    public static String term(String GOID) throws Exception {
        // URL a GO Term in OBO xml format
        String url = "http://www.ebi.ac.uk/QuickGO/GTerm?id=" + GOID + "&partof,isa=" + GOID + "&format=oboxml";
        URL u = new URL(url);
        // Connect
        HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();

        // Parse an XML document from the connection
        InputStream inputStream = urlConnection.getInputStream();
        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        inputStream.close();

        // XPath is here used to locate parts of an XML document
        XPath xpath = XPathFactory.newInstance().newXPath();

        //Locate the term name and print it out
        System.out.println("Term name:" + xpath.compile("/obo/term/name").evaluate(xml));
        return xpath.compile("/obo/term/name").evaluate(xml);

    }

    public static void list(String iD) throws IOException {
        // URL for annotations from QuickGO for one protein
        //String url ="http://www.ebi.ac.uk/QuickGO/GAnnotation?"+"I="+iD+"&format=tsv";
        String url = "www.ebi.ac.uk/QuickGO/GAnnotation?goid=GO%3A0005829&relType=%3D&format=tsv";

        URL u = new URL(url);
        // Connect
        HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
        // Get data
        BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        // Read data
        List<String> columns = Arrays.asList(rd.readLine().split("\t"));
        System.out.println(columns);
        // Collect the unique terms as a sorted set
        Set<String> terms = new TreeSet<String>();
        // Find which column contains GO IDs
        int termIndex = columns.indexOf("GO ID");
        // Read the annotations line by line
        String line;
        String[] fields = null;
        FileWriter writer;

        writer = new FileWriter("E:\\GO.txt");
        for (String column : columns) {
            writer.write(column + "\t");
        }
        writer.write("\n");
        /*while ((line=rd.readLine())!=null) {
                
         }
         writer.close();*/

        while ((line = rd.readLine()) != null) {
            System.out.println("line " + line);
            writer.write(line + "\n");
            // Split them into fields
            fields = line.split("\t");
            // Record the GO ID
            terms.add(fields[termIndex]);
        }
        // close input when finished
        rd.close();
        // Write out the unique terms
        /*for (String term : terms) {
         System.out.println(term);
         }*/
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i] + "\t");
        }
    }
}
