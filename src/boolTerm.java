/**
 * Created by andy on 11/23/2015.
 */
public class boolTerm implements Expression {
    boolFactor boolFactor;
    boolFactorTail boolFactorTail;

    public boolTerm(TokenList tokens){
        System.out.println("BOOLTERM-----");
        tokens.printList();
        System.out.println();

        int firstAnd = tokens.indexOf("AND");
        if(firstAnd == -1){
            boolFactor = new boolFactor(tokens);
        }
        else{
            boolFactor = new boolFactor(tokens.between(0, firstAnd));
            boolFactorTail = new boolFactorTail(tokens.between(firstAnd, tokens.size()));
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
