/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: arithExpr
 * This class is a node used to correctly build out part of the tree for performing additional and subtraction
 */
public class arithExpr implements Expression{

    term term;
    termTail termTail;

    public arithExpr(TokenList tokens){
        System.out.println("ARITHEXPR-----");
        tokens.printList();
        System.out.println();

        int firstPlus = tokens.indexOf("+");
        int firstMinus = tokens.indexOf("-");

        if(firstPlus == -1){
            firstPlus = Integer.MAX_VALUE;
        }
        if(firstMinus == -1){
            firstMinus = Integer.MAX_VALUE;
        }

        int lowest = Math.min(firstPlus, firstMinus);
        if(lowest < Integer.MAX_VALUE){
            term = new term(tokens.between(0, lowest));
            termTail = new termTail(tokens.between(lowest, tokens.size()));
        } else{
            term = new term(tokens);
            termTail = new termTail();
        }

    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ARITHEXPR ISVALID()");
        if(term.isValid() && termTail.isValid()) {
            return true;
        }
        System.out.println("ArithExpr NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables){
        if(termTail.executable){
            switch(termTail.type){
                case PLUS:
                    return term.executeDouble(variables) + termTail.executeDouble(variables);
                case MINUS:
                    return term.executeDouble(variables) - termTail.executeDouble(variables);
            }
        } else{
            return term.executeDouble(variables);
        }
        return 0;
    }

    public void execute() {

    }
}
