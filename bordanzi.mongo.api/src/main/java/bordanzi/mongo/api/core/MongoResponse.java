package bordanzi.mongo.api.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;

public class MongoResponse {

	private String ad_placement;
	private String format_iab;
	private String gender;
	private String age;
	private String scholarity;
	private String marital;
	private String income;
	private String connection;
	private String browser;
	private String so;
	private String interest_id;
	private Integer clicks;
	private Integer prints;
	
	public MongoResponse(Integer clicks,Integer prints,
						 String ad_placement,String format_iab,
						 String gender,String age,String scholarity,
						 String marital,String income,String interest_id,
						 String connection,String browser,String so){
		
		this.clicks=clicks;
		this.prints=prints;
		this.ad_placement=ad_placement;
		this.age=age;
		this.browser=browser;
		this.connection=connection;
		this.format_iab=format_iab;
		this.gender=gender;
		this.income=income;
		this.interest_id=interest_id;
		this.marital=marital;
		this.scholarity=scholarity;
		this.so=so;
	
	}


	public MongoResponse(BasicDBObject document) {
	
		
		this.clicks=document.getInt("clicks", 0);
		this.prints=document.getInt("prints", 0);
		this.ad_placement=document.getString("ad_placement");
		this.age=document.getString("age");
		this.browser=document.getString("browser");
		this.connection=document.getString("connection");
		this.format_iab=document.getString("format_iab");
		this.gender=document.getString("gender");
		this.income=document.getString("income");
		this.interest_id=document.getString("interest_id");
		this.marital=document.getString("marital");
		this.scholarity=document.getString("scholarity");
		this.so=document.getString("so");		
		
	}


	@JsonProperty
	public Integer getClicks() {
		return clicks;
	}

	@JsonProperty
	public Integer getPrints() {
		return prints;
	}

	@JsonProperty
	public String getAd_placement() {
		return ad_placement;
	}

	@JsonProperty
	public String getFormat_iab() {
		return format_iab;
	}

	@JsonProperty
	public String getGender() {
		return gender;
	}

	@JsonProperty
	public String getAge() {
		return age;
	}

	@JsonProperty
	public String getScholarity() {
		return scholarity;
	}

	@JsonProperty
	public String getMarital() {
		return marital;
	}

	@JsonProperty
	public String getIncome() {
		return income;
	}

	@JsonProperty
	public String getConnection() {
		return connection;
	}

	@JsonProperty
	public String getBrowser() {
		return browser;
	}

	@JsonProperty
	public String getSo() {
		return so;
	}

	@JsonProperty
	public String getInterest_id() {
		return interest_id;
	}

}
