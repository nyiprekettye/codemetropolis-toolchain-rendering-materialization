package codemetropolis.toolchain.rendering.materialization.building;

import codemetropolis.toolchain.rendering.materialization.building.point.*;

public class Building {
	private String name;
	private String id;
	private String type;
	private String parent_id;
	private String parent_name;
	private Location location;
	private Size size;
	
	public Building(){
		size= new Size();
		location = new Location();
		name=null;
		id=null;
		type = null;
		parent_id=null;
		parent_name=null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Building: [type=" + type  +", id=" + id + ", parent_id=" + parent_id + ", name=" + name 
				+ ", parent_name=" + parent_name + ", location=" + location.toString() + ", size=" + size.toString() + "]";
	}
}
