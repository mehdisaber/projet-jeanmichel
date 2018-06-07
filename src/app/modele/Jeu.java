package app.modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Jeu {

	private ArrayList<Ennemi> listeEnnemis;
	private ArrayList<PNJ> listePNJs;
	private JeanMichel jeanMichel;
	private ObservableList<Item> listeItems;

	public Jeu() {
		this.listeEnnemis = new ArrayList<Ennemi>();
		this.listePNJs = new ArrayList<PNJ>();
		this.listeItems = FXCollections.observableArrayList();
		this.jeanMichel = new JeanMichel(null, 0, 0);
		this.listeItems.add(new Coeur(20,40));
		init();
	}

	public void init() {
		//ajouter les ennemis
		//zone1
		addEnnemi(new Ennemi("testEnnemi1",50, 0, 80));
		addEnnemi(new Ennemi("testEnnemi2",50, 80, 0));
		
		//zone2

		//ajouter les pnjs
		//zone1
		addPNJ(new PNJArme("testPNJArme", 20, 40));
		addPNJ(new PNJItem("testPNJItem", 125, 40));
		addPNJ(new PNJVie("testPNJVie", 10, 200));
		//zone2
		addPNJ(new PNJArme("testPNJArme", 10, 200));
		addPNJ(new PNJItem("testPNJItem", 125, 40));
		addPNJ(new PNJVie("testPNJVie", 20, 40));

		this.listeItems.addListener(new ListChangeListener<Item>() {
			@Override
			public void onChanged(Change<? extends Item> c) {
				while(c.next()) {
					for (Item remitem: c.getRemoved()) {
						listeItems.remove(remitem);
					}
				}
			}
		});
	}

	public void update() {
		this.listeEnnemis.get(0).seDeplacer();

	}

	public void addEnnemi(Ennemi e) {
		this.listeEnnemis.add(e);
	}

	public ArrayList<Ennemi> getEnnemis() {
		return this.listeEnnemis;
	}

	public void addPNJ(PNJ p) {
		this.listePNJs.add(p);
	}

	public ArrayList<PNJ> getPNJ() {
		return this.listePNJs;
	}

	public JeanMichel getJeanMichel() {
		return jeanMichel;
	}

	public ObservableList<Item> getListeItems() {
		return listeItems;
	}

}
