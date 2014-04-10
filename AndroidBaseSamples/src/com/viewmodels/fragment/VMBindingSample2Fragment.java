package com.viewmodels.fragment;

import android.app.Activity;
import android.widget.Button;

import com.adapters.UserSpinnerAdapter;
import com.androidbase.binding.enums.Bindings;
import com.androidbase.binding.properties.ObjectProperty;
import com.androidbase.injectors.ViewId;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.views.ABSpinner;
import com.androidbase.views.ABTextView;
import com.androidbasesamples.R;
import com.entity.User;

public class VMBindingSample2Fragment extends BaseViewModel {

	// region Properties

	@ViewId(id = R.id.fragment_bindingSample2_spinner_people)
	public ABSpinner spinner_People;

	@ViewId(id = R.id.fragment_bindingSample2_textView_firstName)
	public ABTextView textView_FirstName;

	@ViewId(id = R.id.fragment_bindingSample2_textView_lastName)
	public ABTextView textView_LastName;

	@ViewId(id = R.id.fragment_bindingSample2_textView_age)
	public ABTextView textView_Age;

	@ViewId(id = R.id.test)
	public Button button;

	public final User user = new User();

	public final ObjectProperty UserProperty = new ObjectProperty(this, "user",
			Bindings.SelectedItem);

	private final User[] users = new User[] {
			new User().setFirstName("David").setLastName("Torvalds").setAge(21),
			new User().setFirstName("John").setLastName("Jobs").setAge(32),
			new User().setFirstName("Micheal").setLastName("Carmack")
					.setAge(44) };

	public final UserSpinnerAdapter usersAdapter = new UserSpinnerAdapter(
			getActivity(), users);

	// endregion

	// region Constructors

	public VMBindingSample2Fragment(Activity activity) {
		super(activity);
	}

	// endregion

}
