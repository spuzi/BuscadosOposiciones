package automatizacion.oposiciones;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EmpleoPublico extends WebScrapper {

	String URL = "http://www.empleopublico.eu/lista-de-convocatorias/";
	ArrayList<Oposicion> oposicionesInformatica = new ArrayList<>();

	public EmpleoPublico() {
		super();
	}

	public void visitUrl() {
		visitUrl(URL);
		try {
			List<WebElement> elements = driver.findElements(By.xpath("//div[@class='postitem']"));
			for (int i = 0; i < elements.size(); i++) {
				WebElement cadaElement = elements.get(i);
				Oposicion oposicion = new Oposicion(cadaElement);
				if (oposicion.sonDeInformatica()) {
					oposicionesInformatica.add(oposicion);
				}
			}
			System.out.println("\n Se han encontrado " + oposicionesInformatica.size() + " ");
			for (Oposicion oposicion : oposicionesInformatica) {
				RandomWait.waitARandomAmountOfSeconds(10, 20);
				oposicion.visitarDetallesDeLaOposicion();
				oposicion.mostrarInfoOposocionDetallada();
			}
		} catch (Exception e) {
			System.out.println("Ha ocurrido el siguiente error:");
			System.out.println(e.getMessage());
		} finally {
			stopChromeDriver();
		}
	}

	private class Oposicion {
		
		String nombre;
		String fecha;
		String enlace;
		String procesoSelectivo;
		String tipoAcceso;
		String numeroPlazas;
		String organismoConvocante;
		String fechaConvocatoria;
		String plazoSolicitud;
		String basesConvocatoria;
		
		public Oposicion(WebElement cadaOpo) {
			nombre = cadaOpo.findElement(By.xpath(".//h3")).getText();
			fecha = cadaOpo.findElement(By.xpath(".//div[@class='date']")).getText();
			enlace = cadaOpo.findElement(By.xpath(".//h3/a")).getAttribute("href");
			mostrarInfoOposocion();
		}

		private void mostrarInfoOposocion() {
			if (sonDeInformatica()) {
				System.out.println("*** FOUND ***");
			}
			if ((nombre != null) && !nombre.isEmpty()) {
				System.out.println(nombre);
			}
		}

		public void mostrarInfoOposocionDetallada() {
			System.out.println("\n");
			System.out.println("*** INFORMACION DETALLADA ***");
			if (sonDeInformatica()) {
				System.out.println("*** FOUND ***");
			}
			if ((nombre != null) && !nombre.isEmpty()) {
				System.out.println("Nombre:" + nombre);
			}
			if ((fecha != null) && !fecha.isEmpty()) {
				System.out.println("Fecha:" + fecha);
			}
			if ((procesoSelectivo != null) && !procesoSelectivo.isEmpty()) {
				System.out.println("Proceso selectivo:" + procesoSelectivo);
			}
			if ((tipoAcceso != null) && !tipoAcceso.isEmpty()) {
				System.out.println("Tipo de Acceso:" + tipoAcceso);
			}
			if ((numeroPlazas != null) && !numeroPlazas.isEmpty()) {
				System.out.println("Número de plazas:" + numeroPlazas);
			}
			if ((organismoConvocante != null) && !organismoConvocante.isEmpty()) {
				System.out.println("Organismo Convocante:" + organismoConvocante);
			}
			if ((fechaConvocatoria != null) && !fechaConvocatoria.isEmpty()) {
				System.out.println("Fecha Convocatoria:" + fechaConvocatoria);
			}
			if ((plazoSolicitud != null) && !plazoSolicitud.isEmpty()) {
				System.out.println("Plazo de solicitud:" + plazoSolicitud);
			}
			if ((basesConvocatoria != null) && !basesConvocatoria.isEmpty()) {
				System.out.println("Anuncio de convocatoria:" + basesConvocatoria);
			}
			if ((enlace != null) && !enlace.isEmpty()) {
				System.out.println("Enlace:" + enlace);
			}
		}
		
		public boolean sonDeInformatica() {
			return nombre.toLowerCase().contains("informátic");
		}
		
		private void visitarDetallesDeLaOposicion() {
			visitUrl(enlace);
			try {
				List<WebElement> datosDeLaConvocatoria = driver.findElements(By.xpath("//div[@id=\"convocatoria\"]/table[2]/tbody"));
				for (WebElement element : datosDeLaConvocatoria) {
					/*
					 * Proceso selectivo: Concurso-Oposición
					 * Tipo de Acceso: Libre
					 * Número de Plazas: 2
					 * Organismo Convocante: Ayuntamiento de La Zubia
					 * Fecha Convocatoria: 15/02/2019
					 * Plazo de solicitud: 15 días naturales, contados a partir del día siguiente al de la publicación de la resolución que anuncia la convocatoria en el Boletín Oficial del Estado.
					 * Anuncio de convocatoria: Boletín Oficial del Estado: 15 de Febrero de 2019, núm 40
					 */
					String datos = element.getText();
					procesoSelectivo = datos.split("\n")[0].contains("Proceso selectivo") ? datos.split("\n")[0].split(":")[1].trim() : "";
					tipoAcceso = datos.split("\n")[1].contains("Tipo de Acceso") ? datos.split("\n")[1].split(":")[1].trim() : "";
					numeroPlazas = datos.split("\n")[2].contains("Número de Plazas") ? datos.split("\n")[2].split(":")[1].trim() : "";
					organismoConvocante = datos.split("\n")[3].contains("Organismo Convocante") ? datos.split("\n")[3].split(":")[1].trim() : "";
					fechaConvocatoria = datos.split("\n")[4].contains("Fecha Convocatoria") ? datos.split("\n")[4].split(":")[1].trim() : "";
					plazoSolicitud = datos.split("\n")[5].contains("Plazo de solicitud") ? datos.split("\n")[5].split(":")[1].trim() : "";
					basesConvocatoria = datos.split("\n")[6].contains("Anuncio de convocatoria") ? datos.split("\n")[6].split(":")[1].trim() : "";
				}
			} catch (Exception e) {
				System.out.println("Ha ocurrido el siguiente error:");
				System.out.println(e.getMessage());
			}
		}
	}
}
