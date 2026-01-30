import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim()); // T: 테케 수
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물 개수 (2 <= N <= 1_000)
			int K = Integer.parseInt(st.nextToken()); // 규칙 개수 (1 <= K <= 100_000)

			st = new StringTokenizer(br.readLine());
			int[] times = new int[N + 1]; // 건설 소요 시간
			int[] indegree = new int[N + 1]; // 전입 차수 개수
			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}

			// 전입 차수 관리
			List<List<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			// 건설 순서 X->Y
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				graph.get(X).add(Y);
				indegree[Y]++;
			}

			int W = Integer.parseInt(br.readLine().trim()); // 목표 건물 번호

			int[] dp = new int[N + 1];
			Queue<Integer> q = new ArrayDeque<>();

			// 전입 차수가 0인 건물 추가
			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					q.offer(i);
					dp[i] = times[i];
				}
			}

			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int next : graph.get(cur)) {
					dp[next] = Math.max(dp[next], dp[cur] + times[next]);
					if (--indegree[next] == 0) {
						q.offer(next);
					}
				}
			}

			bw.write(dp[W] + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}