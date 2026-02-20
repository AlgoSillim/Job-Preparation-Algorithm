import java.io.*;
import java.util.*;

public class Main {

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 50
        int K = Integer.parseInt(st.nextToken()); // 0 <= K <= 26

        // K가 5보다 작으면 어떤 단어도 읽을 수 없음
        if (K < 5) {
            bw.write("0");
            // K가 26이면 모든 단어를 읽을 수 있음
        } else if (K == 26) {
            bw.write(N + "");
        } else {
            int mask = 0;

            mask |= 1 << ('a' - 'a');
            mask |= 1 << ('n' - 'a');
            mask |= 1 << ('t' - 'a');
            mask |= 1 << ('i' - 'a');
            mask |= 1 << ('c' - 'a');

//            System.out.println(Integer.toBinaryString(binary));

            int[] biWords = new int[N];

            // 단어를 binary로 변환
            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                word = word.substring(4, word.length() - 4); // anta, tica 제거

                int bit = 0;

                for (char c : word.toCharArray()) {
                    bit |= 1 << (c - 'a');
                }

                biWords[i] = bit;
            }

            // 가르칠 수 있는 모든 경우의 수를 조합으로 완전탐색
            combination(K, 0, 0, biWords, mask);

            bw.write(max + "");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void combination(int K, int start, int depth, int[] biWords, int mask) {
        if (depth == K - 5) {
            int count = 0;

            for (int biWord : biWords) {
                if ((biWord & mask) == biWord) {
                    count++;
                }
            }

            max = Math.max(max, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((mask & (1 << i)) == 0) {
                combination(K, i + 1, depth + 1, biWords, mask | (1 << i));
            }
        }
    }
}