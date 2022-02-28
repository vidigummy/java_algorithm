package programers.sort;

import java.util.Arrays;

public class KthNumber {
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i= 0 ; i< commands.length; i++){
//            System.out.println(arrayToString);
            int from = commands[i][0]-1;
            int to = commands[i][1];
            int k = commands[i][2]-1;
//            System.out.println("ijk :"+from+" "+to+" "+k);
            if(from!=to){
                int[] sub = Arrays.copyOfRange(array,from,to);
                Arrays.sort(sub);
//                for(int j : sub){
////                    System.out.print(j + " ");
//                }
//                System.out.println();
                answer[i] = sub[k];
            }else {
//                System.out.println(array[from]);
                answer[i] = array[from];
            }

        }

        return answer;
    }
    public static void main(String[] args){
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2,5,3},{4,4,1},{1,7,3}};
        int[] ans = solution(array,commands);
        for(int i : ans){
            System.out.print(i+ " ");
        }
    }
}
