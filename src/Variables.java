import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: Variables
 * This class extends the ArrayList class and is used to hold String, Double pairs in order to store variables in the program.
 */
public class Variables extends ArrayList<Pair<String, Double>> {

    public void addVariable(String s, double value){
        Pair<String, Double> temp = null;
        for(Pair<String, Double> variable : this){
            if(variable.getKey().toLowerCase().equals(s.toLowerCase())){
                temp = variable;
            }
        }
        if(temp != null){
            this.remove(temp);
        }
        this.add(new Pair<String, Double>(s.toLowerCase(), value));
    }

    public void print(){
        for(Pair<String, Double> variable : this){
            System.out.println(variable.getKey().toLowerCase() + " : " + variable.getValue());
        }
    }

    public double getValue(String s){
        for(Pair<String, Double> variable : this){
            if(variable.getKey().toLowerCase().equals(s.toLowerCase())){
                return variable.getValue();
            }
        }
        return -1;
    }


    public boolean checkVariable(String v){
        for(Pair<String, Double> variable : this){
            if(variable.getKey().toLowerCase().equals(v.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
