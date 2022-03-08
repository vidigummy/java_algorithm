package programers.dp;

public class Triangle {
    static int max = -Integer.MAX_VALUE;
    static int max_depth;
    public static int solution(int[][] triangle) {
        int answer = 0;
        max_depth = triangle.length -1;
//        dp2(max_depth,max_depth,0,triangle);
        dp(0,0,0,triangle);
        answer = max;
        return answer;
    }
    public static void dp(int nowIndex, int depth,int nowSum, int[][] triangle){
        if(depth == max_depth-1){
            max = Math.max(nowSum+triangle[depth][nowIndex], max);
            return ;
        }else{
            if(nowIndex == depth){
                dp(nowIndex, depth+1, nowSum+triangle[depth][nowIndex], triangle);
            }else{
                dp(nowIndex, depth+1, nowSum+triangle[depth][nowIndex], triangle);
                dp(nowIndex+1, depth+1, nowSum+triangle[depth][nowIndex], triangle);
            }
        }
    }
    public static void dp2(int nowIndex, int depth, int nowSum, int[][] triangle){
        System.out.println("nowDepth : "+depth+" nowIndex : " + nowIndex + " now sum : "+ nowSum);
        if(depth == 0){
            max = Math.max(nowSum+triangle[depth][nowIndex], max);
            return ;
        }else{
            //row의 마지막일 경우.
            if(nowIndex == depth){
                dp2(nowIndex-1, depth-1, nowSum+triangle[depth][nowIndex],triangle);
            }else if(nowIndex == 0){
                dp2(nowIndex, depth-1, nowSum+triangle[depth][nowIndex],triangle);
            }else {
                dp2(nowIndex-1, depth-1, nowSum+triangle[depth][nowIndex], triangle);
                dp2(nowIndex, depth-1, nowSum+triangle[depth][nowIndex],triangle);
            }
        }
    }

    public static void main(String[] args){
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int ans = solution(triangle);
        System.out.println(ans);
    }
}
