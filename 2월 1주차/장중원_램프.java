//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
//        int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 50
//        ArrayList<String> rows = new ArrayList<>();
//
//        for (int r = 0; r < N; r++) {
//            rows.add(br.readLine());
//        }
//
//        int K = Integer.parseInt(br.readLine()); // 0 < K < 1,000
//        int max = 0;
//
//        /**
//         * ------------------------------------------------------------------
//         * Q. 왜 LinkedList + iterator를 사용하지 않고 ArrayList + 인덱스 기반 반복문을 사용하는가?
//         *
//         * A. 이 로직의 핵심 비용은 "삭제"가 아니라 "순회"이다.
//         *    모든 행을 반드시 비교해야 하므로 컨테이너와 상관없이 O(N) 순회가 발생한다.
//         *
//         *    ArrayList는 삭제 시 요소 이동으로 O(N) 비용이 들지만,
//         *    메모리 연속 구조 덕분에 순회(get)이 매우 빠르고 캐시 친화적이다.
//         *
//         *    LinkedList + Iterator는 삭제 자체는 O(1)이지만,
//         *    next()를 통한 순회가 포인터 기반이라 실제 비용이 크고 캐시 미스가 잦다.
//         *
//         *    따라서 이 문제처럼 전체를 탐색해야 하는 경우에는
//         *    LinkedList의 빠른 삭제 이점이 순회 비용에 의해 상쇄된다.
//         *
//         *    (cf. LinkedList는 삭제할 위치를 이미 알고 있는 경우,
//         *    즉 Iterator가 이미 해당 노드를 가리키고 있을 때에만 유리하다.)
//         * ------------------------------------------------------------------
//         */
//
//        // 하나의 row를 reference로 선택하고 동일한 row들을 묶는다
//        // 동일한 row는 동일한 스위치 조작으로 모든 열을 1로 만들 수 있다
//        // 따라서 동일한 row의 개수 중 최대값이 답이다
//        while (!rows.isEmpty()) {
//            // reference row를 선택하고, 중복 계산 방지를 위해 후보에서 제외
//            String reference = rows.remove(0);
//
//            // reference row에서 꺼진 램프 개수 count
//            int offCount = offCount(reference);
//
//            // 최소 스위치 횟수(countOff)가 K를 초과하는 경우
//            // 또는 최소 횟수로 모두 켠 뒤 남은 횟수가 홀수라
//            // 반드시 어떤 열의 상태가 다시 뒤집히는 경우
//            // → 모든 열을 1로 만들 수 없다
//            if (offCount > K || (K - offCount) % 2 == 1) {
//                continue;
//            }
//
//            // reference row와 동일한 row의 개수를 count하고 max를 갱신
//            // 동일한 row들은 중복 계산 방지를 위해 후보에서 제외
//            int count = 1;
//
//            for (int r = rows.size() - 1; r >= 0; r--) {
//                if (reference.equals(rows.get(r))) {
//                    count++;
//                    rows.remove(r);
//                }
//            }
//
//            max = Math.max(max, count);
//        }
//
//        bw.write(max + "");
//        bw.flush();
//    }
//
//    public static int offCount(String lampRow) {
//        int count = 0;
//
//        for (int c = 0; c < lampRow.length(); c++) {
//            count += lampRow.charAt(c) == '0' ? 1 : 0;
//        }
//
//        return count;
//    }
//}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
        int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 50

        // 각 행 문자열의 등장 횟수를 저장
        Map<String, Integer> rowCount = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            rowCount.put(row, rowCount.getOrDefault(row, 0) + 1);
        }

        int K = Integer.parseInt(br.readLine()); // 0 < K < 1,000
        int max = 0;

        // 삭제 비용을 없애고 Map을 활용해 한 번의 순회로 정답을 구하는 방법
        for (Map.Entry<String, Integer> entry : rowCount.entrySet()) {
            String row = entry.getKey();
            int frequency = entry.getValue();
            int offCount = offCount(row);

            if (offCount > K || (K - offCount) % 2 == 1) {
                continue;
            }

            max = Math.max(max, frequency);
        }

        bw.write(max + "");
        bw.flush();
    }

    public static int offCount(String lampRow) {
        int count = 0;

        for (int c = 0; c < lampRow.length(); c++) {
            count += lampRow.charAt(c) == '0' ? 1 : 0;
        }

        return count;
    }
}