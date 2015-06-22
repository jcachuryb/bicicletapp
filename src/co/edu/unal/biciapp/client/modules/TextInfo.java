package co.edu.unal.biciapp.client.modules;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class TextInfo {
	private TextBox textForm;
	private Label label;
	private HorizontalPanel panel;

	public TextInfo(String labelDesc) {
		textForm = new TextBox();
		textForm.getElement().getStyle().setProperty("align", "right");
		
		label = new Label(labelDesc +":" );
		label.addStyleName("label");
		
		panel = new HorizontalPanel();
		panel.addStyleName("dialogVPanel");
		panel.add(label);
		panel.add(textForm);
	}

	public TextBox getTextForm() {
		return textForm;
	}

	public Label getLabel() {
		return label;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

}
