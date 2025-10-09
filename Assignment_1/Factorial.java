import java.util.*;
public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);// sc reference = stack memory  , scanner object = heap memory

        System.out.println("Enter the number : ");
        int num = sc.nextInt(); // num= stack memory
        int fact=1; //fact = stack memory
        for(int i=num ; i>=1 ; i--){
            fact=fact*i;
        }
        System.out.println(fact);
    }
}
