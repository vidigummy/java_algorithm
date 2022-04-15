package softwaremaestro;

import java.util.*;
public class Third {
    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int a, b;
        a = sc.nextInt();
        b = sc.nextInt();
        String input = sc.next();
        int inputLen = input.length();
        for(int i=0; i < inputLen; i++){
            char now = input.charAt(i);
            int now_to_int = now - 97;
//            System.out.println(now_to_int);
            for(int j = 0; j < 100000; j++){
                if((now_to_int+(j*26) - b)%a == 0){
                    char result = (char)((now_to_int+(j*26) - b)/a + 97);
                    String tmp = Character.toString(result);
                    sb.append(tmp);
                    break;
                }
            }
        }
        System.out.println(sb.toString());
    }
}
