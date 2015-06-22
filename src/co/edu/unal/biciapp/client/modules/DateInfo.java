package co.edu.unal.biciapp.client.modules;

import java.util.Date;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DatePicker;

public class DateInfo {
	private Label label;
	private HorizontalPanel panel;
	private DatePicker datePicker;

	public DateInfo(String labelDesc) {
		datePicker = new DatePicker();
		datePicker.setCurrentMonth(new Date());
		label = new Label(labelDesc);
		panel = new HorizontalPanel();
		panel.add(label);
		panel.add(datePicker);
	}

	public Label getLabel() {
		return label;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

}
