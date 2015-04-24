/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

import org.sbml.jsbml.Annotation;
import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;

/**
 *
 * @author Mathialakan.Thavappi
 */
/*import org.sbml.libsbml.libsbmlConstants;
import org.sbml.libsbml.CVTerm;
import org.sbml.libsbml.Date;
import org.sbml.libsbml.Model;
import org.sbml.libsbml.ModelCreator;
import org.sbml.libsbml.ModelHistory;
import org.sbml.libsbml.SBMLWriter;
import org.sbml.libsbml.Species;
import org.sbml.libsbml.SBMLDocument;
import org.sbml.libsbml.SBMLReader;*/

public class AppendAnnotation {
    SBMLDocument document;
    public AppendAnnotation(SBMLDocument document) { 
                    System.out.println("start..");

        this.document = document;
    }

    public SBMLDocument getDocument() {
        return document;
    }

    public void setDocument(SBMLDocument document) {
        this.document = document;
    }

    public SBMLDocument test() {
       

        long errors = document.getNumErrors();

        if (errors > 0) {
            println("Read Error(s):");
            document.printErrors();
            println("Correct the above and re-run.");
        } else {
            System.out.println("start.");
            String model_history_annotation =
                    "<annotation>\n"
                    + "  <rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:vCard=\"http://www.w3.org/2001/vcard-rdf/3.0#\" xmlns:bqbiol=\"http://biomodels.net/biology-qualifiers/\" xmlns:bqmodel=\"http://biomodels.net/model-qualifiers/\">\n"
                    + "    <rdf:Description rdf:about=\"#\">\n"
                    + "      <dc:creator rdf:parseType=\"Resource\">\n"
                    + "        <rdf:Bag>\n"
                    + "          <rdf:li rdf:parseType=\"Resource\">\n"
                    + "            <vCard:N rdf:parseType=\"Resource\">\n"
                    + "              <vCard:Family>Keating</vCard:Family>\n"
                    + "              <vCard:Given>Sarah</vCard:Given>\n"
                    + "            </vCard:N>\n"
                    + "            <vCard:EMAIL>sbml-team@caltech.edu</vCard:EMAIL>\n"
                    + "            <vCard:ORG>\n"
                    + "              <vCard:Orgname>University of Hertfordshire</vCard:Orgname>\n"
                    + "            </vCard:ORG>\n"
                    + "          </rdf:li>\n"
                    + "        </rdf:Bag>\n"
                    + "      </dc:creator>\n"
                    + "      <dcterms:created rdf:parseType=\"Resource\">\n"
                    + "        <dcterms:W3CDTF>1999-11-13T06:54:32Z</dcterms:W3CDTF>\n"
                    + "      </dcterms:created>\n"
                    + "      <dcterms:modified rdf:parseType=\"Resource\">\n"
                    + "        <dcterms:W3CDTF>2007-11-31T06:54:00-02:00</dcterms:W3CDTF>\n"
                    + "      </dcterms:modified>\n"
                    + "    </rdf:Description>\n"
                    + "  </rdf:RDF>\n"
                    + "</annotation>\n";
            //org.sbml.jsbml.SBMLDocument doc = new org.sbml.jsbml.SBMLDocument();
            //doc.getModel()
            //document.getModel().appendAnnotation(model_history_annotation);
            /*
             * The above code can be replaced by the following code.
             *
             ModelHistory h = new ModelHistory();

             ModelCreator c = new ModelCreator();
             c.setFamilyName("Keating");
             c.setGivenName("Sarah");
             c.setEmail("sbml-team@caltech.edu");
             c.setOrganisation("University of Hertfordshire");

             h.addCreator(c);

             Date date = new Date("1999-11-13T06:54:32");
             Date date2 = new Date("2007-11-31T06:54:00-02:00");

             h.setCreatedDate(date);
             h.setModifiedDate(date2);

             document.getModel().setModelHistory(h);
             *
             */

            long n = document.getModel().getNumSpecies();

            if (n > 0) {
                Species s = document.getModel().getSpecies(0);

                String cvterms_annotation =
                        "<annotation>\n"
                        + "  <rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:vCard=\"http://www.w3.org/2001/vcard-rdf/3.0#\" xmlns:bqbiol=\"http://biomodels.net/biology-qualifiers/\" xmlns:bqmodel=\"http://biomodels.net/model-qualifiers/\">\n"
                        + "    <rdf:Description rdf:about=\"#\">\n"
                        + "      <bqbiol:isVersionOf>\n"
                        + "        <rdf:Bag>\n"
                        + "          <rdf:li rdf:resource=\"http://www.geneontology.org/#GO:0005892\"/>\n"
                        + "          <rdf:li rdf:resource=\"http://www.ebi.ac.uk/interpro/#IPR002394\"/>\n"
                        + "        </rdf:Bag>\n"
                        + "      </bqbiol:isVersionOf>\n"
                        + "      <bqbiol:is>\n"
                        + "        <rdf:Bag>\n"
                        + "          <rdf:li rdf:resource=\"http://www.geneontology.org/#GO:0005895\"/>\n"
                        + "        </rdf:Bag>\n"
                        + "      </bqbiol:is>\n"
                        + "    </rdf:Description>\n"
                        + "  </rdf:RDF>\n"
                        + "</annotation>\n";

//                s.appendAnnotation(cvterms_annotation);

                /*
                 * The above code can be replaced by the following code.
                 *
                 CVTerm cv = new CVTerm();
                 cv.setQualifierType(libsbmlConstants.BIOLOGICAL_QUALIFIER);
                 cv.setBiologicalQualifierType(libsbmlConstants.BQB_IS_VERSION_OF);
                 cv.addResource("http://www.geneontology.org/#GO:0005892");

                 CVTerm cv2 = new CVTerm();
                 cv2.setQualifierType(libsbmlConstants.BIOLOGICAL_QUALIFIER);
                 cv2.setBiologicalQualifierType(libsbmlConstants.BQB_IS);
                 cv2.addResource("http://www.geneontology.org/#GO:0005895");

                 CVTerm cv1 = new CVTerm();
                 cv1.setQualifierType(libsbmlConstants.BIOLOGICAL_QUALIFIER);
                 cv1.setBiologicalQualifierType(libsbmlConstants.BQB_IS_VERSION_OF);
                 cv1.addResource("http://www.ebi.ac.uk/interpro/#IPR002394");

                 s.addCVTerm(cv);
                 s.addCVTerm(cv2);
                 s.addCVTerm(cv1);
                 * 
                 */
            }
        }
       return document;
    }

