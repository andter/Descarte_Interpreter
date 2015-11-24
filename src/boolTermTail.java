/**
 * Created by andy on 11/24/2015.
 */
public class boolTermTail implements Expression{


    public boolTermTail(TokenList tokens){
        System.out.println("BoolTermTail-----");
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
