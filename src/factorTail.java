/**
 * Created by andy on 11/26/2015.
 */
public class factorTail implements Expression {
    boolean valid;
    boolean executable = true;

    factor factor;
    factorTail factorTail;

    public factorTail(TokenList tokens){
        System.out.println("FACTORTAIL-----");
        tokens.printList();
        System.out.println();

        if(tokens.get(0).equals("*") || tokens.get(0).equals("/")) {
            tokens.remove(0);

            int firstMult = tokens.indexOf("*");
            int firstDiv = tokens.indexOf("/");

            if (firstMult == -1) {
                firstMult = Integer.MAX_VALUE;
            }

            if (firstDiv == -1) {
                firstDiv = Integer.MAX_VALUE;
            }

            int lowest = Math.min(firstMult, firstDiv);
            if (lowest < Integer.MAX_VALUE) {
                factor = new factor(tokens.between(0, lowest));
                factorTail = new factorTail(tokens.between(lowest, tokens.size()));
            } else {
                factor = new factor(tokens);
                factorTail = new factorTail();
            }
        }
    }

    public factorTail(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid() {
        if(valid){
            return true;
        }
        if(factor.isValid() && factorTail.isValid()){
            return true;
        }
        System.out.println("FACTOR TAIL NOT VALID");
        return false;
    }

    @Override
    public void execute() {

    }
}
