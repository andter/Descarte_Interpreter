/**
 * Created by andy on 11/23/2015.
 */
public class elsePart implements Expression{
    boolean valid = false;
    boolean executable = false;

    stmtList statementList;

    public elsePart(TokenList tokens){
        System.out.println("ELSEPART-----");
        tokens.printList();
        System.out.println();

        int firstElse = tokens.indexOf("ELSE");

        if (firstElse == -1) { //Else doesn't exist
            if(tokens.size() == 1 && tokens.get(0).equals("FI")){
                valid = true;
            }
        } else{
            if(tokens.get(tokens.size()-1).equals("FI"));
            if(firstElse < tokens.size()){
                if(tokens.between(firstElse+1, tokens.size()-1).size() > 1) {
                    statementList = new stmtList(tokens.between(firstElse + 1, tokens.size()-1));
                    executable = true;
                } else{
                    statementList = new stmtList();
                    executable = false;
                }
            }
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ELSEPART ISVALID()");
        if(valid || statementList.isValid()){
            return true;
        }
        System.out.println("ELSE PART NOT VALID");
        return false;
    }

    public void execute(Variables variables) {
        if(statementList != null) {
            statementList.execute(variables);
        }
    }
}
