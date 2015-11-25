/**
 * Created by andy on 11/23/2015.
 */
public class assignStmt extends stmt implements Expression {
    expr expression = null;

    public assignStmt(TokenList tokens){
        System.out.println("ASSIGNSTMT-----");
        tokens.printList();
        System.out.println();

        System.out.println("TOKEN ENDING: " + tokens.get(tokens.size()-1));
        if(tokens.size() > 2) {
                if (tokens.get(1).equals(":=")) {
                    expression = new expr(tokens.between(2, tokens.size()));
                }
        }
    }
    @Override
    public boolean isValid() {
        System.out.println("CHECKING ASSIGNSTATEMENT ISVALID()");
        return true;
    }

    @Override
    public void execute() {

    }
}
