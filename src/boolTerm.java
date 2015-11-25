/**
 * Created by andy on 11/23/2015.
 */
public class boolTerm implements Expression {

    public boolTerm(TokenList tokens){
        System.out.println("BOOLTERM-----");
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
