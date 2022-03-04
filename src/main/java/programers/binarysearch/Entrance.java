package programers.binarysearch;

import java.util.*;

public class Entrance {
    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times); // 크기 순으로 정렬

        long left = 1;
        long right = n * (long)times[times.length-1]; // 가장 오래 걸리는 심사관이 n명 보는거- 최대
        //타입
        long sum = 0;
        answer = right;
        while(left <= right){
            long mid = (left + right) / 2;
            sum = 0;
            for(int i = 0; i < times.length; i++){
                sum += mid / times[i];
            }
            if(sum < n){
                left = mid + 1;
                // 불가능-> 시간을 더 많이
            }
            else{
                right = mid -1;
                answer = mid;
//                System.out.println(mid);
                //가능 -> 시간을 최대한 줄여보기
            }
        }
        return answer;
    }


    public static void main(String[] args){
        int n = 6;
        int[] times = {7,10};
        Long ans = solution(n, times);
        System.out.println(ans);
    }
}
