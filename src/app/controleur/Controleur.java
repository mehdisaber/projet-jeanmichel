package app.controleur;

import java.net.URL;
import java.util.ResourceBundle;
import app.modele.Coeur;
import app.modele.Ennemi;
//import app.modele.Item;
import app.modele.JeanMichel;
import app.modele.Jeu;
import app.modele.Terrain;
import app.vue.VueCoeur;
import app.vue.VueEnnemi;
import app.vue.VueItem;
import app.vue.VueJeanMichel;
import app.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable {

	//modeles
	private Jeu jeu;
	private Terrain map;
	//vues
	private VueTerrain vueMap;
	private VueJeanMichel vueHeros;

	private VueItem vueitem;

	private Timeline gameLoop;

	//private ObservableList<Item> listeItems;

	//FXML
	@FXML
	private BorderPane borderpane;
	@FXML
	private Pane pane;
	@FXML
	private TilePane tilemap;

	@FXML
	private GridPane grid;

	@FXML
	private ImageView heart0;

	@FXML
	private ImageView heart1;

	@FXML
	private ImageView heart2;

	@FXML
	private ImageView heart3;

	@FXML
	private ImageView heart4;

	@FXML
	private DialogPane dialog;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.map = new Terrain();
		this.jeu = new Jeu();
		//listeItems = FXCollections.observableArrayList();
		this.dialog = new DialogPane();
		this.jeu.getJeanMichel().setJeu(this.jeu);
		jeu.getJeanMichel().pointsVieProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				verifVie();
			}
		});
		this.vueMap = new VueTerrain(this.map);
		this.vueHeros = new VueJeanMichel(this.jeu.getJeanMichel());
		this.vueitem = new VueCoeur((Coeur)this.jeu.getListeItems().get(0));
		//Ajout des élements dans le Scene Builder

		this.tilemap.getChildren().add(this.vueMap.getTileMap());
		this.pane.getChildren().add(this.vueMap.getTileMap());
		this.pane.getChildren().add(this.vueMap.getTileMapObs());
		this.pane.getChildren().add(this.vueMap.getTileMapMov());

		//affichage des persos
		this.pane.getChildren().add(vueitem.getSprite());
		this.pane.getChildren().add(vueHeros.getSprite());
		for (Ennemi en : jeu.getEnnemis()) {
			en.setJeu(jeu);
			new VueEnnemi(en);
			this.pane.getChildren().add(en.getVue().getSprite());
		}
		
		pane.getChildren().add(dialog);
		dialog.setVisible(false);
		init();
		getGameLoop().play();
	}

	//GameLoop
	private void init() {

		setGameLoop(new Timeline());
		getGameLoop().setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.018), //environ 60 FPS
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda

				(ev ->{
//					TODO gérer le changement de sprite
//					switch (this.getJeanMichel().getOrientation()) {
//					case 0:
//					break;
//					case 1: 
//					break;
//					case 2: 
//					break;
//					case 3: 
//					break;
//					}
					if (jeu.collisionObjet()) {
						//TODO le faire dans le modèle
						this.pane.getChildren().remove(vueitem.getSprite());
						this.getJeanMichel().pointsVieProperty().set(this.getJeanMichel().getPointsVie()+1);
						if (this.getJeanMichel().getPointsVie() > 5) this.getJeanMichel().pointsVieProperty().set(5);

					}
					if(this.jeu.getJeanMichel().getPointsVie() == 0){
						System.out.println("Vous êtes mort");
						heart0.setImage(new Image("file:./src/app/img/heartempty.png"));
						this.pane.getChildren().remove(vueHeros.getSprite());
						
						dialog.setContentText("GAME OVER");
						dialog.setPrefWidth(110);
						dialog.setPrefHeight(20);
						dialog.setLayoutY(510/2 - dialog.getPrefHeight()/2);
						dialog.setLayoutX(510/2 - dialog.getPrefWidth()/2);
						dialog.setOpacity(.75);
						dialog.setVisible(true);
						getGameLoop().stop();

					}
					else
						this.jeu.update();
						if (Jeu.ennemiRetiré != null) {
							pane.getChildren().remove(Jeu.ennemiRetiré.getVue().getSprite());
							Jeu.ennemiRetiré=null;
							if(jeu.getEnnemis().size() == 4) {					  
							dialog.setContentText("Bravo, tu as tué\nton premier cactus");
							dialog.setPrefWidth(170);
							dialog.setPrefHeight(72);
							dialog.setLayoutY(400);
							dialog.setLayoutX(500-dialog.getPrefWidth());
							dialog.setOpacity(.59);
							dialog.setVisible(true);
							}else
								dialog.setVisible(false);
						}

					
				})
				);
		getGameLoop().getKeyFrames().add(kf);

	}

	private Timeline getGameLoop() {
		return gameLoop;
	}

	private void setGameLoop(Timeline gameLoop) {
		this.gameLoop = gameLoop;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public JeanMichel getJeanMichel() {
		return this.jeu.getJeanMichel();
	}

	public void verifVie() {
		int vieJM = jeu.getJeanMichel().getPointsVie();
		if (vieJM >= 5) {
			heart4.setImage(heart0.getImage());
		}else {
			heart4.setImage(new Image("file:./src/app/img/heartempty.png"));
		}
		if (vieJM >= 4) {
			heart3.setImage(heart0.getImage());
		}else {
			heart3.setImage(new Image("file:./src/app/img/heartempty.png"));
		}
		if (vieJM >= 3) {
			heart2.setImage(heart0.getImage());
		}else {
			heart2.setImage(new Image("file:./src/app/img/heartempty.png"));
		}
		if (vieJM >= 2) {
			heart1.setImage(heart0.getImage());
		}else {
			heart1.setImage(new Image("file:./src/app/img/heartempty.png"));
		}
		if (vieJM >= 1) {
			heart0.setImage(heart0.getImage());
		}
	}

}

