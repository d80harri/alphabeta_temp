import java.util.Arrays;

import d80harri.alphabeta.core.AdditiveRandom;


public class Test {
	public static void main(String[] args) {
//		int res[] = new int[10];
//		
//		for (int i=0; i<100000; i++){
//			int x = (int)(Math.random() * 3.333 + Math.random() * 3.333+ Math.random() * 3.333);
//			res[x]++;
//		}
//		
//		System.out.println(Arrays.toString(res));
		
		AdditiveRandom random = new AdditiveRandom(-3, 5, 2);
		for (int i=0; i<100; i++){
			System.out.println(random.nextDouble());
		}
	}
}
