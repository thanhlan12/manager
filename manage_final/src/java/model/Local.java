package model;

/**
 * Local.java
 This is a model class represents a Local entity
 * @author Ramesh Fadatare
 *
 */
public class Local {
	protected int id;
	protected String name;
	protected String image;
	protected String country;
	
	public Local() {
	}
	
	public Local(String name, String image, String country) {
		super();
		this.name = name;
		this.image = image;
		this.country = country;
	}

	public Local(int id, String name, String image, String country) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.country = country;
	}

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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
