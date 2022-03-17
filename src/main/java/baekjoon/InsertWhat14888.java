package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InsertWhat14888 {
    static int max = -Integer.MAX_VALUE;
    static int min = Integer.MAX_VALUE;
    static ArrayList<String[]> kk_arr = new ArrayList<>();

    public static void permutation(String[] arr, int depth, int n, int r){

        if(depth == r){
            String[] newArr = new String[r];
            for(int i =0; i < r; i++){
                newArr[i] = arr[i];
            }
            kk_arr.add(newArr);
            return;
        }
        for(int i = depth; i <n; i++){
            swap(arr, depth, i);
            permutation(arr, depth+1, n, r);
            swap(arr,depth,i);
        }
    }
    public static void swap(String[] arr, int depth, int i){
        String tmp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> inputNumbers = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i < N; i++){
            int a = Integer.parseInt(st.nextToken());
            inputNumbers.add(a);
        }
        StringTokenizer stt = new StringTokenizer(br.readLine());
        ArrayList<String> a = new ArrayList<>();
        int plus = Integer.parseInt(stt.nextToken());
        for(int i =0; i < plus; i++){
            a.add("+");
        }
        int minus = Integer.parseInt(stt.nextToken());
        for(int i = 0; i <minus; i++){
            a.add("-");
        }
        int mult = Integer.parseInt(stt.nextToken());
        for(int i = 0; i < mult; i++){
            a.add("*");
        }
        int div = Integer.parseInt(stt.nextToken());
        for(int i = 0; i < div; i++){
            a.add("/");
        }
        String[] t = a.toArray(new String[a.size()]);
        permutation(t,0,a.size(),N-1);

//        for(int i = 0 ; i < kk_arr.size(); i++){
//            String[] arr = kk_arr.get(i);
//            for(int j  = 0; j < arr.length; j++){
//                System.out.print(arr[j]);
//            }
//            System.out.println();
//        }

        for(int i = 0; i <kk_arr.size(); i++){
            int result = inputNumbers.get(0);
//            System.out.println("----- first -----\n"+result);
            String[] arr = kk_arr.get(i);
            for(int j = 1; j < N; j++){
                String what = arr[j-1];
//                System.out.println(what);
//                System.out.println(inputNumbers.get(j));
                if(what.equals("+")){
                    result += inputNumbers.get(j);
                }else if(what.equals("-")){
                    result -= inputNumbers.get(j);
                }else if(what.equals("*")){
                    result *= inputNumbers.get(j);
                }else{
                    result /= inputNumbers.get(j);
                }
            }
            if(result>max){
                max = result;
            }
            if(min>result){
                min = result;
            }
        }
        System.out.println(max);
        System.out.println(min);
    }
}
