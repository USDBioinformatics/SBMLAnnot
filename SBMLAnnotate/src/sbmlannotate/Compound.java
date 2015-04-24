/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sbmlannotate;

import java.util.ArrayList;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class Compound {
    String id;
    String description;
    String name;
    ArrayList<String> names;

    public Compound() {
          names = new  ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public  ArrayList<String> getNames(){
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }
    
    public void addName(String name){
        this.names.add(name);
    }
    
    public int getNumberOfNames(){
        return this.names.size();
    }
}
