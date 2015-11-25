/**
 * Created by andy on 11/25/2015.
 */
public class boolFactor implements Expression{

    public boolFactor(TokenList tokens){
        System.out.println("BoolFactor-----");
        tokens.printList();
        System.out.println();


    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void execute() {

    }
}
