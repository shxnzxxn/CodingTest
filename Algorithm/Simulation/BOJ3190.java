package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int numOfApples;
    static List<int[]> locationOfApples = new ArrayList<>();
    static int numOfTurn;
    static Deque<int[]> turningInfo = new ArrayDeque<>();  // L(왼쪽) : 0, D(오른쪽) : 1
    static Deque<int[]> snake = new ArrayDeque<>();
    static int[][] map;
    static int[][] moveWay = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int way = 0;
    static int time = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        numOfApples = Integer.parseInt(br.readLine());
        visited = new boolean[numOfApples];

        for(int i=0; i<numOfApples; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            locationOfApples.add(new int[]{x, y});
            map[x][y]++;
        }

        numOfTurn = Integer.parseInt(br.readLine());

        for(int i=0; i<numOfTurn; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int way = st.nextToken().equals("L")? 0 : 1;
            turningInfo.add(new int[]{time, way});
        }

        snake.addFirst(new int[]{0, 0});
//        moveFront();

        while(true){
            moveFront();
            time++;
            if(isTouchedBody() || isTouchWall()) break;
            if(!isInApple()){
                diminishTail();
                clearAppleLocation();
            }
            isTimeToTurn();
        }

        System.out.println(time);
    }

    private static void clearAppleLocation() {
        int[] head = snake.getFirst();

    }

    private static boolean isTouchWall() {
        int[] head = snake.getFirst();
        if(head[0] < 0 || head[0] >= N || head[1] < 0 || head[1] >= N){
            return true;
        }

        return false;
    }

    private static void diminishTail() {
        snake.removeLast();
    }

    private static boolean isInApple() {
        int[] head = snake.getFirst();

//        for (int[] locationOfApple : locationOfApples) {
        for(int i=0; i<locationOfApples.size(); i++){
            int[] locationOfApple = locationOfApples.get(i);
            if(locationOfApple[0] == head[0] && locationOfApple[1] == head[1] && !visited[i]){
                visited[i] = true;
                return true;
            }
        }

        return false;
    }

    private static void isTimeToTurn() {
        while(!turningInfo.isEmpty()){
            int[] turnInfo = turningInfo.poll();
            if(turnInfo[0] == time && turnInfo[1] == 0){
                way--;
                if(way < 0) way = 3;
                break;
            }else if(turnInfo[0] == time && turnInfo[1] == 1){
                way++;
                if(way > 3) way = 0;
                break;
            }else if(turnInfo[0] > time){
                turningInfo.addFirst(turnInfo);
                return;
            }
        }

        for (int[] turnInfo : turningInfo) {
            if(turnInfo[0] == time && turnInfo[1] == 0){
                way--;
                if(way < 0) way = 3;
                break;
            }else if(turnInfo[0] == time && turnInfo[1] == 1){
                way++;
                if(way > 3) way = 0;
                break;
            }else if(turnInfo[0] < time){
                turningInfo.remove(turnInfo);
            }else if(turnInfo[0] > time){
                return;
            }
        }
    }

    private static void moveFront() {
        int[] head = snake.getFirst();
        int[] newHead = new int[]{head[0]+moveWay[way][0], head[1]+moveWay[way][1]};
        snake.addFirst(newHead);
    }

    private static boolean isTouchedBody() {
        // 머리가 몸에 닿거나 벽에 닿으면 true 반환
        if(snake.size() < 4){
            return false;
        }

        Iterator<int[]> iterator = snake.iterator();
        int[] head = iterator.next();
        while(iterator.hasNext()){
            int[] body = iterator.next();
            if(head[0] == body[0] && head[1] == body[1]){
                return true;
            }
        }

        return false;
    }
}