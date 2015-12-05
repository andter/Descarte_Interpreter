/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: boolTermTail
 * This class is a node used to build out and execute the grammar for using statements with OR
 */
public class boolTermTail implements Expression{
    boolTerm boolTerm;
    boolTermTail boolTermTail;

    boolean valid;
    boolean executable = true;

    public boolTermTail(TokenList tokens){
        System.out.print("BoolTermTail: ");
        tokens.printList();
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
        System.out.println("BOOLTERMTAIL NOT VALID");
        return false;
    }

    public boolean executeBoolean(Variables variables){
        if(boolTermTail.executable){
            if(boolTerm.executeBoolean(variables) || boolTermTail.executeBoolean(variables)){
                return true;
            } else{
                return false;
            }
        }
        return boolTerm.executeBoolean(variables);
    }
}
