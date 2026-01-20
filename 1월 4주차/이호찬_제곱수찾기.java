import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static boolean isSquare(long numStr) {
		long sqrtNum = (long)Math.sqrt(numStr);
		return sqrtNum * sqrtNum == numStr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 1 <= n <= 9
		int m = Integer.parseInt(st.nextToken()); // 1 <= m <= 9

		char[][] board = new char[n][m];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j); // 1 <= board[i][j] <= 9
			}
		}

		long result = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (isSquare(board[i][j] - '0')) {
					result = Math.max(result, board[i][j] - '0');
				}
				for (int dr = (n * -1 + 1); dr <= n - 1; dr++) {
					for (int dc = (m * -1 + 1); dc <= m - 1; dc++) {
						if (dr == 0 && dc == 0) {
							continue;
						}
						int r = i;
						int c = j;
						long num = 0;
						while (r >= 0 && r < n && c >= 0 && c < m) {
							num = num * 10 + (board[r][c] - '0');
							if (isSquare(num)) {
								result = Math.max(result, num);
							}
							r += dr;
							c += dc;
						}
					}
				}
			}
		}

		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
