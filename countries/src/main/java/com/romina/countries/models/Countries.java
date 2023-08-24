package com.romina.countries.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="countries")
public class Countries {
	
	@Id
	private int id;
	@Column(columnDefinition = "CHAR(3)")
	private String code;
	@Column(columnDefinition = "CHAR(52)")
	private String name;
	@Column(columnDefinition = "CHAR(26)")
	private String region;
	private int population;

	@Column(columnDefinition = "float(10,2)",name = "surface_area")
	private double surfaceArea;

	@Column(columnDefinition = "float(3,1)",name = "life_expectancy")
	private double lifeExpectancy;
	
	@Column(columnDefinition = "char(45)", name="government_form")
	private String governmentForm;


	//CONSTRUCTOR VACÍO
	public Countries() {
		
	}

	//GETTERS Y SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public double getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(double lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}
	
	

}
