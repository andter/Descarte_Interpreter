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

        int firstElse = tokens.indexOf("ELSE");
        int firstFI = tokens.indexOf("FI");

        if (firstElse == -1) { //Else doesn't exist
            if(tokens.size() == 1 && tokens.get(0).equals("FI")){
                valid = true;
            }
        } else{
            if(firstElse < firstFI){
                if(tokens.between(firstElse+1, firstFI).size() > 1) {
                    statementList = new stmtList(tokens.between(firstElse + 1, firstFI));
                } else{
                    statementList = new stmtList();
                }
                executable = true;
            }
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING ELSEPART ISVALID()");
        if(valid || statementList.isValid()){
            System.out.println("ELSE PART VALID");
            return true;
        }
        return false;
    }

    @Override
    public void execute() {

    }
}
