package automatizacion.oposiciones;

import java.util.Random;

public class RandomWait {
	
	public static int waitARandomAmountOfSeconds(int min, int max) {
		min = min * 1000;
		max = max * 1000;
		Random r = new Random();
		int seconds = r.nextInt(max - min) + min;
		try {
			System.out.println("\n");
			System.out.println("...Esperando " + seconds + " para no sobrecargar la web.");
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		return seconds;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			int seconds = waitARandomAmountOfSeconds(4, 8);
			System.out.println(seconds);
		}
	}
}
