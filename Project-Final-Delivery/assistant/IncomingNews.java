package assistant;

public class IncomingNews {
	private String title;
	private String status;
	
	public IncomingNews(String newsTitle, int status){
		title = newsTitle;
		if (status == 0){
			this.status = "Breaking News!";
		}
		else{
			this.status = "Missed News";
		}
	}
	
	public String getTitle(){
		return title;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(int n){
		if (n == 0){
			status = "Breaking News!";
		}
		else{
			status = "Missed News";
		}
	}
}
