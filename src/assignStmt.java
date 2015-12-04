/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: assignStmt
 * This class is a node used to correctly build out the statement for assigning a value to a variable
 */
public class assignStmt extends stmt implements Expression {
    expr expression = null;
    String variable;

    public assignStmt(TokenList tokens){
        System.out.println("ASSIGNSTMT-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() > 2) {
                variable = tokens.get(0);
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
    public void execute(Variables variables) {
        variables.addVariable(variable, expression.executeDouble(variables));
    }

    @Override
    public boolean executeLoop(Variables variables){
        variables.addVariable(variable, expression.executeDouble(variables));
        return false;
    }
}
