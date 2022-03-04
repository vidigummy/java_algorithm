package programers.dp;

public class MakeWithN {
    static int min = Integer.MAX_VALUE;
    public static int solution(int N, int number) {
        //number가 주어졌을 때, 경우의 수 는 같거나, 작거나, 크거나 이다.
        //now로 할 수 있는 것은 N을 number의 수 만큼 늘여놓거나, 나누거나, 곱하거나, 더하거나, 빼거나 이다(물론 N*N N/N도 더하거나 뺄 수 있다.),
        int answer = 0;
        makeN(0, N,number,0);
        if(min != Integer.MAX_VALUE){
            return min;
        }
        return -1;
    }
    public static void makeN(int now, int N, int number, int cnt){

        if(now==number){
//            System.out.println(now + " cnt : "+ cnt);
            min = Math.min(cnt,min);
            return ;
        }
        if(cnt > 9){
            return ;
        }
        int NN = 0;
        for(int i= 0; i < 9; i++){
            if(cnt+i < 8){
                NN = NN*10+N;
//                System.out.println("now : "+NN);
                makeN(now+NN, N, number, cnt+1+i);
                makeN(now-NN, N, number, cnt+1+i);
                makeN(now*NN, N, number, cnt+1+i);
                makeN(now/NN, N, number, cnt+1+i);
                makeN(NN-now, N, number, cnt+1+i);
                if(now!=0){
                    makeN(NN/now, N, number, cnt+1+i);
                }
            }
        }
    }
    public static void main(String[] args){
        int N = 2;
        int number = 11;
        int ans = solution(N,number);
        System.out.println(ans);

    }
}
