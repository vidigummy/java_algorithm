package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StartAndLink14889 {
    static int[][] inputArr;
    static int[] person;
    static List<int[]> team_possible = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    public static void permutation(int[] arr, int depth, int n, int r){
            if(depth == r){
                int[] newArr = new int[arr.length];
                for(int i = 0; i < r; i++){
                    newArr[i] = arr[i];
                }
                findMin(newArr,n);
                return;
            }
            for(int i = depth; i < n; i++){
                swap(arr, depth, i);
                permutation(arr, depth+1, n, r);
                swap(arr, i, depth);
            }
    }
    public static void swap(int[] arr, int depth, int i){
        int tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }
    public static void findMin(int[] arr, int N){
        int startSum = 0;
        int linkSum = 0;
        for(int i = 0; i < N/2; i++){
            int sx = arr[i];
            int lx = arr[i+N/2];
            for(int j = 0; j<N/2; j++){
                int sy = arr[j];
                int ly = arr[j+N/2];
                startSum += inputArr[sx][sy];
                linkSum += inputArr[lx][ly];
            }
        }
        if(Math.abs(startSum-linkSum)<min){
            min = Math.abs(startSum-linkSum);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputArr = new int[N][N];
        person = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            person[i] = i;
            for(int j = 0 ; j < N; j++){
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        permutation(person,0,N,N);
        System.out.println(min);
    }
}
