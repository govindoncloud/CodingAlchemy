package alchemytec.expenses.api;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty
	String id;
	public Response(){
		super();
	}
	public Response(String id){
		this.id=id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId() {
		this.id=id;
	}
	

}
