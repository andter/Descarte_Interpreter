/**
 * Created by andy on 11/23/2015.
 */
public class ifStmt implements Expression{
    boolean valid = false;
    expr expression;
    stmtList statementList;
    elsePart elsePart;

    public ifStmt(TokenList tokens){
        if(tokens.get(0).equals("IF")){
            int beginThen = tokens.indexOf("THEN");
            int beginElse = tokens.indexOf("ELSE");
            int beginFI = tokens.indexOf("FI");
            if(beginThen != -1) {
                if (beginElse != -1) { //No else statement
                    if (beginFI != -1) {
                        expression = new expr(tokens.between(0, beginThen));
                    }
                }
                else{

                }
            }
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void execute() {

    }
}
