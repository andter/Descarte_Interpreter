/**
 * Created by andy on 11/25/2015.
 */
public class arithExpr implements Expression{

    public arithExpr(TokenList tokens){
        System.out.println("ARITHEXPR-----");
        tokens.printList();
        System.out.println();


    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void execute() {

    }
}
