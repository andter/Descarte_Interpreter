/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: boolFactorTail
 * This class is a node used to build out and execute the grammar for using statements with AND
 */
public class boolFactorTail implements Expression{
    boolFactor boolFactor;
    boolFactorTail boolFactorTail;
    boolean valid;
    boolean executable = true;

    public boolFactorTail(TokenList tokens){
        System.out.print("BoolFactorTail: ");
        tokens.printList();
        if(tokens.size() > 0 && tokens.get(0).equals("AND")){
            tokens.remove(0);
            int firstAnd = tokens.indexOf("AND");
            if(firstAnd == -1){
                boolFactor = new boolFactor(tokens);
                boolFactorTail = new boolFactorTail();
            }
            else{
                boolFactor = new boolFactor(tokens.between(0, firstAnd));
                boolFactorTail = new boolFactorTail(tokens.between(firstAnd, tokens.size()));
            }
        }
    }

    public boolFactorTail(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING BOOLFACTORTAIL ISVALID()");
        if(valid){
            return true;
        }
        if (boolFactor.isValid() && boolFactorTail.isValid()) {
            return true;
        }
        System.out.println("BOOLFACTORTAIL NOT VALID");
        return false;
    }

    public String executeString() {
        return null;
    }

    public boolean executeBoolean(Variables variables){
        if(boolFactorTail.executable){
            if(boolFactor.executeBoolean(variables) && boolFactorTail.executeBoolean(variables)){
                return true;
            } else{
                return false;
            }
        }
        else{
            return boolFactor.executeBoolean(variables);
        }
    }
}