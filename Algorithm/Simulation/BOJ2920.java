import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = new int[8];
        for(int i=0; i<input.length; i++) input[i] = sc.nextInt();

        if(input[0] == 1){
            for(int i=0; i<input.length; i++){
                if(input[i] != i+1) {
                    System.out.println("mixed");
                    System.exit(0);
                }
            }
            System.out.println("ascending");
        }else if(input[0] == 8){
            for(int i=8; i>0; i--){
                if(input[8-i] != i){
                    System.out.println("mixed");
                    System.exit(0);
                }
            }
            System.out.println("descending");
        }else{
            System.out.println("mixed");
        }
    }
}