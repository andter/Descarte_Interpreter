/**
 * Created by andy on 11/25/2015.
 */
public class boolFactorTail implements Expression{
    boolFactor boolFactor;
    boolFactorTail boolFactorTail;
    boolean valid;
    boolean executable;

    public boolFactorTail(TokenList tokens){
        System.out.println("BoolFactorTail-----");
        tokens.printList();
        System.out.println();
        if(tokens.size() > 0 && tokens.get(0).equals("AND")){
            tokens.remove(0);
            int firstAnd = tokens.indexOf("AND");
            if(firstAnd == -1){
                boolFactor = new boolFactor(tokens);
                boolFactorTail = new boolFactorTail();
                valid = true;
            }
            else{
                boolFactor = new boolFactor(tokens.between(0, firstAnd));
                boolFactorTail = new boolFactorTail(tokens.between(firstAnd, tokens.size()));
                valid = true;
            }
        }
    }

    public boolFactorTail(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid() {
        if(boolFactor.isValid() && boolFactorTail.isValid()) {
            return valid;
        }
        return false;
    }

    @Override
    public void execute() {

    }
}