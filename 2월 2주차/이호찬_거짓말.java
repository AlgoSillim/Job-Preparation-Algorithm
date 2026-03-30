import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int[] parents, ranks;

	private static int find(int n1) {
		if (parents[n1] == n1) {
			return n1;
		}
		return parents[n1] = find(parents[n1]);
	}

	private static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);

		if (p1 == p2) {
			return;
		}

		if (p1 == 0 || p2 == 0) {
			parents[p1] = 0;
			parents[p2] = 0;
			return;
		}

		if (ranks[p1] < ranks[p2]) {
			parents[p1] = p2;
		} else {
			parents[p2] = p1;
			if (ranks[p1] == ranks[p2]) {
				ranks[p1]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수, 1 <= N <= 50
		int M = Integer.parseInt(st.nextToken()); // 파티 수, 1 <= M <= 50

		parents = new int[N + 1];
		ranks = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		// parents[i] == 0: 진실을 아는 사람
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수
		for (int i = 0; i < K; i++) {
			union(0, Integer.parseInt(st.nextToken()));
		}

		List<int[]> parties = new ArrayList<>(); // 참가자 집합

		// 참가자 그룹화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // 파티 참가자 수

			int[] party = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				party[j] = Integer.parseInt(st.nextToken());
			}
			parties.add(party);

			// 같은 파티 union
			if (cnt >= 2) {
				int first = party[0];
				for (int j = 1; j < cnt; j++) {
					union(first, party[j]);
				}
			}
		}

		int result = 0;
		for (int[] party : parties) {
			// 파티 인원 모두 같은 집합
			if (find(party[0]) != 0) {
				result++;
			}
		}

		bw.write(String.valueOf(result));
		bw.flush();
	}
}
