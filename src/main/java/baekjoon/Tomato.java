package baekjoon;

import java.util.*;

public class Tomato {
    static HashMap<Integer[], Set<Integer[]>> total_graph = new HashMap<>();
    static HashMap<Integer[], Integer> now_state = new HashMap<>();
    static List<Integer[]> doneTomato = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,m;
        m = sc.nextInt();
        n = sc.nextInt();
        for(int y =0 ; y < n; y++){
            for(int x = 0; x < m; x++){
                int now = sc.nextInt();
                Integer[] now_xy = {x, y};
                now_state.put(now_xy, now);
                if(now == 1){
                    doneTomato.add(now_xy);
                }
//                if(y == 0){
//                    if(x == 0){
//
//                    }
//                }else{
//
//                }
            }
        }
        System.out.println(now_state);
    }

}
