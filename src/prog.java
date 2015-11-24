import java.util.ArrayList;

/**
 * Created by andy on 11/22/2015.
 */
public class prog implements Expression{
    private boolean valid = false;
    stmtList statementList = null;
    boolean debug;

    public prog(TokenList tokens){
        System.out.println("PROG-----");
        tokens.printList();

        if(tokens.get(tokens.size()-1).equals(".")){
            valid = true;
            statementList = new stmtList(tokens.between(0, tokens.size()-1));
        }
        else{
            System.out.println("Error: Program MUST end in a .");
        }
    }

    public void execute(){
        statementList.execute();
    }
    @Override
    public boolean isValid() {
        if(statementList == null){
            return false;
        }
        else{
            if(statementList.isValid()){
                return true;
            }
        }
        return false;
    }
}
