import java.io.*;
import java.util.*;

public class Main {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 1 <= N <= 50
        List<List<Integer>> tree = new ArrayList<>(); // 인접리스트

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;

        for (int node = 0; node < N; node++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                root = node;
                continue;
            }

            tree.get(parent).add(node);
        }

        int delete = Integer.parseInt(br.readLine());

//        for (int i = 0; i < N; i++) {
//            System.out.println(tree.get(i));
//        }

        // 삭제 노드를 제외하고 DFS를 수행하며 leaf 개수 계산
        DFS(tree, root, delete);

        bw.write(result + "");
        bw.flush();
    }

    public static int DFS(List<List<Integer>> tree, int parent, int delete) {
        // 삭제된 노드에 도달하면 해당 서브트리는 탐색하지 않음
        // 0을 반환하여 count를 유지
        if (parent == delete) {
            return 0;
        }

        int count = 0;

        for (int child : tree.get(parent)) {
            count += DFS(tree, child, delete);
        }

        /*
         * count == 0 인 경우:
         * - 원래 leaf 노드이거나
         * - 모든 자식이 삭제되어 leaf가 된 경우
         * → 현재 노드를 leaf로 판단
         */
        if (count == 0) {
            result++;
        }

        // count를 증가시켜 leaf 노드와 구분
        return 1;
    }
}