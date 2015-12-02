/**
 * Created by andy on 11/28/2015.
 */
public class printStmt extends stmt implements Expression{
    idListTail idListTail;
    String token;

    public printStmt(TokenList tokens){
        System.out.println("PRINTSTMT-----");
        tokens.printList();
        System.out.println();

        token = tokens.get(1);
        int firstComma = tokens.indexOf(",");
        if(firstComma != -1 && firstComma > 1){
            idListTail = new idListTail(tokens.between(firstComma, tokens.size()));
        }
    }

    @Override
    public boolean isValid() {
        if(idListTail != null){
            if(idListTail.isValid()){
                return true;
            }
        }
        return true;
    }

    public void execute(Variables variables){
        if(variables.checkVariable(token)){
            System.out.print(variables.getValue(token) + " ");
        }
        else{
            System.out.print("null ");
        }
        if(idListTail != null){
            idListTail.execute(variables);
        }
    }

    @Override
    public boolean executeLoop(Variables variables){
        if(variables.checkVariable(token)){
            System.out.print(variables.getValue(token) + " ");
        }
        else{
            System.out.print("null ");
        }
        if(idListTail != null){
            idListTail.execute(variables);
        }
        return false;
    }
}
