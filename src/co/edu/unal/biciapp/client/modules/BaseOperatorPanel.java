package co.edu.unal.biciapp.client.modules;

import co.edu.unal.biciapp.shared.BookingInfo;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BaseOperatorPanel {
	public VerticalPanel vertPanel;
	private TextInfo userInfo;
	private Button btnGet;
	private Button btnActivate;
	private Label userName;
	private Label userDoc;

	public BaseOperatorPanel() {
		Label label = new Label("Buscador de reservas.");
		label.addStyleName("title");
		
		vertPanel = new VerticalPanel();
		userInfo = new TextInfo("Email usuario");
		userInfo.getTextForm().setWidth("250px");
		userName = new Label("Nombre");
		userDoc = new Label("Documento");
		btnGet = new Button("Buscar");
		btnActivate = new Button("Activar");

		HorizontalPanel hp = new HorizontalPanel();
		HorizontalPanel buttons = new HorizontalPanel();
		VerticalPanel vp = new VerticalPanel();
		vp.add(userName);
		vp.add(userDoc);
		buttons.add(btnGet);
		buttons.add(btnActivate);

		btnActivate.getElement().setId("btn-guardar");
		btnGet.getElement().setId("btn-aceptar");
		
		btnActivate.setEnabled(false);
		
		vertPanel.addStyleName("regPanel");

		hp.add(userInfo.getPanel());
		hp.add(vp);
		vertPanel.add(label);
		vertPanel.add(hp);
		vertPanel.add(buttons);

	}

	public TextInfo getUserInfo() {
		return userInfo;
	}

	public Button getBtnGet() {
		return btnGet;
	}

	public Button getBtnActivate() {
		return btnActivate;
	}

	public Label getUserName() {
		return userName;
	}

	public Label getUserDoc() {
		return userDoc;
	}

}
