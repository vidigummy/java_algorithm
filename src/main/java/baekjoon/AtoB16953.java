package baekjoon;

import java.util.Scanner;

public class AtoB16953 {
    static long min = Integer.MAX_VALUE;
    public static void solution(long now, long B, int depth){
        if(now > B){
            return;
        }
        if(now==B){
            if(depth<min){
                min = depth;
            }
            return ;
        }else {
            solution(now*2, B, depth+1);
            solution((long)now*10+1,B,depth+1);
        }

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = (long)sc.nextInt();
        long b = (long)sc.nextInt();
        solution(a,b,0);
        if(min != Integer.MAX_VALUE){
            System.out.println(min+1);
        }else{
            System.out.println(-1);
        }
    }
}
