import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        LinkedList<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) q.add(i);
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < m; i++) list.add(sc.nextInt());

        int cnt = 0;
        for (int x : list) {
            if (q.get(0) != x) {
                // 2번과 3번의 연산 수행
                // 적게 걸리는 횟수를 cnt에 반영하고,
                // pop을 하면 x가 튀어나오게 세팅하기
                int a = cntWay2(q, x);
                int b = cntWay3(q, x);
                if(a<=b){ // 2번 방법을 사용
                    cnt+=a;
                    doWay2(q, x);
                }else{ // 3번 방법을 사용
                    cnt +=b;
                    doWay3(q, x);
                }
            }
            q.poll();
        }
        System.out.println(cnt);
    }

    static int cntWay2(LinkedList<Integer> q_origin, int x){ // 앞에있는 애들 뒤로 옮기면서, x가 바로 앞까지 오는데 걸리는 횟수를 return
        // q는 주소값을 전달하므로, 연동됩니다. 따라서 복사해줍니다.
        LinkedList<Integer> q = new LinkedList<>();
        for(int i : q_origin) q.add(i);
        int res = 0;
        while(true){
            int y = q.get(0);
            if(y == x) break;
            int tmp = q.remove(0);
            q.add(tmp);
            res++;
        }
        return res;
    }

    static void doWay2(LinkedList<Integer> q, int x){ // 앞에있는 애들 뒤로 옮기면서, x가 바로 앞까지 오는데 걸리는 횟수를 return
        while(true){
            int y = q.get(0);
            if(y == x) break;
            int tmp = q.remove(0);
            q.add(tmp);
        }
    }

    static int cntWay3(LinkedList<Integer> q_origin, int x){ // 뒤에 있는 애를 앞으로 옮기면서, x가 바로 앞까지 오는데 걸리는 횟수를 return
        // q는 주소값을 전달하므로, 연동됩니다. 따라서 복사해줍니다.
        LinkedList<Integer> q = new LinkedList<>();
        for(int i : q_origin) q.add(i);
        int res = 0;
        while(true){
            int y = q.get(0);
            if(y == x) break;
            int tmp = q.remove(q.size()-1);
            q.add(0, tmp);
            res++;
        }
        return res;
    }
    static void doWay3(LinkedList<Integer> q, int x){ // 뒤에 있는 애를 앞으로 옮기면서, x가 바로 앞까지 오는데 걸리는 횟수를 return
        while(true){
            int y = q.get(0);
            if(y == x) break;
            int tmp = q.remove(q.size()-1);
            q.add(0, tmp);
        }
    }
}