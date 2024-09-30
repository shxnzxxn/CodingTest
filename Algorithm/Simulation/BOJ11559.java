import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // BFS로 하나씩 확인해봐야할 것 같다.
    // 시간 복잡도 12*6*3*18 + 입력(12*6) = 3960
    // 모든 공간의 개수를 세서 파악하는 법 : 12*6
    // 최대 18번 터질 수 있다. : *18
    // 터트릴 때의 연산 : +(12*6)
    // 터진 후에 중력의 영향으로 밑으로 내리는 연산 : +(12*6)
    static String[][] input = new String[12][6];
    static boolean[][] visited;
    static int[][] areaCnt = new int[12][6];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<input.length; i++) input[i] = sc.nextLine().split("");

        int cnt = 0;
        while(true) {
            extracted(); // 지금여기까지 하면, 각 위치에 areaCnt까지 채워진 상태.
            // 이제 areaCnt가 4 이상인 곳을 찾아서 그곳을 remove하면 된다.
            boolean overFour = findOverFour();
            if(overFour) cnt++;
            else break;
            // 이제 중력의 영향을 받아 아래로 떨어트리는 함수를 구현하자.
            fallDown();
        }
        System.out.println(cnt);
    }

    private static void fallDown() { // 밑에서 위로, 왼쪽부터 오른쪽으로 가면서 밑으로 떨궈주자!!
        // 시간복잡도 : 최대 6*12
        for(int i=0; i<input[0].length; i++){
            int j =12;
            int start = 11;
            int x;

            while(j>0 && start > 0) {
                x = areaCnt[--j][i];
                if(x!=0) {
                    input[start--][i] = input[j][i];
                    if(start+1 != j)input[j][i] = ".";
                }
            }
        }
    }

    private static boolean findOverFour() { // 처음부터 끝까지 areaCnt를 돌면서 4 이상이라면, remove함수 호출하자.
        // 시간복잡도 : 6*12
        boolean crash = false;
        for(int i=0; i< areaCnt.length; i++){
            for(int j=0; j<areaCnt[i].length; j++){
                if(areaCnt[i][j] >= 4){
                    remove(i, j);
                    crash = true;
                }
            }
        }
        return crash;
    }

    private static void remove(int x, int y) { // (x, y)부터 연관된 모든 애들의 input을 .으로 만들고, areaCnt도 0으로 만들자.
        // 시간복잡도 : 6*12
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        boolean[][] visited = new boolean[12][6];
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            input[poll[0]][poll[1]] = ".";
            areaCnt[poll[0]][poll[1]] = 0;

            for(int i=0; i<dx.length; i++){
                int xNew = poll[0] + dx[i];
                int yNew = poll[1] + dy[i];

                if(xNew < 0 || xNew >= 12 || yNew < 0 || yNew >= 6) continue;
                if(visited[xNew][yNew]) continue;
                if(input[x][y].equals(input[xNew][yNew])){
                    visited[xNew][yNew] = true;
                    q.add(new int[]{xNew, yNew});
                }
            }
        }
    }

    private static void extracted() { // 처음부터 끝까지 순회하면서, 알파벳 위치에서 cnt를 시키거나 하는 역할을 수행.
        visited = new boolean[12][6];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if(!input[i][j].equals(".") && !visited[i][j]){
                    BFS(i, j);
                }
            }
        }
    }

    private static void BFS(int x, int y) { // (x, y)에서 시작해서, 종류가 같은 것들의 개수를 count하고, areaCnt에 등록.
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;


        while(!q.isEmpty()){
            int[] tmp = q.poll();

            for(int i=0; i<dx.length; i++){
                int xNew = tmp[0] + dx[i];
                int yNew = tmp[1] + dy[i];

                if(xNew < 0 || xNew >= 12 || yNew < 0 || yNew >= 6) continue;
                if(visited[xNew][yNew]) continue;
                if(input[x][y].equals(input[xNew][yNew])){
                    visited[xNew][yNew] = true;
                    q.add(new int[]{xNew, yNew});
                    cnt++;
                }
            }
        }
        fillCnt(x, y, cnt);
    }

    private static void fillCnt(int x, int y, int cnt) { // areaCnt에 (x, y)부터 엮여있는 모든 곳에다가 cnt로 부여해줌.
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        boolean[][] visited = new boolean[12][6];
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            areaCnt[tmp[0]][tmp[1]] = cnt;

            for(int i=0; i<dx.length; i++){
                int xNew = tmp[0] + dx[i];
                int yNew = tmp[1] + dy[i];

                if(xNew < 0 || xNew >= 12 || yNew < 0 || yNew >= 6) continue;
                if(visited[xNew][yNew]) continue;
                if(input[x][y].equals(input[xNew][yNew])){
                    visited[xNew][yNew] = true;
                    q.add(new int[]{xNew, yNew});
                }
            }
        }
    }
}