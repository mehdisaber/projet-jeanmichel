package app.modele;

public class Cactus extends Ennemi{

	private int orientation;
	
	 public Cactus(String n, int pv, int x, int y) {
		super(n, pv, x, y, 12, 29);
		this.orientation = 0;
	}

		public void haut() {
			if(!this.collision.collisionneObstacle(getX(), getY() - 1) 
			&& !this.collision.collisionneObstacle(getX() + getTailleX(), getY() - 1)
			&& !this.collision.collisionnePerso(this.jeu.getJeanMichel(), this, 0, 1)) {
				this.positionY.set(getY() - 1);
				this.orientation = 0;
			}
			else
				this.orientation = 3;
		}

		public void bas() {
			if(!this.collision.collisionneObstacle(getX(), getY() + 1 + getTailleY()) 
			&& !this.collision.collisionneObstacle(getX() + getTailleX(), getY() + 1 + getTailleY())
			&& !this.collision.collisionnePerso(this.jeu.getJeanMichel(), this, 1, 1)) {
				this.positionY.set(getY() + 1);
				this.orientation = 1;
			}
			else
				this.orientation = 2;
		}

		public void gauche() {
			if(!this.collision.collisionneObstacle(getX() - 1, getY() + getTailleY()) 
			&& !this.collision.collisionneObstacle(getX() - 1, getY() + getTailleY()) 
			&& !this.collision.collisionneObstacle(getX() - 1, getY() + getTailleY()/2)
			&& !this.collision.collisionnePerso(this.jeu.getJeanMichel(), this, 2, 1)) {
				this.positionX.set(getX() - 1);
				this.orientation = 2;
			}
			else
				this.orientation = 0;
		}

		public void droite() {
			if(!this.collision.collisionneObstacle(getX() + 1 + getTailleX(), getY()) 
			&& !this.collision.collisionneObstacle(getX() + getTailleX() + 1, getY() + getTailleY()) 
			&& !this.collision.collisionneObstacle(getX() + getTailleX() + 1, getY() + getTailleY()/2)
			&& !this.collision.collisionnePerso(this.jeu.getJeanMichel(), this, 3, 1)) {    
				this.positionX.set(getX() + 1);
				this.orientation = 3;
			}
			else
				this.orientation = 1;
		}

	@Override
	public void seDeplacer() {
		switch(this.orientation) {
		case 0: haut();
		break;
		case 1: bas();
		break;
		case 2: gauche();
		break;
		case 3: droite();
		break;
		}
		
	}
	
}
