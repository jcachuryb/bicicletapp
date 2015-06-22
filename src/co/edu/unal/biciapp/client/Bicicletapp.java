package co.edu.unal.biciapp.client;

import java.util.Date;

import co.edu.unal.biciapp.client.modules.BaseOperatorPanel;
import co.edu.unal.biciapp.client.modules.RegForm;
import co.edu.unal.biciapp.client.modules.booking.BookingPanel;
import co.edu.unal.biciapp.shared.BaseInfo;
import co.edu.unal.biciapp.shared.BookingInfo;
import co.edu.unal.biciapp.shared.LoginInfo;
import co.edu.unal.biciapp.shared.UserInfo;
import co.edu.unal.biciapp.shared.many2many.guice.IUser;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Bicicletapp implements EntryPoint {
	// TODO #05: add constants for OAuth2 (don't forget to update
	// GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String GOOGLE_CLIENT_ID = "121501725692-ipik75m6lhugrnatf5r2cvmufs8rg3on.apps.googleusercontent.com";
	private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	private static final String LABEL_MESSAGE = "Rol de usuario: ";
	// TODO #05:> end

	// TODO #06: define controls for login
	private final HorizontalPanel loginPanel = new HorizontalPanel();
	private final Anchor signInLink = new Anchor("");
	private final Image loginImage = new Image();
	// TODO #06:> end

	public static LoginInfo loginInfo = new LoginInfo();
	public static BookingInfo bookingInfo = new BookingInfo();
	public static UserInfo userInfo = new UserInfo("", "", "");
	public static UserInfo backUpInfo = new UserInfo("", "", "");
	public static BaseInfo baseInfo = new BaseInfo();
	public static String userID = "";

	// Controls for changes in user Data.
	private final Button btnUserInfo = new Button();
	private final Button btnRefresh = new Button();
	private final RegForm regForm = new RegForm();
	private final BookingPanel bookingPanel = new BookingPanel();
	private final BaseOperatorPanel baseOpPanel = new BaseOperatorPanel();
	private final Label mainTitle = new Label();

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	// TODO #07: add helper methods for Login, Logout and AuthRequest

	private void loadLogin(final LoginInfo loginInfo) {
		signInLink.setHref(loginInfo.getLoginUrl());
		signInLink.setText("Inicia sesión");
		signInLink.setTitle("Salir");
	}

	private void loadLogout(final LoginInfo enteringLoginInfo) {
		loginInfo.setLogoutUrl(enteringLoginInfo.getLogoutUrl());
		signInLink.setHref(enteringLoginInfo.getLogoutUrl());
		signInLink.setText(enteringLoginInfo.getName());
		signInLink.setTitle("Sign out");
	}

	private void addGoogleAuthHelper() {
		AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
				.withScopes(PLUS_ME_SCOPE);
		AUTH.login(req, new Callback<String, Throwable>() {
			@Override
			public void onSuccess(final String token) {

				if (!token.isEmpty()) {
					greetingService.loginDetails(token,
							new AsyncCallback<UserInfo>() {
								@Override
								public void onFailure(final Throwable caught) {
									GWT.log("loginDetails -> onFailure");
								}

								@Override
								public void onSuccess(
										final UserInfo enteringUserInfo) {
									userID = enteringUserInfo.getEmail();
									userInfo.setEmail(userID);
									userInfo.setName(enteringUserInfo.getName());
									userInfo.setLastName(enteringUserInfo
											.getLastName());
									userInfo.setRole(enteringUserInfo.getRole());
									userInfo.setDocument(enteringUserInfo
											.getDocument());
									userInfo.setAddress(enteringUserInfo
											.getAddress());
									userInfo.setPhoneNumber(enteringUserInfo
											.getPhoneNumber());

									bookingInfo.setUserID(userID);
									Window.alert(userInfo.toString());
									
									signInLink.setText(enteringUserInfo
											.getName());
									signInLink.setStyleName("login-area");
									loginImage.setUrl(enteringUserInfo
											.getPictureURL());
									loginImage.setVisible(false);
									loginPanel.add(loginImage);
									loginImage
											.addLoadHandler(new LoadHandler() {
												@Override
												public void onLoad(
														final LoadEvent event) {
													final int newWidth = 24;
													final com.google.gwt.dom.client.Element element = event
															.getRelativeElement();
													if (element.equals(loginImage
															.getElement())) {
														final int originalHeight = loginImage
																.getOffsetHeight();
														final int originalWidth = loginImage
																.getOffsetWidth();
														if (originalHeight > originalWidth) {
															loginImage
																	.setHeight(newWidth
																			+ "px");
														} else {
															loginImage
																	.setWidth(newWidth
																			+ "px");
														}
														loginImage
																.setVisible(true);
													}
												}
											});
									greetingService.loadUser(userID,
											new AsyncCallback<IUser>() {

												@Override
												public void onSuccess(
														IUser result) {
													if (result != null) {
														Window.alert("Cargando usuario.");
														fromIUser2UserInfo(
																userInfo,
																result);
														loadUserModules();
														loadUserData();
													} else {
														greetingService
																.saveUser(
																		userInfo,
																		new AsyncCallback<Boolean>() {

																			@Override
																			public void onSuccess(
																					Boolean result) {
																				if (result) {
																					Window.alert("Nuevo usuario creado.");
																					loadUserData();
																				}
																			}

																			@Override
																			public void onFailure(
																					Throwable caught) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub

																			}
																		});
													}

													loadUserModules();

												}

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated
													// method stub

												}
											});
								}
							});

				}
			}

			@Override
			public void onFailure(final Throwable caught) {
				GWT.log("Error -> loginDetails\n" + caught.getMessage());
			}
		});

	}
	
	public void setTotalVisibility(boolean visible){
		mainTitle.setVisible(visible);
		btnUserInfo.setVisible(visible);
		btnRefresh.setVisible(visible);
		regForm.vertPanel.setVisible(visible);
		bookingPanel.vertPanel.setVisible(visible);
		baseOpPanel.vertPanel.setVisible(visible);
	}

	public void loadUserModules() {
		setTotalVisibility(true);
		
		switch (userInfo.getRole()) {
		case UserInfo.ROLE_NORMAL:
			baseOpPanel.vertPanel.setVisible(false);
			if (userInfo
					.hasABooking()) {
				greetingService
						.loadBooking(
								userInfo.getEmail(),
								new AsyncCallback<BookingInfo>() {

									@Override
									public void onSuccess(
											BookingInfo result) {
										if (result != null) {
											Window.alert("Tiene Reserva");
											bookingInfo = result;
											bookingInfo
													.setAvailability(false);
											updateBookingPanel();
										}
									}

									@Override
									public void onFailure(
											Throwable caught) {
										Window.alert("Error actualizando reserva.");

									}
								});
			}
			bookingPanel.vertPanel.setVisible(true);
			break;
		default:
			bookingPanel.vertPanel.setVisible(false);
			baseOpPanel.getBtnGet().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String search = baseOpPanel.getUserInfo().getTextForm()
							.getText();
					if (search.length() > 6) {
						greetingService.loadBookingByOperator(search,
								new AsyncCallback<BookingInfo>() {

									@Override
									public void onSuccess(BookingInfo result) {
										if (result != null) {
											baseOpPanel.getBtnActivate()
													.setEnabled(true);
											baseOpPanel.getUserName().setText(
													result.userName);
											baseOpPanel.getUserDoc().setText(
													result.userDoc);

										} else {
											Window.alert("El usuario no tiene una reserva.");
										}
									}

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub

									}
								});
					}

				}
			});
			baseOpPanel.getBtnActivate().addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					String search = baseOpPanel.getUserInfo().getTextForm()
							.getText();
					if (search.length() > 6) {
						greetingService.cancelBooking(search, new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								if (result) {
									Window.alert("Reserva activada con éxito.");
									restartBookingSearcher();
								}
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});
					}

				}
			});
			break;
		}
	}
	
	public void restartBookingSearcher(){
		baseOpPanel.getUserInfo().getTextForm().setText("");
		baseOpPanel.getBtnActivate().setEnabled(false);
		baseOpPanel.getUserDoc().setText("Documento");
		baseOpPanel.getUserName().setText("Nombre");
	}

	public void loadUserData() {
		regForm.getFirstName().getTextForm().setText(userInfo.getName());
		regForm.getLastName().getTextForm().setText(userInfo.getLastName());
		regForm.getDocument().getTextForm().setText(userInfo.getDocument());
		regForm.getPhoneNumber().getTextForm()
				.setText(userInfo.getPhoneNumber());
		regForm.getAddress().getTextForm().setText(userInfo.getAddress());

		mainTitle.setText(LABEL_MESSAGE + userInfo.getRole());

		// boolean panelsVis = UserInfo.ROLE_NORMAL.equals(userInfo.getRole());
		// bookingPanel.vertPanel.setVisible(panelsVis);
	}

	public void fromIUser2UserInfo(UserInfo ui, IUser user) {
		ui.setEmail(user.getEmail());
		ui.setName(user.getName());
		ui.setLastName(user.getLastName());
		ui.setDocument(user.getDocument());
		ui.setPhoneNumber(user.getPhoneNumber());
		ui.setAddress(user.getAddress());
		ui.setRole(user.getRole() != null ? user.getRole() : UserInfo.ROLE_NORMAL);
		ui.setBookingID(user.hasABooking());

	}

	// TODO #07:> end

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// TODO #08: create login controls
		HorizontalPanel btns = new HorizontalPanel();
		mainTitle.getElement().setId("role-title");
		RootPanel.get("role-stmt").add(mainTitle);

		btnUserInfo.setText("Info de usuario.");
		btnRefresh.setText("Actualizar");
		btnUserInfo.getElement().setId("btn-aceptar");
		btnRefresh.getElement().setId("btn-aceptar");

		btns.add(btnUserInfo);
		btns.add(btnRefresh);
		RootPanel.get("menu-bar").add(btns);
		RootPanel.get("user-info-panel").add(regForm.vertPanel);
		RootPanel.get("booking-panel").add(bookingPanel.vertPanel);
		RootPanel.get("booking-panel").add(baseOpPanel.vertPanel);
		
		setTotalVisibility(false);
		

		addRegFormPanelButtonsFunc();
		addBookingPanelButtonsFunc();
		updateBookingPanel();
		restartBookingSearcher();

		signInLink.getElement().setClassName("login-area");
		signInLink.setTitle("sign out");
		signInLink.setText("LOGIN");
		signInLink.setVisible(true);
		loginImage.getElement().setClassName("login-area");
		loginPanel.add(signInLink);
		RootPanel.get("loginPanelContainer").add(loginPanel);

		final StringBuilder userEmail = new StringBuilder();

		greetingService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					@Override
					public void onFailure(final Throwable caught) {
						GWT.log("login -> onFailure");
					}

					@Override
					public void onSuccess(final LoginInfo result) {
						if (result.getName() != null
								&& !result.getName().isEmpty()) {
							addGoogleAuthHelper();
							loadLogout(result);
						} else {
							loadLogin(result);
						}
						userEmail.append(result.getEmailAddress());

					}
				});
		// TODO #08:> end
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				loadUserData();
				updateBookingPanel();
			}
		});

		regForm.setVisibility(false);
		bookingPanel.vertPanel.setVisible(false);
	}

	private void addBookingPanelButtonsFunc() {
		bookingPanel.getButton().addClickHandler(new ClickHandler() {
			int timesClicked = 0;

			@Override
			public void onClick(ClickEvent event) {
				bookingInfo.setUserID(userID);
				Date selected = bookingPanel.getDatePick().getValue();
				if (bookingInfo.isAvailable()) {
					if (selected.after(new Date())) {
						bookingInfo.setDate(selected);
						greetingService.createBooking(bookingInfo,
								new AsyncCallback<Boolean>() {

									@Override
									public void onSuccess(Boolean result) {
										if (result) {
											Window.alert("Reserva creada.");
											bookingInfo.setAvailability(false);
										}
									}

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("Error al crear la reserva.");
									}
								});
					} else {
						Window.alert("Rservas a partir del día de mañana.");
					}
				} else {
					if (timesClicked < 1) {
						Window.alert("Haga clic de nuevo para cancelar la reserva.");
						timesClicked++;
					} else {
						timesClicked = 0;
						if (!bookingInfo.isAvailable()) {
							greetingService.cancelBooking(userID,
									new AsyncCallback<Boolean>() {

										@Override
										public void onSuccess(Boolean result) {
											Window.alert("Reserva cancelada.");
											bookingInfo.setDate(new Date());
											bookingInfo.setAvailability(true);

										}

										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Error cancelando reserva.");
										}
									});
						}
					}

				}
				updateBookingPanel();
			}
		});
	}

	public void updateBookingPanel() {

		if (bookingInfo.isAvailable()) {
			bookingPanel.setLabelMessage(BookingPanel.MSG_SIN_RESERVA);
			bookingPanel.setButtonMessage(BookingPanel.MSG_BTN_RESERVAR);
			bookingPanel.setButtonStyle(BookingPanel.reservaStyle);
		} else {
			bookingPanel.setLabelMessage(BookingPanel.MSG_CON_RESERVA);
			bookingPanel
					.setButtonMessage(BookingPanel.MSG_BTN_CANCELAR_RESERVA);
			bookingPanel.setButtonStyle(BookingPanel.eliminarStyle);
			bookingPanel.getDatePick().setValue(bookingInfo.getDate());
		}
	}

	private void addRegFormPanelButtonsFunc() {
		regForm.setButtonHandler(regForm.saveBtn, new MyHandler());
		regForm.setButtonHandler(regForm.cancelBtn, new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			}
		});

		btnUserInfo.addClickHandler(new ClickHandler() {
			private boolean isShowing = false;

			@Override
			public void onClick(ClickEvent event) {
				isShowing = !isShowing;
				regForm.setVisibility(isShowing);
			}
		});
	}

	class MyHandler implements ClickHandler {
		UserInfo user = new UserInfo();

		@Override
		public void onClick(ClickEvent event) {
			user.setEmail(userID);
			user.setName(regForm.getFirstName().getTextForm().getText()
					.toString());
			user.setLastName(regForm.getLastName().getTextForm().getText()
					.toString());
			user.setDocument(regForm.getDocument().getTextForm().getText()
					.toString());
			user.setPhoneNumber(regForm.getPhoneNumber().getTextForm()
					.getText().toString());
			user.setAddress(regForm.getAddress().getTextForm().getText()
					.toString());
			user.setRole(userInfo.getRole());
			greetingService.saveUser(user, new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						userInfo.setName(user.getName());
						userInfo.setLastName(user.getLastName());
						userInfo.setDocument(user.getDocument());
						userInfo.setAddress(user.getAddress());
						userInfo.setPhoneNumber(user.getPhoneNumber());
						userInfo.setRole(user.getRole());
						loadUserData();
						Window.alert("DATOS GUARDADOS.");
					} else {
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("ERROR AL GRABAR LOS DATOS.\n INTENTE MÁS TARDE.");
				}
			});

		}

	}
}
