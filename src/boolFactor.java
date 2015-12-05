/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: boolFactor
 * This class is a node used to build out and execute the grammar for comparing boolean expressions
 */
public class boolFactor implements Expression{
    arithExpr arithmeticExpression;
    relationOption relationOption;

    public boolFactor(TokenList tokens){
        System.out.print("BoolFactor: ");
        tokens.printList();

        int lessThan = tokens.indexOf("<");
        int lessThanEquals = tokens.indexOf("<=");
        int equals = tokens.indexOf("=");
        int greaterThanEquals = tokens.indexOf(">=");
        int greaterThan = tokens.indexOf(">");
        int lessThanGreaterThan = tokens.indexOf("<>");
        int notEqualTo = tokens.indexOf("!=");

        if(lessThan == -1){
            lessThan = Integer.MAX_VALUE;
        }
        if(lessThanEquals == -1){
            lessThanEquals = Integer.MAX_VALUE;
        }
        if(equals == -1){
            equals = Integer.MAX_VALUE;
        }
        if(greaterThanEquals == -1){
            greaterThanEquals = Integer.MAX_VALUE;
        }
        if(greaterThan == -1){
            greaterThan = Integer.MAX_VALUE;
        }
        if(lessThanGreaterThan == -1){
            lessThanGreaterThan  = Integer.MAX_VALUE;
        }
        if(notEqualTo == -1){
            notEqualTo = Integer.MAX_VALUE;
        }

        int lowest = Math.min(lessThan, lessThanEquals);
        lowest = Math.min(lowest, equals);
        lowest = Math.min(lowest, greaterThanEquals);
        lowest = Math.min(lowest, greaterThan);
        lowest = Math.min(lowest, lessThanGreaterThan);
        lowest = Math.min(lowest, notEqualTo);

        if(lowest < Integer.MAX_VALUE){
            arithmeticExpression = new arithExpr(tokens.between(0, lowest));
            relationOption = new relationOption(tokens.between(lowest, tokens.size()));
        } else{
            arithmeticExpression = new arithExpr(tokens);
            relationOption = new relationOption();
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING BOOLFACTOR ISVALID()");
        if(arithmeticExpression.isValid() && relationOption.isValid()) {
            return true;
        }
        System.out.println("BOOLFACTOR NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables) {
        return arithmeticExpression.executeDouble(variables);
    }

    public boolean executeBoolean(Variables variables){
        if(relationOption.executable){
            switch(relationOption.type){
                case LESSTHAN:
                    if(arithmeticExpression.executeDouble(variables) < relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
                case LESSTHANEQUALS:
                    if(arithmeticExpression.executeDouble(variables) <= relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
                case GREATERTHAN:
                    if(arithmeticExpression.executeDouble(variables) > relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
                case GREATERTHANEQUALS:
                    if(arithmeticExpression.executeDouble(variables) >= relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
                case EQUALS:
                    if(arithmeticExpression.executeDouble(variables) == relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
                case LESSTHANGREATERTHAN:
                    return true;
                case NOTEQUALTO:
                    if(arithmeticExpression.executeDouble(variables) != relationOption.executeDouble(variables)){
                        return true;
                    }
                    return false;
            }
        }
        return false;
    }


}
