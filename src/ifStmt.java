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

        if(tokens.get(0).equals("IF")){
            int beginThen = tokens.indexOf("THEN");
            int beginElse = tokens.indexOf("ELSE");
            int beginFI = tokens.indexOf("FI");
            System.out.println("IF INFO: \nBeginThen:" + beginThen + "\nBeginElse:" + beginElse + "\nBeginFI:" + beginFI + "\n");
            if(beginThen != -1) {
                if (beginElse == -1) { //No else statement
                    if (beginFI != -1 && (beginThen < beginFI)) {
                        expression = new expr(tokens.between(1, beginThen));
                        if(tokens.between(beginThen+1, beginFI).size() > 0) {
                            statementList = new stmtList(tokens.between(beginThen + 1, beginFI)); //Grammar 11 makes sure statement list has something in it
                        } else{
                            statementList = new stmtList();
                        }
                        elsePart = new elsePart(tokens.between(beginFI, beginFI+1));
                    }
                }
                else{
                    if(beginFI != -1 && (beginThen < beginElse) && (beginElse < beginFI)){
                        expression = new expr(tokens.between(1, beginThen));
                        if(tokens.between(beginThen+1, beginElse).size() > 0) {
                            statementList = new stmtList(tokens.between(beginThen + 1, beginElse)); //Grammar 11 makes sure statement list has something in it
                        }
                        else{
                            statementList = new stmtList();
                        }
                        elsePart = new elsePart(tokens.between(beginElse, tokens.size()));
                    }
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
