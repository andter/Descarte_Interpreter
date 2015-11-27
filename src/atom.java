/**
 * Created by andy on 11/26/2015.
 */
public class atom implements Expression{
    boolean valid = false;

    public atom(TokenList tokens){
        System.out.println("ATOM-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() == 1){
            valid = true;
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ATOM ISVALID()");
        if(valid){
            return true;
        }
        System.out.println("ATOM NOT VALID");
        return false;
    }

    @Override
    public void execute() {

    }
}
