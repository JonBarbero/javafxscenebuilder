package ehu.isad.controller.ui;

import ehu.isad.Webguneak;
import ehu.isad.controller.dao.BusKud;
import ehu.isad.controller.dao.DatuakKud;
import ehu.isad.model.Webgunea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class WebguneKud implements Initializable {

  // Reference to the main application.
  private Webguneak mainApp;

  @FXML
  private TableView<Webgunea> tbData;

  @FXML
  private TableColumn<Webgunea, String> url;

  @FXML
  private TableColumn<Webgunea, String> cms;

  @FXML
  private TableColumn<Webgunea, String> version;

  @FXML
  private TableColumn<Webgunea, LocalDate> lastupdated;

  @FXML
  private TableColumn<Webgunea, String> screenshot;

  // add your data here from any source
  private ObservableList<Webgunea> taulaModels = FXCollections.observableArrayList(
          DatuakKud.getInstance().getWebguneak());

  public void setMainApp(Webguneak main) {
    this.mainApp = mainApp;
  }

  @FXML
  public void onClick(ActionEvent actionEvent) {
    System.out.println("Clicked");
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    tbData.setEditable(true);
    //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
    url.setCellValueFactory(new PropertyValueFactory<>("url"));
    cms.setCellValueFactory(new PropertyValueFactory<>("cms"));
    version.setCellValueFactory(new PropertyValueFactory<>("version"));
    lastupdated.setCellValueFactory(new PropertyValueFactory<>("lastupdated"));
    screenshot.setCellValueFactory(new PropertyValueFactory<>("screenshot"));

//
//    Callback<TableColumn<Webgunea, Integer>, TableCell<Webgunea, Integer>> defaultTextFieldCellFactory
//            = TextFieldTableCell.<Webgunea, Integer>forTableColumn(new IntegerStringConverter());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    lastupdated.setCellFactory((TableColumn<Webgunea, LocalDate> column) -> {
      return new TableCell<Webgunea, LocalDate>() {
        @Override
        protected void updateItem(LocalDate item, boolean empty) {
          super.updateItem(item, empty);
          if (item == null || empty) {
            setText(null);
          }
          else {
            setText(formatter.format(item));
          }
        }
      };
    });


//    lastupdated.setCellFactory(col -> {
//      TableCell<Webgunea, Integer> cell = defaultTextFieldCellFactory.call(col);
//      return cell ;
//    });

    //add your data to the table here.
    tbData.setItems(taulaModels);
  }

}
