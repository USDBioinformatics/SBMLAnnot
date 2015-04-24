/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miriamquery;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import uk.ac.ebi.miriam.lib.MiriamLink;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class MIRIAMQuery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {
        // TODO code application logic here
        String dbname = "uniprot", id = "P62158", uri = "urn:miriam:uniprot",
                url = "http://www.ebi.ac.uk/chebi/#CHEBI:17891", miriamid = "MIR:00100009";
        String[] dbs = {"uniprot", "chebi"}, ids = {"P62158", "GO:0045202"};
        if (args.length > 2) {
            dbname = args[0];
            id = args[1];
        }
        System.out.println("miriamid : " + getDatatypeId(dbname));
        System.out.println("uri: " + getURI(dbname, id));
        System.out.println("location: " + Arrays.toString(getLocations(dbname, id)));
        System.out.println("resources: " + Arrays.toString(getDataResources(dbname)));
        System.out.println("uris : " + Arrays.toString(getURIs(dbs, ids)));
        System.out.println("datatypeuris: " + Arrays.toString(getDataTypeURIs(dbname)));
        System.out.println("datatypeids: " + Arrays.toString(getDataTypesId()));
        System.out.println("datatypenamess: " + Arrays.toString(getDataTypesName()));
        System.out.println("names: " + Arrays.toString(getNames(uri)));
        System.out.println("resource info: " + getResourceInfo(miriamid));
        System.out.println("resource loc: " + getResourceLocation(miriamid));
        System.out.println("resource ins: " + getResourceInstitution(miriamid));
        System.out.println("uri: " + getURIs(dbname, id));
        System.out.println("Data types (JSON):\n" + getDatatypes());
        System.out.println("Data type (JSON):" + getDatatype(miriamid));
        System.out.println("URIs (JSON): " + getURIs(getURI(dbname, id)));

    }

    /**
     *
     * @param dbname
     * @return MIRIAM datatype id for a given data type
     * @throws JSONException
     */
    public static ArrayList<String> getDatatypeId(String dbname) throws JSONException {
        JSONObject obj = new JSONObject(getDatatypes());
        JSONArray jlist = obj.getJSONArray("datatype");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < jlist.length(); i++) {
            JSONObject jdtype = jlist.getJSONObject(i);
            if (jdtype.getString("name").toLowerCase().contains(dbname)) {
                list.add(jdtype.getString("id"));
            }
        }
        return list;
    }

    public static String getURI(String dbname, String id) {
        return new MiriamLink().getURI(dbname, id);
    }

    public static String[] getURIs(String[] dbname, String[] id) {
        return new MiriamLink().getURIs(dbname, id);
    }

    public static String[] getLocations(String dbname, String id) {
        return new MiriamLink().getLocations(getURI(dbname, id));
    }

    public static String[] getDataResources(String dbname) {
        return new MiriamLink().getDataResources(dbname);
    }

    public static String[] getDataResources(String dbname, String id) {
        return new MiriamLink().getDataResources(getURI(dbname, id));
    }

    public static String[] getDataTypeURIs(String dbname) {
        return new MiriamLink().getDataTypeURIs(dbname);
    }

    public static String[] getDataTypesId() {
        return new MiriamLink().getDataTypesId();
    }

    public static String[] getDataTypesName() {
        return new MiriamLink().getDataTypesName();
    }

    public static String[] getNames(String uri) {
        return new MiriamLink().getNames(uri);
    }

    public static String getAddress() {
        return new MiriamLink().getAddress();
    }

    public static String getDataTypeDef(String dbname) {
        return new MiriamLink().getDataTypeDef(dbname);
    }

    public static String getMiriamURI(String url) {
        return new MiriamLink().getMiriamURI(url);
    }

    public static String getLocation(String uri, String miriamId) {
        return new MiriamLink().getLocation(uri, miriamId);
    }

    public static String getResourceInfo(String miriamId) {
        return new MiriamLink().getResourceInfo(miriamId);
    }

    public static String getResourceInstitution(String miriamId) {
        return new MiriamLink().getResourceInstitution(miriamId);
    }

    public static String getResourceLocation(String miriamId) {
        return new MiriamLink().getResourceLocation(miriamId);
    }

    public static String getURIs(String dbname, String id) {
        return getString("http://www.ebi.ac.uk/miriamws/main/rest/resolve/" + getURI(dbname, id));
    }

    private static String getString(String url) {
        String content = "";
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            while ((line = rd.readLine()) != null) {
                line = line.trim();
                if (!line.equals("")) {
                    content += "\n" + line;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public static String getDatatypes() {
        // initialisation
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://www.ebi.ac.uk/miriamws/main/rest/").build());

        // GET queries
        //System.out.println("GET (XML):\n" + service.path("datatypes").accept(MediaType.APPLICATION_XML).get(String.class));
        return service.path("datatypes").accept(MediaType.APPLICATION_JSON).get(String.class);
    }

    public static String getDatatype(String miriamid) {
        // initialisation
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://www.ebi.ac.uk/miriamws/main/rest/").build());

        Form form = new Form();
        form.add("id", miriamid);
        ClientResponse response = service.path("datatypes").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
        return response.getEntity(String.class);
    }

    public static String getURIs(String uri) {
        // initialisation
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("http://www.ebi.ac.uk/miriamws/main/rest/").build());
        // POST queries
        Form form = new Form();
        form.add("uri", uri);
        ClientResponse response = service.path("resolve").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
        return response.getEntity(String.class);
    }

    public static Map datatypeMap(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray jlist = obj.getJSONArray("datatype");
        Map miriamdatatype = new HashMap();;
        for (int i = 0; i < jlist.length(); i++) {
            JSONObject jdtype = jlist.getJSONObject(i);
            miriamdatatype.put(jdtype.getString("id"), jdtype.getString("name"));
        }
        return miriamdatatype;
    }
}
