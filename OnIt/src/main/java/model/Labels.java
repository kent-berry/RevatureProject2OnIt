package model;

public class Labels {
	private String id;
	private String labelText;
	
	//Constructor
	public Labels(String id, String labelText) {
		super();
		this.id = id;
		this.labelText = labelText;
	}

	//Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}

	//toString
	@Override
	public String toString() {
		return "Labels [id=" + id + ", labelText=" + labelText + "]";
	}

}
