
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main{
    public static void main(String args[])  throws FileNotFoundException{
        Scanner scan = new Scanner( new File("test.txt"));
        while(scan.hasNext()){
            String[] str = scan.nextLine().split(" ");
            ArrayList<Integer> divA = getFactors(Integer.parseInt(str[0]));
            ArrayList<Integer> divB = getFactors(Integer.parseInt(str[1]));
            int a = sum(divA);
            int b = sum(divB);
            if(a==Integer.parseInt(str[1]) && b==Integer.parseInt(str[0])){
                System.out.println("Agreeable");
            } else {
                System.out.println("Not Agreeable");
            }
        }
        
    }

    public static ArrayList<Integer> getFactors(int a){
        ArrayList<Integer> divs = new ArrayList<>();
            for(int i = 1; i < a; i++){
                if(a%i == 0){
                    divs.add(i);
                }
            }
            return divs;
    }

    public static int sum(ArrayList<Integer> nums){
        int x = 0;
        for(Integer a: nums){
            x+=a;
        }
        return x;
    }
}