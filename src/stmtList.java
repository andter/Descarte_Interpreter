import java.util.ArrayList;

/**
 * Created by andy on 11/22/2015.
 */
public class stmtList implements Expression {
    stmt statement;
    stmtTail statementTail;
    boolean valid = false;

    public stmtList(TokenList tokens){
        String newStatement = tokens.get(0);
        int beginTail = -1;

        switch(newStatement){
            case "ID":
                beginTail = tokens.indexOf(";");
                break;
            case "IF":
                beginTail = tokens.indexOf("FI");
                break;
            case "LOOP":
                beginTail = tokens.indexOf("REPEAT");
                break;
            default:
                beginTail = tokens.indexOf(";");
                break;
        }

        if(beginTail == -1){
            System.out.println("BeginTail == -1");
            statement = new stmt(tokens);
            statementTail = new stmtTail();
        }
        else{
            System.out.println("BeginTail != -1");
            statement = new stmt(tokens.between(0, beginTail));
            statementTail = new stmtTail(tokens.between(beginTail, tokens.size()));
        }
    }

    public void execute(){
        System.out.println("Execute StatementList");
        statement.execute();
        statementTail.execute();
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
