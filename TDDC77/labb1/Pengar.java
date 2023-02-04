//1.8
import java.util.Scanner;

public class Pengar{
   public static void main(String[] args) {

     Scanner scan = new Scanner (System.in);

     System.out.print("Skriv in ett pris(kr): ");
     double price  = scan.nextFloat();

     System.out.print("Skriv in betalsumma(kr): ");
     double amntpaid = scan.nextFloat();

         if (price>amntpaid || price<0 || amntpaid<0){
           System.out.println("Antingen har du betalat för lite eller skrivit in fel format.");
           return;
         }

         double change = amntpaid - price;

         double [] numOfBills = new double [8];

         double [] valueOfBills = {500, 100, 50, 20, 10, 5, 1, 0.5};

         String [] unitOfBills = {" st Femhundralappar, " ,
         " st Hundralappar, ", " st Femtiolappar, " ,
         " st Tjugolappar, ", " st Tiokronor, " , " st Femkronor, " ,
         " Enkronor, " , " Femtioöringar." };

         System.out.println("Du får tillbaka: ");

         for(int i = 0; i < numOfBills.length; i++){

           numOfBills[i] = ((change - change%valueOfBills[i]))/valueOfBills[i];
           change = change%valueOfBills[i] ;

           System.out.println(numOfBills[i] + unitOfBills[i]);
          }
         }

         /*
         float numOf500 = 0;
         float numOf100 = 0;
         float numOf50 = 0;
         float numOf20 = 0;
         float numOf10 = 0;
         float numOf5 = 0;
         float numOf1 = 0;
         float numOf05 = 0;
         */
         /*for(float i = ((change - change%500))/500; i>0; i--){
           numOf500++;
           change-=500;
         }
         for(float i = ((change - change%100))/100; i>0; i--){
           numOf100++;
           change-=100;
         }
         for(float i = ((change - change%50))/50; i>0; i--){
           numOf50++;
           change-=50;
         }
         for(float i = ((change - change%20))/20; i>0; i--){
           numOf20++;
           change-=20;
         }
         for(float i = ((change - change%10))/10; i>0; i--){
           numOf10++;
           change-=10;
         }
         for(float i = ((change - change%5))/5; i>0; i--){
           numOf5++;
           change-=5;
         }
         for(float i = ((change - change%1))/1; i>0; i--){
           numOf1++;
           change-=1;
         }
         if (change >= 0.5){
           numOf05++;
         }

         System.out.println("Du får tillbaka: "
         + numOf500 + " st Femhundralappar, "
         + numOf100 + " st Hundralappar, "
         + numOf50 + " st Femtiolappar, "
         + numOf20 + " st Tjugolappar, "
         + numOf10 + " st Tiokronor, "
         + numOf5 + " st Femkronor, "
         + numOf1 + " Enkronor, "
         + numOf05 + " Femtioöringar."
         );*/



   }
