package baekjoon;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sudoku2580 {
    static int[][] result = new int[9][9];
    static ArrayList<Integer[]> toGo= new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean isItSmall(int[][]nowState, int x, int y){
        int sum = 0;
        for(int i = y; i < y+3; i++){
            for(int j = x; j < x+3; j++){
                sum += nowState[i][j];
            }
        }
        if(sum != 45){
            return false;
        }
        return true;
    }
    public static boolean isIt(int[][] nowState){
        int[] dx = {0,3,6};
        int[] dy = {0,3,6};
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(!isItSmall(nowState,dx[j],dy[i])){
                    return false;
                }
            }
        }
        for(int i = 0; i < 9; i++){
            int sum = 0;
            for(int j = 0; j < 9; j++){
                sum += nowState[i][j];
            }
            if(sum!= 45){
                return false;
            }
        }
        for(int i = 0; i < 9; i++){
            int sum = 0;
            for(int j = 0; j < 9; j++){
                sum += nowState[j][i];
            }
            if(sum!= 45){
                return false;
            }
        }
        return true;
    }
    public static void sudoku(int now, int[][] nowState) throws IOException {
//        wr.write("-------"+now+"-------\n");
//        for(int i =0 ; i < 9; i++){
//            for(int j = 0; j < 9; j++){
//                wr.write(nowState[i][j] + " ");
//            }
//            wr.write("\n");
//        }
        wr.flush();
        if(now == toGo.size()){
            if(isIt(nowState)){
                result = nowState;
                return;
            }else{
                return;
            }
        }else{
            for(int i = 1; i <=9; i++){
                nowState[toGo.get(now)[0]][toGo.get(now)[1]] = i;
                sudoku(now+1, nowState);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int[][] input = new int[9][9];
        for(int y = 0; y < 9; y++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0; x < 9; x++){
                input[y][x] = Integer.parseInt(st.nextToken());
                if(input[y][x] == 0){
                    toGo.add(new Integer[] {y,x});
                }
            }
        }
        sudoku(0,input);
        for(int i =0 ; i < 9; i++){
            for(int j = 0; j < 9; j++){
                wr.write(result[i][j] + " ");
            }
            wr.write("\n");
        }
        wr.flush();
    }
}
