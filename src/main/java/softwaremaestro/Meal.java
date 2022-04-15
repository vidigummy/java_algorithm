package softwaremaestro;

import java.util.*;
public class Meal{
        static int[] array;
        static boolean[] is_visit;
        static int[] result;
        static int cnt = 0;
        public static void main(String args[]) {
                int x;
                Scanner sc = new Scanner(System.in);

                x = sc.nextInt();
                array = new int[x];
                is_visit = new boolean[x];
                result = new int[x];
                for(int i = 0; i < x ; i++){
                        int tmp = sc.nextInt();
                        array[i] = tmp;
                        is_visit[i] = false;
                }
                perm(0, x, 0, 0, 0);
                System.out.println(cnt);
        }
        private static void perm(int n, int r, int depth,int start, int sum){
                if(r == depth){
                        if(sum >= 2000 && sum <=2500){
                                cnt++;
                        }
                        return;
                }
                for(int i = start; i< n; i++){
                        if(!is_visit[i]){
                                is_visit[i]= true;
                                result[depth] = array[i];
                                perm(n,r,depth+1, i, sum+array[i]);
                                is_visit[i]=false;
                        }
                }
        }
}
