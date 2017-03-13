package codemetropolis.toolchain.rendering.materialization.building.point;

public class Size {
	private int x;
	private int y;
	private int z;
	public Size(){
		x=0;
		y=0;
		z=0;
	}
	@Override
	public String toString() {
		return "Size [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
}
