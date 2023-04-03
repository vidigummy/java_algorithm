package forPro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon1725 {
    static class IndexedTree{
        int[] tree;
        int[] values;
        int depth;
        int leafSize;
        IndexedTree(int[] nums){
            values = nums;
            depth = (int)Math.ceil(Math.log(values.length)/Math.log(2));
            leafSize = (int)Math.pow(2, depth);
            tree = new int[leafSize*2];
            makeTree(1,0,leafSize-1);
        }

        private int makeTree(int index, int start, int end){
            if(start == end){
                return tree[index] = (start < values.length) ? start : -1;
            }
            int mid = (start+end)/2;
            int left = makeTree(index*2, start, mid);
            int right = makeTree(index*2+1, mid+1, end);
            if(left == -1){
                return tree[index] = right;
            }else if(right == -1){
                return tree[index] = left;
            }
            return tree[index] = values[left]< values[right]?left:right;
        }

        private int query(int index, int start, int end, int left, int right){
            if(right < start || end < left){
                return -1;
            }
            if(left <= start && end <= right){
                return tree[index];
            }
            int mid = (start+end)/2;
            int leftV = query(index*2, start, mid, left,right);
            int rightV = query(index*2+1, mid+1, end, left, right);
            if(leftV == -1){
                return rightV;
            }else if(rightV == -1){
                return leftV;
            }
            return values[leftV] < values[rightV] ? leftV : rightV;
        }

        public int getArea(int left, int right){
            int minIdx = query(1,1,leafSize,left,right);
            int mid = (left+right)/2;

            if(left == right){
                return values[left-1];
            }

            int area = (right-left+1)*values[minIdx];
//            System.out.println(area);
            int val = 0;
            if(left<minIdx){
                val=getArea(left,mid);
                area=area<val?val:area;
            }if(minIdx<right){
                val = getArea(mid+1, right);
                area=area<val?val:area;
            }
            return area;
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
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        IndexedTree it = new IndexedTree(nums);
//        it.printTree();
        System.out.println(it.getArea(1,N));

    }
}
