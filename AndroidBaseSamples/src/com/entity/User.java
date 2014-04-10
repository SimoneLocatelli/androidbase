package com.entity;

import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.properties.IntegerProperty;
import com.androidbase.binding.properties.StringProperty;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.interfaces.ICopiable;

public final class User implements ICopiable {

	// region Properties

	private String firstName;
	private String lastName;
	private int age;

	public final StringProperty FirstNameProperty = new StringProperty(this,
			"firstName", Bindings.Text);
	public final StringProperty LastNameProperty = new StringProperty(this,
			"lastName", Bindings.Text);
	public final IntegerProperty AgeProperty = new IntegerProperty(this, "age",
			Bindings.Text);

	// endregion

	// region Get / Set

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		FirstNameProperty.setValue(firstName);

		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		LastNameProperty.setValue(lastName);

		return this;
	}

	public int getAge() {
		return age;
	}

	public User setAge(int age) {
		this.age = age;
		AgeProperty.setValue(age);

		return this;
	}

	// endregion

	// region ICopiable

	@Override
	public void copy(Object value) {

		User user = CS.as(value);

		if (user == null) {
			user = new User();
		}

		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setAge(user.getAge());

	}

	// endregion
}
