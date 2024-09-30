package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[] goal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n][n];
        goal = new int[m];
        
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i==j){
                    // 같은 위치의 도시는 갈 수 있는 것.
                    map[i][j] = 1;
                }
            }
        }

        findMap();

        String[] stringGoal = br.readLine().split(" ");
        for(int i=0; i< stringGoal.length; i++){
            goal[i] = Integer.parseInt(stringGoal[i])-1;
        }
        
        String res = canGo() ? "YES" : "NO";
        System.out.println(res);
    }

    private static boolean canGo() {
        for(int i=0; i< goal.length-1; i++){
            if(map[goal[i]][goal[i+1]] == 0) return false;
        }

        return true;
    }

    private static void findMap() {
        for(int k=0; k<n; k++){
            // k번째를 거쳐간다면 갈 수 있는 경우를 찾아야한다.
            for(int i=0; i<n; i++){
                // i번째에서 j번째까지 갈텐데, i번째를 거치는 경우는 고려하지 않아도 된다.
                if(i == k) continue;
                for(int j=0; j<n; j++){
                    if(map[i][j] == 0){
                        // 직접 갈 수 없지만
                        if(map[i][k] == 1 && map[k][j] == 1){
                            // i부터 j까지 k를 거쳐서 갈 수 있다면, 갱신해준다.
                            map[i][j] = 1;
                            map[j][i] = 1;
                        }
                    }
                }
            }
        }
    }
}