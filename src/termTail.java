/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: termTail
 * This is a node used for the term node in order to ensure terms are executed sequentially
 * Note: See Term for additional details
 */
public class termTail implements Expression{
    boolean valid;
    boolean executable = true;
    public enum Type{
        NONE,
        PLUS,
        MINUS
    }

    Type type;
    term term;
    termTail termTail;

    public termTail(TokenList tokens){
        System.out.println("TERMTAIL: ");
        tokens.printList();

        if(tokens.get(0).equals("+") || tokens.get(0).equals("-")) {
            if(tokens.get(0).equals("+")){
                type = Type.PLUS;
            } else{
                type = Type.MINUS;
            }
            tokens.remove(0);

            int firstPlus = tokens.indexOf("+");
            int firstMinus = tokens.indexOf("-");

            if (firstPlus == -1) {
                firstPlus = Integer.MAX_VALUE;
            }
            if (firstMinus == -1) {
                firstMinus = Integer.MAX_VALUE;
            }

            int lowest = Math.min(firstPlus, firstMinus);
            if (lowest < Integer.MAX_VALUE) {
                term = new term(tokens.between(0, lowest));
                termTail = new termTail(tokens.between(lowest, tokens.size()));
            } else {
                term = new term(tokens);
                termTail = new termTail();
            }
        }
    }

    public termTail(){
        valid = true;
        executable = false;
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING TERMTAIL ISVALID()");
        if(valid){
            return true;
        }
        if(term.isValid() && termTail.isValid()){
            return true;
        }
        System.out.println("TERMTAIL NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables) {
        if(termTail.executable){
            switch(termTail.type){
                case PLUS:
                    return term.executeDouble(variables) + termTail.executeDouble(variables);
                case MINUS:
                    return term.executeDouble(variables) - termTail.executeDouble(variables);
            }
        } else{
            return term.executeDouble(variables);
        }
        return 0;
    }
}
