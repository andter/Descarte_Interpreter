import java.util.ArrayList;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: stmtList
 * This class is a statementList node that creates and has many statements, which are executed in order.
 */
public class stmtList implements Expression {
    stmt statement = null;
    stmtTail statementTail = null;
    boolean valid = false;
    boolean executable = true;
    TokenList temp;

    public stmtList(TokenList tokens){
        System.out.println("StmtLIST-----");
        tokens.printList();
        System.out.println();

        temp = tokens;

        String newStatement = tokens.get(0);
        int beginTail = -1;

        switch(newStatement){
            case "IF":
                beginTail = tokens.indexOf("FI");
                boolean complete = false;
                int count = 0;
                int index = 0;
                int location = -1;
                for(String s : tokens){
                    if(s.equals("IF")){
                        count++;
                    } else if(s.equals("FI")){
                        count--;
                    }
                    if(count == 0 && s.equals("FI")){
                        if(!complete) {
                            location = index;
                            complete = true;
                        }
                    }
                    index++;
                }
                if(location != -1){
                    statement = new stmt(tokens.between(0, location + 1));
                    statementTail = new stmtTail(tokens.between(location +1, tokens.size()));
                }
                break;
            case "LOOP":
                beginTail = tokens.indexOf("REPEAT");
                statement = new stmt(tokens);
                statementTail = new stmtTail();
                break;
            default:
                beginTail = tokens.indexOf(";");
                if(beginTail == -1){
                    statement = new stmt(tokens);
                    statementTail = new stmtTail();
                }
                else{
                    statement = new stmt(tokens.between(0, beginTail));
                    statementTail = new stmtTail(tokens.between(beginTail, tokens.size()));
                }
                break;
        }
    }

    public stmtList(){
        valid = true;
        executable = false;

    }

    public void execute(Variables variables){
        statement.execute(variables);
        statementTail.execute(variables);
    }


    public boolean executeLoop(Variables variables){
        //System.out.println("Execute STMTLIST LOOP:");
        if(statement != null) {
          //  temp.printList();

            if (statement.type == stmt.Type.BREAK) {
                return true;
            } else {
                if (statement.executeLoop(variables)) {
                    return true;
                } else {
                    if(statementTail != null) {
                        if (statementTail.executeLoop(variables)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean executeStmtListLoop(Variables variables){
        //return statement.executeLoop(variables) || statementTail.executeLoop(variables);
        return false;
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING STMTLIST ISVALID()");
        if(statement == null || statementTail == null){
            if(valid){
                return true;
            }
            return false;
        }
        else{
            if(statement.isValid() && statementTail.isValid()){
                return true;
            }
        }
        System.out.println("STMTLIST NOT VALID");
        return false;
    }
}
