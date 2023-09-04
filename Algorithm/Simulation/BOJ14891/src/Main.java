import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer>[] screwWheel = new ArrayList[4];
    static int[] turnWay;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<4; i++){
            String[] tmp = br.readLine().split("");
            screwWheel[i] = new ArrayList<>();
            for(int j=0; j<8; j++) screwWheel[i].add(Integer.parseInt(tmp[j]));
        }

        int iter = Integer.parseInt(br.readLine());

        for(int k=0; k<iter; k++){
            turnWay = new int[4];
            String[] tmp = br.readLine().split(" ");
            int obj = Integer.parseInt(tmp[0])-1;
            int way = Integer.parseInt(tmp[1]);

            determineTurnWay(obj, way); // 각 톱니바퀴의 회전 방향을 결정해줌.
            screwTurn();
        }

        int score = 0;
        for(int i=0; i<4; i++){
            int x = screwWheel[i].get(0);
            if(x==1) score += Math.pow(2, i);
        }
        System.out.println(score);
    }

    private static void screwTurn() {
        for(int i=0; i<4; i++){
            ArrayList<Integer> list = screwWheel[i];

            if(turnWay[i] == -1){ // 반시계 방향은 첫번째 원소를 빼서 마지막에 집어넣으면 된다.
                list.add(list.get(0));
                list.remove(0);
            }else if(turnWay[i] == 1){ // 시계 방향은 마지막 원소를 가장 앞에 삽입.
                list.add(0, list.get(7));
                list.remove(8); // 윗 행에서 이미 리스트에 원소를 삽입했으므로, 사이즈가 9이므로 마지막 원소인 8을 삭제.
            }
        }
    }

    private static void determineTurnWay(int obj, int way) {
        turnWay[obj] = way;
        int leftWay = way;
        int left = screwWheel[obj].get(6);
        for(int i = obj-1; i>=0; i--){
            int y = screwWheel[i].get(2);

            if(leftWay == 0) {
                turnWay[i] = 0;
                continue;
            }

            if(left == y) turnWay[i] = 0;
            else turnWay[i] = leftWay*-1;

            left = screwWheel[i].get(6);
            leftWay = turnWay[i];
        }

        int right = screwWheel[obj].get(2);
        int rightWay = way;
        for(int i = obj+1; i<4; i++){
            int y = screwWheel[i].get(6);

            if(rightWay == 0) {
                turnWay[i] = 0;
                continue;
            }

            if(right == y) turnWay[i] = 0;
            else turnWay[i] = rightWay*-1;

            right = screwWheel[i].get(2);
            rightWay = turnWay[i];
        }
    }
}