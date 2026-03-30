import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static void swap(char[] numArr, int i, int j) {
		char tmp = numArr[i];
		numArr[i] = numArr[j];
		numArr[j] = tmp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 1_000_000
		int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 10
		int M = String.valueOf(N).length(); // N의 자릿수

		Queue<Status> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[K + 1][1_000_001];

		q.offer(new Status(0, N));
		visited[0][N] = true;

		int result = -1;
		while (!q.isEmpty()) {
			Status cur = q.poll();

			if (cur.depth == K) {
				result = Math.max(result, cur.value);
				continue;
			}

			char[] curArr = String.valueOf(cur.value).toCharArray();

			// 다음 수 만들기
			for (int i = 0; i < M; i++) {
				for (int j = i + 1; j < M; j++) {
					// 0으로 시작 불가
					if (i == 0 && curArr[j] == '0') {
						continue;
					}

					char[] nextArr = Arrays.copyOf(curArr, curArr.length);
					swap(nextArr, i, j);

					int nextValue = Integer.parseInt(String.valueOf(nextArr));

					if (visited[cur.depth + 1][nextValue]) {
						continue;
					}

					visited[cur.depth + 1][nextValue] = true;
					q.offer(new Status(cur.depth + 1, nextValue));
				}
			}
		}

		bw.write(String.valueOf(result));
		bw.flush();
	}

	private static class Status {
		int depth;
		int value;

		public Status(int depth, int value) {
			this.depth = depth;
			this.value = value;
		}
	}
}
