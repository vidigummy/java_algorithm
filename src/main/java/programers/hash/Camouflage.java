package programers.hash;

import java.util.HashMap;
import java.util.Iterator;

public class Camouflage {
    public static int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for(String[] item : clothes){
            String cloth_type = item[1];
            if(map.containsKey(cloth_type)){
                map.put(cloth_type, map.get(cloth_type)+1);
            }else{
                map.put(cloth_type,2);
            }
        }
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            answer = answer*map.get(key);
        }
        return answer-1;
    }

    public static void main(String[] args){
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int ans = solution(clothes);
        System.out.println(ans);
    }
}
