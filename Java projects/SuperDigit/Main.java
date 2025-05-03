
public class Main {
    public static void main(String args[]){
        

        System.out.println(superDigit("123", 3));

    }

    public static int superDigit(String a, int y){
        String b = a;
        while(y>1){
            a += b;
            y--;
        }
        int x = Integer.parseInt(a);
        int sum = 0;
        if(x%10>0){
            sum+=(x%10)+superDigit((x/10)+"", 0);
            if(sum%10!=sum){
                return superDigit(sum+"", 0);
            }
            return sum;
            
        }  
        return 0;
    }
}