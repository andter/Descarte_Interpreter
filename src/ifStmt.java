/**
 * Created by andy on 11/23/2015.
 */
public class ifStmt extends stmt implements Expression{
    boolean valid = false;
    expr expression = null;
    stmtList statementList = null;
    elsePart elsePart = null;

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
            return false;
        }else{
            if(expression.isValid() && statementList.isValid() && elsePart.isValid()){
                return true;
            }
        }
        return valid;
    }

    @Override
    public void execute() {

    }
}
