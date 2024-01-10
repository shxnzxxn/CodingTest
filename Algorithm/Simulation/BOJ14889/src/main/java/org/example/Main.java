package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] table;
    static int gap = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        table = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] selectTeam = new int[N/2];
        splitTeam(0, 0, selectTeam);
        System.out.println(gap);
    }

    private static void splitTeam(int start, int x, int[] selectTeam) {
        if(x==N/2){
            int candidateGap = calculateTeamScore(selectTeam);
            gap = Math.min(gap, candidateGap);
            return;
        }

        for(int i=start; i<N; i++){
            selectTeam[x] = i;
            splitTeam(i+1, x+1, selectTeam);
        }
    }

    private static int calculateTeamScore(int[] selectTeam) {
        int[] unselectTeam = createUnselectTeam(selectTeam);
        int scoreA = 0;
        int scoreB = 0; // select는 A팀, unselect는 B팀

        for(int i=0; i<N; i++){
            if(contains(selectTeam, i)){
                for(int j=0; j<selectTeam.length; j++){
                    if(i==selectTeam[j]) continue;
                    scoreA += table[i][selectTeam[j]];
                }
            }else{
                for(int j=0; j<unselectTeam.length; j++){
                    if(i==unselectTeam[j]) continue;
                    scoreB += table[i][unselectTeam[j]];
                }
            }
        }

        return Math.abs(scoreA - scoreB);
    }

    private static int[] createUnselectTeam(int[] selectTeam) {
        int[] unselectTeam = new int[N/2];

        int idx = 0;
        for(int i=0; i<N; i++){
            if(!contains(selectTeam, i)) unselectTeam[idx++] = i;
        }

        return unselectTeam;
    }

    private static boolean contains(int[] selectTeam, int i) {
        for (int i1 : selectTeam) {
            if(i1 == i) return true;
        }

        return false;
    }
}