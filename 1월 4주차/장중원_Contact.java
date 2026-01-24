import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            String input = br.readLine();
            String buffer = "";
            int point = 0;

            while (point < input.length()) {
                char nextChar = input.charAt(point++);
                buffer += nextChar;

                if (buffer.equals("00")) {
                    break;
                }

                if (buffer.equals("01")) {
                    buffer = "";
                }

                if (buffer.equals("11")) {
                    break;
                }

                if (buffer.equals("101")) {
                    break;
                }

                if (buffer.equals("1000")) {
                    buffer = "100";
                }

                if (buffer.equals("10010")) {
                    buffer = "0";
                }

                if (buffer.equals("100111")) {
                    buffer = "10011";
                }

                if (buffer.equals("1001101")) {
                    buffer = "";
                }

                if (buffer.equals("1001100")) {
                    buffer = "100";
                }
            }

            if (buffer.equals("") || buffer.equals("1001") || buffer.equals("10011")) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        bw.flush();
    }
}