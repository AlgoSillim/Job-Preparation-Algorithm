import java.io.*;
import java.util.*;

class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 15
        int r = Integer.parseInt(st.nextToken()); // 0 <= r <= 2^N
        int c = Integer.parseInt(st.nextToken()); // 0 <= c <= 2^N

        int first = 0;
        Coordinate firstCoord = new Coordinate(0, 0);

        // N이 0이 될 때까지 사분면을 계속 좁혀나감 (분할 정복)
        while (N > 0) {
            N--;
            int power = (int) Math.pow(2, N); // 현재 단계에서 한 사분면의 변의 길이

//            System.out.println("power : " + power);
//            System.out.println("first : " + first);
//            System.out.println();

            // Z 모양 순서대로 4개의 사분면(0:좌상, 1:우상, 2:좌하, 3:우하)을 확인
            for (int section = 0; section < 4; section++) {
                Coordinate sectionFirstCoord = new Coordinate(firstCoord.x, firstCoord.y);
                Coordinate sectionLastCoord = new Coordinate(firstCoord.x + power - 1, firstCoord.y + power - 1);

                if (section == 1) {
                    sectionFirstCoord.y += power;
                    sectionLastCoord.y += power;
                } else if (section == 2) {
                    sectionFirstCoord.x += power;
                    sectionLastCoord.x += power;
                } else if (section == 3) {
                    sectionFirstCoord.x += power;
                    sectionFirstCoord.y += power;
                    sectionLastCoord.x += power;
                    sectionLastCoord.y += power;
                }

                // 목표 좌표 (r, c)가 현재 사분면 범위 내에 있는지 확인
                if (sectionFirstCoord.x <= r && r <= sectionLastCoord.x && sectionFirstCoord.y <= c && c <= sectionLastCoord.y) {
//                    System.out.println("(" + sectionFirstCoord.x + " " + sectionFirstCoord.y + ")");
//                    System.out.println("(" + sectionLastCoord.x + " " + sectionLastCoord.y + ")");
//                    System.out.println();
                    // 찾으려는 좌표가 이 사분면에 있다면:
                    // 1. 기준 좌표를 해당 사분면의 시작점으로 업데이트
                    firstCoord.x = sectionFirstCoord.x;
                    firstCoord.y = sectionFirstCoord.y;

                    // 2. 이전 사분면들의 넓이만큼 방문 횟수를 더함 (한 사분면의 크기 = power * power)
                    first += power * power * section;

                    // 해당 단계를 마쳤으므로 사분면 탐색 루프 탈출
                    break;
                }

            }
        }

        bw.write(first + "");
        bw.flush();
    }
}