package programers.xfs;

import java.util.HashMap;

public class Network {
    static HashMap<Integer,Integer> network = new HashMap<>();
    static int network_size = 0;
    static int network_cnt = 1;
    public static int solution(int n, int[][] computers) {
        network_size = n;
        for(int i= 0; i < network_size; i++){
            if(!network.containsKey(i)){
//                System.out.println(network);
//                System.out.println(i+" is not in network "+network_cnt);
                dfs(i, computers);
                network_cnt++;
            }
        }
        int answer = network_cnt-1;
        return answer;
    }

    public static void dfs(int index, int[][] computers){
//        System.out.println("now : "+ index);
        int[] connetedList = computers[index];
        for(int i = 0; i < network_size; i++){
            if(connetedList[i] == 1 && !network.containsKey(i)){
                network.put(i,network_cnt);
//                System.out.println(network);
//                System.out.println(i+" is in network "+network_cnt);
                dfs(i,computers);
            }
        }
    }

    public static void main(String[] args){
        int n = 3;
        int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};
        int ans = solution(n,computers);
        System.out.println(ans);
    }
}
