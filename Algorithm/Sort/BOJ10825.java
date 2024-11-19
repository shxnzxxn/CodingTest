import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static Student[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new Student[n];

        for(int i=0; i<n; i++){
            String[] s = br.readLine().split(" ");

            arr[i] = new Student(s);
        }

        List<Student> list = Arrays.stream(arr).sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.korean > o2.korean) {
                    return -1;
                } else if (o1.korean < o2.korean) {
                    return 1;
                } else {
                    if (o1.english > o2.english) {
                        return 1;
                    } else if (o1.english < o2.english) {
                        return -1;
                    } else {
                        if (o1.math > o2.math) {
                            return -1;
                        } else if (o1.math < o2.math) {
                            return 1;
                        } else {
                            return o1.name.compareTo(o2.name);
                        }
                    }
                }
            }
        }).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Student s : list) {
            sb.append(s.name + "\n");
        }

        System.out.println(sb);
    }

    static class Student{
        int korean;
        int english;
        int math;
        String name;

        public Student(String[] s){
            name = s[0];
            korean = Integer.parseInt(s[1]);
            english = Integer.parseInt(s[2]);
            math = Integer.parseInt(s[3]);
        }
    }
}