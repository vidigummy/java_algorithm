package programers.heap;

import java.util.*;

public class Disk {
    public static int solution(int[][] jobs) {
        int answer = 0;
        int time = 0;
        HashMap<Integer, PriorityQueue<Integer>> job_hash = new HashMap<>();
        for(int[] job : jobs){
            if(!job_hash.containsKey(job[0])){
                PriorityQueue<Integer> tmp = new PriorityQueue<>();
                tmp.add(job[1]);
                job_hash.put(job[0],tmp);
            }else{
                PriorityQueue<Integer> tmp = job_hash.get(job[0]);
                tmp.add(job[1]);
                job_hash.put(job[0],tmp);
            }
        }
        PriorityQueue<Integer> now_job_select_queue = new PriorityQueue<>();
        while(!job_hash.isEmpty()){
            HashMap<Integer,PriorityQueue<Integer>> new_job_hash = new HashMap<>();
            for (Integer time_key : job_hash.keySet()) {
                if (time_key > time) {
                    new_job_hash.put(time_key,job_hash.get(time_key));
                } else {
                    PriorityQueue<Integer> time_job_queue = job_hash.get(time_key);
                    while (!time_job_queue.isEmpty()) {
                        now_job_select_queue.add(time_job_queue.peek());
                        time_job_queue.remove();
                    }
                }
            }
            job_hash = new_job_hash;
            if(now_job_select_queue.isEmpty()){
                for(Integer time_key : job_hash.keySet()){
                    PriorityQueue<Integer> time_job_queue = job_hash.get(time_key);
                    while(!time_job_queue.isEmpty()){
                        now_job_select_queue.add(time_job_queue.peek());
                        time_job_queue.remove();
                    }
                }
            }
            if(!now_job_select_queue.isEmpty()){
                time += now_job_select_queue.poll();
            }
        }
        return answer;
    }

    public static void main(String[] args){
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}, {1, 8}};
        int ans = solution(jobs);
        System.out.println(ans);
    }
}
