package programers.sort;

import java.util.Arrays;

public class HIndex {
    public static int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        Arrays.sort(citations);
        for(int h = citations[n-1]; h > 0; h--){
//          cnt는 인용수가 h번 이하인 개수
            int cnt = 0;
            for(int cit : citations){
                if(cit<h){
                    cnt++;
                }else{
                    if(n-cnt>=h && cnt <= h){
                        return h;
                    }else {
                        break;
                    }

                }
            }
        }
        return answer;
    }
    public static void main(String[] args){
        int[] citations = {3, 0, 6, 1, 5};
        int ans = solution(citations);
        System.out.println(ans);
    }
}
