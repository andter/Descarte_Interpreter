/**
 * Created by andy on 11/28/2015.
 */
public class idListTail implements Expression {
    idListTail idListTail;
    String token;

    public idListTail(TokenList tokens){
        System.out.println("IDLISTTAIL-----");
        tokens.printList();
        System.out.println();

        tokens.remove(0);
        token = tokens.get(0);
        int firstComma = tokens.indexOf(",");
        if(firstComma != -1 && firstComma > 0){
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
        } else{
            System.out.print("null ");
        }
        if(idListTail != null){
            idListTail.execute(variables);
        }
    }
}
