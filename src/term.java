/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: term
 * This class is a term node used for execution of multiplication and division
 */
public class term implements Expression {
    factor factor;
    factorTail factorTail;

    public term(TokenList tokens){
        System.out.print("TERM: ");
        tokens.printList();

        int firstPlus = tokens.indexOf("*");
        int firstMinus = tokens.indexOf("/");

        if(firstPlus == -1){
            firstPlus = Integer.MAX_VALUE;
        }
        if(firstMinus == -1){
            firstMinus = Integer.MAX_VALUE;
        }

        int lowest = Math.min(firstPlus, firstMinus);
        if(lowest < Integer.MAX_VALUE){
            factor = new factor(tokens.between(0, lowest));
            factorTail = new factorTail(tokens.between(lowest, tokens.size()));
        } else{
            factor = new factor(tokens);
            factorTail = new factorTail();
        }


    }

    @Override
    public boolean isValid() {
        if(factor.isValid() && factorTail.isValid()){
            return true;
        }
        System.out.println("TERM NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables) {
        if (factorTail.executable) {
            switch (factorTail.type) {
                case MULTIPLY:
                    return factor.executeDouble(variables) * factorTail.executeDouble(variables);
                case DIVIDE:
                    return factor.executeDouble(variables) / factorTail.executeDouble(variables);
            }
        } else {
            return factor.executeDouble(variables);
        }
        return 0;
    }
}
