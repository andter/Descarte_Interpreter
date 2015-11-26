/**
 * Created by andy on 11/25/2015.
 */
public class term implements Expression {
    factor factor;
    factorTail factorTail;

    public term(TokenList tokens){
        System.out.println("TERM-----");
        tokens.printList();
        System.out.println();

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
        return false;
    }

    @Override
    public void execute() {

    }
}
