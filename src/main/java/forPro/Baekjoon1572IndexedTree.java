package forPro;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baekjoon1572IndexedTree {
    static class IndexedTree{
        long[] tree;
        long[] values;
        int leafSize;
        IndexedTree(long[] numbers){
            values = numbers;
            int height = (int)Math.ceil(Math.log(numbers.length)/Math.log(2));
            int leafSize = (int)Math.pow(2,height);
            tree = new long[leafSize*2];

        }
        public long makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = (start < values.length)? values[start] : 0;
                return tree[index];
            }
            int mid = (start+end)/2;
            tree[index] = makeTree(index, start, mid);
            tree[index] += makeTree(index, mid+1, end);
            return tree[index];
        }

        public long query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return 0;
            }
            if(q_s <= start || end <= q_e){
                return tree[index];
            }
            int mid = (start+end)/2;
            return query(index*2, start, mid, q_s,q_e)+query(index*2+1,mid+1,end,q_s,q_e);
        }

        public void update(int index, int start, int end, int valueIndex, long diff){
            if(valueIndex < start || end < valueIndex){
                return ;
            }
            tree[index] += diff;
            int mid = (start+end)/2;
            if(start != end){
                update(index*2,start,mid,valueIndex,diff);
                update(index*2+1, mid+1,end, valueIndex,diff);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] args_line = br.readLine().split(" ");
        int N = Integer.parseInt(args_line[0]);
        int K = Integer.parseInt(args_line[1]);
        long ans = 0;
        long[] numbers = new long[N];
        for(int i =0; i < N; i++){
            long input = Long.parseLong(br.readLine());
        }
//        long[] numbers = new long[N];
//        for(int i = 0 ; i < N; i++){
//            numbers[i] = 0;
//        }
//        int cnt = 0;
//        while(cnt+1 < K){
//            long input = Long.parseLong(br.readLine());
//            numbers[cnt] = input;
//            cnt++;
//        }
//        IndexedTree it = new IndexedTree(numbers);
//        int ls = it.leafSize;
//        while(cnt < N){
//            long input = Long.parseLong(br.readLine());
//            numbers[cnt] = input;
//            it.update(1,1, ls, cnt+1, input);
//            cnt++;
//        }


    }
}
