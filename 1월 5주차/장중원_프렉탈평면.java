import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R1 = Integer.parseInt(st.nextToken());
        int R2 = Integer.parseInt(st.nextToken());
        int C1 = Integer.parseInt(st.nextToken());
        int C2 = Integer.parseInt(st.nextToken());

        boolean[][] arr = new boolean[R2 - R1 + 1][C2 - C1 + 1];

        while (s > 0) {
            int len = (int) Math.pow(N, s);
            int unit = (int) Math.pow(N, s - 1);
            int start = unit * ((N - K) / 2);
            int end = start + unit * K;

            for (int r = R1; r <= R2; r++) {
                for (int c = C1; c <= C2; c++) {
                    int unitR = r % len;
                    int unitC = c % len;

                    if (start <= unitR && unitR < end && start <= unitC && unitC < end) {
                        arr[r - R1][c - C1] = true;
                    }
                }
            }

            s--;
        }

        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                bw.write(arr[r][c] ? "1" : "0");
            }
            bw.write("\n");
        }

        bw.flush();
    }
}