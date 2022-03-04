package programers.xfs;

public class TargetNumber {
    static int answer = 0;
    public static int solution(int[] numbers, int target) {
        dfs(0,0,numbers,target);
        return answer;
    }
    public static void dfs(int now, int now_index, int[] numbers, int target){
        if(now_index>numbers.length-1){
            if(now == target){
                answer++;
            }
            return ;
        }else{
            int now_negative = now - numbers[now_index];
            int now_positive = now + numbers[now_index];
            dfs(now_positive, now_index+1,numbers,target);
            dfs(now_negative, now_index+1,numbers,target);
        }

    }

    public static void main(String[] args){
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        int ans = solution(numbers,target);
        System.out.println(ans);
    }
}
