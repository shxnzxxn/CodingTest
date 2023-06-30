import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
//            boolean istrue = false; // 팰린드롬수인지 아닌지
//            int input2 = sc.nextInt(); // 입력 값을 받아옴
//            String[] input1 = String.valueOf(input2).split(""); // 한 글자씩 split
//
//            int[] input = new int[input1.length];
//            for(int i=0; i<input.length; i++){
//                input[i] = Integer.parseInt(input1[i]); // 정수배열로 변환
//            }
//
//            if(input.length == 1 && input[0] == 0) System.exit(0); // 입력값이 0이면 종료
//
//            Stack<Integer> stack = new Stack<>();
//            int mid = input.length/2;
//            for(int i=0; i<mid; i++) stack.push(input[i]); // 입력된 수의 반절만큼 스택에 push
//
//            if(input.length%2 != 0) mid++; // 글자수가 홀수이면 가운데 수는 버림.
//
//            for(int i=mid; i<input.length; i++){
//                int x = stack.pop();    // 그 후 스택에서 하나씩 꺼내면서
//                if(x != input[i]){      // 같지 않다면 no를 출력하고 바로 다음 입력을 받음.
//                    System.out.println("no");
//                    istrue = true;
//                    break;
//                }
//            }
//            if(!istrue) System.out.println("yes"); // 모두 같다면 yes 출력.
            String input = String.valueOf(sc.nextInt());
            if(input.equals("0")) System.exit(0);
            boolean istrue = false; // 팰린드롬수인지 아닌지
            for(int i=0; i<input.length()/2; i++){
                if(input.charAt(i) != input.charAt(input.length()-1-i)){
                    istrue = true;
                    System.out.println("no");
                    break;
                }
            }
            if(!istrue) System.out.println("yes");
        }
    }
}