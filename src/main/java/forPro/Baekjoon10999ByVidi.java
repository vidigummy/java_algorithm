package forPro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon10999ByVidi {
    static class IndexedTree{
        long[] tree;
        long[] values;
        long depth;
        int leafCnt;
        IndexedTree(long[] numbers){
            this.values = numbers;
            depth = (long)Math.ceil(Math.log(this.values.length)/Math.log(2));
            leafCnt = (int)Math.pow(2,depth);
            tree = new long[leafCnt*2];
            this.makeTree(1,0,leafCnt-1);
        }
        public long makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = (start < values.length) ? values[start] : 0l;
                return tree[index];
            }
            int mid = (start + end) / 2;
            tree[index] = makeTree(index*2, start, mid);
            tree[index] += makeTree(index*2+1, mid+1, end);
            return tree[index];
        }
        public long query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return 0;
            }
            if(q_s <= start && end <= q_e){
                return tree[index];
            }
            int mid = (start+end)/2;
            return query(index*2,start,mid, q_s, q_e)+query(index*2+1,mid+1,end,q_s,q_e);
        }
        public void update(int index, int start, int end, int valueIndex, long diff){
            if(valueIndex < start || end < valueIndex){
                return;
            }
            tree[index] += diff;
            int mid = (start+end)/2;
            if(start != end){
                update(index*2, start, mid, valueIndex, diff);
                update(index*2+1, mid+1, end, valueIndex, diff);
            }
        }
        public void updateRange(int start, int end, long diff){
            for(int i = start; i <= end; i++){
                update(1,1, leafCnt, i, diff);
            }
        }

        public void printTree(){
            int nowDepth = 0;
            List<Integer> goTo = new ArrayList<>();
            goTo.add(1);
            while(!goTo.isEmpty() && nowDepth <= depth){
                nowDepth++;
                for(int i : goTo){
                    try{
                        System.out.printf("%d ",tree[i]);
                    }catch (Exception e){
                        break;
                    }
                }
                List<Integer> newGoTo = new ArrayList<>();
                int goToLen = goTo.size();
                for(int i =0 ; i < goToLen; i++){
                    int tmp = goTo.get(0);
                    goTo.remove(0);
                    newGoTo.add(tmp*2);
                    newGoTo.add(tmp*2+1);
                }
                goTo = newGoTo;
                System.out.println("");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        long[] nums;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int K = Integer.parseInt(firstLine[2]);
        nums = new long[N];
        for(int i =0; i < N; i++){
            String[] newLine = br.readLine().split(" ");
            nums[i] = Long.parseLong(newLine[0]);
        }
        IndexedTree it = new IndexedTree(nums);
        int ls = it.leafCnt;
        for(int i = 0; i < M+K; i++){
            String[] newLine = br.readLine().split(" ");
            int a = Integer.parseInt(newLine[0]);
            int b = Integer.parseInt(newLine[1]);
            int c =Integer.parseInt(newLine[2]);
            if(a == 1){
                long d = Long.parseLong(newLine[3]);
                it.updateRange(b,c,d);
            }else{
                bw.write(String.valueOf(it.query(1,1,ls,b,c))+"\n");
            }
        }
        bw.flush();
    }
}
