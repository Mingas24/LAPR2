//package lapr.project.gpsd.ui;
//
//import java.time.LocalDate;
//import java.util.List;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.stage.Stage;
//import lapr.project.gpsd.model.PrestadorServico;
//import lapr.project.gpsd.controller.IndicarDisponibilidadeDiariaController;
//import lapr.project.gpsd.model.Data;
//import lapr.project.gpsd.model.Tempo;
//
//
//public class IndicarDisponibilidadeDiariaUI {
//
//    @FXML
//    private Button buttonAdicionar;
//    @FXML
//    private ComboBox<Tempo> horaInicio;
//    @FXML
//    private ComboBox<Tempo> horaFim;
//    @FXML
//    private Button btnSair;
//    @FXML
//    private Button btnConcluido;
//    @FXML
//    private DatePicker dataInicio;
//    @FXML
//    private DatePicker dataFim;
//    
//    private PrestadorServico m_oPrestador;
//    IndicarDisponibilidadeDiariaController m_oIndicarDisponibilidade = new IndicarDisponibilidadeDiariaController();
//    List<Tempo> lt = m_oIndicarDisponibilidade.getHorasTrabalho();
//   
//    public void initialize() {
//        m_oPrestador = m_oIndicarDisponibilidade.indicarNovasDisponibilidades();
//        horaInicio.setItems(FXCollections.observableArrayList(lt));
//        horaFim.setItems(FXCollections.observableArrayList(lt));
//    }
//
//    @FXML
//    private void actionAdicionar(ActionEvent event) {
//        Tempo horaI = horaInicio.getValue();
//        Tempo horaF = horaFim.getValue();
//        LocalDate dat1 = dataInicio.getValue();
//        int ano = dat1.getYear();
//        int mes = dat1.getMonthValue();
//        int dia = dat1.getDayOfMonth();
//        Data dataI = new Data(ano, mes, dia);
//        LocalDate dat2 = dataInicio.getValue();
//        ano = dat2.getYear();
//        mes = dat2.getMonthValue();
//        dia = dat2.getDayOfMonth();
//        Data dataF = new Data(ano, mes, dia);
//        m_oIndicarDisponibilidade.novoPeriodoDisponiblidade(horaI, horaF, dataI, dataF);
//    }
//
//    @FXML
//    private void actionSair(ActionEvent event) {
//        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
//    }
//
//    @FXML
//    private void actionConcluido(ActionEvent event) {
//    }   
//}