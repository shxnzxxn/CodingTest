package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static String[][] map;
    static int[] R;
    static int[] B;
    static int minTime = 12;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new String[M][N];

        for(int i=0; i<M; i++){
            String[] tmp = br.readLine().split("");
            for(int j=0; j<N; j++){
                if(tmp[j].equals("R")){
                    R = new int[]{i, j};
                    map[i][j] = ".";
                }else if(tmp[j].equals("B")){
                    B = new int[]{i, j};
                    map[i][j] = ".";
                }else {
                    map[i][j] = tmp[j];
                }
            }
        }

        startGame(R.clone(), B.clone(), 0, 0, 0, false, false);

        minTime = minTime == 12 ? -1: minTime;
        System.out.println(minTime);
    }

    /**
     * @param direction : 0:none,   1:up,   2:down,    3:left,    4:right
     * @param i         : iteration
     * @param rGoal     : if r in goal
     * @param bGoal     : if b in goal
     */
    private static void startGame(int[] r, int[] b, int direction, int i, int time, boolean rGoal, boolean bGoal) {
        if(i == 11) return;
//        if(direction != 0 && r[0] == R[0] && r[1] == R[1]) return;
        if(direction == 1){
            // 더 위에 있는 구슬을 먼저 움직인다.
            if(r[0] < b[0]){ // R이 더 위에 있으므로 R부터 움직인다.
                while(true){
                    if(!map[r[0]-1][r[1]].equals("#")) {
                        r[0]--;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            r[0] = -1;
                            r[1] = -1;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[b[0]-1][b[1]].equals("#") && !((b[0]-1 == r[0]) && (b[1] == r[1]))){
                        b[0]--;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }

                    }
                    else break;
                }
            }else{
                while(true){
                    if(!map[b[0]-1][b[1]].equals("#")) {
                        b[0]--;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[r[0]-1][r[1]].equals("#") && !((r[0]-1 == b[0]) && (r[1] == b[1]))){
                        r[0]--;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            break;
                        }

                    }
                    else break;
                }
            }
        }else if(direction == 2){
            if(r[0] < b[0]){
                while(true){
                    if(!map[b[0]+1][b[1]].equals("#")) {
                        b[0]++;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[r[0]+1][r[1]].equals("#") && !((r[0]+1 == b[0]) && (r[1] == b[1]))){
                        r[0]++;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }else{
                while(true){
                    if(!map[r[0]+1][r[1]].equals("#")) {
                        r[0]++;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            r[0] = -1;
                            r[1] = -1;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[b[0]+1][b[1]].equals("#") && !((b[0]+1 == r[0]) && (b[1] == r[1]))){
                        b[0]++;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }
        }else if(direction == 3){
            if(r[1] > b[1]){
                while(true){
                    if(!map[b[0]][b[1]-1].equals("#")) {
                        b[1]--;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[r[0]][r[1]-1].equals("#") && !((r[0] == b[0]) && (r[1]-1 == b[1]))){
                        r[1]--;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }else{
                while(true){
                    if(!map[r[0]][r[1]-1].equals("#")) {
                        r[1]--;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            r[0] = -1;
                            r[1] = -1;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[b[0]][b[1]-1].equals("#") && !((b[0] == r[0]) && (b[1]-1 == r[1]))){
                        b[1]--;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }
        }else if(direction == 4){
            if(r[1] < b[1]){
                while(true){
                    if(!map[b[0]][b[1]+1].equals("#")) {
                        b[1]++;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[r[0]][r[1]+1].equals("#") && !((r[0] == b[0]) && (r[1]+1 == b[1]))){
                        r[1]++;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }else{
                while(true){
                    if(!map[r[0]][r[1]+1].equals("#")) {
                        r[1]++;

                        if(map[r[0]][r[1]].equals("O")) {
                            rGoal = true;
                            r[0] = -1;
                            r[1] = -1;
                            break;
                        }
                    } else break;
                }

                while(true){
                    if(!map[b[0]][b[1]+1].equals("#") && !((b[0] == r[0]) && (b[1]+1 == r[1]))){
                        b[1]++;

                        if(map[b[0]][b[1]].equals("O")) {
                            bGoal = true;
                            break;
                        }
                    }
                    else break;
                }
            }
        }

        if(bGoal) return;
        else if(rGoal){
            minTime = Math.min(minTime, time);
            return;
        }

        List<Integer> direct = setDirection(r, b, direction);

         for (Integer j : direct) {
            startGame(r.clone(), b.clone(), j, i+1, time+1, false, false);
        }
    }

    static int[] xWay = new int[]{-1, 1, 0, 0};
    static int[] yWay = new int[]{0, 0, -1, 1};
    private static List<Integer> setDirection(int[] r, int[] b, int direction) {
        List<Integer> res = new ArrayList<>();
        for(int i=1; i<=4; i++){
            int x = r[0]+xWay[i-1];
            int y = r[1]+yWay[i-1];

            if(!(map[x][y].equals("#"))){
                if(canMoveR(r, b, i)) res.add(i);
            }
        }

        for(int i=1; i<=4; i++){
            int x = b[0]+xWay[i-1];
            int y = b[1]+yWay[i-1];

            if(!(map[x][y].equals("#") || direction == flip(i))){
                if(canMoveB(r, b, i) && !res.contains(i)) res.add(i);
            }
        }

        return res;
    }

    private static boolean canMoveB(int[] R, int[] B, int direction) {
        if(direction == 1 && B[0]-1 == R[0] && B[1] == R[1]){
            if(!map[R[0]-1][R[1]].equals("#")) return true;
            else return false;
        }else if(direction == 2 && B[0] + 1 == R[0] && B[1] == R[1]){
            if(!map[R[0]+1][R[1]].equals("#")) return true;
            else return false;
        }else if(direction == 3 && B[1]-1 == R[1] && B[0] == R[0]){
            if(!map[R[0]][R[1]-1].equals("#")) return true;
            else return false;
        }else if(direction == 4 && B[1]+1 == R[1] && B[0] == R[0]){
            if(!map[R[0]][R[1]+1].equals("#")) return true;
            else return false;
        }

        return true;
    }

    private static boolean canMoveR(int[] R, int[] B, int direction) {
        if(direction == 1 && R[0]-1 == B[0] && R[1] == B[1]){
            if(!map[B[0]-1][B[1]].equals("#")) return true;
            else return false;
        }else if(direction == 2 && R[0] + 1 == B[0] && R[1] == B[1]){
            if(!map[B[0]+1][B[1]].equals("#")) return true;
            else return false;
        }else if(direction == 3 && R[1]-1 == B[1] && R[0] == B[0]){
            if(!map[B[0]][B[1]-1].equals("#")) return true;
            else return false;
        }else if(direction == 4 && R[1]+1 == B[1] && R[0] == B[0]){
            if(!map[B[0]][B[1]+1].equals("#")) return true;
            else return false;
        }

        return true;
    }

    private static int flip(int i) {
        if(i == 1) return 2;
        else if(i==2) return 1;
        else if(i==3) return 4;
        else return 3;
    }
}