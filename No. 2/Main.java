package samsung1;

import java.util.Scanner;

class Point {// 좌표 객체
	int x;
	int y;
	
	Point(int X, int Y) {
		x = X;
		y = Y;
	}
}

public class Main {
	static int[][] cityMatrix; // 도시를 만드는 객체
	static Point[] HOME; // 집의 좌표를 저장할 객체 배열
	static Point[] WORK; // 직장의 좌표를 저장할 객체 배열

	public static void main(String[] args) {
		WORK = new Point[8 + 1]; // 0번은 집과 회사가 아니므로 이용하지않음
		HOME = new Point[8 + 1];
		
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();

		for (int testCase = 0; testCase < T; testCase++) {
			int[] ret = scanMaze(s); 	// 미로를 스캔하고 그 정보인 가로세로 사이즈 n과 직장-집이 몇쌍인지를
										// 저장하는 m을 저장함
			int n = ret[0];
			int m = ret[1];

			System.out.println(tryNcaseSubwaySimulation(n, m));
		}

	}

	// 표준입력받아 미로를 완성하는 함수
	private static int[] scanMaze(Scanner s) {

		int cityLength = s.nextInt();
		int WorkHomeCount = Integer.MIN_VALUE;

		cityMatrix = new int[cityLength][cityLength];

		for (int i = 0; i < cityLength; i++) {
			for (int j = 0; j < cityLength; j++) {
				int temp = cityMatrix[i][j] = s.nextInt();
				if (temp > 0) {
					HOME[temp] = new Point(i, j);
				} else if (temp < 0) {
					// temp는 음수이다.
					temp = Math.abs(temp);
					WORK[temp] = new Point(i, j);
				}
				if (temp > WorkHomeCount)
					WorkHomeCount = temp;
			}
		}
		int[] ret = { cityLength, WorkHomeCount };
		return ret;

	}

	// 두개지점의거리 계산
	private static int calcDistance(Point A, Point B) {
		int ret = Math.abs(A.x - B.x) + Math.abs(A.y - B.y);

		return ret;
	}

	// 가장가까운 정류장이나 직접 가는 것중 가장 짧은곳으로 향함
	private static int minDistanceToStation(Point src, Point Dst, Point station1, Point station2, Point station3) {
		// calc most nearstation
		int dst = calcDistance(src, Dst);
		int min = dst;// 10
		Point[] stations = new Point[3];

		stations[0] = station1;
		stations[1] = station2;
		stations[2] = station3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int temp = calcDistance(src, stations[i]) + calcDistance(stations[j], Dst);// 3
				if (temp < min) {
					min = temp;
				}
			}
		}
		return min;
	}

	// 현재의정류장을 이용했을때 이동거리 계산
	private static int subwaycalc(int M, Point station1, Point station2, Point station3) {
		int totalDistance = 0;

		for (int i = 1; i <= M; i++) {
			totalDistance += minDistanceToStation(WORK[i], HOME[i], station1, station2, station3);
		}

		return totalDistance;
	}

	private static int tryNcaseSubwaySimulation(int N, int M) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++)	
		for (int j = 0; j < N; j++) // 1
		for (int k = 0; k < N; k++)
		for (int k2 = 0; k2 < N; k2++) // 2
		for (int l = 0; l < N; l++)
		for (int l2 = 0; l2 < N; l2++) {// 3
			if (k == i && k2 == j)
				continue;
			if (l == i && l2 == j)
				continue;
			if (l == k && l2 == k2)
				continue;
			if ((cityMatrix[k][k2] != 0) || (cityMatrix[l][l2] != 0) || (cityMatrix[i][j] != 0))
				continue;

			Point st1 = new Point(i, j);
			Point st2 = new Point(k, k2);
			Point st3 = new Point(l, l2);
			int temp = subwaycalc(M, st1, st2, st3);
			if (temp < min)
				min = temp;

		}
		return min;
	}
}
