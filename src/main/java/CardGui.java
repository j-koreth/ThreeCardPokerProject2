import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardGui {
    VBox card;
    ImageView pic;

    public CardGui(String picUrl){
        pic = new ImageView(new Image(picUrl));
        card = new VBox();

        pic.setFitHeight(100);
        pic.setFitWidth(65);
        card.getChildren().add(pic);
        card.getStyleClass().add("card");
    }

    void setPic(String picUrl){
        pic.setImage(new Image(picUrl));
    }
}
