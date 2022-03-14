package baekjoon;

import java.io.*;

public class CantSleep15755 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); int i = 0;
        while (N > 0) {
            i++;
            String str = String.valueOf(i);
            N -= str.length();
            if (N <= 0) {
                bw.write(str.charAt(str.length() - 1 + N));
            } }
        bw.flush();
    }
}
