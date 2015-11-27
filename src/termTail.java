/**
 * Created by andy on 11/25/2015.
 */
public class termTail implements Expression{
    boolean valid;
    boolean executable;

    term term;
    termTail termTail;

    public termTail(TokenList tokens){
        System.out.println("TERMTAIL-----");
        tokens.printList();
        System.out.println();

        if(tokens.get(0).equals("+") || tokens.get(0).equals("-")) {
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
        executable = true;
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

    @Override
    public void execute() {

    }
}
