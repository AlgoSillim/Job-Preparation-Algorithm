import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int result = -1;
        int num = 0;
        String square = Integer.toString(num * num);

        while (square.length() <= 9) {
            result = Math.max(result, solution(arr, square));

            num++;
            square = Integer.toString(num * num);
        }

        bw.write(result + "");
        bw.flush();
    }

    public static int solution(String[] arr, String square) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length(); c++) {
                if (square.charAt(0) != arr[r].charAt(c)) {
                    continue;
                }

                if (square.length() < 2) {
                    return Integer.parseInt(square);
                }

                for (int rr = 0; rr < arr.length; rr++) {
                    for (int cc = 0; cc < arr[rr].length(); cc++) {
                        if (square.charAt(1) != arr[rr].charAt(cc)) {
                            continue;
                        }

                        if (square.length() < 3) {
                            return  Integer.parseInt(square);
                        }

                        int p = 2;
                        int rowDiff = rr - r;
                        int colDiff = cc - c;
                        int nr = rr + rowDiff;
                        int nc = cc + colDiff;

                        while (p < square.length()) {
                            if (nr < 0 || nr >= arr.length || nc < 0 || nc >= arr[0].length()) {
                                break;
                            }

                            if (arr[nr].charAt(nc) != square.charAt(p)) {
                                break;
                            }

                            p++;
                            nr += rowDiff;
                            nc += colDiff;

                            if (p == square.length()) {
                                return Integer.parseInt(square);
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
}