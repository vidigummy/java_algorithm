package forPro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Beakjoon1395 {
    static int[] tree;
    static int depth;
    static int startZeroIndex;
    static int leafSize;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        for(int i =0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int firstArg = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if(firstArg == 0){
                lazyUpdate(left,right);
//                printTree();
            }else{
                int result = query(1,1,leafSize,left,right);
                sb.append(Integer.toString(result)+"\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void init(int N){
        depth = (int)Math.ceil(Math.log(N)/Math.log(2));
        leafSize = (int)Math.pow(2,depth);
        startZeroIndex = leafSize;
        tree = new int[leafSize*2];
        for(int i=0; i < leafSize*2; i++){
            tree[i] = 0;
        }
    }

    static void lazyUpdate(int left, int right){
        Set<Integer> toGo = new HashSet<>();
        for(int i = left; i <=right; i++){
            toGo.add(startZeroIndex+i-1);
        }
        int nowDepth = 0;
        while(!toGo.isEmpty()){
//            System.out.println(toGo.toString());
            Set<Integer> newToGo = new HashSet<>();
            Iterator<Integer> it = toGo.iterator();
            while(it.hasNext()){
                int now = it.next();
                if(nowDepth ==0){
                    if(tree[now] == 0){
                        tree[now] = 1;
                    }else{
                        tree[now] = 0;
                    }
                }else {
                    tree[now] = tree[now * 2] + tree[now * 2 + 1];
//                    System.out.printf("left : %d  || right : %d = %d  \n",tree[now*2],tree[now*2+1],tree[now]);
                }
                int newIdx = (now%2 ==1)?(now-1)/2: now/2;
                if(newIdx >0){
                    newToGo.add(newIdx);
                }
            }
            toGo = newToGo;
            nowDepth++;
        }
    }

    static int query(int index, int start, int end, int q_s, int q_e){
        if(q_e < start || end < q_s){
            return 0;
        }
        if(q_s <= start && end <= q_e){
            return tree[index];
        }
        int mid = (start + end)/2;
        return query(index*2, start, mid, q_s,q_e)+query(index*2+1, mid+1, end,q_s,q_e);
    }

    static void printTree(){
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
