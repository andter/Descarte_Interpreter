/**
 * Created by andy on 11/22/2015.
 */
public class stmtTail implements Expression{
    boolean valid;
    boolean executable = true;
    stmt statement = null;
    stmtTail statementTail = null;
    TokenList temp;

    public stmtTail(TokenList tokens) {
        System.out.println("stmtTail-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() > 0) {
            if (tokens.get(0).equals(";")) {
                tokens.remove(0);
            }
                if(tokens.size() > 0) {
                    String newStatement = tokens.get(0);
                    int beginTail = -1;
                    switch (newStatement) {
                        case "IF":
                            beginTail = tokens.indexOf("FI");
                            statement = new stmt(tokens);
                            statementTail = new stmtTail();
                            break;
                        case "LOOP":
                            beginTail = tokens.indexOf("REPEAT");
                            statement = new stmt(tokens);
                            statementTail = new stmtTail();
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

    public void execute(){
        /*if(valid && executable) {
            statement.execute();
            statementTail.execute();
        }*/
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING STMTTAIL ISVALID()");
        if(statement == null || statementTail == null){
            if(valid){
                return true;
            }
            return false;
        }
        if(statement.isValid() && statementTail.isValid()){
            return true;
        }
        return false;
    }
}
