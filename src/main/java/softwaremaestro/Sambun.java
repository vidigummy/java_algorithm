package softwaremaestro;

import java.util.*;
public class Sambun {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        int len = 2;
        int a, b;
        a = sc.nextInt();
        b = sc.nextInt();
        arr.add(a);
        arr.add(b);
        int N;
        N = sc.nextInt();
        for(int i = 0 ; i < N; i++){
            int x,y,z;
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            arr.add(x);
            arr.add(y);
            arr.add(z);
            len += 3;
            Collections.sort(arr);
            System.out.println(arr.get(len/3)+ " "+arr.get(len*2/3));

        }
    }
}
