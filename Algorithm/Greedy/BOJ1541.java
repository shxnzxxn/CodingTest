import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("-");
        int[] equiv = new int[input.length];
        for(int i=0; i<input.length; i++){
            String[] tmp = input[i].split("\\+");
            int sum = 0;
            for(int j=0; j<tmp.length; j++){
                sum += Integer.parseInt(tmp[j]);
            }
            equiv[i] = sum;
        }
        int res = equiv[0];
        for(int i=1; i<equiv.length; i++){
            res -= equiv[i];
        }
        System.out.println(res);
    }
}