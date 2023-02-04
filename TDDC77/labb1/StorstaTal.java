import java.util.Scanner;

public class StorstaTal{
   public static void main(String[] args) {
     //1.7
     Scanner scan = new Scanner (System.in);
     System.out.print("Mata in ett tal: ");
     int heltal1 = scan.nextInt();
     System.out.print("Mata in ett till tal: ");
     int heltal2 = scan.nextInt();
     if(heltal1 > heltal2){
       System.out.println(heltal1 + " är störst.");
     }
     else if (heltal2 > heltal1){
       System.out.println(heltal2 + " är störst.");
     }
     else{
       System.out.println("Samma tal äggskalle!");
     }
   }
}
