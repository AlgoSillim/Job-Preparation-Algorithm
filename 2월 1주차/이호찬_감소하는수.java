import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine().trim()); // 0 <= N <= 1_000_000

		// 감소하는 수 경우의 수 개수: 2^10 - 1 = 1023
		if (N >= 1023) {
			bw.write(String.valueOf(-1));
			bw.flush();
			return;
		}

		// 감소하는 수 생성
		Queue<Long> q = new ArrayDeque<>();
		for (long i = 0; i < 10; i++) {
			q.offer(i);
		}

		int cnt = -1;
		while (!q.isEmpty()) {
			long cur = q.poll();
			cnt++;

			if (cnt == N) {
				bw.write(String.valueOf(cur));
				bw.flush();
				return;
			}

			long lastDigit = cur % 10;
			for (long digit = 0; digit < lastDigit; digit++) {
				q.offer(cur * 10 + digit);
			}
		}

		// 안전 장치
		bw.write(String.valueOf(-1));
		bw.flush();
	}
}
