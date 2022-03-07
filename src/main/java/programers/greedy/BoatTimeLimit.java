package programers.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoatTimeLimit {
    public static int solution(int[] people, int limit) throws InterruptedException {
        Arrays.sort(people);
        int answer = 0;
        int unboard = people.length-1;
        int nowWeight = 0;
        for(int i= 0; i<= unboard; answer++){
            while(i < unboard && people[i] + people[unboard--] >limit){
                answer++;
            }
        }
        return answer;

    }

    public static void main(String[] args) throws InterruptedException {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        int ans = solution(people,limit);

        System.out.println(ans);
    }
}
