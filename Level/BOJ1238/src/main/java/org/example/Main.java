package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int X;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken())-1;

        map = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = Integer.MAX_VALUE;
            }
            map[i][i] = 0;
        }

        int start, end, time;
        for(int i=0; i<M; i++){
            st=  new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken())-1;
            end = Integer.parseInt(st.nextToken())-1;
            time = Integer.parseInt(st.nextToken());

            map[start][end] = time;
        }

        for(int k=0; k<N; k++){
            // i를 거쳐서 갈 수 있는 경로들이 최소가 되도록 update
            for(int i=0; i<N; i++){
                if(i==k) continue; // 자기 자신은 자기 자신을 거쳐갈 수 없음.

                for(int j=0; j<N; j++){
                    if(i==j || j==k) continue; // 자기 자신을 가는 경로는 0임.
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE) continue; // overFlow 방지

                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int maxTime = -1;
        int usedTime;
        for(int i=0; i<N; i++){
            if(i == X) continue; // 자기 자신은 이동하지 않으므로 시간을 소요하지 않음.

            usedTime = map[i][X] + map[X][i];
            maxTime = Math.max(maxTime, usedTime);
        }

        System.out.println(maxTime);
    }
}