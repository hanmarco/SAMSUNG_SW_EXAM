package samsung1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[][] cityMatrix; // 도시를 만드는 배열
	static Position[] HOME; // 집의 좌표를 저장할 Point객체 배열
	static Position[] WORK; // 직장의 좌표를 저장할 Point객체 배열
	
	public static void main(String[] args) {
		WORK = new Position[8 + 1]; // 0번은 집과 회사가 아니므로 이용하지않음 범위는 3<=m<=8이므로
		HOME = new Position[8 + 1]; // 인덱스를 직접 활용하기위해 1을 더해줌 (메모리 차지 매우 작음)

		Scanner scanner = new Scanner(System.in); // 표준입력 스캐너 객체생성

		int T = scanner.nextInt(); // T는 테스트케이스 크기
		
		for (int testCase = 1; testCase <= T; testCase++) {
			int[] ret = cityConstructor_use(scanner); // 미로를 스캔하고
			int n = ret[0]; // 그 정보인 가로세로 사이즈 n
			int m = ret[1]; // 직장-집의 쌍 크기 m

			System.out.println("Case #" + testCase);			
			System.out.println(trySimulation2(n, m));
			
		}
		
	}

	// 표준입력받아 미로를 완성하는 함수
	static int[] cityConstructor_use(Scanner scanner) {

		int n = scanner.nextInt();
		int m = Integer.MIN_VALUE;

		cityMatrix = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				int inputValue = cityMatrix[i][j] = scanner.nextInt();
				if (inputValue > 0) {
					HOME[inputValue] = new Position(i, j);
				} else if (inputValue < 0) {
					// temp는 음수이다.
					inputValue = Math.abs(inputValue);
					WORK[inputValue] = new Position(i, j);
				}
				if (inputValue > m)
					m = inputValue;
			}

		int[] returnValue = { n, m };
		return returnValue;

	}

	// 가장가까운 정류장이나 직접 가는 것중 가장 짧은곳으로 향하여 시뮬레이션
	// 결과로 집에서 회사까지 가는 가장 짧은 거리를 반환
	static int minDistanceHomeToCompany_Via_Station(Position home, Position company, Position[] stations) {
		int min = home.toDistance(company);

		for (Position inStation : stations)
			for (Position outStation : stations) {
				int temp = home.toDistance(inStation) + company.toDistance(outStation);
				if (temp < min)
					min = temp;
			}
		return min;
	}

	// 현재의정류장을 이용했을때 이동거리 계산함
	// 모든 집-회사 쌍의최소 이동거리 반환
	static int distancesSummary(int M, Position[] stations) {
		int totalDistance = 0;

		for (int i = 1; i <= M; i++) 
			totalDistance += minDistanceHomeToCompany_Via_Station(WORK[i], HOME[i], stations);
		
		return totalDistance;
	}

	// 모든 경우에 대해 시뮬레이션하여 최소값 반환
	static int trySimulation2(int N, int M) {
		int min = Integer.MAX_VALUE;
		ArrayList<Position> stationCases = new ArrayList<Position>();
		// 테스트할 지하철의 좌표를 저장

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (cityMatrix[i][j] != 0) // 집이나 회사가 있는 위치이면 생략
					continue;
				// 모든 경우를 리스트에 채운다
				stationCases.add(new Position(i, j));
			}

		for (int i = 0; i < stationCases.size(); i++) 
			for (int j = i+1; j < stationCases.size(); j++) 
				for (int k = j+1; k < stationCases.size(); k++)
				{
					Position st1, st2, st3; //3개의 역이 있다.
					st1 = stationCases.get(i);
					st2 = stationCases.get(j);
					st3 = stationCases.get(k);
					
					// 여기까지 오면 1-2-3정류장이 모두 다른 케이스
					// 현재 정류장 쌍의 거리합을 측정한다.
					Position[] testCase = { st1, st2, st3 }; 
					int summary = distancesSummary(M, testCase); 
					
					if (summary < min) // 측정값이 이전까지 등장하지 않았던 최소값이면 min에 저장
						min = summary;
				}

		return min; // 모든 경우중 가장 작은 값을 반환
	}
}

class Position {// 좌표 객체
	int x, y;

	// constructor
	Position(int X, int Y) {
		x = X;
		y = Y;
	}

	boolean isSame(Position target) {
		if ((target.x == this.x) && (target.y == this.y))
			return true;

		return false;
	}

	int toDistance(Position target) {
		return Math.abs(this.x - target.x) + Math.abs(this.y - target.y);
	}
}