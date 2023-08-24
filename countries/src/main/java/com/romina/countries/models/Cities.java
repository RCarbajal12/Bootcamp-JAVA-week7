package com.romina.countries.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cities")
public class Cities {

	@Id
    private int id;

    @Column(columnDefinition = "CHAR(35)")
	private String name;
    private int population;
    
    @Column(columnDefinition = "CHAR(20)")
	private String district;
    
    @Column(columnDefinition = "CHAR(3)",name="country_code")
	private String countryCode;
    
    
    //CONSTRUCTOR VACÍO 
    public Cities() {
    	
    }
    
    //GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
    
    
}
