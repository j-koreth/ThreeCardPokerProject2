import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class Character {
    VBox characterAvatar;
    ImageView characterPic;

    boolean hidden;

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

        hidden = true;
    }

    public void changeTheme(String pic, String backcover) {
        characterPic.setImage(new Image(pic));
        this.backcover = backcover;

        if (hidden) {
            card.setPic(backcover);
            card1.setPic(backcover);
            card2.setPic(backcover);
        }
    }
    public void setCards(ArrayList<Card> cards){
        String cardUrl = "JPEG/" + cards.get(0).getValue() + cards.get(0).getSuit() + ".jpg";
        String card2Url = "JPEG/" + cards.get(1).getValue() + cards.get(1).getSuit() + ".jpg";
        String card3Url = "JPEG/" + cards.get(2).getValue() + cards.get(2).getSuit() + ".jpg";
        try{
            card.setPic(cardUrl);
            card1.setPic(card2Url);
            card2.setPic(card3Url);
        }
        catch (IllegalArgumentException e){
            System.out.println(cardUrl);
            System.out.println(card2Url);
            System.out.println(card3Url);
        }

    }

    public void hideCards(){
        card.setPic(backcover);
        card1.setPic(backcover);
        card2.setPic(backcover);

        hidden = true;
    }

}
