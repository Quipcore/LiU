import java.util.Scanner;

public class Labb1{
  public static void main(String[] args) {
    Scanner scan = new Scanner (System.in);
    //1.4
    /*
    System.out.println("Hej hur gammal är du?");
    int age = scan.nextInt();
    System.out.println("Du är " + age + " år gammal.");
    */
    //1.5
    /*
    System.out.print("Mata in ett tal: ");
    double tal1 = scan.nextDouble();
    System.out.print("Mata in ett till tal: ");
    double tal2 = scan.nextDouble();
    System.out.println(tal1 +" + "+ tal2 + " = " + (tal1 + tal2));
    System.out.println(tal1 +" - "+ tal2 + " = " + (tal1 - tal2));
    System.out.println(tal1 +" * "+ tal2 + " = " + (tal1 * tal2));
    System.out.println(tal1 +" / "+ tal2 + " = " + (tal1 / tal2));
    */
    //1.6
    /*
    System.out.print("Vad heter du? ");
    String name = scan.nextLine();
    System.out.println("Hej, " + name + "!");
    */
    //1.7
    /*
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
    */
    //1.8
    System.out.print("Skriv in ett pris(kr): ");
    float price  = scan.nextFloat();

    System.out.print("Skriv in betalsumma(kr): ");
    float amntpaid = scan.nextFloat();

    float change = amntpaid - price;

    float numOf500 = 0;
    float numOf100 = 0;
    float numOf50 = 0;
    float numOf20 = 0;
    float numOf10 = 0;
    float numOf5 = 0;
    float numOf1 = 0;
    float numOf05 = 0;


    for(float i = ((change - change%500))/500; i>0; i--){
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
    );



  }
}
