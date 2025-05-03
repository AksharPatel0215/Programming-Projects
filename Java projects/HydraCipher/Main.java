import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    for( int i = 0; i != -1; i++){
    System.out.println("\nInput message to cipher.\nFor simplicity, all messages will display in lowercase\n");
    String message = scan.nextLine();
    if(message.equals("-1")){
      return;
      }
    Hydra(message);

    }  
  }



  public static void Hydra(String str){
//hello
//19 23 7 0 3
boolean special = false;
str = " "+str.toLowerCase()+" ";
    //for every character
    for(int i = 1; i < str.length(); i++){
      //x is the char val of current letter
      int x = str.charAt(i)-96;
      //n represents the letter before
      int n;
      //if its the first letter then subtract 64 & set n to the last letter of that word. 
      if(str.charAt(i-1)==' ' && i-1==0){  
        str = str.substring(i);
        int y = 1;
        if(!"abcdefghijklmopqrstuvwxyz".contains(str.substring(str.indexOf(" ")-y, str.indexOf(" ")-y+1))){
          y++;
        }
        n = str.charAt(str.indexOf(" ")-y)-96;
        if(x < n){
        x+=26;
      }x-=n;
      System.out.print((char)(x+97));
        i = 0;
      } else if(!"abcdefghijklmopqrstuvwxyz".contains(str.substring(i, i+1))){
System.out.print(str.substring(i, i+1));
special = true;
      } else {
        n = str.charAt(i-1)-96;
        if(x < n){
        x+=26;
      }x-=n;
      if(!special){
      System.out.print((char)(x+97));
      } else {
        special = false;
        Hydra(str.substring(i, str.length()-1));
        return;
      }
      }
    }
  }
}