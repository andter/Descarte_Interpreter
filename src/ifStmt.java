import javafx.util.Pair;
/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: ifStmt
 * This class is a node that creates and executes the statement for all if statements
 * Note:  The class has special logic in order to execute either the statementList, if the expression returns true
 *        or the elsePart isf the expression returns false
 */
public class ifStmt extends stmt implements Expression{
    boolean valid = false;
    expr expression = null;
    stmtList statementList = null;
    elsePart elsePart = null;

    Pair<String, Double> variables;

    public ifStmt(TokenList tokens){
        System.out.println("IFSTMT-----");
        tokens.printList();
        System.out.println();
        int firstThen = tokens.indexOf("THEN");

        if(tokens.get(0).equals("IF")){
            boolean complete = false;
            int count = 0;
            int fiLocation = 0;
            int elseLocation = 0;
            int index = 0;
            for(String s : tokens){
                if(s.equals("IF")){
                    count++;
                } else if(s.equals("FI")){
                    count--;
                }
                if(count == 0 && s.equals("FI")){
                    if(!complete) {
                        fiLocation = index;
                        complete = true;
                    }
                }
                if(count == 1 && s.equals("ELSE")){
                    if(!complete) {
                        elseLocation = index;
                        System.out.println("COUNT == 1");
                    }
                }
                index++;
            }
            if(firstThen != -1 && count != -1) {
                expression = new expr(tokens.between(1, firstThen));
                if(elseLocation != 0){
                    statementList = new stmtList(tokens.between(firstThen + 1, elseLocation));
                    elsePart = new elsePart(tokens.between(elseLocation, fiLocation));
                } else{
                    statementList = new stmtList(tokens.between(firstThen+1, fiLocation));
                    elsePart = new elsePart(tokens.between(fiLocation, fiLocation+1));
                }
            }
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING IFSTATEMENT VALID");
        if(expression == null || statementList == null || elsePart == null){
            System.out.println("IF STATEMENT NOT VALID");
            return false;
        }else{
            if(expression.isValid() && statementList.isValid() && elsePart.isValid()){
                return true;
            }
        }
        System.out.println("IF STATEMENT NOT VALID");
        return valid;
    }

    @Override
    public void execute(Variables variables) {
        if(expression.executeBoolean(variables)){
            statementList.execute(variables);
        }
        else{
            elsePart.execute(variables);
        }
    }

    @Override
    public boolean executeLoop(Variables variables){
        if(expression.executeBoolean(variables)) {
            if (statementList.executeLoop(variables)) {
                return true;
            }
        }
        else{
            if(elsePart.executeLoop(variables)){
                return true;
            }
        }
        return false;
    }
}
