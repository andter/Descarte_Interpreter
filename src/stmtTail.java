/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: stmtTail
 * This class is part of a statement and contains both a statement and statement tail, it is used for the creation
 * of statements in a tree as well as their execution.
 */
public class stmtTail implements Expression{
    boolean valid;
    boolean executable = true;
    stmt statement = null;
    stmtTail statementTail = null;
    TokenList temp;

    public stmtTail(TokenList tokens) {
        temp = tokens;
        if(tokens.size() > 0) {
            if (tokens.get(0).equals(";")) {
                tokens.remove(0);
            }
                if(tokens.size() > 0) {
                    System.out.print("stmtTail: ");
                    tokens.printList();
                    String newStatement = tokens.get(0);
                    int beginTail = -1;
                    switch (newStatement) {
                        case "IF":
                            beginTail = tokens.indexOf("FI");
                            if(beginTail == -1){
                                statement = new stmt(tokens);
                                statementTail = new stmtTail();
                            } else{
                                statement = new stmt(tokens.between(0, beginTail+1));
                                statementTail = new stmtTail(tokens.between(beginTail+1, tokens.size()));
                            }
                            break;
                        case "LOOP":
                            beginTail = tokens.indexOf("REPEAT");
                            if(beginTail == -1){
                                statement = new stmt(tokens);
                                statementTail = new stmtTail();
                            } else{
                                statement = new stmt(tokens.between(0, beginTail+1));
                                statementTail = new stmtTail(tokens.between(beginTail+1, tokens.size()));
                            }
                            break;
                        default:
                            beginTail = tokens.indexOf(";");
                            if (beginTail == -1) {
                                statement = new stmt(tokens);
                                statementTail = new stmtTail();
                            } else {
                                statement = new stmt(tokens.between(0, beginTail));
                                statementTail = new stmtTail(tokens.between(beginTail, tokens.size()));
                            }
                    }
                } else{ //This gets triggered if the tokenList only had ";" in it
                    valid = true;
                    executable = false;
                }
            }
            else{
                valid = true;
                executable = false;
            }
        }

    public stmtTail(){
        valid = true;
        executable = false;
    }

    public void execute(Variables variables){
        if(statement != null) {
            statement.execute(variables);
        }
        if(statementTail != null) {
            statementTail.execute(variables);
        }
    }


    public boolean executeLoop(Variables variables) {
        //System.out.println("Execute STMTTAIL LOOP");
        if(statement != null) {
          //  temp.printList();

            if (statement.type == stmt.Type.BREAK) {
                return true;
            } else {
                if (statement.executeLoop(variables)) {
                    return true;
                } else {
                    if (statementTail != null) {
                        if (statementTail.executeLoop(variables)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING STMTTAIL ISVALID()");
        if(statement == null || statementTail == null){
            if(valid){
                return true;
            }
            System.out.println("STMTTAIL NOT VALID");
            return false;
        }
        if(statement.isValid() && statementTail.isValid()){
            return true;
        }
        System.out.println("STMTTAIL NOT VALID");
        return false;
    }
}
