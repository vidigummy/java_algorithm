import java.util.*;
public class Second {
    static Integer[] memoryLimit;
    static Integer[][] inputSet;
    static Integer[] memoryNow;
    public static void insert(int where, int what){

    }
    public static void main(String args[]) {
        int N, Q;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();

        memoryLimit = new Integer[N];
        memoryNow = new Integer[N];
        inputSet = new Integer[N][];
        for(int i = 0; i < N; i++){
            int tmpInput = sc.nextInt();
            memoryLimit[i] = tmpInput;
            memoryNow[i] = 0;
            inputSet[i] = new Integer[tmpInput];
        }
        for(int i = 0; i < Q; i++){
            String Op = sc.next();
            if(Op.charAt(0) == 'I'){
                int where = sc.nextInt();
                int what = sc.nextInt();
                insert(where,what);
            }else{
                int what = sc.nextInt();

            }
        }
    }
}
