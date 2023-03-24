package forPro;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexedTreeByVidi {
    static class IndexedTree{
        long[] tree;
        int[] values;
        public int leafSize;
        public int depth;
        IndexedTree(int[] nums){
            values = nums;
            depth = (int)Math.ceil(Math.log(values.length)/Math.log(2));
            leafSize = (int)Math.pow(2,depth);
            tree = new long[leafSize*2];
            this.makeTree(1,0,leafSize-1);
        }
        private long makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = Long.valueOf((start < values.length) ? values[start] : 0);
                return tree[index];
            }
            int mid = (start+end)/2;
            tree[index] = makeTree(index*2, start, mid);
            tree[index] += makeTree(index*2+1, mid+1, end);
            return tree[index];
        }
        public long sumPart(int index, int start, int end, int sumStart, int sumEnd){
            if(sumEnd < start || end < sumStart){
                return 0;
            }
            if(sumStart <= start &&  end <= sumEnd){
                return tree[index];
            }
            int mid = (start+end)/2;
            return sumPart(index*2, start, mid, sumStart, sumEnd) + sumPart(index*2+1, mid+1, end, sumStart, sumEnd);
        }

        public void update(int index, int start, int end, int valueIndex, int diff){
            if(valueIndex < start || end < valueIndex){
                return;
            }
            tree[index] += diff;
            if(start != end){
                int mid = (start+end)/2;
                update(index*2, start, mid, valueIndex, diff);
                update(index*2+1, mid+1, end, valueIndex, diff);
            }

        }


        public void updateFromBottom(int valueIndex, int newValue){
            int nowIndex = (int)Math.pow(2, depth)+valueIndex-1;
            int firstIndex = nowIndex;
            while(nowIndex > 0){
                System.out.println("nowIndex : "+nowIndex);
                if(nowIndex == firstIndex){
                    tree[nowIndex] = newValue;
                }else{
                    tree[nowIndex] = tree[nowIndex*2]+tree[nowIndex*2+1];
                }
                nowIndex = (nowIndex%2 == 1)? (nowIndex-1)/2 : nowIndex/2;
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
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numbers[];
        String[] line = bf.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);
        numbers = new int[N];
        for(int i = 0; i < N; i++){
            int input = Integer.parseInt(bf.readLine());
            numbers[i] = input;
        }
        IndexedTree it = new IndexedTree(numbers);
        int ls = it.leafSize;
        it.printTree();
        for(int i = 0; i < M+K; i++){
            String[] inputLine = bf.readLine().split(" ");
            int inputMode = Integer.parseInt(inputLine[0]);
            int args_first = Integer.parseInt(inputLine[1]);
            int args_second = Integer.parseInt(inputLine[2]);

            if(inputMode == 2){
                bw.write(Long.toString(it.sumPart(1,1,ls,args_first,args_second))+"\n");
            }else{
                it.updateFromBottom(args_first,args_second);
                it.printTree();
            }
        }
        bw.flush();
    }
}
