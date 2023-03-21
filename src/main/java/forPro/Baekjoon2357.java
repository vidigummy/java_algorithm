package forPro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon2357 {
    static class IndexedTree{
        long[][] tree;
        long[] values;
        int depth;
        int leafSize;
        IndexedTree(long[] numbers){
            values = numbers;
            depth = (int)Math.ceil(Math.log(values.length)/Math.log(2));
            leafSize =(int)Math.pow(2, depth);
            tree = new long[leafSize*2][];
            makeTree(1,0,leafSize-1);
        }
        public long[] makeTree(int index, int start, int end){
            if(start == end){
                long[] new_tmp = (start < values.length) ? new long[]{values[start],values[start]} : new long[]{-1l,-1l};
                tree[index] = new_tmp;
                return tree[index];
            }
            int mid = (start+end)/2;
            long[] leftChild = makeTree(index*2, start, mid);
            long[] rightChild = makeTree(index*2+1, mid+1, end);
            long leftAns = 0l;
            if(leftChild[0] < 0l && rightChild[0] < 0l){
                leftAns = -1l;
            }else if(leftChild[0] < 0l && rightChild[0] >= 0l){
                leftAns = rightChild[0];
            } else if(leftChild[0] >= 0l && rightChild[0] < 0l){
                leftAns = leftChild[0];
            }else{
                leftAns = Math.min(leftChild[0],rightChild[0]);
            }
            long[] new_tmp = new long[]{leftAns, Math.max(leftChild[1],rightChild[1])};
            tree[index] = new_tmp;
            return tree[index];
        }

        public long[] query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return new long[]{-1l,-1l};
            }
            if(q_s <= start && end <= q_e){
//                System.out.printf("query end at : %d %d \n",tree[index][0],tree[index][1]);
                return tree[index];
            }
            int mid = (start+end)/2;
            long[] leftChild = query(index*2, start, mid, q_s, q_e);
            long[] rightChild = query(index*2+1, mid+1, end, q_s,q_e);
            long leftAns = 0l;
            if(leftChild[0] < 0l && rightChild[0] < 0l){
                leftAns = -1l;
            }else if(leftChild[0] < 0l && rightChild[0] >= 0l){
                leftAns = rightChild[0];
            } else if(leftChild[0] >= 0l && rightChild[0] < 0l){
                leftAns = leftChild[0];
            }else{
                leftAns = Math.min(leftChild[0],rightChild[0]);
            }
            return new long[]{leftAns,Math.max(leftChild[1],rightChild[1])};
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        long[] numbers = new long[N];
        for(int i = 0; i < N; i++){
            long input = Long.parseLong(br.readLine());
            numbers[i] = input;
        }
        IndexedTree it = new IndexedTree(numbers);
//        it.printTree();
        int ls = it.leafSize;
        for(int i =0; i < M; i++){
            String[] arg = br.readLine().split(" ");
            int start = Integer.parseInt(arg[0]);
            int end = Integer.parseInt(arg[1]);
//            System.out.printf("Query! %d to %d \n",start,end);
            long[] ans = it.query(1,1, ls, start, end);

            bw.write(Long.toString(ans[0])+" "+Long.toString(ans[1])+"\n");
            bw.flush();
        }

    }
}