    static void println(String msg) {
        System.out.println(msg);
    }

    /**
     * Loads the SWIG-generated libSBML Java module when this class is loaded,
     * or reports a sensible diagnostic message about why it failed.
     */
    /*static {
        String varname;
        String shlibname;

        if (System.getProperty("os.name").startsWith("Mac OS")) {
            varname = "DYLD_LIBRARY_PATH";    // We're on a Mac.
            shlibname = "'libsbmlj.jnilib'";
        } else {
            varname = "LD_LIBRARY_PATH";      // We're not on a Mac.
            shlibname = "'libsbmlj.so' and/or 'libsbml.so'";
        }

        try {
            System.loadLibrary("sbmlj");
            // For extra safety, check that the jar file is in the classpath.
            Class.forName("org.sbml.libsbml.libsbml");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error encountered while attempting to load libSBML:");
            e.printStackTrace();
            System.err.println("Please check the value of your " + varname
                    + " environment variable and/or"
                    + " your 'java.library.path' system property"
                    + " (depending on which one you are using) to"
                    + " make sure it list the directories needed to"
                    + " find the " + shlibname + " library file and the"
                    + " libraries it depends upon (e.g., the XML parser).");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: unable to load the file 'libsbmlj.jar'."
                    + " It is likely that your -classpath command line "
                    + " setting or your CLASSPATH environment variable "
                    + " do not include the file 'libsbmlj.jar'.");
            System.exit(1);
        } catch (SecurityException e) {
            System.err.println("Error encountered while attempting to load libSBML:");
            e.printStackTrace();
            System.err.println("Could not load the libSBML library files due to a"
                    + " security exception.\n");
            System.exit(1);
        }
    }
    */
    public void addAnnotation(String name, String uri){
       
        if(this.document != null) {
            SBMLDocument document = this.document.clone();
          Model model = document.getModel(); 
          if (model.getSpecies(name)!= null) {
//              CVTerm cvterm = new CVTerm();
//                cvterm.addResource(uri);
//                System.out.println("cvterm :"+ cvterm.toString());
//                model.getSpecies(name).setInitialAmount(12);
                CVTerm cvterm = new CVTerm();
//                 cv.setQualifierType(libsbmlConstants.BIOLOGICAL_QUALIFIER);
//                 cv.setBiologicalQualifierType(libsbmlConstants.BQB_IS_VERSION_OF);
                cvterm.removeResource("urn:miriam:interpro:IPR002394");
                //cvterm.
                 cvterm.addResource("http://www.geneontology.org/#GO:0005892");
                 //cvterm.removeResource("http://www.geneontology.org/#GO:0005892");
//              Species sp = model.getSpecies(name);//.addCVTerm(cvterm);
              //sp.addCVTerm(cvterm);
              Annotation an =model.getSpecies(name).getAnnotation();
              an.addCVTerm(cvterm);
              model.getSpecies(name).setAnnotation(an);
              FileCtrl fc =new FileCtrl();
              System.out.println(fc.writeString(document));
              fc.write(document, "C:\\Users\\Mathialakan.Thavappi\\Documents\\curated\\test.xml");
              System.out.println("sp cvterm :"+ java.util.Arrays.toString(document.getModel().getSpecies(name).getCVTerms().toArray()));
          }
        }
      /*  
        public void addAnnotation(int index, String uri){
       
        if(this.document != null) {
          Model model = this.document.getModel(); 
          if (model.getSpecies(index)!= null) {
              CVTerm cvterm = new CVTerm();
                cvterm.addResource(uri);
              model.getSpecies(index).addCVTerm(cvterm);
          }
        }
     */           
    }
}
