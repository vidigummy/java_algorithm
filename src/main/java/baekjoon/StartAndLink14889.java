package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StartAndLink14889 {
    static int[][] inputArr;
    static int[] person;
    static ArrayList<int[]> team_possible = new ArrayList<>();
    public static void permutation(int[] arr, int depth, int n, int r){
            if(depth == r){
                int[] newArr = new int[arr.length];
                for(int i = 0; i < r; i++){
                    newArr[i] = arr[i];
                }
                team_possible.add(newArr);
                return;
            }

    }
    public static void swap(int[] arr, int depth, int i){
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputArr = new int[N][N];
        person = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N; j++){
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
