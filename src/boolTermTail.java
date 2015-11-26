/**
 * Created by andy on 11/24/2015.
 */
public class boolTermTail implements Expression{
    boolTerm boolTerm;
    boolTermTail boolTermTail;

    boolean valid;
    boolean executable;

    public boolTermTail(TokenList tokens){
        System.out.println("BoolTermTail-----");
        tokens.printList();
        System.out.println();
        if(tokens.size() > 1 && tokens.get(0).equals("OR")){
            tokens.remove(0);
            int firstOr = tokens.indexOf("OR");
            if(firstOr == -1){
                boolTerm = new boolTerm(tokens);
                boolTermTail = new boolTermTail();
                valid = true;
            }
            else{
                boolTerm = new boolTerm(tokens.between(0, firstOr));
                boolTermTail = new boolTermTail(tokens.between(firstOr, tokens.size()));
                valid = true;
            }
        }
    }

    public boolTermTail(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING BOOLTERMTAIL ISVALID()");
        if(valid){
            return true;
        }
        if(boolTerm.isValid() && boolTermTail.isValid()) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {

    }
}
