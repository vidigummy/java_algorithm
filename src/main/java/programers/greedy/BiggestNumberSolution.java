package programers.greedy;

import java.util.ArrayList;
import java.util.List;

public class BiggestNumberSolution {
    public static String solution(String number, int k) {
        String answer = "";
        List<String> result = new ArrayList<>();
        int maxIndex = 0;
        int numberLen = number.length();
        for(int i =0 ; i < numberLen-k; i++){
            int max = 0;
            for(int j = maxIndex; j <= k+i; j++){
                if(max < number.charAt(j)-'0'){
                    max = number.charAt(j)-'0';
                    maxIndex = j+1;
                }
            }
            result.add(Integer.toString(max));
        }
        answer = String.join("",result);
        return answer;
    }

    public static void main(String[] args){
        String number = "4177252841";
        int k = 4;
        String ans = solution(number,k);
        System.out.println(ans);
    }
}
