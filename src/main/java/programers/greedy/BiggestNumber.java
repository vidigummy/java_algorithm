package programers.greedy;

import java.awt.*;

public class BiggestNumber {
    public static String solution(String number, int k) {
        String answer = "";
        int lenOriginNumber = number.length();
        Integer[] arr = new Integer[lenOriginNumber];
        int cnt = 0;
        int maxInFirst = -999;
        int maxIndex =0;
        for(int i = 0; i < k; i++){
            if(maxInFirst<number.charAt(i)-'0'){
                maxInFirst = number.charAt(i)-'0';
                maxIndex = i;
            }
        }
        cnt += maxIndex;
        String newString = number.substring(maxIndex,lenOriginNumber);
        int nowLen = newString.length();
        do {
            if(nowLen == lenOriginNumber-k){
                break;
            }
            for(int i= 1; i < nowLen-1; i++){
                if(newString.charAt(i)-'0' < newString.charAt(i+1)-'0'){
                    newString = newString.substring(0,i)+newString.substring(i+1, nowLen);
                    cnt++;
                    nowLen--;
                    break;
                }
            }
        }while(cnt <= k);
        answer = newString;
        return answer;
    }
    public static void main(String[] args){
        String number = "4177252841";
        int k = 4;
        String ans = solution(number,k);
        System.out.println(ans);
    }
}
