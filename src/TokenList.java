import java.util.ArrayList;

/**
 * Created by andy on 11/22/2015.
 */
public class TokenList extends ArrayList<String> {
    public TokenList between(int i, int j){
        TokenList list = new TokenList();
        for(int x = i; x < j; x++){
            list.add(this.get(x));
        }
        return list;
    }

    public void printList(){
        for(String s : this){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
