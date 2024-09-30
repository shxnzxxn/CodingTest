import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int[] num;
    static long min;
    static long max;
    static boolean isFirst = true;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        num = new int[n];
        for(int i=0; i<n; i++) num[i] = sc.nextInt();
        sc.nextLine();
        LinkedList<Integer> operator = new LinkedList<>(); // 1, 2, 3, 4 순서로 +, -, *, /
        for(int i=1; i<= 4; i++) {
            int tmp = sc.nextInt();
            for(int j=0; j<tmp; j++) operator.add(i);
        }
        boolean[] visited = new boolean[operator.size()];
        backTracking(new int[n-1], operator, visited, 0, operator.size(), operator.size());
        System.out.println(max);
        System.out.println(min);
    }

    public static void backTracking(int[] output, LinkedList<Integer> operator, boolean[] visited, int depth, int start, int n){
        if(depth == n){
            long result = operate(num, output);
            if(isFirst){
                min = result;
                max = result;
            }else{
                if(min > result) min = result;
                if(max < result) max = result;
            }
            isFirst = false;
            return;
        }

        for(int i=0; i< operator.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                output[depth] = operator.get(i);
                backTracking(output, operator, visited, depth+1, start+1, n);
                visited[i] = false;
            }
        }
    }

    public static long operate(int[] num, int[] output){
        int idx = 0;
        long res = num[idx];
        while(idx < output.length){
            switch(output[idx++]){
                case 1:
                    res += num[idx];
                    break;
                case 2:
                    res -= num[idx];
                    break;
                case 3:
                    res *= num[idx];
                    break;
                case 4:
                    boolean minus = false;
                    if(res <0) {
                        minus = true;
                        res *= -1;
                    }
                    res /= num[idx];
                    if(minus) res *= -1;
            }
        }
        return res;
    }
}