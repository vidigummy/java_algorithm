package softwaremaestro;

import java.lang.reflect.Array;
import java.util.*;

public class First {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N;
        int T;
        N = sc.nextInt();
        String[] suggestion = new String[N];
        for(int i = 0; i < N; i++){
            String tmp_input = sc.next();
            suggestion[i] = tmp_input;
        }
        T = sc.nextInt();
        String[] inputText = new String[T];
        for(int i = 0; i < T; i++){
            String tmp_input = sc.next();
            inputText[i] = tmp_input;
        }

        for(String a: inputText){
            int cnt = 0;
            int inputLen = a.length();
//            System.out.println(inputLen);
            for(String target: suggestion){
                int right_cnt = 0;
                int targetLen = target.length();
                if(targetLen>=inputLen)
                    for(int i= 0 ; i < inputLen ; i++){
                        if(a.charAt(i) == target.charAt(i)){
                            right_cnt +=1;
                        }
                    }
                if(right_cnt == inputLen){
                    cnt ++;
                }
            }
            System.out.println(cnt);
        }
    }
}
