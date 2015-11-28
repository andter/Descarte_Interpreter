/**
 * Created by andy on 11/28/2015.
 */
public class printStmt extends stmt implements Expression{
    public printStmt(TokenList tokens){
        System.out.println("PRINTSTMT-----");
        tokens.printList();
        System.out.println();
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
