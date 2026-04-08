import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	private static String makeRoomNumber(String curNum, int addNum) {
		if (curNum == null) {
			return String.valueOf(addNum);
		}

		if (curNum.equals("0") && addNum == 0) {
			return curNum;
		}

		StringBuilder sb = new StringBuilder();
		boolean flag = false; // addNum 반영 여부

		for (char ch : curNum.toCharArray()) {
			int cur = Character.getNumericValue(ch);
			if (!flag && addNum >= cur) {
				sb.append(addNum);
				flag = true;
			}
			sb.append(cur);
		}

		if (!flag) {
			sb.append(addNum);
		}

		return sb.toString();
	}

	private static String compareStrNum(String strNum1, String strNum2) {
		int len1 = strNum1 == null ? 0 : strNum1.length();
		int len2 = strNum2 == null ? 0 : strNum2.length();
		if (len1 == len2) {
			for (int i = 0; i < len1; i++) {
				if (strNum1.charAt(i) > strNum2.charAt(i)) {
					return strNum1;
				} else if (strNum1.charAt(i) < strNum2.charAt(i)) {
					return strNum2;
				}
			}
		}
		return len1 > len2 ? strNum1 : strNum2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 구매 가능한 숫자 종류 0 ~ N-1, 1 <= N <= 10

		st = new StringTokenizer(br.readLine());
		int[] prices = new int[N];
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(st.nextToken()); // 숫자 가격, 1 <= prices[i] <= 50
		}

		int M = Integer.parseInt(br.readLine()); // 보유 금액, 1 <= M <= 50

		String[] dp = new String[M + 1]; // dp[i]: i 금액 사용 시, 최대 방 번호
		String result = "";

		// 방 번호 갱신
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < N; j++) {
				if (i < prices[j]) {
					continue;
				}
				String newNum = makeRoomNumber(dp[i - prices[j]], j);
				dp[i] = compareStrNum(dp[i], newNum);
			}
		}

		// 방 번호 최댓값 찾기
		for (int i = 0; i <= M; i++) {
			result = compareStrNum(result, dp[i]);
		}

		bw.write(result);
		bw.flush();
	}
}
