package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] arr;
    static int score = 0;
    static int[][] realArr;
    static int[] I = new int[]{1, 4};
    static int[] square = new int[]{2, 2};
    static int[] L = new int[]{3, 2};
    static int[] H = new int[]{3, 2};
    static int[] T = new int[]{2, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        realArr = new int[M][N];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                realArr[i][j] = arr[i][j];
            }
        }

        // ㅡ 점수 찾기
        for (int i = 0; i < 2; i++) {
            checkI();
            spinArr();
        }
        // 네모 점수 찾기
        checkSquare();

        // ㄴ 점수 찾기
        for(int i=0; i<2; i++){
            for(int j=0; j<4; j++){
                checkL();
                spinArr();
            }
            flipArr();
        }

        // ㄱㄴ 점수 찾기
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                checkH();
                spinArr();
            }
            flipArr();
        }

        // ㅗ 점수 찾기
        for(int i=0; i<4; i++){
            checkT();
            spinArr();
        }

        System.out.println(score);
    }

    private static void flipArr() {

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length/2; j++){
                int tmp = arr[i][arr[i].length-1-j];
                arr[i][arr[i].length-1-j] = arr[i][j];
                arr[i][j] = tmp;
            }
        }
    }

    private static void spinArr() {
        int[][] arrNew = new int[arr[0].length][arr.length];

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                arrNew[j][arrNew[0].length-i-1] = arr[i][j];
            }
        }

        arr = arrNew;
    }

    private static void checkI() {
        for(int i=0; i<=arr.length-I[0]; i++){
            for(int j=0; j<=arr[0].length-I[1]; j++){
                score = Math.max(score, arr[i][j]+arr[i][j+1]+arr[i][j+2]+arr[i][j+3]);
            }
        }
    }

    private static void checkSquare() {
        for(int i=0; i<=arr.length-square[0]; i++){
            for(int j=0; j<=arr[0].length-square[1]; j++){
                score = Math.max(score, arr[i][j]+arr[i][j+1]+arr[i+1][j]+arr[i+1][j+1]);
            }
        }
    }

    private static void checkL() {
        for(int i=0; i<=arr.length-L[0]; i++){
            for(int j=0; j<=arr[0].length-L[1]; j++){
                score = Math.max(score, arr[i][j]+arr[i+1][j]+arr[i+2][j]+arr[i+2][j+1]);
            }
        }
    }

    private static void checkH() {
        for(int i=0; i<=arr.length-H[0]; i++){
            for(int j=0; j<=arr[0].length-H[1]; j++){
                score = Math.max(score, arr[i][j]+arr[i+1][j]+arr[i+1][j+1]+arr[i+2][j+1]);
            }
        }
    }

    private static void checkT() {
        for(int i=0; i<=arr.length-T[0]; i++){
            for(int j=0; j<=arr[0].length-T[1]; j++){
                score = Math.max(score, arr[i][j]+arr[i][j+1]+arr[i][j+2]+arr[i+1][j+1]);
            }
        }
    }
}