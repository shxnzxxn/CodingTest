import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] fourWay = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        String[][] input = new String[M][N];
        int[][] fireTime = new int[M][N];
        int[][] personTime = new int[M][N];
        int[] person = new int[2];
        LinkedList<int[]> fire = new LinkedList<>();
        for(int i=0; i<M; i++){
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                input[i][j] = tmp[j];
                if(input[i][j].equals("J")) person = new int[]{i, j};
                else if(input[i][j].equals("F")) fire.add(new int[]{i, j});

                if(input[i][j].equals("#")) {
                    fireTime[i][j] = -2;
                    personTime[i][j] = -2;
                }
                else{
                    fireTime[i][j] = -1;
                    personTime[i][j] = -1;
                }
            }
        }

        // 불이 길을 태우는 시간을 넣습니다.
        BFS_fire(fireTime, fire);

        int res = BFS_person(fireTime, personTime, person);
        if(res == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(res);

//        for(int i=0; i<M; i++){
//            System.out.println(Arrays.toString(fireTime[i]));
//        }
//        System.out.println("--------------------------------------------");
//        for(int i=0; i<M; i++){
//            System.out.println(Arrays.toString(personTime[i]));
//        }
    }
    static int BFS_person(int[][] fireTime, int[][] personTime, int[] person){
        int personX = person[0];
        int personY = person[1];
        Queue<int[]> q = new LinkedList<>();
        personTime[personX][personY] = 0;
        q.add(person);

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for (int i = 0; i < fourWay.length; i++) {
                int newX = x + fourWay[i][0];
                int newY = y + fourWay[i][1];
                if (newX >= 0 && newX < M && newY >= 0 && newY < N) {
                    if (((personTime[x][y] + 1 < fireTime[newX][newY]) || (fireTime[newX][newY] == -1))
                            && personTime[newX][newY] == -1) {
                        personTime[newX][newY] = personTime[x][y] + 1;
                        q.add(new int[]{newX, newY});
                    }
                } else {
                    return personTime[x][y]+1;
                }
            }
        }
        return -1;
    }

    static void BFS_fire(int[][] fireTime, LinkedList<int[]> fireList){
        Queue<int[]> q = new LinkedList<>();

        for(int[] fire : fireList) {
            int fireX = fire[0];
            int fireY = fire[1];
            fireTime[fireX][fireY] = 0;
            q.add(fire);
        }

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for(int i=0; i< fourWay.length; i++) {
                int newX = x + fourWay[i][0];
                int newY = y + fourWay[i][1];
                if (newX >= 0 && newX < M && newY >= 0 && newY < N) {
                    if (fireTime[newX][newY] == -1) {
                        fireTime[newX][newY] = fireTime[x][y] + 1;
                        q.add(new int[]{newX, newY});
                    }
                }
            }
        }

    }
}