package model;

/**
 * Pplace.java
 This is a model class represents a Pplace entity
 * @author Ramesh Fadatare
 *
 */
public class Pplace {
	protected int id;
	protected String name;
	protected String image;
	protected String status;
	
	public Pplace() {
	}
	
	public Pplace(String name, String image, String status) {
		super();
		this.name = name;
		this.image = image;
		this.status = status;
	}

	public Pplace(int id, String name, String image, String status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.status = status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
