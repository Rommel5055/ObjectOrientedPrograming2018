package assistant;

public class IncomingNews {
	private String title;
	private boolean isRead;
	
	public IncomingNews(String newsTitle, boolean read){
		title = newsTitle;
		isRead = read;
	}
	
	public String getTitle(){
		return title;
	}
	public boolean isRead(){
		return isRead;
	}
}
