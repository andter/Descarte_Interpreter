/**
 * Created by andy on 11/26/2015.
 */
public class factor implements Expression{

    public factor(TokenList tokens){
        System.out.println("FACTOR-----");
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
