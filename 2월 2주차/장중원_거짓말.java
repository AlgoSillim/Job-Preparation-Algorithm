import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
        int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 50
        parent = new int[N + 1]; // 부모가 0번 이면 진실을 아는 사람

        init(N);

        st = new StringTokenizer(br.readLine());
        int truthNum = Integer.parseInt(st.nextToken()); // 진실을 아는 사람 수

        for (int i = 0; i < truthNum; i++) {
            int truthId = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 번호
            parent[truthId] = 0;
        }

        List<List<Integer>> parties = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken()); // 파티에 참석하는 사람의 수
            List<Integer> party = new ArrayList<>();

            for (int j = 0; j < partySize; j++) {
                int id = Integer.parseInt(st.nextToken()); // 파티에 참석한 사람의 번호

                party.add(id);
            }

            parties.add(party);
        }

        unionPartyMembers(parties); // 파티마다 참석한 사람들을 한 집단으로 묶는다.
        bw.write(getResult(parties) + ""); // 파티 집단의 부모가 0번 이면 진실을 아는 집단이다.

        bw.flush();
    }

    public static void init(int N) {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        int max = Math.max(x, y);
        int min = Math.min(x, y);

        parent[max] = min;
    }

    public static void unionPartyMembers(List<List<Integer>> parties) {
        for (List<Integer> party : parties) {
            for (int i = 0; i < party.size() - 1; i++) {
                union(party.get(i), party.get(i + 1));
            }
        }
    }

    public static int getResult(List<List<Integer>> parties) {
        int count = 0;

        for (List<Integer> party : parties) {
            for (int id : party) {
                if (find(id) == 0) {
                    count++;
                    break;
                }
            }
        }

        return parties.size() - count;
    }
}