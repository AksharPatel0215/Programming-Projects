public class Main{
    public static void main(String args[]){
       permutate("", "abc");
       
    }

    public static void permutate(String prefix, String str){
        int x = str.length();
        if(x==0){
            System.out.println(prefix);
        }
        for(int i = 0; i < x; i++){
            permutate(prefix+str.charAt(i), str.substring(0,i)+str.substring(i+1));
        }
    }
}