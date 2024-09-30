import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] input = new String[n][2*n];
        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++) input[i][j] = " ";
        }

        start(input, 0, n-1, n); // 마지막은 포함 안돼요!!

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length; i++){
            for(int j=0; j<input[i].length; j++) sb.append(input[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void start(String[][] input, int m, int n, int standard) { // 가운데 꼭짓점을 가르켜준다.
        if(standard == 3){
            input[m][n] = "*";
            input[m+1][n-1] = "*";
            input[m+1][n+1] = "*";
            for(int i=-2; i<3; i++) input[m+2][n+i] = "*";
            return;
        }

        start(input, m, n, standard/2);
        start(input, m+standard/2, n-standard/2, standard/2);
        start(input, m+standard/2, n+standard/2, standard/2);
    }
}