package assistant;

import java.util.ArrayList;
import java.util.List;

public class IncomingNews {
	public String title;
	public boolean isRead;
	
	public void newNews(String newsTitle, boolean read){
		title = newsTitle;
		isRead = read;
	}

	public List<String> createNews(){
		List<String> news = new ArrayList<String>();
		news.add("Ex espia ruso envenenado en Londres");
		news.add("Facebook en problemas por privacidad de usarios");
		news.add("Presidente de USA demandado por actriz de cine para adultos");
		news.add("Tiroteo en las oficinas de YouTube");
		news.add("Sernageomin subió a alerta naranja el Nevados de Chillán por aumento de sismicidad");
		news.add("Juez federal emitió orden de arresto contra Lula");
		news.add("Registro de evasores del Transantiago comenzará a regir el 5 de junio");
		news.add("Arrestan a 14 chilenos que asaltaron 400 casas en Canadá: Robaron más de mil millones de pesos");
		news.add("Fila por hora médica termina en pelea entre pacientes en Cesfam de San Pedro de la Paz");
		news.add("Grupos antivacunas piden que CDE no les cobre las costas de los juicios que perdieron");
		return news;
	}
}
