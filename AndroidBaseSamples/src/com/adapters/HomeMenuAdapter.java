package com.adapters;

import android.app.Activity;

import com.androidbase.adapters.ABaseAdapter;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.viewmodels.BaseNotify;
import com.androidbasesamples.R;
import com.entity.HomeMenuItem;
import com.viewmodels.adapter.VMHomeMenuAdapter;

public class HomeMenuAdapter extends ABaseAdapter<HomeMenuItem> {

	// region Constructors

	public HomeMenuAdapter(Activity activity) {
		super(activity, R.layout.adapter_home_menu);
	}

	// endregion

	// region Overrides

	@Override
	protected VMHomeMenuAdapter getViewModel() {
		return new VMHomeMenuAdapter();
	}

	@Override
	protected void initializeItem(int position, BaseNotify inputViewModel,
			HomeMenuItem item) {

		VMHomeMenuAdapter viewModel = CS.as(inputViewModel);

		viewModel.textView_Header.setText(item.getHeader());
	}

	// endregion

}
