/**
 * Created by andy on 11/25/2015.
 */
public class relationOption implements Expression{
    arithExpr arithmeticExpression;
    boolean executable;
    boolean valid;
    public relationOption(TokenList tokens){
        System.out.println("RELATIONOPTION-----");
        tokens.printList();
        System.out.println();

        int lessThan = tokens.indexOf("<");
        int lessThanEquals = tokens.indexOf("<=");
        int equals = tokens.indexOf("=");
        int greaterThanEquals = tokens.indexOf(">=");
        int greaterThan = tokens.indexOf(">");
        int lessThanGreaterThan = tokens.indexOf("<>");

        if(lessThan == 0 || lessThanEquals == 0 || equals == 0
                || greaterThanEquals == 0 || greaterThan == 0 || lessThanGreaterThan == 0){
            arithmeticExpression = new arithExpr(tokens.between(1, tokens.size()));

        }
    }

    public relationOption(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid()
    {
        if(valid){
            return true;
        }
        if(arithmeticExpression.isValid()){
            return true;
        }
        System.out.println("RELATION OPTION NOT VALID");
        return false;
    }

    @Override
    public void execute() {

    }
}
