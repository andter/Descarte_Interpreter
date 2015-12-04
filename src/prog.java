import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: prog
 * This class is a node used at the top level to build out the program
 * Note: All nodes in this program check nodesunderneath them for validity as well as their own to determine if the program is valid
 */
public class prog implements Expression{
    private boolean valid = false;
    stmtList statementList = null;
    boolean debug;

    public prog(TokenList tokens){
        System.out.println("PROG-----");
        tokens.printList();
        System.out.println("");

        if(tokens.get(tokens.size()-1).equals(".")){
            valid = true;
            statementList = new stmtList(tokens.between(0, tokens.size()-1));
        }
        else{
            System.out.println("Error: Program MUST end in a .");
        }
    }

    public void execute(Variables variables){
        statementList.execute(variables);
    }

    @Override
    public boolean isValid() {
        if(statementList == null){
            return false;
        }
        else{
            if(statementList.isValid()){
                return true;
            }
        }
        System.out.println("PROGRAM NOT VALID");
        return false;
    }
}
