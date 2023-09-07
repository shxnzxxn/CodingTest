import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static int w;
    static int l;
    static Queue<Integer> truck;
    static int[] bridge;
    static int sumTruck = 0;
    static int cntTruck = 0;
    static int time = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        w = sc.nextInt();
        l = sc.nextInt();

        sc.nextLine();
        truck = new LinkedList<>();
        bridge = new int[w];
        for(int i=0; i<n; i++) truck.add(sc.nextInt());

        while(true){
            time++;
            // 다리에 있는 트럭을 전진시킨다.
            forwardTruck();

            if(canAddTruck()){ // 만약 다리에 트럭이 더 들어갈 수 있다면
                bridge[w-1] = truck.poll();
                sumTruck += bridge[w-1];
                cntTruck++;
            }
            if(truck.isEmpty() && isEmptyBridge()) break; // 중단 조건
        }

        System.out.println(time);
    }

    private static void forwardTruck() {
        if(bridge[0] != 0) {
            // 만약 전진하면서, 트럭이 나가게 된다면 다리 위에 있는 트럭의 수와, 다리 위에 있는 트럭의 무게의 합을 갱신해줘야한다.
            cntTruck--;
            sumTruck -= bridge[0];
        }

        for(int i=1; i<bridge.length; i++) bridge[i-1] = bridge[i];
        bridge[bridge.length-1] = 0; // 마지막 자리가 비게 된다.
    }

    private static boolean isEmptyBridge() {
        for(int i=0; i< bridge.length; i++) if(bridge[i] != 0) return false;
        return true;
    }

    private static boolean canAddTruck() {
        // bridge에 트럭이 더이상 들어갈 수 없거나, 현재 다리에 있는 트럭의 무게 + 들어올 트럭의 무게가 다리의 수용량보다 크면 false
        if(truck.isEmpty()) return false;
        if(cntTruck >= w || sumTruck + truck.peek() > l) return false;
        return true;
    }
}