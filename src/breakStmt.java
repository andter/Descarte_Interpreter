/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: breakStmt
 * This class is a node used to break the while loop when it is complete
 */
public class breakStmt extends stmt implements Expression{
    boolean valid = false;

    public breakStmt(TokenList tokens) {
        System.out.println("BREAKSTMT-----");
        tokens.printList();
        System.out.println();

        if(tokens.get(0).equals("BREAK")){
            valid = true;
        }

    }

    public void execute(Variables variables){

    }

    public boolean executeLoop(Variables variables){
        return true;
    }



    @Override
    public boolean isValid() {
        return valid;
    }
}
