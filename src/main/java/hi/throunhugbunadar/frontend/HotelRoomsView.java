package hi.throunhugbunadar.frontend;

import hi.throunhugbunadar.backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HotelRoomsView {
    @FXML
    private ChoiceBox<String> choiceBoxSort;
    @FXML
    private Slider sliderMinPrice;
    @FXML
    private Slider sliderMaxPrice;
    @FXML
    private Slider sliderMinStars;
    @FXML
    private Slider sliderMaxStars;
    @FXML
    private Button buttonFilter;
    @FXML
    private Button buttonReset;
    @FXML
    private ListView<Hotel> listViewHotelroomList;
    private HotelList hotelList;
    private static final String[] sort = {"Verði", "Stjörnum"}; //ATH vantar ennþá sort (raða eftir)
    private SearchView searchView;


    /**
     * Setja upp notendaviðmót.
     * @param hotelList listi af hótelum sem á að birta
     */
    public void frumstilla(HotelList hotelList) {
        this.hotelList = hotelList;

        ObservableList<String> sortNode = FXCollections.observableArrayList(sort);
        choiceBoxSort.setItems(sortNode);

        showList(hotelList);

        listViewHotelroomList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    nyrGluggi((Hotel)newValue);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Sýnir lista af hótelum.
     * @param hotelList listi af hótelum
     */
    private void showList(HotelList hotelList) {
        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
        listViewHotelroomList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Hotel hotel, boolean empty) {
                super.updateItem(hotel, empty);

                if (empty || hotel == null) {
                    setText(null);
                } else {
                    try {
                        setText(hotel.getName() + " - " + hotel.getStars() + " stjörnur - " + hotelList.getPrice(hotel) + " kr nóttin");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    /**
     * Sýnir bókunarglugga.
     * @param selectedHotel Hótel sem er valið úr lista
     * @throws IOException
     */
    private void nyrGluggi(Hotel selectedHotel) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        BookingView bv = loader.getController();

        stage.setTitle(selectedHotel.getName());
        Scene s = new Scene(root, 600, 730);
        stage.setScene(s);

        bv.setTenging(this);
        bv.frumstilla(selectedHotel, true);

        stage.show();
    }

    /**
     * Síar lista af hótelum.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     */
    public void filterMouseClicked(MouseEvent mouseEvent) {
        hotelList.filterByPrice((int)sliderMinPrice.getValue()*1000, (int)sliderMaxPrice.getValue()*1000);
        hotelList.filterByStars((int)sliderMinStars.getValue(), (int)sliderMaxStars.getValue());

        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
    }

    /**
     * Endurstillir síun á lista hótela.
     * @param mouseEvent atburðurinn sem kemur inn en er ónotaður
     */
    public void resetMouseClicked(MouseEvent mouseEvent) {
        sliderMinPrice.setValue(0);
        sliderMaxPrice.setValue(40);
        sliderMinStars.setValue(0);
        sliderMaxStars.setValue(5);

        hotelList.restartFilter();

        ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList(hotelList.getList());

        listViewHotelroomList.setItems(hotelObservableList);
    }

    public Criteria getCriteria(){
        return hotelList.getCriteria();
    }

    /**
     * Tengir HotelRoomsView við SearchView.
     * @param searchView SearchView
     */
    public void setTenging(SearchView searchView) {
        this.searchView = searchView;
    }

    public User getUser() {
        return searchView.getUser();
    }
}
