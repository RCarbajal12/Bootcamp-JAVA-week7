package com.romina.countries.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="languages")
public class Languages {
	
	@Id
    private int id;
    @Column(columnDefinition = "CHAR(30)")
	private String language;

    @Column(columnDefinition = "float(4,1)")
	private double percentage;

    @Column(columnDefinition = "CHAR(3)",name="country_code")
	private String countryCode;
    
    //CONSTRUCTOR VACÍO
    public Languages() {
    	
    }
    
    //GETTERS Y SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	

}
