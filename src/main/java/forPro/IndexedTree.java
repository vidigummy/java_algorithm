package forPro;

import java.util.Arrays;

class IndexedTree {
        static class IndexTree{
            int nodes[]; //노드(만들어 놓은 것)

            int nums[]; // 실제 값(입력값)
            int height, leafCount; //높이, leafCount의 개수
            IndexTree(int[] nums){
                this.nums = nums;
                int len = nums.length-1;
                this.height = 0;
                while(len != 0){
                    len /= 2;
                    this.height++;
                }
                this.leafCount = (int)Math.pow(2, this.height-1);
                this.nodes = new int[(int)Math.pow(2, this.height)];
            }

            private int makeSubTree(int node, int left, int right) {
                if (left == right) {
                    if (left < nums.length) {
                        return nodes[node] = nums[left];
                    } else {
                        return nodes[node] = 0;
                    }
                }
                int mid = (left + right) / 2;
                nodes[node] = makeSubTree(node*2, left, mid); // left child
                nodes[node] += makeSubTree(node*2+1, mid+1, right); // right child
                return nodes[node];
            }
            public void makeTree(){ makeSubTree(1,1,leafCount); }
            private int target(int node, int left, int right, int tLeft, int tRight){
                if (left > tRight || right < tLeft){
                    return 0;
                }
                if (tLeft <= left && right <= tRight){
                    return nodes[node];
                }
                int mid = (left + right) / 2;
                return target(node*2, left,mid, tLeft, tRight) + target(node*2+1, mid+1, right, tLeft, tRight);
            }
            void update(int targetIndex, int targetValue){
                subUpdate(1,1,leafCount, targetIndex, targetValue-nums[targetIndex]);
                nums[targetIndex] = targetValue;
            }
            void subUpdate(int node, int left, int right, int t_i, int diff){
                if (t_i >= left && t_i <= right){
                    nodes[node] += diff;
                    if ( left == right){
                        return ;
                    }
                }
                else{
                    return;
                }
                int mid = (left+right)/2;
                subUpdate(node*2, left, mid, t_i, diff);
                subUpdate(node*2+1, mid+1, right, t_i, diff);
            }
            public int getPartialSum(int targetLeft, int targetRight){
                return target(1,1,leafCount, targetLeft, targetRight);
            }


        }


    public static void main(String[] args){
        int numbers[] = {0,8,3,26,1,7,2,4,10};
        System.out.println(Arrays.toString(numbers));
        IndexTree it = new IndexTree(numbers);
        it.makeTree();
        System.out.println(it.toString());

        System.out.println("2+ ~ 5 + SUM = ");
        System.out.println(it.getPartialSum(2,5));
//        for(int number : numbers){
//
//        }
    }
}
