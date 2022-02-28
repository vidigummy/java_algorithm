package programers.sort;

import java.util.Arrays;
import java.util.Comparator;
public class BiggestNumber {
    public static String solution(int[] numbers) {
        String answer;
        String ans[] = new String[numbers.length];
        for(int i =0; i < numbers.length; i++){
            ans[i] = String.valueOf(numbers[i]);
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        };
        Arrays.sort(ans,comparator);
        if(ans[0].equals("0")){
            answer = "0";
        }else{
            answer = String.join("",ans);
        }
        return answer;
    }
    public static void main(String[] args){
        int[] numbers = {6,10,2};
        String ans = solution(numbers);
        System.out.println(ans);
    }
}
