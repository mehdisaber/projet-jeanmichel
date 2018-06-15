package app.modele;

public class Tile {

	private int x;
	
	private int y;
	
	public Tile(int nx, int ny) {
		this.x = nx/16;
		this.y = ny/16;
	}
	
	public Tile(int nx, int ny, String bla) {
		this.x = nx;
		this.y = ny;
	
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
