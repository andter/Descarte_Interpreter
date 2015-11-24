/**
 * Created by andy on 11/23/2015.
 */
public class ifStmt implements Expression{
    boolean valid = false;
    expr expression;
    stmtList statementList;
    elsePart elsePart;

    public ifStmt(TokenList tokens){
        System.out.println("IFSTMT-----");
        tokens.printList();
        if(tokens.get(0).equals("IF")){
            int beginThen = tokens.indexOf("THEN");
            int beginElse = tokens.indexOf("ELSE");
            int beginFI = tokens.indexOf("FI");
            if(beginThen != -1) {
                if (beginElse == -1) { //No else statement
                    if (beginFI != -1 && beginThen < beginFI) {
                        expression = new expr(tokens.between(1, beginThen));
                        statementList = new stmtList(tokens.between(beginThen+1, beginFI));
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
