package com.entity;

public final class HomeMenuItem {

	// region Consts

	public static final int ID_HelloWorld = 0;
	public static final int ID_BindingSample1 = 1;
	public static final int ID_BindingSample2 = 2;

	public static final int[] IDs = new int[] { HomeMenuItem.ID_HelloWorld,
			HomeMenuItem.ID_BindingSample1, HomeMenuItem.ID_BindingSample2 };

	// endregion

	// region Properties

	private int id;
	private String header;

	// endregion

	// region Constructors

	public HomeMenuItem(int id, String header) {
		this.id = id;
		this.header = header;
	}

	// endregion

	// region Get / Set

	public int getId() {
		return id;
	}

	public String getHeader() {
		return header;
	}

	// endregion

}
