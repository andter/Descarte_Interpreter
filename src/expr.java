/**
 * Created by andy on 11/23/2015.
 */
public class expr implements Expression{
    public expr(TokenList tokens){
        System.out.println("EXPR-----");
        tokens.printList();
        System.out.println();
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING EXPR ISVALID()");
        return true;
    }

    @Override
    public void execute() {

    }
}
