삼성 SW소프트웨어 역량 검사 2번 문제를 못푼것이 한이 되어 집에와서 풀어봤다
완벽하게 풀고싶은 마음에 최적화에 최적화를 거듭한 결과 12시간이 걸림

첫번쨰 줄 테스트케이스 T 입력 (T<=50)
두번째 줄 도시의 사이즈 N입력 (N<=100)
그리고 도시 정보 입력 받음 
집과 회사는 각각 숫자로 표현되는데 1부터 N까지 번호가 매겨져있고 집은 양수, 회사는 음수로 표현 ( 3<= N <=8)
각 집-회사 쌍은 자신의 번호의 음수-양수를 이동한다.
이동거리계산 방법은 A의 좌표가 (AX, AY), B의 좌표가 (BX, BY)이면  |AX-BX|+|AY-BY|이다. (|A|는 절대값A라는 뜻)
도시에는 지하철 정거장을 3곳에만 지을수 있는데 모든 집-회사의 이동거리의 합이 최소가 되도록 하여 
가장 최소의 이동거리를 출력하는것이 문제이다. 

test input 
 
3								
9								
2	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	1	0	-1	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	-3	0	0	0	0	-2	3	0
5
-3 0 0 -1 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
-2 0 0 3 2
9								
2	0	0	0	0	0	0	0	-4
0	0	0	0	0	-5	0	0	0
0	1	0	-1	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	4	0	0	0
0	0	0	0	0	0	0	0	0
0	0	0	0	0	0	0	5	0
0	-3	0	0	0	-2	0	3	0


testcase output 

case #1
7
case #2
6
case #3
18