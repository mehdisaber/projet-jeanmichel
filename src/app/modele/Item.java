package app.modele;

import javafx.scene.image.Image;

public class Item {
	private Image img;
	private String lienIMG;
	private boolean deplacable;
	private boolean cassable;

	//La dimension d'objet cassable est à revoir

	public Item(String url, boolean dplcbl,boolean cssble) {
		img=new Image(url,true);
		deplacable=dplcbl;
		cassable=cssble;
	}
	public final Image getImg() {
		return img;
	}
	public final String getLienIMG() {
		return lienIMG;

	}
	public final boolean isDeplacable() {
		return deplacable;
	}
	public final boolean isCassable() {
		return cassable;
	}

}