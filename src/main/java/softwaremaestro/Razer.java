package softwaremaestro;

import java.util.Scanner;
import java.util.Stack;

public class Razer {
    public static void main(String args[]) {
        String input = "";
        int beam = 0;
        Scanner sc = new Scanner(System.in);
        input = sc.next();
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) == '('){
                beam++;
            }else{
                beam--;
            }
        }
        if(beam==0){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
