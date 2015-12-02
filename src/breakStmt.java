/**
 * Created by andre on 12/1/2015.
 */
public class breakStmt extends stmt implements Expression{
    boolean valid = false;

    public breakStmt(TokenList tokens) {
        System.out.println("BREAKSTMT-----");
        tokens.printList();
        System.out.println();

        if(tokens.get(0).equals("BREAK") && tokens.size() < 3){
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
