import java.util.*;

public class Main{
 
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        System.out.println(f(n)%1000000007);
        sc.close();
    }
    
    
	
	private static long f(int n) {
		
		if(n == 1) {
			return 1;
		}else {
			return f(n-1) + 3*n -1;
		}
		
	}
	
	

}




