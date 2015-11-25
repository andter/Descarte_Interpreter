import java.util.ArrayList;

/**
 * Created by andy on 11/22/2015.
 */
public class stmtList implements Expression {
    stmt statement = null;
    stmtTail statementTail = null;
    boolean valid = false;
    boolean executable = true;

    public stmtList(TokenList tokens){
        System.out.println("StmtLIST-----");
        tokens.printList();
        System.out.println();

        String newStatement = tokens.get(0);
        int beginTail = -1;

        switch(newStatement){
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

    public void execute(){
        System.out.println("Execute StatementList");
        statement.execute();
        statementTail.execute();
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
        return false;
    }
}
