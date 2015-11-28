/**
 * Created by andy on 11/26/2015.
 */
public class factorTail implements Expression {
    boolean valid;
    boolean executable = true;

    public enum Type{
        NONE,
        MULTIPLY,
        DIVIDE
    }

    Type type;

    factor factor;
    factorTail factorTail;

    public factorTail(TokenList tokens){
        System.out.println("FACTORTAIL-----");
        tokens.printList();
        System.out.println();

        if(tokens.get(0).equals("*") || tokens.get(0).equals("/")) {
            if(tokens.get(0).equals("*")){
                type = Type.MULTIPLY;
            } else{
                type = Type.DIVIDE;
            }
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

    public double executeDouble(Variables variables) {
        if (factorTail.executable) {
            switch (factorTail.type) {
                case MULTIPLY:
                    return factor.executeDouble(variables) * factorTail.executeDouble(variables);
                case DIVIDE:
                    return factor.executeDouble(variables) / factorTail.executeDouble(variables);
            }
        } else {
            return factor.executeDouble(variables);
        }
        return 0;
    }
}
