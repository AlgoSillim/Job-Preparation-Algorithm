import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	private static List<Long> bitWords;
	private static char[] setArr;          // 필수 5글자 제외 후보 문자들
	private static boolean[] visited;      // setArr 인덱스 기준 선택 여부
	private static int maxCnt;
	private static long essentialMask;     // a,c,i,n,t 마스크

	private static boolean isEssential(char ch) {
		return ch == 'a' || ch == 'c' || ch == 'i' || ch == 'n' || ch == 't';
	}

	private static void combination(int depth, int start, int goal) {
		if (depth == goal) {
			checkWordCnt();
			return;
		}

		for (int i = start; i < setArr.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combination(depth + 1, i + 1, goal);
			visited[i] = false;
		}
	}

	private static void checkWordCnt() {
		int cnt = 0;
		long cur = essentialMask;

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				continue;
			cur |= (1L << (setArr[i] - 'a'));
		}

		for (long bitWord : bitWords) {
			if ((bitWord & cur) == bitWord) {
				cnt++;
			}
		}

		maxCnt = Math.max(maxCnt, cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 단어 개수, 1 <= N <= 50
		int K = Integer.parseInt(st.nextToken()); // 글자 개수, 1 <= K <= 26

		// 5개 고정: a c i n t
		if (K < 5) {
			bw.write("0");
			bw.flush();
			return;
		}
		if (K == 26) {
			bw.write(String.valueOf(N));
			bw.flush();
			return;
		}

		bitWords = new ArrayList<>();
		Set<Character> candidateSet = new HashSet<>();

		// 필수 마스크 설정
		essentialMask = 0;
		char[] essentials = {'a', 'c', 'i', 'n', 't'};
		for (char e : essentials) {
			essentialMask |= (1L << (e - 'a'));
		}

		// 단어 마스크 저장 + 후보 글자 수집(필수 제외)
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			long bitWord = 0;

			for (char ch : word.toCharArray()) {
				bitWord |= (1L << (ch - 'a'));
				if (!isEssential(ch)) {
					candidateSet.add(ch);
				}
			}
			bitWords.add(bitWord);
		}

		// 후보 배열로 변환
		setArr = new char[candidateSet.size()];
		int idx = 0;
		for (char ch : candidateSet) {
			setArr[idx++] = ch;
		}

		visited = new boolean[setArr.length];
		maxCnt = 0;

		int goal = K - 5;

		// 후보 수 이하로 전부 가르칠 수 있으면 조합 불필요
		if (goal >= setArr.length) {
			bw.write(String.valueOf(N));
			bw.flush();
			return;
		}

		combination(0, 0, goal);
		bw.write(String.valueOf(maxCnt));
		bw.flush();
	}
}