package programers.stack_queue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Truck {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int now_weight = 0;
        Queue<HashMap<Integer, Integer>> wating_queue = new LinkedList<>();
        Queue<HashMap<Integer, Integer>> bridge_queue = new LinkedList<>();
        for(Integer truck: truck_weights){
            HashMap<Integer, Integer> tmp = new HashMap<>();
            tmp.put(truck, bridge_length);
            wating_queue.add(tmp);
        }
        while(!wating_queue.isEmpty() || !bridge_queue.isEmpty()){
            answer ++;
            if(!bridge_queue.isEmpty()){
                Iterator<HashMap<Integer,Integer>> bridge_iterator = bridge_queue.iterator();
                Queue<HashMap<Integer, Integer>> new_bridge_queue = new LinkedList<>();
                while(bridge_iterator.hasNext()){
                    HashMap<Integer,Integer> truck = bridge_iterator.next();
                    Iterator<Integer> truck_weights_iterator = truck.keySet().iterator();
                    if(truck_weights_iterator.hasNext()){
                        Integer truck_weight = truck_weights_iterator.next();
                        Integer truck_now = truck.get(truck_weight);
                        if(truck_now <= 1){
                            bridge_iterator.remove();
                            now_weight -= truck_weight;
                        }else{
                            HashMap<Integer,Integer> tmp = new HashMap<>();
                            tmp.put(truck_weight,truck_now-1);
                            new_bridge_queue.add(tmp);
                        }
                    }
                }
                bridge_queue = new_bridge_queue;
            }
            if(!wating_queue.isEmpty()) {
                Iterator<HashMap<Integer, Integer>> wait_iterator = wating_queue.iterator();
                if (wait_iterator.hasNext()) {
                    HashMap<Integer, Integer> wait_truck = wait_iterator.next();
                    Iterator<Integer> keys = wait_truck.keySet().iterator();
                    Integer first_wait_weight = keys.next();
                    if (first_wait_weight + now_weight <= weight) {
                        bridge_queue.add(wait_truck);
                        wait_iterator.remove();
                        now_weight += first_wait_weight;
                    }
                }
            }else {
                continue;
            }
        }
        return answer;
    }
    public static void main(String[] args){
        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
        int ans = solution(bridge_length, weight,truck_weights);
        System.out.println(ans);
    }
}
