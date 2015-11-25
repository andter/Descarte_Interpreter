/**
 * Created by andy on 11/23/2015.
 */
public class expr implements Expression{
    boolTerm boolTerm;
    boolTermTail boolTermTail;

    public expr(TokenList tokens){
        System.out.println("EXPR-----");
        tokens.printList();
        System.out.println();

        int firstOr = tokens.indexOf("OR");
        if(firstOr == -1){
            boolTerm = new boolTerm(tokens);
            boolTermTail = new boolTermTail();
        }
        else{
            boolTerm = new boolTerm(tokens.between(0, firstOr));
            boolTermTail = new boolTermTail(tokens.between(firstOr, tokens.size()));
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
