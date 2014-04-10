package com.viewmodels.adapter;

import com.androidbase.injectors.ViewId;
import com.androidbase.viewmodels.BaseNotify;
import com.androidbase.views.ABTextView;
import com.androidbasesamples.R;

public class VMHomeMenuAdapter extends BaseNotify {

	// region Properties

	@ViewId(id = R.id.adapter_homeMenu_textView_header)
	public ABTextView textView_Header;

	// endregion

}
