package assistant;

public class IncomingNews {
	public String title;
	public boolean isRead;
	
	public void newNews(String newsTitle, boolean read){
		title = newsTitle;
		isRead = read;
	}
}
