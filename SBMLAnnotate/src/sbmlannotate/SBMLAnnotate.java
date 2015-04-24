/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

//import org.sbml.libsbml.SBMLReader;
//import org.sbml.libsbml.SBMLWriter;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class SBMLAnnotate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* if (args.length != 2) {
            println("\n  usage: java appendAnnotation <input-filename> <output-filename>\n");
            System.exit(2);
        }*/
//        String keggDb ="compound";
//        String targetDb = "pubchem";
//        String keggId ="10458";//"C00001";
//        System.out.println("keggid: "+keggId+"\t"+targetDb+ " : " +new KeggDb().convertChemicalID(keggId, keggDb, targetDb));
//        System.out.println("keggid: "+keggId+"\t"+new KeggDb().getUniprotId(keggId, "hsa"));
         String arg[] = new String[]{"C:\\Users\\Mathialakan.Thavappi\\Documents\\curated\\BIOMD0000000001.xml", "C:\\Users\\Mathialakan.Thavappi\\Documents\\curated\\test.xml"};
        //SBMLDocument d;
                     System.out.println("start.00000000000");
        FileCtrl fc =new FileCtrl();
        
        //SBMLReader reader = new SBMLReader();
        //SBMLWriter writer = new SBMLWriter();
                    System.out.println("start.99999999");
        AppendAnnotation aannot = new AppendAnnotation(fc.read(arg[0]));
        aannot.addAnnotation("BLL", "urn:miriam:obo.go:GO%3A0000004");
        //fc.write(aannot.getDocument(), arg[1]);
        //writer.writeSBML(aannot.getDocument(), arg[1]);
        
    }
}
