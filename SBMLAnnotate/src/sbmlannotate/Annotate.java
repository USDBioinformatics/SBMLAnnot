/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

import org.sbml.jsbml.CVTerm;
import org.sbml.jsbml.SBMLDocument;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class Annotate {

    private SBMLDocument sBMLDocument = new SBMLDocument();

    public Annotate() {
    }

    private CVTerm createCVTerm(String resource) {
        CVTerm cvterm = new CVTerm();
        cvterm.addResource(resource);
        return cvterm;
    }

    public void species(String resource) {
        sBMLDocument.addCVTerm(createCVTerm(resource));
    }
}
