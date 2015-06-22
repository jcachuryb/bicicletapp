package co.edu.unal.biciapp.client.modules;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RegForm {

	public VerticalPanel vertPanel;

	private TextInfo firstName;

	private TextInfo lastName;

	private TextInfo address;

	private TextInfo document;

	private TextInfo phoneNumber;

	private DateInfo dateField;

	public Button saveBtn;

	public Button cancelBtn;

	public RegForm() {
		Label label = new Label("Datos del usuario");
		label.addStyleName("title");

		firstName = new TextInfo("Nombre");
		lastName = new TextInfo("Apellido");
		document = new TextInfo("Documento");
		phoneNumber = new TextInfo("Teléfono");
		address = new TextInfo("Dirección");
		dateField = new DateInfo("Fecha de Nacimiento");
		saveBtn = new Button("Guardar");
		cancelBtn = new Button("Cancelar");
		
		firstName.getLabel().setWidth("70px");
		lastName.getLabel().setWidth("70px");
		document.getLabel().setWidth("70px");
		phoneNumber.getLabel().setWidth("70px");
		address.getLabel().setWidth("70px");
		
		
		saveBtn.getElement().setId("btn-guardar");
		cancelBtn.getElement().setId("btn-cancelar");

		vertPanel = new VerticalPanel();
		vertPanel.add(label);
		vertPanel.add(firstName.getPanel());
		vertPanel.add(lastName.getPanel());
		vertPanel.add(document.getPanel());
		vertPanel.add(phoneNumber.getPanel());
		vertPanel.add(address.getPanel());
		// vertPanel.add(dateField.getPanel());
		HorizontalPanel hp = new HorizontalPanel();
		hp.getElement().setPropertyString("align", "right");
		vertPanel.addStyleName("regPanel");
		hp.add(saveBtn);
		hp.add(cancelBtn);
		vertPanel.add(hp);

	}

	public void setVisibility(boolean v){
		this.vertPanel.setVisible(v); 
	}
	
	public void showOnlyNames(boolean b) {
		document.getPanel().setVisible(b);
		address.getPanel().setVisible(b);
		phoneNumber.getPanel().setVisible(b);
		saveBtn.setVisible(b);
		cancelBtn.setVisible(b);
	}

	public void showButtons(boolean show) {
		saveBtn.setVisible(show);
		cancelBtn.setVisible(show);
	}

	public void setButtonHandler(Button btn, ClickHandler handler) {
		btn.addClickHandler(handler);
	}

	public TextInfo getFirstName() {
		return firstName;
	}

	public TextInfo getLastName() {
		return lastName;
	}

	public TextInfo getAddress() {
		return address;
	}

	public TextInfo getDocument() {
		return document;
	}

	public TextInfo getPhoneNumber() {
		return phoneNumber;
	}

	public DateInfo getDateField() {
		return dateField;
	}

}
