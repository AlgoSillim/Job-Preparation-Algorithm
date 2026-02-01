import java.io.*;
import java.util.*;

public class Main {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] buildTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());

            for (int i = 1; i < N + 1; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            List<List<Integer>> arr = new ArrayList<>();

            for (int i = 0; i < N + 1; i++) {
                arr.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                arr.get(to).add(from);
            }

            int W = Integer.parseInt(br.readLine());
            int[] DP = new int[N + 1];

            for (int i = 0; i < N + 1; i++) {
                DP[i] = -1;
            }

            bw.write(DFS(buildTime, arr, W, DP) + "\n");
        }

        bw.flush();
    }

    public static int DFS(int[] buildTime, List<List<Integer>> arr, int node, int[] DP) {
        if (DP[node] != -1) return DP[node];

        int maxPrev = 0;

        for (int prev : arr.get(node)) {
            maxPrev = Math.max(maxPrev, DFS(buildTime, arr, prev, DP));
        }

        return DP[node] = buildTime[node] + maxPrev;
    }
}