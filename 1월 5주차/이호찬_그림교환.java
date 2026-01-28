import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int N;
	private static int[][] price;
	private static int[][][] dp;

	private static int dfs(int mask, int owner, int last) {
		// 가지 치기
		int cached = dp[mask][owner][last];
		if (cached != -1) {
			return cached;
		}

		int maxCnt = Integer.bitCount(mask); // 현재까지 방문 수

		for (int next = 0; next < N; next++) {
			// 이미 구매했던 사람
			if ((mask & (1 << next)) != 0) {
				continue;
			}
			int cost = price[owner][next];
			if (cost < last) {
				continue;
			}
			int nextMask = mask | (1 << next);
			maxCnt = Math.max(maxCnt, dfs(nextMask, next, cost));
		}

		return dp[mask][owner][last] = maxCnt;
	}

	// 그림을 구매 가격 이상으로 판매 -> 구매 가격 관리
	// 중복 구매 불가능 -> 구매 여부 확인: 비트마스킹

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 예술가 수, (2 <= N <= 15)

		price = new int[N][N];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				price[i][j] = input.charAt(j) - '0';
			}
		}

		dp = new int[1 << N][N][10]; // 앞으로 거쳐 갈 수 있는 사람 수 최댓값, [소유 했던 사람들][그림 소유자][직전 구매 가격]

		for (int i = 0; i < 1 << N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		int result = dfs(1 << 0, 0, 0);

		System.out.println(result);
		br.close();
	}
}
