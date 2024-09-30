import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 시간복잡도 : 20*20*5*3*4^5 = 4,096,000
    // 20*20은 최대 도화지의 사이즈이고, 5는 총 5번 연산을 실행하여 옮겨야하니까!
    // 옮긴 후에는 다시 합칠 숫자들을 파악하기 위해 위와 같은 연산량을 추가해줌. 그 다음에 또 미는 작업 그러니까 *3
    // 4^5는 4가지의 연산 종류 중 중복을 허용하여 선택해 5번을 해야하니까!
    static int n;
    static int[][] input;
    static int[][] way = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] toDo = new int[5][2]; // 5번의 수행을 뭘 할건지 알려주는 배열
    static int maxNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        input = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if(input[i][j] > maxNum) maxNum = input[i][j];
            }
        }

        backTracking(0);

//        swipeRight(input);
//        for(int i=0; i<n; i++) System.out.println(Arrays.toString(input[i]));
//        System.out.println("-----------------------------");
//        checkCrashRight(input);
//        for(int i=0; i<n; i++) System.out.println(Arrays.toString(input[i]));
//        System.out.println("-----------------------------");
//        swipeRight(input);
//        for(int i=0; i<n; i++) System.out.println(Arrays.toString(input[i]));
        System.out.println(maxNum);

    }

    private static void backTracking(int x) { // toDo : 5번의 연산을 정해주는 함수
        if(x == 5){
            int[][] copyToDo = new int[5][2];
            for (int i = 0; i < toDo.length; i++) copyToDo[i] = toDo[i].clone();
            startGame(copyToDo);
            return;
        }

        for(int i=0; i<way.length; i++){
            toDo[x] = way[i];
            backTracking(x+1);
        }
    }

    private static void startGame(int[][] toDo) { // toDo 배열에 담긴 순서대로 게임을 진행시키자
        int[][] board = new int[n][n];
        for(int i=0; i<input.length; i++) board[i] = input[i].clone();

        for(int i=0; i< toDo.length; i++){
            if(toDo[i][0] == -1){ // 이 경우는 왼쪽으로 밀었을 때임.
                swipeLeft(board);
                checkCrashLeft(board); //
                swipeLeft(board);
            }else if(toDo[i][0] == 1){ // 이 경우는 오른쪽으로 밀었을 때
                swipeRight(board);
                checkCrashRight(board);
                swipeRight(board);
            }else if(toDo[i][1] == -1){ // 이 경우는 위쪽으로 밀었을 때
                swipeUp(board);
                checkCrashUp(board);
                swipeUp(board);
            }else{ // 이 경우는 아래로 밀었을 때
                swipeDown(board);
                checkCrashDown(board);
                swipeDown(board);
            }
        }
    }

    private static void checkCrashLeft(int[][] board) {
        for(int i=0; i<board.length; i++){
            int before = board[i][0];
            for(int j=1; j<board[i].length; j++){
                if(before == board[i][j]){
                    board[i][j-1] *=2;
                    if(board[i][j-1] > maxNum) maxNum = board[i][j-1];
                    board[i][j] = 0;
                    j++;
                }
                if(j<n) before = board[i][j];
            }
        }
    }

    private static void checkCrashRight(int[][] board) {
        for(int i=0; i<board.length; i++){
            int before = board[i][board[i].length-1];
            for(int j=board[i].length-2; j>= 0; j--){
                if(before == board[i][j]){
                    board[i][j+1] *=2;
                    if(board[i][j+1] > maxNum) maxNum = board[i][j+1];
                    board[i][j] = 0;
                    j--;
                }
                if(j>= 0) before = board[i][j];
            }
        }
    }

    private static void checkCrashDown(int[][] board) {
        for(int i=0; i<board[0].length; i++){
            int before = board[board.length-1][i];
            for(int j=board.length-2; j>= 0; j--){
                if(before == board[j][i]){
                    board[j+1][i] *= 2;
                    if(board[j+1][i] > maxNum) maxNum = board[j+1][i];
                    board[j][i] = 0;
                    j--;
                }
                if(j>=0) before = board[j][i];
            }
        }
    }

    private static void checkCrashUp(int[][] board) {
        for(int i=0; i<board[0].length; i++){
            int before = board[0][i];
            for(int j=1; j<board.length; j++){
                if(before == board[j][i]){
                    board[j-1][i] *= 2;
                    if(board[j-1][i] > maxNum) maxNum = board[j-1][i];
                    board[j][i] = 0;
                    j++;
                }
                if(j<n) before = board[j][i];
            }
        }
    }


    private static void swipeLeft(int[][] board) {
        for(int i=0; i<board.length; i++){
            int start = 0;
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] != 0){ // 빈 공간이 아니라, 해당 위치에 숫자가 있을 때, start로 옮겨야지!
                    board[i][start] = board[i][j];
                    if(start != j) board[i][j] = 0;
                    start++;
                }
            }
        }
    }

    private static void swipeRight(int[][] board) {
        for(int i=0; i<board.length; i++){
            int start = board[i].length-1;
            for(int j=board[i].length-1; j>= 0; j--){
                if(board[i][j] != 0){ // 빈 공간이 아니라, 해당 위치에 숫자가 있을 때, start로 옮겨야지!
                    board[i][start] = board[i][j];
                    if(start != j) board[i][j] = 0;
                    start--;
                }
            }
        }
    }

    private static void swipeUp(int[][] board) {
        for(int i=0; i<board[0].length; i++){
            int start = 0;
            for(int j=0; j<board.length; j++){
                if(board[j][i] != 0){ // 빈 공간이 아니라, 해당 위치에 숫자가 있을 때, start로 옮겨야지!
                    board[start][i] = board[j][i];
                    if(start != j) board[j][i] = 0;
                    start++;
                }
            }
        }
    }

    private static void swipeDown(int[][] board) {
        for(int i=0; i<board[0].length; i++){
            int start = board.length-1;
            for(int j= board.length-1; j>= 0; j--){
                if(board[j][i] != 0){ // 빈 공간이 아니라, 해당 위치에 숫자가 있을 때, start로 옮겨야지!
                    board[start][i] = board[j][i];
                    if(start != j) board[j][i] = 0;
                    start--;
                }
            }
        }
    }
}