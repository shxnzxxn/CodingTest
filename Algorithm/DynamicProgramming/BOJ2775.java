import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int floor = Integer.parseInt(br.readLine());
            int number = Integer.parseInt(br.readLine());
            System.out.println(sum(floor, number));
        }
    }
    public static int sum(int floor, int number){
        if(floor == 0 || number == 1) return number;
        return sum(floor, number-1) + sum(floor-1, number);
    }
}