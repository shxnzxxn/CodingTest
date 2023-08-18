import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int M = 7;
    static int N = 25;
    static String[] input = new String[N]; // 양 옆은 +- 1로, 위아래는 +-5로 확인하면 된다.
    static int[][] res = new int[M][2]; // { [0 : 이다솜, 1:임서연] , 인덱스}
    static boolean[] visited = new boolean[N];
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;
    static int[] fourWay = {-1, 1, -5, 5};
    static LinkedList<Integer> leftSide = new LinkedList<>();
    static LinkedList<Integer> rightSide = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tmpCnt = 0;
        for(int i=0; i<5; i++){
            String[] tmp = sc.nextLine().split("");
            for(int j=0; j<5; j++) input[tmpCnt++] = tmp[j];
        }

        for(int i=0; i<25; i+=5) leftSide.add(i); // 2차원을 1차원으로 바꿨으니, 제일 왼쪽과 제일 오른쪽은 인접하다고 판단하면 안된다.
        for(int i=4; i<25; i+=5) rightSide.add(i); // 따라서 양쪽 벽면 리스트를 줘서, 이 두개는 교차하지 못하게 한다.

        BackTracking(0, 0);
        System.out.println(cnt);
    }

    private static void BackTracking(int x, int start) {
        if(x == M){
            if(check(res)) cnt++;
            return;
        }

        for(int i=start; i<N; i++){
            if(!visited[i]){ // 미리 되는지 안되는지 확인해보자.
                res[x] = new int[]{input[i].equals("S")? 0:1, i}; // 이다솜 : 0, 임서연 : 1
                visited[i] = true;
                BackTracking(x+1, i+1);
                visited[i] = false;
            }
        }
    }

    private static boolean check(int[][] res) {
        // 7명이 채워지기 전에 확인할 때
        // 가로나 세로로 인접하지 않는다면 false
        // 인접하는지 찾기 위해서, 편하게 인덱스 전용 LinkedList를 만들자.
        LinkedList<Integer> idxList = new LinkedList<>();
        for(int i=0; i<res.length; i++) idxList.add(res[i][1]);

        Queue<Integer> q = new LinkedList<>();
        q.add(idxList.get(0));
        while(!q.isEmpty()){
            int idx = q.poll();
            idxList.remove((Object) idx);
            for(int i=0; i<fourWay.length; i++){
                int idxNew = idx + fourWay[i];
                if(idxNew >= 0 && idxNew < 25){
                    if(leftSide.contains(idx) && rightSide.contains(idxNew)) continue; // 양쪽 벽에서는 교차하지 못한다.
                    if(leftSide.contains(idxNew) && rightSide.contains(idx)) continue; // 2차원을 1차원으로 바꾸었으므로, 이 경우를 다뤄줘야한다.
                    if(idxList.contains(idxNew)){
                        q.add(idxNew);
                    }
                }
            }
        }

        if(!idxList.isEmpty()) return false;

        // 이다솜파가 4명 이상 있는지
        int cnt = 0;
        for (int[] tmp : res) if(tmp[0] == 0) cnt++;
        if(cnt < 4) return false;

        return true;
    }
}