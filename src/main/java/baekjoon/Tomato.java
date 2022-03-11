package baekjoon;

import java.util.*;

public class Tomato {
    static int[][] tomatoes;
    static int[] dx = {-1,1,0,0};//상하좌우
    static int[] dy = {0,0,-1,1}; //상하좌우
    static int cnt = 0;
    static int depth = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,m;
        m = sc.nextInt();//가로
        n = sc.nextInt();//세로
        Queue<int[]> queue = new LinkedList<>();
        tomatoes = new int[n][m];
        for(int y = 0; y < n; y++){//입력
            for(int x = 0; x < m; x++){
                tomatoes[y][x] = sc.nextInt();
                if(tomatoes[y][x] == 1){
                    queue.add(new int[] {y,x});
                }
                else if(tomatoes[y][x] ==0){
                    cnt++;
                }
            }
        }

        while (cnt >0 && !queue.isEmpty()){
            for(int i = queue.size(); i > 0; i--){
                int[] tmp = queue.poll();
                for(int j = 0; j < 4; j++){
                    int nx = tmp[1] + dx[j];
                    int ny = tmp[0] +dy[j];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= m || tomatoes[ny][nx] != 0){
                        continue;
                    }
                    cnt--;
                    tomatoes[ny][nx] = 1;
                    queue.add(new int[] {ny, nx});
                }
            }
            depth++;
        }
        if(cnt == 0){
            System.out.println(depth);
        }else {
            System.out.println(-1);
        }
    }

}
