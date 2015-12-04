import java.util.ArrayList;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: TokenList
 * This class is a less generic version of the ArrayList class in which some helper methods are used to aid in the
 * process creating nodes
 */
public class TokenList extends ArrayList<String> {
    public TokenList between(int i, int j){
        TokenList list = new TokenList();
        for(int x = i; x < j; x++){
            list.add(this.get(x));
        }
        return list;
    }

    public int getTokenIndex(int pos, String value){
        if(pos < 0){
            return -1;
        }
        int temp = 0;
        int x = 0;
        for(String s : this){
            if (s.equals(value)) {
                if(temp == pos){
                    return x;
                }
                else{
                    temp++;
                }
            }
            x++;
        }
        return -1;
    }

    public void printList(){
        for(String s : this){
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
