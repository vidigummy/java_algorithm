package programers.stack_queue;

import java.util.*;

public class Printer {
    public static int solution(int[] priorities, int location) {
        int answer = 1;
        HashMap<Integer, Integer> map =new HashMap<>();
        Queue<HashMap<Integer,Integer>> queue = new LinkedList<>();
//        키는 중요도, 밸류는 숫자
        for (int priority : priorities) {
            if (map.containsKey(priority)) {
                map.put(priority, map.get(priority) + 1);
            } else {
                map.put(priority, 1);
            }
        }
//        키는 중요도, 밸류는 인덱스
        for(int i = 0; i < priorities.length; i++){
            HashMap<Integer, Integer> tmp = new HashMap<>();
            tmp.put(priorities[i], i);
            queue.add(tmp);
        }
        while (!queue.isEmpty()){
            boolean now = true;
            int key =0;
            HashMap<Integer, Integer> tmp = queue.peek();
            for (Integer integer : tmp.keySet()) {
                key = integer;
                for (int i = key; i <= 9; i++) {
                    if (map.containsKey(i)) {
                        if (map.get(i) > 0 && i > key) {
                            now = false;
                            break;
                        }
                    }
                }
            }
            if(!now) {
                queue.remove();
                queue.add(tmp);
            }else{
                if(tmp.get(key) == location){
                    return answer;
                }else{
                    answer++;
                    queue.remove();
                    map.put(key, map.get(key)-1);
                }

            }
        }
        return answer;
    }

    public static void main(String[] args){
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        int ans = solution(priorities, location);
        System.out.println(ans);
    }
}
