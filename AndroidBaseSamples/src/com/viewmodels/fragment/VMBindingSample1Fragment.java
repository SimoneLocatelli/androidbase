package com.viewmodels.fragment;

import com.androidbase.injectors.ViewId;
import com.androidbase.viewmodels.BaseViewModel;
import com.androidbase.views.ABEditText;
import com.androidbase.views.ABTextView;
import com.androidbasesamples.R;
import com.entity.User;

public class VMBindingSample1Fragment extends BaseViewModel {

	// region Properties

	@ViewId(id = R.id.fragment_bindingSample1_editText_firstName)
	public ABEditText editText_FirstName;

	@ViewId(id = R.id.fragment_bindingSample1_editText_lastName)
	public ABEditText editText_LastName;

	@ViewId(id = R.id.fragment_bindingSample1_editText_age)
	public ABEditText editText_Age;

	@ViewId(id = R.id.fragment_bindingSample1_textView_firstName)
	public ABTextView textView_FirstName;

	@ViewId(id = R.id.fragment_bindingSample1_textView_lastName)
	public ABTextView textView_LastName;

	@ViewId(id = R.id.fragment_bindingSample1_textView_age)
	public ABTextView textView_Age;

	public final User user = new User();

	// endregion

}
