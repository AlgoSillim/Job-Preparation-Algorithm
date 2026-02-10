import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        int result = -1;

        if (isPossible(N)) {
            result = BFS(N, K);
        }

        bw.write(result + "");

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean isPossible(String N) {
        if (N.length() == 1 || (N.length() == 2 && N.charAt(1) == '0')) {
            return false;
        }

        return true;
    }

    public static int BFS(String N, int K) {
        int max = 0;
        Queue<int[]> que = new LinkedList<>();
        boolean[][] checked = new boolean[1000001][K + 1];

        que.offer(new int[] {Integer.parseInt(N), 0});

        while (!que.isEmpty()) {
            int num = que.peek()[0];
            int cnt = que.poll()[1];

            if (cnt == K) {
                max = Math.max(max, num);
                continue;
            }

            for (int i = 0; i < N.length(); i++) {
                for (int j = i + 1; j < N.length(); j++) {
                    int swapNum = swap(num, i, j);

                    if (swapNum == -1 || checked[swapNum][cnt + 1]) {
                        continue;
                    }

                    que.offer(new int[] {swapNum, cnt + 1});
                    checked[swapNum][cnt + 1] = true;
                }
            }
        }

        return max;
    }

    public static int swap(int num, int i, int j) {
        String strNum = String.valueOf(num);
        char[] charNum = strNum.toCharArray();

        char temp = charNum[i];
        charNum[i] = charNum[j];
        charNum[j] = temp;

        return charNum[0] == '0' ? -1 : Integer.parseInt(String.valueOf(charNum));
    }
}