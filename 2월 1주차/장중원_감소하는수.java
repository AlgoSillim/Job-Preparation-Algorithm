//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int N = Integer.parseInt(br.readLine()); // 0 <= N <= 1,000,000
//
//        bw.write(BFS(N));
//        bw.flush();
//    }
//
//    public static String BFS(int N) {
//        Queue<Long> que = new LinkedList<>();
//        int count = 0;
//
//        que.offer((long) 0);
//
//        while (!que.isEmpty()) {
//            long num = que.poll();
////            System.out.println(num);
//
//            if (count++ == N) {
//                return String.valueOf(num);
//            }
//
//            for (int i = 0; i < num % 10; i++) {
//                que.offer(num * 10 + i);
//            }
//        }
//
//        return "-1";
//    }
//}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 감소하는 수는 최대 1023개
        if (N > 1022) {
            System.out.println(-1);
            return;
        }

        List<Long> list = new ArrayList<>();

        for (int mask = 1; mask < (1 << 10); mask++) {
            long num = 0;

            for (int d = 9; d >= 0; d--) {
                if ((mask & (1 << d)) != 0) {
                    num = num * 10 + d;
                }
            }
            list.add(num);
        }

        Collections.sort(list);
        System.out.println(list.get(N));
    }
}