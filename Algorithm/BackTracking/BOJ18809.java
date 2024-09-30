import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int maxGreen;
    static int maxRed;
    static int[][] plant;
    static boolean[] visitedPlant;
    static int[][] distance;
    static int[][] input;
    static int[][] redPlant;
    static int[][] greenPlant;
    static Queue<int[]> greenQ;
    static Queue<int[]> redQ;
    static boolean isExtend = true;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int flower = -1;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        maxGreen = Integer.parseInt(st.nextToken());
        maxRed = Integer.parseInt(st.nextToken());

        LinkedList<int[]> plantList = new LinkedList<>();
        input = new int[m][n];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int x = Integer.parseInt(st.nextToken());
                if(x==2) plantList.add(new int[]{i, j});
                input[i][j] = x;
            }
        }

        plant = new int[plantList.size()][2];
        visitedPlant = new boolean[plantList.size()];
        redPlant = new int[maxRed][2];
        greenPlant = new int[maxGreen][2];
        for(int i=0; i<plant.length; i++) plant[i] = plantList.get(i);

        // 우선 배양액을 심을 위치부터 백트래킹으로 고르자.
        // 초록과 빨강 중 누굴 먼저 심을지는 중요하지 않다.
        greenBT(0, 0);

        System.out.println(flower);
    }


    private static void BFS() {
        // BFS를 돌려야하는데, 주의할 점은 두 배양액이 동시에 만날 떄 꽃을 픠운다는 것이다.
        // 그래서 일반적인 BFS보다는 한단계 한단계 진행하는 BFS가 낫지 않을까..? 하는 생각
        // 이 문제도 저번에 풀었던 확장게임과 비슷할 것 같다.
        // 현재 BFS 해야할 것들은 현재 큐에 넣고, 다음 차례에 BFS 해야할 것들을 저장해놓는 방식.
        // green은 홀수를 넣는다. red는 짝수를 넣는다.
        // 이 때, green은 무조건 빈 공간에만 갈 수 있다.
        // red는 자신의 현재 숫자(2, 4, 6, ...)보다 1 작은 green과 만나야만 꽃을 피울 수 있다.

        greenQ = new LinkedList<>();
        redQ = new LinkedList<>();
        isExtend = true;
        cnt = 0;
        distance = new int[m][n];

        for(int i=0; i<greenPlant.length; i++) {
            greenQ.add(greenPlant[i]);
            int x = greenPlant[i][0];
            int y = greenPlant[i][1];

            distance[x][y] = 1; // 이미 배양액이 심어져있는 곳은 true라고 해놓고 방문하지 못하게!
        }
        for(int i=0; i<redPlant.length; i++) {
            redQ.add(redPlant[i]);
            int x = redPlant[i][0];
            int y = redPlant[i][1];

            distance[x][y] = 2; // 이미 배양액이 심어져있는 곳은 true라고 해놓고 방문하지 못하게!
        }

        while(isExtend){ // 확장을 멈추면 게임이 끝난다.
            isExtend = false;
            greenBFS();
            redBFS();
        }
        if(cnt > flower) flower = cnt;
    }

    private static void redBFS() {
        // 확장할 때마다 isExtend = true 해줘야함.
        // 현재 큐에 있는 것만 빼서 상하좌우로 확장해야하고, 그 이후에는 nextQ에 넣어야함.
        // 상하좌우 확인하고, 만약 distance가 내가 이제 놓을(그러니까 나의 +2) 위치에 distance가 1 작은 놈이 있다면 그 green하고는 동시에 만나게 됨.
        // 그 자리에서 꽃을 피우고, count를 늘려준 후, 해당 위치는 nextQ에 넣지 말아야함.
        // 상하좌우 확인할 때, 호수가 있으면 안되며 위에 명시한 조건 외에는 확장할 수 없음.
        // 또한 꽃을 피운 곳이 있다면, 그 곳의 distance는 -10으로 하자.

        Queue<int[]> nextQ = new LinkedList<>();

        while(!redQ.isEmpty()){
            int[] tmp = redQ.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) continue; // 범위를 넘으면 X
                if(input[xNew][yNew] == 0) continue; // 호수는 X
                if(distance[x][y]+1 == distance[xNew][yNew]){ // 만약 이 곳에서 동시에 만난다면
                    cnt++;
                    distance[xNew][yNew] = -10;
                    continue;
                }
                if(distance[xNew][yNew] != 0) continue; // 이미 방문한 곳 or 동시에 만나지 않는 다른 배양액이 있는 곳이라면 X
                distance[xNew][yNew] = distance[x][y]+2; // 2씩 증가시키기로 했음!
                nextQ.add(new int[]{xNew, yNew});
                isExtend = true;
            }
        }
        redQ = nextQ;
    }

    private static void greenBFS() {
        // 확장할 때마다 isExtend = true 해줘야함.
        // 현재 큐에 있는 것만 빼서 상하좌우로 확장해야하고, 그 이후에는 NextQ에 넣어야함.
        // 상하좌우 확인할 때, red가 있는 곳은 안되며, 호수도 안된다.
        Queue<int[]> nextQ = new LinkedList<>();

        while(!greenQ.isEmpty()){
            int[] tmp = greenQ.poll();
            int x = tmp[0];
            int y = tmp[1];

            if(distance[x][y] == -10) continue; // 만약 이전 redBFS에서 이 위치에 꽃을 피웠다면, 확장하지 말아야함.

            for(int i=0; i<dx.length; i++){
                int xNew = x + dx[i];
                int yNew = y + dy[i];

                if(xNew < 0 || xNew >= m || yNew < 0 || yNew >= n) continue; // 범위를 넘으면 X
                if(input[xNew][yNew] == 0) continue; // 호수는 X
                if(distance[xNew][yNew] != 0) continue; // 이미 방문한 곳 or 다른 배양액이 있는 곳이라면 X
                distance[xNew][yNew] = distance[x][y]+2; // 2씩 증가시키기로 했음!
                nextQ.add(new int[]{xNew, yNew});
                isExtend = true;
            }
        }
        greenQ = nextQ;
    }

    private static void greenBT(int x, int start) {
        if(maxGreen == x){
            // realPlant에 4개가 담겨져 있으니, 이 중에서 G과 R을 어디에 심을건지 정해야한다.
            redBT(0, 0); // 이 메서드는 realPlant에서 R과 B를 분배한 후에, BFS를 호출한다. 이러면 꽃의 수까지 모두 연산 끝.
            return;
        }

        for(int i = start; i<plant.length; i++){
            if(!visitedPlant[i]){
                visitedPlant[i] = true;
                greenPlant[x] = plant[i];
                greenBT( x+1, i+1);
                visitedPlant[i] = false;
            }
        }
    }

    private static void redBT(int x, int start) {
        if(x == maxRed){
            BFS();
            return;
        }

        for(int i=start; i<plant.length; i++){
            if(!visitedPlant[i]){
                visitedPlant[i] = true;
                redPlant[x] = plant[i];
                redBT(x+1, i+1);
                visitedPlant[i] = false;
            }
        }
    }
}