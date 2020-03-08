import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class Character {
    ImageView characterAvatar;
    String backcover;
    Label name;

    public Character(String pic, String backcover, String inputName) {
        characterAvatar = new ImageView(new Image(pic));
        characterAvatar.setFitHeight(175);
        characterAvatar.setFitWidth(150);
        characterAvatar.setPreserveRatio(true);

        this.backcover = backcover;
        name = new Label(inputName);
        name.getStyleClass().add("headerLabels");
    }

    public void changeTheme(String pic, String backcover){
        characterAvatar = new ImageView(new Image(pic));

        this.backcover = backcover;
    }

    public Label getName(){
        return name;
    }

    public VBox getImage(){
        VBox vbox = new VBox();
        vbox.getChildren().add(characterAvatar);
        vbox.getStyleClass().add("avatar");
        return vbox;
    }

    public ArrayList<VBox> getBackCards(){

        CardGui back = new CardGui(backcover);
        CardGui back1 = new CardGui(backcover);
        CardGui back2 = new CardGui(backcover);

        ArrayList backs = new ArrayList<VBox>();
        backs.add(back.card);
        backs.add(back1.card);
        backs.add(back2.card);

        return backs;
    }
}
