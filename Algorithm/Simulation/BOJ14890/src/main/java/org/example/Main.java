package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int L;
    static int[][] map;
    static int res = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            String[] tmp = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }


        for(int i=0; i<N; i++) {
            if(canPass(map[i])){
                res++;
            }
        }
        int[] load = new int[N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                load[j] = map[j][i];
            }
            if(canPass(load)){
                res++;
            }
        }

        System.out.println(res);
    }

    private static boolean canPass(int[] load) {
        int pastLocate = 0;
        int pastHeight = load[0];
        int nowHeight;

        int flatHeight = 1; // 평평한 곳의 길이. 경사로를 둘 수 있는지 없는지를 판단하는데 쓰임.

        for(int nowLocate = 1; nowLocate < load.length; nowLocate++){
            // 이제 nowLocate를 가야하는데, 갈 수 있을까? 를 검사해야한다.
            nowHeight = load[nowLocate];

            if(nowHeight == pastHeight) {
                flatHeight++;
                continue; // 같으면 지나갈 수 있음.
            }
            int gap = nowHeight - pastHeight;
            if(Math.abs(gap) != 1) return false; // 높이 차이가 1이 아니면 경사로를 설정할 수 없음.

            // 높이 차이가 1일 때는, 올라가야하는지, 내려가야하는지를 확인해야한다.
            if(pastHeight < nowHeight){ // 올라가는 경우
                if(flatHeight >= L){ // 경사로를 둘 수 있는 경우
                    pastHeight = nowHeight;
                    pastLocate = nowLocate;
                    flatHeight = 1; // flatHeight 초기화
                }else return false; // 경사로를 둘 수 없으면 해당 경로를 지나갈 수 없다.
            }else{ // 내려가야하는 경우
                // 이제 발 디딜 곳에 flat이 얼만큼인지 계산한다.
                int nextHeight = check(load, nowLocate);
                if(nextHeight >= L){ // 내려가는 곳에 경사로를 둘 수 있다면
                    pastHeight = nowHeight;
                    pastLocate = nowLocate;
                    flatHeight = -1*L+1; // flatHeight 초기화(내려갈 때는 다시 올라가는 경우 경사로가 겹칠 경우를 대비해야하므로!
                }else return false;
            }
        }

        return true;
    }

    private static int check(int[] load, int nowLocate) {
        int res = 1;
        int height = load[nowLocate];
        for(int i=nowLocate+1; i< load.length; i++){
            if(height == load[i]) res++;
            else return res;
        }

        return res;
    }
}