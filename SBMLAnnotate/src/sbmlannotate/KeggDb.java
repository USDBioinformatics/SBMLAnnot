 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class KeggDb {

    public ArrayList<String> getIDList(String key, String kind) {
        if (key.length() > 0) {
            if (key.charAt(0) == '_') {
                key = key.replaceFirst("_", "");
            }

            if (key.length() > 2) {
                if (!Character.isDigit(key.charAt(0)) && Character.isLowerCase(key.charAt(0)) && (key.charAt(1) == '_')) {
                    key = key.replaceFirst(key.substring(0, 2), "");
                }
            }
            ArrayList<Compound> compList = new ArrayList<Compound>();
            WebData webdata = new WebData();
            ArrayList<String> KEGG = webdata.getStringList("http://rest.kegg.jp//find/" + kind + "/" + key);
            String subkey = key.trim();
            while (KEGG.isEmpty() && !subkey.equals("")) {
                subkey = getReduce(subkey.trim());
                if (!this.isNumeric(subkey)) {
                    KEGG = webdata.getStringList("http://rest.kegg.jp//find/" + kind + "/" + subkey);
                }
            }
            if (!KEGG.isEmpty()) {
                compList = this.createCompound(KEGG);
                System.out.println("ids: : " + compList);
            }
            System.out.println("kegg return........");
            return KEGG;
        } else {
            return null;
        }
    }

    private String getReduce(String key) {
        System.out.println("key reduce : " + key);
        if (key.equals("") || isNumeric(key)) {
            return "";
        }
        if (key.contains("_")) {
            return key.substring(0, key.lastIndexOf("_")).trim();
        }
        if (key.contains(" ")) {
            return key.substring(0, key.lastIndexOf(" ")).trim();
        }
        if (key.contains("-")) {
            return key.substring(0, key.lastIndexOf("-")).trim();
        }
        return key.substring(0, key.length() - 1).trim();
    }

    private boolean isNumeric(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Compound> createCompound(ArrayList<String> list) {
        ArrayList<Compound> compoundList = new ArrayList<Compound>();
        for (String line : list) {
            Compound comp = new Compound();
            String KEGGId = "";
            KEGGId = line.substring(line.indexOf(":") + 1, line.indexOf(":") + 7);
            comp.setId(KEGGId);
            comp.setNames(split(line.substring(10), ";"));
            compoundList.add(comp);
        }
        return compoundList;
    }

    private ArrayList<String> split(String terms, String delim) {
        ArrayList<String> list = new ArrayList<String>();
        String[] r = terms.split(delim);
        for (String term : r) {
            System.out.println("term : " + term);
            list.add(term.trim());
        }
        return list;
    }

    public String getPubchemCompundId(String keggId) {
        return convertChemicalID(keggId, "compound", "pupchem");
    }

    public String getChebiCompundId(String keggId) {
        return convertChemicalID(keggId, "compound", "chebi");
    }

    public String getPubchemDrugId(String keggId) {
        return convertChemicalID(keggId, "drug", "pupchem");
    }

    public String getChebiDrugId(String keggId) {
        return convertChemicalID(keggId, "drug", "chebi");
    }
    
    public String getPubchemGlycanId(String keggId) {
        return convertChemicalID(keggId, "glycan", "pupchem");
    }

    public String getChebiGlycanId(String keggId) {
        return convertChemicalID(keggId, "glycan", "chebi");
    }

    public String getUniprotId(String key, String org) {
        return convertGeneID(key, org, "uniprot");
    }
    
    public String getNcbi_giId(String key, String org) {
        return convertGeneID(key, org, "ncbi-gi");
    }
    
    public String getNcbi_geneid(String key, String org) {
        return convertGeneID(key, org, "ncbi-geneid");
    }
    
    public String convertChemicalID(String keggId, String keggDb, String targetDb) {
        String url = "http://rest.kegg.jp/conv/" + targetDb + "/" + keggDb + "/";
        return getId(new WebData().getStringList(url), keggId);
        // return convertMap(new WebData().getStringList(url)).get(keggId).toString();
    }
    
    public String convertGeneID(String keggId, String keggDb, String targetDb) {
        String url = "http://rest.kegg.jp/conv/" + targetDb + "/" + keggDb + ":"+keggId;
        //return getId(new WebData().getStringList(url), keggId);
        return convertMap(new WebData().getStringList(url)).get(keggId).toString();
    }

    public Map generateMap(String keggDb, String targetDb) {
        String url = "http://rest.kegg.jp/conv/" + targetDb + "/" + keggDb + "/";
        return convertMap(new WebData().getStringList(url));
    }

    public Map convertMap(ArrayList<String> list) {
        Map map = new HashMap();;
        for (String line : list) {
            Scanner scan = new Scanner(line);
            String key, value;
            if (scan.hasNext()) {
                key = scan.next();
                key = key.substring(key.indexOf(":") + 1).trim();
                if (scan.hasNext()) {
                    value = scan.next();
                    value = value.substring(value.indexOf(":") + 1).trim();
                    map.put(key, value);
                }
            }
        }
        //System.out.println("size: "+map.toString());
        return map;
    }

    private String getId(ArrayList<String> list, String key) {
        for (String line : list) {
            Scanner scan = new Scanner(line);
            while (scan.hasNext()) {
                if (scan.next().contains(key)) {
                    if (scan.hasNext()) {
                        String value = scan.next();
                        return value.substring(value.indexOf(":") + 1);
                    }
                }
            }
        }
        return "";
    }
}
