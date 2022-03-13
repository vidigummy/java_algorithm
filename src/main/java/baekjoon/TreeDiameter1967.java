package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class TreeDiameter1967 {
    static HashMap<Integer, List<int[]>> tree = new HashMap<>();
    static HashMap<Integer, Boolean> isVisitFirst = new HashMap<>();
    static HashMap<Integer, Boolean> isVisitSecond = new HashMap<>();
    static int max = -Integer.MAX_VALUE;
    static int maxNode = -1;
    public static void dfs(HashMap<Integer,Boolean> is_visit_map, int now, int nowVal, int distance){
        List<int[]> nowToGo = tree.get(now);
        is_visit_map.put(now,true);
        if(max < nowVal){
            max = nowVal;
            maxNode =now;
        }
        for(int[] ToGo : nowToGo){
            if(!is_visit_map.get(ToGo[0])){
                dfs(is_visit_map, ToGo[0], nowVal+ToGo[1], distance+1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
//        System.out.println(N);
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
//            System.out.println(p+ " " + v + " " + w);
            isVisitFirst.put(p,false);
            isVisitSecond.put(p,false);
            if(tree.containsKey(p)){
                List<int[]> now = tree.get(p);
                now.add(new int[] {v, w});
                tree.put(p,now);
            }else{
                List<int[]> now = new ArrayList<>();
                now.add(new int[] {v, w});
                tree.put(p,now);
            }
        }
        br.close();
        System.out.println("dd???");
        for(Integer a: tree.keySet()){
            List<int[]> tmp = tree.get(a);
            System.out.println(a);
            for(int[] t : tmp){
                System.out.println(t[0] + " " + t[1]);
            }
        }
//        dfs(isVisitFirst, 1, 0, 0);
//        dfs(isVisitSecond, maxNode, 0, 0);
        System.out.println(max);
    }
}
