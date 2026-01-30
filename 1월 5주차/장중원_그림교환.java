import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] prices;
    static int[][][] DP;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        prices = new int[N][N];

        for (int r = 0; r < N; r++) {
            String str = br.readLine();

            for (int c = 0; c < N; c++) {
                prices[r][c] = str.charAt(c) - '0';
            }
        }

        // DP[현재 사람][방문 비트마스크][이전 가격]
        //
        // 방문 여부는 비트마스크(mask)로 관리하며,
        // mask는 "어떤 사람들을 방문했는지"를 집합 형태로 표현한다.
        //
        // 이 문제에서 DFS만 사용하면,
        // 서로 다른 경로로 동일한 상태에 도달했을 때
        // 같은 탐색을 여러 번 반복하게 된다.
        //
        // 예:
        //   0 -> 1 -> 2 -> 3
        //   0 -> 2 -> 1 -> 3
        //
        // 위 두 경로는 방문 순서는 다르지만,
        // (현재 사람 = 3, 방문 집합 = {0,1,2,3}, 이전 가격 동일)
        // 이라는 동일한 상태에 도달한다.
        //
        // 이 상태에서 앞으로 가능한 선택과 결과는
        // 이전에 어떤 순서로 방문했는지와 무관하게 항상 동일하다.
        //
        // 따라서
        // (현재 사람, 방문 집합, 이전 가격)
        // 이 동일한 상태를 DP로 기억하여,
        // 이미 더 깊거나 같은 depth로 방문한 적이 있다면
        // 중복 탐색을 방지한다.
        DP = new int[N][1 << N][10];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }

        DFS(0, 1, 0, 1);

        bw.write(result + "");
        bw.flush();
    }

    public static void DFS(int seller, int mask, int prevPrice, int depth) {
        if (DP[seller][mask][prevPrice] >= depth) {
            return;
        }

        DP[seller][mask][prevPrice] = depth;
        result = Math.max(result, depth);

        // mask는 방문 여부를 나타내는 비트마스크이다.
        //
        // 1) 방문 처리 (OR 연산)
        //    mask | (1 << buyer)
        //    예)
        //      buyer = 3 : 00001 | 01000 = 01001
        //      buyer = 2 : 01001 | 00100 = 01101
        //
        // 2) 방문 여부 확인 (AND 연산)
        //    (mask & (1 << buyer)) != 0
        //    예)
        //      buyer = 4 : 01101 & 10000 = 00000 -> 첫 방문
        //      buyer = 2 : 01101 & 00100 = 00100 -> 이미 방문

        for (int buyer = 0; buyer < N; buyer++) {
            // 재구매 혹은 값싼 판매가일 경우 continue
            if ((mask & (1 << buyer)) != 0 || prevPrice > prices[seller][buyer]) {
                continue;
            }

            DFS(buyer, mask | (1 << buyer), prices[seller][buyer], depth + 1);
        }
    }
}