package com.adapters;

import android.app.Activity;

import com.androidbase.adapters.ABaseAdapter;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.viewmodels.BaseNotify;
import com.androidbasesamples.R;
import com.entity.User;
import com.viewmodels.adapter.VMUserSpinnerAdapter;

public class UserSpinnerAdapter extends ABaseAdapter<User> {

	// region Constructors

	public UserSpinnerAdapter(Activity activity, User[] users) {
		super(activity, R.layout.adapter_user);

		updateItems(users);

	}

	// endregion

	// region Overrides

	@Override
	protected VMUserSpinnerAdapter getViewModel() {
		return new VMUserSpinnerAdapter();
	}

	@Override
	protected void initializeItem(int position, BaseNotify inputViewModel,
			User item) {

		VMUserSpinnerAdapter viewModel = CS.as(inputViewModel);

		viewModel.textView_Header.setText(item.getFirstName() + " "
				+ item.getLastName());
	}

	// endregion

}
