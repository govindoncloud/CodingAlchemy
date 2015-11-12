package alchemytec.expenses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ExpensesConfiguration extends Configuration {
	
	@JsonProperty
    @NotEmpty
    public String mongohost;
 
    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport;
 
    @JsonProperty
    @NotEmpty
    public String mongodb;

	public String getMongohost() {
		return mongohost;
	}

	public void setMongohost(String mongohost) {
		this.mongohost = mongohost;
	}

	public int getMongoport() {
		return mongoport;
	}

	public void setMongoport(int mongoport) {
		this.mongoport = mongoport;
	}

	public String getMongodb() {
		return mongodb;
	}

	public void setMongodb(String mongodb) {
		this.mongodb = mongodb;
	}

}
