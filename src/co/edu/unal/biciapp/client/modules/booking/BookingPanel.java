package co.edu.unal.biciapp.client.modules.booking;

import java.util.Date;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class BookingPanel {
	private Label label;
	private DatePicker datePick;
	private Button button;

	public VerticalPanel vertPanel;

	public BookingPanel() {
		vertPanel = new VerticalPanel();
		label = new Label("label");
		button = new Button("botón");
		datePick = new DatePicker();
		HorizontalPanel h = new HorizontalPanel();

		h.add(label);
		h.add(button);
		datePick.setCurrentMonth(new Date());
		label.setWidth("135px");
		label.getElement().setAttribute("font-weight", "bold");
		vertPanel.add(h);
		vertPanel.add(datePick);
	}

	public DatePicker getDatePick() {
		return datePick;
	}

	public Button getButton() {
		return button;
	}

	public void setLabelMessage(String m) {
		label.setText(m);
	}

	public void setButtonMessage(String m) {
		button.setText(m);
	}

	public void setButtonStyle(String style) {
		button.getElement().setId(style);
	}

	public final static String reservaStyle = "btn-guardar";
	public final static String eliminarStyle = "btn-eliminar";
	public final static String MSG_BTN_RESERVAR = "Reserva";
	public final static String MSG_BTN_CANCELAR_RESERVA = "Eliminar";
	public final static String MSG_SIN_RESERVA = "¡Reserva una bici!";
	public final static String MSG_CON_RESERVA = "Ya tienes una reserva.";
}
