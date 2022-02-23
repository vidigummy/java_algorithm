package programers.stack_queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Development {
    public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int cnt = 0;
        Queue<Integer> p_queue = new LinkedList<>();
        Queue<Integer> s_queue = new LinkedList<>();
        for(int progress : progresses){
            p_queue.add(progress);
        }
        for(int speed: speeds){
            s_queue.add(speed);
        }
        while(cnt<100){
            int tmp = 0;
            cnt++;
            if(!p_queue.isEmpty()){
                while(p_queue.peek()+s_queue.peek()*cnt >= 100){
                    tmp += 1;
                    p_queue.remove();
                    s_queue.remove();
                    if(p_queue.isEmpty()){
                        break;
                    }
                }
                if(tmp != 0){
                    answer.add(tmp);
                }
            }else{
                break;
            }
        }
        int[] ans = new int[answer.size()];
        int i = 0;
        for(int a : answer){
            ans[i] = a;
            i++;
        }
        return ans;
    }
    public static void main(String[] args){
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] ans = solution(progresses, speeds);
        for(int i : ans){
            System.out.print(i + " ");
        }
    }
}
