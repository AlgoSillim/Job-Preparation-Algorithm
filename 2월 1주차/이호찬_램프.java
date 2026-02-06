import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 행, 1 <= N <= 50
		int M = Integer.parseInt(st.nextToken()); // 열, 1 <= M <= 50

		// 램프 패턴 저장
		Map<String, Integer> lampMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			if (lampMap.containsKey(input)) {
				lampMap.put(input, lampMap.get(input) + 1);
			} else {
				lampMap.put(input, 1);
			}
		}

		int K = Integer.parseInt(br.readLine().trim()); // 스위치 누르는 횟수, 0 <= K <= 1,000

		int result = 0;
		for (Map.Entry<String, Integer> e : lampMap.entrySet()) {
			String lamp = e.getKey();
			int lampCnt = lampMap.get(lamp);

			int cnt = 0; // 0 상태 램프 개수
			for (int i = 0; i < M; i++) {
				if (lamp.charAt(i) == '0') {
					cnt++;
				}
			}
			// 스위치 K번 눌렀을 때 만들 수 있는지 확인
			if ((K >= cnt) && (K - cnt) % 2 == 0) {
				result = Math.max(result, lampCnt);
			}
		}

		bw.write(String.valueOf(result));
		bw.flush();
	}
}
