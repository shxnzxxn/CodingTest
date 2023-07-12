import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = new int[10];
        for(int i=0; i<input.length; i++) input[i] = sc.nextInt();

        List<Integer> res = new ArrayList<>();
        for(int i=0; i<input.length; i++){
            if(!res.contains(input[i]%42)) res.add(input[i]%42);
        }
        System.out.println(res.size());
    }
}