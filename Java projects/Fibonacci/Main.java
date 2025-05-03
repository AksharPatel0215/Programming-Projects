public class Main{
    public static void main(String args[]){
    System.out.println(Fibonacci(0));
    }
    public static int Fibonacci(int x){
        if(x==0)
            return 0;
        if(x<=2)
            return 1;
        return Fibonacci(x-1)+Fibonacci(x-2);
    }
}