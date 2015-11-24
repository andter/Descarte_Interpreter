/**
 * Created by andy on 11/22/2015.
 */
public class stmtTail implements Expression{
    boolean valid;
    boolean executable = true;
    stmt statement = null;
    stmtTail statementTail = null;

    public stmtTail(TokenList tokens) {
        //System.out.println("stmtTail-----");
        //tokens.printList();
        if(tokens.size() > 1) {
            if (tokens.get(0).equals(";")) {
                valid = true;
                tokens.remove(0);
            }
            if (tokens.get(0).equals("FI")) {
                valid = true;
                tokens.remove(0);
            }

            String newStatement = tokens.get(0);
            int beginTail = -1;

            switch (newStatement) {
                case "ID":
                    beginTail = tokens.indexOf(";");
                    break;
                case "IF":
                    beginTail = tokens.indexOf("FI");
                    break;
                case "LOOP":
                    beginTail = tokens.indexOf("REPEAT");
                    break;
                default:
                    beginTail = tokens.indexOf(";");
                    break;
            }

            if (beginTail == -1) {
                statement = new stmt(tokens);
                statementTail = new stmtTail();
            } else {
                statement = new stmt(tokens.between(0, beginTail));
                statementTail = new stmtTail(tokens.between(beginTail, tokens.size()));
            }
        }
    }

    public stmtTail(){
        valid = true;
        executable = false;
    }

    public void execute(){
        /*if(valid && executable) {
            statement.execute();
            statementTail.execute();
        }*/
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}
