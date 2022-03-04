package programers.graph;

import java.util.LinkedList;
import java.util.Queue;

public class FurthestNodeQueueBFS {
    public int solution(int n, int[][] edge){
        boolean[] check = new boolean[n];
        boolean[][] connect = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<edge.length; i++){
            connect[edge[i][0]-1][edge[i][1]-1]=true;
            connect[edge[i][1]-1][edge[i][0]-1]=true;
        }
        check[0]=true;
        q.add(0);

        int answer = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i=0; i<qSize;i++){
                int node = q.poll();
                for(int j=0; j<n; j++){
                    if(connect[j][node]&&!check[j]){
                        check[j]=true;
                        q.add(j);
                    }
                }
            }
            answer=qSize;
        }
        return answer;
    }

}
