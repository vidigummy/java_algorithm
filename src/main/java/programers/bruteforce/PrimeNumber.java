package programers.bruteforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrimeNumber {
    static boolean[] visited ;
    static String[] token;
    static String[] sel;
    static int answer ;
    static HashMap<Integer, Integer> hs;
    public int solution(String numbers) {
        answer = 0;
        visited = new boolean[ numbers.length()];
        token = new String[ numbers.length() ];
        sel = new  String [ numbers.length() ];
        hs = new HashMap<Integer, Integer>();

        for( int i=0 ; i< numbers.length(); i++){
            token[i] = numbers.charAt(i)+ "";
        }
        // 1. 순열구하기
        for( int i=1; i<= numbers.length(); i++){
            perm(0, token, i);
        }

        // perm(0, token);
        // 2. 각 순열 정수로 이어 붙여서 소수되는거 개수 세자.
        /*
        for( Entry<Integer, Integer> tmp : hs.entrySet()){
            System.out.println( tmp.getKey() + " value : " + tmp.getValue());
        }
        */
        L : for( Integer value : hs.keySet()){
            // 소수체크
            System.out.println(value);
            if ( value == 1 || value == 0) continue;
            for( int i=2; i<value; i++){
                if( value % i == 0 ) continue L ;
            }
            answer ++;
        }


        return answer;
    }
    private static void perm(int idx, String[] token, int s_idx){
        // 기저조건
        if ( idx == s_idx){
            // 순서대로 다 붙이고
            String test="";
            for( int i=0; i< visited.length; i++){
                test += sel[i] == null? "" : sel[i];

            }
            hs.put( Integer.parseInt(test), hs.getOrDefault(Integer.parseInt(test), 1));

            return;
        }

        for( int i=0; i<visited.length; i++){
            if ( !visited[i]){
                sel[idx] = token[i];
                visited[i] = true;
                perm(idx+1,token, s_idx);
                visited[i] = false;
            }
        }

    }
    public static void main(String[] args){

    }
}
