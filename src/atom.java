/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: atom
 * This class is the final bottom node in a grammar, and can be either an identifier or a value
 */
public class atom implements Expression{
    boolean valid = false;
    String token;

    public atom(TokenList tokens){
        System.out.println("ATOM-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() == 1){
            token = tokens.get(0);
            valid = true;
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ATOM ISVALID()");
        if(valid){
            return true;
        }
        System.out.println("ATOM NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables) {
        if(valid) {
            try {
                return Double.parseDouble(token);
            } catch(NumberFormatException e){
                if(variables.checkVariable(token)){
                    return variables.getValue(token);
                }
                else{
                    System.out.println("ERROR! Reference to Variable: " + token + " was not found!");
                    System.exit(0);
                }
            }
        }
        return 0;
    }
}
