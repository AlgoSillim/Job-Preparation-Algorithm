import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int[] parents; // 부모 노드 번호
	private static int[] childCnt; // 자식 노드 개수

	private static void removeNode(int rmNo) {
		// 부모 노드가 루트 노드가 아닐 경우
		int parentNo = parents[rmNo];
		if (parentNo != -1) {
			childCnt[parentNo]--;
		}
		// 부모, 자식 노드 정보 제거
		parents[rmNo] = -1;
		childCnt[rmNo] = -1;

		for (int i = 0; i < N; i++) {
			if (parents[i] == rmNo) {
				removeNode(i);
			}
		}
	}

	private static int getLeafNodeCnt() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			if (childCnt[i] == 0) {
				total++;
			}
		}
		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 노드 개수, 1 <= N <= 50
		parents = new int[N];
		childCnt = new int[N];

		// 입력: 부모 노드 번호, 자식 노드 개수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			parents[i] = cur;
			if (cur == -1) {
				continue;
			}
			childCnt[cur]++;
		}

		int R = Integer.parseInt(br.readLine()); // 지울 노드 번호

		// 목표 노드 삭제
		removeNode(R);

		// 리프 노드 개수 파악
		int result = getLeafNodeCnt();

		bw.write(String.valueOf(result));
		bw.flush();
	}
}
