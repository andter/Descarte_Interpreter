/**
 * Created by andy on 11/25/2015.
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
        return true;
    }

    @Override
    public void execute() {

    }
}
