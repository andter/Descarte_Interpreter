/**
 * Created by andy on 12/1/2015.
 */
public class loopStmt extends stmt implements Expression{
    String id;
    stmtList statementList;

    public loopStmt(TokenList tokens){
        System.out.println("LOOPSTMT-----");
        tokens.printList();
        System.out.println();
        if(tokens.get(0).equals("LOOP") && tokens.get(2).equals(":")){
            id = tokens.get(1);

            boolean complete = false;
            int count = 0;
            int repeatLocation = 0;
            int index = 0;
            for(String s : tokens) {
                if (s.equals("LOOP")) {
                    count++;
                } else if (s.equals("REPEAT")) {
                    count--;
                }
                if (count == 0 && s.equals("REPEAT")) {
                    if (!complete) {
                        repeatLocation = index;
                        complete = true;
                    }
                }
                index++;
            }
            statementList = new stmtList(tokens.between(3, repeatLocation));
        }
    }

    @Override
    public boolean isValid() {
        if(statementList != null && statementList.isValid()){
            return true;
        }
        return false;
    }

    @Override
    public boolean executeLoop(Variables variables){
        if(statementList.executeStmtListLoop(variables)){
            return true;
        }
        return false;
    }

    public void execute(Variables variables){
        statementList.executeLoop(variables);
    }
}
