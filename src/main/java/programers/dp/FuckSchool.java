package programers.dp;

public class FuckSchool {
    static int[][] dp_result;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = 1;
            }
        }
        for(int i  =0; i < puddles.length; i++){
            dp[puddles[i][1]][puddles[i][0]] = Integer.MAX_VALUE;
        }
        int x = 0;
        int y = 0;
        return answer;
    }
    public static void main(String[] args){
        int m = 4;
        int n = 3;
        int[][] paddles = {{2,2}};
    }
}
