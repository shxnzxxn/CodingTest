import java.util.Scanner;

public class Main {
    static String[][] input;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        input = new String[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) input[i][j] = " ";
        }

        start(n, 0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for (int j = 0; j < n; j++) sb.append(input[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void start(int n, int x, int y) {
        if(n==1){
            input[x][y] = "*";
            return;
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i==1 && j==1) continue;
                start(n/3, x+n/3*i, y+n/3*j);
            }
        }
    }
}