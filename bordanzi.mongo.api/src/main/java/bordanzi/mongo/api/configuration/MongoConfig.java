package bordanzi.mongo.api.configuration;

import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MongoConfig extends Configuration{
	
	@NotEmpty
	String ip;
	
	@NotNull
	Integer port;
	
	@NotEmpty
	String dbName;
	
	@NotEmpty
	String collectionName;

	@NotEmpty
	String fileName;
	
	
	@JsonProperty
	public String getFileName() {
		return fileName;
	}

	@JsonProperty
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonProperty
	public String getCollectionName() {
		return collectionName;
	}

	@JsonProperty
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	@JsonProperty
	public String getIp() {
		return ip;
	}

	@JsonProperty
	public void setIp(String ip) {
		this.ip = ip;
	}

	@JsonProperty
	public Integer getPort() {
		return port;
	}

	@JsonProperty
	public void setPort(Integer port) {
		this.port = port;
	}

	@JsonProperty
	public String getDbName() {
		return dbName;
	}

	@JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
}
