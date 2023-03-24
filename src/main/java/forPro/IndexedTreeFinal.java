package forPro;

import java.util.ArrayList;
import java.util.List;

public class IndexedTreeFinal {
    static class IndexedTree{
        long[] tree;
        long[] values;
        int depth;
        int leafSize;

        public IndexedTree(long[] numbers){
            values = numbers;
            depth = (int)Math.ceil(Math.log(numbers.length)/Math.log(2));
            leafSize = (int)Math.pow(2,depth);
            tree = new long[leafSize*2];
            makeTree(1,0,leafSize);
        }

        public long makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = (start < values.length)? values[start] : 0;
            }
            int mid = (start+end)/2;
            tree[index] = makeTree(index*2, start, mid);
            tree[index] += makeTree(index*2+1, mid+1, end);
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
            return query(index*2, start, mid, q_s, q_e) + query(index*2+1, mid+1, end, q_s,q_e);
        }

        public void updateTopDown(int index, int start, int end, int valueIndex, long diff){
            if(valueIndex < start || end < valueIndex){
                return;
            }
            tree[index] += diff;
            if(start != end){
                int mid = (start+end)/2;
                updateTopDown(index*2, start, mid, valueIndex, diff);
                updateTopDown(index*2+1, mid+1, end, valueIndex, diff);
            }
        }

        public void updateBottomUp(int valueIndex, long newValue){
            int startIndex = (int)Math.pow(2,depth)+valueIndex-1;
            int nowIndex = startIndex;
            while(nowIndex > 0){
                if(nowIndex == startIndex){
                    tree[nowIndex] = newValue;
                }else{
                    tree[nowIndex] = tree[nowIndex*2]+tree[nowIndex*2+1];
                }
                nowIndex = (nowIndex%2 == 1) ? (nowIndex-1)/2 : nowIndex/2;
            }
        }

        public void printTree() {
            int nowDepth = 0;
            List<Integer> goTo = new ArrayList<>();
            goTo.add(1);
            while (!goTo.isEmpty() && nowDepth <= depth) {
                nowDepth++;
                for (int i : goTo) {
                    try {
                        System.out.printf("%d ", tree[i]);
                    } catch (Exception e) {
                        break;
                    }
                }
                List<Integer> newGoTo = new ArrayList<>();
                int goToLen = goTo.size();
                for (int i = 0; i < goToLen; i++) {
                    int tmp = goTo.get(0);
                    goTo.remove(0);
                    newGoTo.add(tmp * 2);
                    newGoTo.add(tmp * 2 + 1);
                }
                goTo = newGoTo;
                System.out.println("");
            }
        }
    }
}
