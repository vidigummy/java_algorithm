package programers.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class TrainingSuit {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        answer = n - lost.length;
        HashMap<Integer,Integer> reserve_list = new HashMap<>();
        HashMap<Integer,Integer> lost_list = new HashMap<>();
        Arrays.sort(lost);
        for(int losted_student: lost){
            lost_list.put(losted_student,1);
        }
        for(int reserved_student: reserve){
            reserve_list.put(reserved_student,1);
        }
//        System.out.println(reserve_list.toString());
        for(int losted_student : lost){
            if(reserve_list.containsKey(losted_student)) {
                reserve_list.remove(losted_student);
                lost_list.remove(losted_student);
                answer++;
            }
        }
//        System.out.println(reserve_list.toString());
        Iterator<Integer> lost_it = lost_list.keySet().iterator();
        for (Iterator<Integer> it = lost_it; it.hasNext(); ) {
            Integer losted_student = it.next();
            System.out.println(losted_student);
            if(reserve_list.containsKey(losted_student-1)){
                answer++;
                reserve_list.remove(losted_student-1);
            }else if(reserve_list.containsKey(losted_student+1)){
                answer++;
                reserve_list.remove(losted_student+1);
            }
        }
        return answer;
    }
    public static void main(String[] args){
        int[] lost = {6,2,4,3};
        int[] reserve = {1,5,3};
        int n = 6;
        int ans = solution(n,lost,reserve);
        System.out.println(ans);
    }
}
