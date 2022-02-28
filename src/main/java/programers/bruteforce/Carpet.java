package programers.bruteforce;

import java.util.List;

public class Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown+yellow;
        int y = 3;
        while(true){
            if(total%y == 0){
                int x = total / y;
                //좌우 상하 갈색
                if((x-2)*(y-2) == yellow){
                    answer[0]= x;
                    answer[1] = y;
                    return answer;
                }
            }
            y++;
        }
    }
    public static void main(String[] args){
        int brown = 8;
        int yellow =1;
    }
}
