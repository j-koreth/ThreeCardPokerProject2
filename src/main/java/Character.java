import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class Character {
    VBox characterAvatar;
    ImageView characterPic;

    CardGui card;
    CardGui card1;
    CardGui card2;

    String backcover;
    Label name;
    HBox cardContainer;
    ArrayList<VBox> cards;

    public Character(String pic, String backcover, String inputName) {
        characterPic = new ImageView(new Image(pic));
        characterPic.setFitHeight(175);
        characterPic.setFitWidth(150);
        characterPic.setPreserveRatio(true);

        characterAvatar = new VBox();
        characterAvatar.getChildren().add(characterPic);
        characterAvatar.getStyleClass().addAll("avatar");

        this.backcover = backcover;
        name = new Label(inputName);
        name.getStyleClass().add("headerLabels");

        card = new CardGui(backcover);
        card1 = new CardGui(backcover);
        card2 = new CardGui(backcover);

        cardContainer = new HBox();

        cardContainer.getChildren().add(card.card);
        cardContainer.getChildren().add(card1.card);
        cardContainer.getChildren().add(card2.card);

    }

    public void changeTheme(String pic, String backcover){
        characterPic.setImage(new Image(pic));
        this.backcover = backcover;

        card.setPic(backcover);
        card1.setPic(backcover);
        card2.setPic(backcover);

//        characterAvatar = new VBox();
//        characterAvatar.getChildren().add(characterPic);
//        characterAvatar.getStyleClass().addAll("avatar");
//

        setBackCover();
    }

    void setBackCover(){


    }
}
