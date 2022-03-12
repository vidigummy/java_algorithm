package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeDiameter1167 {
    static HashMap<Integer, List<int[]>> tree = new HashMap<>();
    static int max = -Integer.MAX_VALUE;
    public static int maxNode = -1;
    public static void dfs(HashMap<Integer,Boolean> is_visit_map, int now, int nowVal, int distance){
        List<int[]> nowToGo = tree.get(now);
        is_visit_map.put(now,true);
//        System.out.println("now : " + now + " len : "+ distance+ " sum : " + nowVal + " | "+is_visit_map);
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
        int V = Integer.parseInt(br.readLine());
        HashMap<Integer,Boolean> is_visit_map = new HashMap<>();
        HashMap<Integer,Boolean> is_visit_map_second = new HashMap<>();
        for(int i = 0; i < V; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            is_visit_map.put(x,false);
            is_visit_map_second.put(x,false);
            while(true){
                int next = Integer.parseInt(st.nextToken());
                int diameter;
                if(next == -1){
                    break;
                }else {
                    diameter = Integer.parseInt(st.nextToken());
                }
                int[] tmp = {next, diameter};
                if(tree.containsKey(x)){
                    List<int[]> tree_now = tree.get(x);
                    tree_now.add(tmp);
                    tree.put(x,tree_now);
                }else {
                    List<int[]> tree_now = new ArrayList<>();
                    tree_now.add(tmp);
                    tree.put(x,tree_now);
                }
            }
        }
        Integer[] a = tree.keySet().toArray(new Integer[0]);
//        for(Integer t: a){
//            System.out.println(t);
//            List<int[]> arr = tree.get(t);
//            for(int[] d : arr){
//                System.out.print(d[0]+","+d[1]+" | ");
//
//            }
//            System.out.println();
//        }

        dfs(is_visit_map,1,0,0);
//        System.out.println(is_visit_map);
//        System.out.println(maxNode);
        dfs(is_visit_map_second,maxNode,0,0);
        System.out.println(max);
    }
}
