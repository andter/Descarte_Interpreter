/**
 * Created by andy on 11/23/2015.
 */
public class assignStmt extends stmt implements Expression {
    expr expression = null;

    public assignStmt(TokenList tokens){
        System.out.println("ASSIGNSTMT-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() > 2) {
                if (tokens.get(1).equals(":=")) {
                    expression = new expr(tokens.between(2, tokens.size()));
                }
        }
    }
    @Override
    public boolean isValid() {
        if(expression != null) {
            if (expression.isValid()) {
                return true;
            }
        }
        System.out.println("ASSIGNSTMT NOT VALID");
        return false;
    }

    @Override
    public void execute() {

    }
}
