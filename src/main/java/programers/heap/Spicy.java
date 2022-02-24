package programers.heap;

import java.util.PriorityQueue;

public class Spicy {
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int food : scoville){
            minHeap.add(food);
        }

        while(minHeap.size()>=2 && minHeap.peek() < K){
            answer++;
            int tmp1 = minHeap.poll();
            int tmp2 = minHeap.poll();
            int new_tmp = tmp1+ (tmp2*2);
            minHeap.add(new_tmp);
        }
        if(minHeap.peek()<K)
            return -1;
        return answer;
    }
    public static void main(String[] args){
        int[] scoville = {8, 10, 11};
        int K = 7;
        int ans = solution(scoville, K);
        System.out.println(ans);
    }
}
