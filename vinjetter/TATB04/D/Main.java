package vinjetter.TATB04.D;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double  xn = 33;
		double xn1; 
		double a = 56;
		double root = Math.sqrt(a);
		double eta = xn/root -1;
		final double RElATIVT_FEL = 0.000000000001;
		
		int loopcount = 0;
		while(eta > RElATIVT_FEL) {
			xn1 = (xn + a/xn)/2;
			eta = xn1/root -1;
			System.out.println(xn1 + ", " + eta);
			xn = xn1;
			loopcount++;
		}
		System.out.println("\nRoten av a = " + root);
		System.out.println("        xn = " + xn);
		System.out.println("       eta = " + eta);
		System.out.println("      loop = " + loopcount);
	}

}
