package programers.level.one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class two {

    public static int solution(int n, int[] lost, int[] reserve){
        Map<Integer, Integer> lostMap = new HashMap<>();
        Map<Integer, Integer> reserveMap = new HashMap<>();
        for(int i =0; i < reserve.length;i ++){
            reserveMap.put(reserve[i],1);
        }
        for(int i=0; i < lost.length; i++){
            lostMap.put(lost[i],1);
            if(reserveMap.containsKey(lost[i])){
                reserveMap.remove(lost[i]);
                lostMap.remove(lost[i]);
            }
        }
        for(int stu : reserveMap.keySet()){
            if(lostMap.containsKey(stu-1)){
                lostMap.remove(stu-1);
                continue;
            }
            if(lostMap.containsKey(stu+1)){
                lostMap.remove(stu+1);
                continue;
            }
        }
        return n-lostMap.keySet().size();
    }

    public static void main(String[] args){
        int n = 5;
        int[] lost = new int[]{2,4};
        int[] reserve = new int[]{1,3,5};
        System.out.println(solution(n,lost,reserve));
    }
}
