import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        String[] a = new String[input.length];  // 들어온 알파벳을 저장할 배열
        int[] d = new int[input.length];        // String 배열 a의 위치에 출현한 빈도수
        int idx = 0;
        for(int i=0; i<input.length; i++){
            String x = input[i].toUpperCase();// 대소문자를 구별하지 않기 위해 모두 대문자로 치환
            // 만약 a에 해당하는 문자가 있다면 d의 해당 인덱스의 값만 늘림
            if(Arrays.asList(a).contains(x)) d[Arrays.asList(a).indexOf(x)]++;
            else{                               // 만약 그렇지 않다면, a에 해당 문자를 삽입하고
                a[idx] = x;                     // d의 해당 위치를 증가시킴
                d[idx++]=1;
            }
        }

        int real_len = input.length;
        for(int i=0; i<input.length; i++)    // 실제 사용된 문자의 개수를 확인하기
            if(d[i] == 0){
                real_len = i;               // 입력된 문자의 개수
                break;
        }
        int[] copy_d = new int[real_len];   // 배열 d를 정렬하기 전 copy_d에 복사
        for(int i=0; i<real_len; i++) copy_d[i] = d[i];

        Arrays.sort(d);                     // sort 하기
        int max = d[input.length-1];              // 가장 큰 빈도수를 max에 저장
        if(real_len<2) System.out.println(a[0]); // 만약 입력값이 하나면 바로 count 출력
        // 만약 오름차순한 배열의 맨 끝에의 값과 그 전의 값이 같으면
        else if(d[input.length-1] == d[input.length-2]) System.out.println("?");
        else{
            for(int i=0; i<real_len; i++){
                if(max == copy_d[i]){       // 그렇지 않고 최대 빈도가 하나라면, 해당 값을 찾았을 때 결과를 출력
                    System.out.println(a[i]);
                    break;
                }
            }
        }

    }
}
//
//import java.io.IOException;
//public class Main {
//    public static void main(String[] args) throws IOException {
//        int[] input = new int[26];
//        int a, max = 0;
//        int max_str = -2;
//        while( (a = System.in.read()) > 64){
//            input[a -= a<96 ? 65 : 97]++;
//
//            if(max < input[a]){
//                max = input[a];
//                max_str = a;
//            }else if(max == input[a]) max_str = -2;
//        }
//        System.out.println((char)(max_str+65));
//    }
//}