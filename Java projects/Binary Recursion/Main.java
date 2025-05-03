public class Main{
    public static void main(String args[]){
        Binary(3, "");
    }

    public static void Binary(int n, String s){
        if(n==0){
            System.out.println(s);
            return;
        }
        
        Binary(n-1, "1"+s);
        Binary(n-1, "0"+s);
        
    }
}