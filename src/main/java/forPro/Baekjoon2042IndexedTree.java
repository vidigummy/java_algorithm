package forPro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2042IndexedTree {

    static class IndexedTree {
        int[] tree;
        int n;

        public IndexedTree(int[] nums) {
            n = nums.length;
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int i, int val) {
            i++;
            while (i <= n) {
                tree[i] += val;
                i += (i & -i);
            }
        }

        public int sum(int i) {
            int res = 0;
            i++;
            while (i > 0) {
                res += tree[i];
                i -= (i & -i);
            }
            return res;
        }

        public int sumRange(int i, int j) {
            return sum(j) - sum(i - 1);
        }

        public void printTree(){
            System.out.println(Arrays.toString(tree));
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numbers[];
        String[] line = bf.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);
        numbers = new int[N+1];
        numbers[0] = 0;
        int resultAdd = 0;
        for(int i = 0; i < N; i++){
            int input = Integer.parseInt(bf.readLine());
            numbers[i+1] = input;
        }
        IndexedTree it = new IndexedTree(numbers);
        for(int i = 0; i < M+K; i++){
            String[] inputLine = bf.readLine().split(" ");
            if(inputLine[0].equals("2")){
               System.out.println(it.sumRange(Integer.parseInt(inputLine[1]), Integer.parseInt(inputLine[2])));
            }else{
                it.update(Integer.parseInt(inputLine[1]), Integer.parseInt(inputLine[2]));
            }
        }


    }
}
