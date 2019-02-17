package automatizacion.oposiciones;

/**
 * To make an executable of this with maven:
 * cd /go/to/myApp
 * mvn clean
 * mvn compile
 * mvn package
 * java -cp target/myApp-0.0.1-SNAPSHOT.jar automatizacion.oposiciones.App
 */
public class App {

	public static void main(String[] args) {
		EmpleoPublico empleoPublico = new EmpleoPublico();
		empleoPublico.visitUrl();
	}
}
