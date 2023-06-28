import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt() * sc.nextInt() * sc.nextInt();
        String[] input = Integer.toString(x).split("");
        int[] arr = new int[input.length];
        for(int i=0; i< input.length; i++) arr[i] = Integer.parseInt(input[i]);
        Arrays.sort(arr);
        int[] res = new int[10];
        int obj = 0;
        for(int i=0; i<input.length; i++){
            if(obj != arr[i]) {while (obj != arr[i]) obj++;}
            res[obj]++;
        }
        for(int i=0; i<res.length; i++){
            System.out.println(res[i]);
        }
    }
}