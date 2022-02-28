package programers.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static int[] solution(int[] answers) {

        int answers_len = answers.length;
        int first_cnt = 0;
        int[] first_ans = {1,2,3,4,5};
        int first_ans_len = first_ans.length;
        int second_cnt = 0;
        int[] second_ans = {2,1,2,3,2,4,2,5};
        int second_ans_len = second_ans.length;
        int third_cnt =0;
        int[] third_ans = {3,3,1,1,2,2,4,4,5,5};
        int third_ans_len = third_ans.length;
        int max = -999;
        for(int i =0; i < answers_len; i++){
            if(first_ans[i%first_ans_len] == answers[i]){
                first_cnt++;
                if(max < first_cnt){
                    max = first_cnt;
                }
            }
            if(second_ans[i%second_ans_len] == answers[i]){
                second_cnt++;
                if(max < second_cnt){
                    max=second_cnt;
                }
            }
            if(third_ans[i%third_ans_len] == answers[i]){
                third_cnt++;
                if(max<third_cnt){
                    max=third_cnt;
                }
            }
        }
        List<Integer> arr = new ArrayList<>();
        if(first_cnt==max){
            arr.add(1);
        }
        if(second_cnt==max){
            arr.add(2);
        }
        if(third_cnt==max){
            arr.add(3);
        }
        int[] answer = new int[arr.size()];
        for(int i =0 ; i< arr.size(); i++){
            answer[i] = arr.get(i);
        }
        return answer;
    }

    public static void main(String[] args){
        int[] answers = {1,3,2,4,2};
        int[] ans = solution(answers);
        for(int i: ans){
            System.out.println(i);
        }
    }
}
