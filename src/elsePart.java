/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: elsePart
 * This class is a node used to build out and execute the grammar for an else statement.
 * Note: This statement is only executed if the boolean expression in an IF statement returns false
 */
public class elsePart implements Expression{
    boolean valid = false;
    boolean executable = false;
    TokenList temp;
    stmtList statementList;

    public elsePart(TokenList tokens){
        System.out.print("ELSEPART: ");
        temp = tokens;
        tokens.printList();
        System.out.println();

        int firstElse = tokens.indexOf("ELSE");

        if (firstElse == -1) { //Else doesn't exist
            if(tokens.size() == 1 && tokens.get(0).equals("FI")){
                valid = true;
            }
        } else{
            if(firstElse < tokens.size()){
                if(tokens.between(firstElse+1, tokens.size()-1).size() > 0) {
                    statementList = new stmtList(tokens.between(firstElse + 1, tokens.size()-1));
                    executable = true;
                } else{
                    statementList = new stmtList();
                    executable = false;
                }
            }
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ELSEPART ISVALID()");
        if(valid || statementList.isValid()){
            return true;
        }
        System.out.println("ELSE PART NOT VALID");
        return false;
    }

    public void execute(Variables variables) {
        if(statementList != null) {
            statementList.execute(variables);
        }
    }

    public boolean executeLoop(Variables variables){
        //System.out.println("ELSE PART");
        //temp.printList();
        if(statementList != null) {
            if (statementList.executeLoop(variables)) {
                return true;
            }
        }
        return false;
    }
}
