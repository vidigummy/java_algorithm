package forPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon14428 {
    static class IndexedTree{
        long[][] tree;
        long[][] values;
        int depth;
        int leafSize;

        IndexedTree(long[][] numbers){
            this.values = numbers;
            depth = (int)Math.ceil(Math.log(this.values.length)/Math.log(2));
            leafSize = (int)Math.pow(2, depth);
            tree = new long[leafSize*2][];
            makeTree(1,0,leafSize-1);
        }
        public long[] makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = (start < values.length) ? values[start] : new long[]{-1l,-1l};
                return tree[index];
            }
            int mid = (start+end)/2;

            long[] leftResult = makeTree(index*2, start, mid);
            long[] rightResult = makeTree(index*2+1, mid+1, end);
//            System.out.println("leftValue : "+Long.toString(leftResult[1])+" | rightValue : "+Long.toString(rightResult[1]));
            if(leftResult[0] == -1l && rightResult[0] == -1l){
//                System.out.println("return nothing");
                tree[index] = leftResult;
                return tree[index];
            }
            if(leftResult[1] == -1l || (leftResult[1] > rightResult[1] && rightResult[1] != -1l)){
//                System.out.println("return rightValue");
                tree[index] = rightResult;
            }else {
//                System.out.println("return leftValue");
                tree[index] = leftResult;
            }
            return tree[index];
        }


        public long[] query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return new long[]{-1l,-1l};
            }
            if(q_s <= start && end <= q_e){
                return tree[index];
            }
            int mid = (start+end)/2;
            long[] left = query(index*2, start, mid, q_s, q_e);
            long[] right = query(index*2+1, mid+1, end, q_s, q_e);
            if(left[0] == -1l && right[0] == -1l){
                return left;
            }
            if(left[0] == -1l){
                return right;
            }
            if(right[0] == -1l) {
                return left;
            }
            if(left[1] == right[1]){
                return left;
            }
            if(left[1] > right[1]){
                return right;
            }else{
                return left;
            }
        }

        public void update(int index, int start, int end, int valueIndex, long[] next) {
            if (valueIndex < start || end < valueIndex) {
                return;
            }
            if (tree[index][1] > next[1]) {

                tree[index] = next;
            }
            if(tree[index][1] == next[1] && tree[index][0] > next[0]){
                System.out.println("origin Index : "+Long.toString(tree[index][0])+"   |   next Index : "+Long.toString(next[0]));
                tree[index] = next;
            }
            if(tree[index][0] == valueIndex+0l){
                tree[index] =next;
            }
            if (start != end) {
                int mid = (start + end) / 2;
                update(index * 2, start, mid, valueIndex, next);
                update(index * 2 + 1, mid + 1, end, valueIndex, next);
            }
        }


        public void updateNew(int index, int start, int valueIndex, long[] next){
            values[valueIndex-1] = next;
            makeTree(1,0,leafSize-1);
        }

        public void updateBottomUp(int index, int start, int end, int valueIndex, long[] next){

        }

        public void printTree(){
            int nowDepth = 0;
            List<Integer> goTo = new ArrayList<>();
            goTo.add(1);
            while(!goTo.isEmpty() && nowDepth <= depth){
                nowDepth++;
                for(int i : goTo){
                    try{
                        System.out.printf("[%d %d] ",tree[i][0],tree[i][1]);
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

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long[][] inputArray = new long[N][];
        String[] inputNodes = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            long[] tmp = new long[]{i+1l,0l};
            tmp[1] = Long.parseLong(inputNodes[i]);
            inputArray[i] = tmp;
        }
        IndexedTree it = new IndexedTree(inputArray);
        int ls = it.leafSize;
        int M = Integer.parseInt(br.readLine());

//        System.out.printf("depth : %d",it.depth);

//        it.printTree();
        for(int i =0; i < M; i++){
            String[] inputArgs = br.readLine().split(" ");
            int state = Integer.parseInt(inputArgs[0]);
            int first = Integer.parseInt(inputArgs[1]);
            if(state == 1){
                long first_l = first+0l;
                long value = Long.parseLong(inputArgs[2]);
//                it.update(1,1,ls, first,new long[]{first_l, value});
                it.updateNew(1,1,first,new long[]{first_l, value});
//                it.printTree();

            }else{
                int second = Integer.parseInt(inputArgs[2]);
                long[] ans = it.query(1,1,ls,first, second);
                bw.write(Long.toString(ans[0])+ " "+Long.toString(ans[1])+"\n");
                bw.flush();
            }
        }
    }
}
