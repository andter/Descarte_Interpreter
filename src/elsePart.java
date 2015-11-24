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
                statementList = new stmtList(tokens.between(firstElse+1, firstFI));
                valid = true;
                executable = true;
            }
        }
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void execute() {

    }
}
