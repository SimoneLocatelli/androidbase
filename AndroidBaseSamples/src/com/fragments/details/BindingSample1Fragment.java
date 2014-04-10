package com.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbase.binding.interfaces.IPropertyChangedListener;
import com.androidbase.binding.properties.PropertyBinding;
import com.androidbase.fragments.BaseFragment;
import com.androidbasesamples.R;
import com.viewmodels.fragment.VMBindingSample1Fragment;

public class BindingSample1Fragment extends BaseFragment {

	// region Properties

	private VMBindingSample1Fragment viewModel;

	// endregion

	// region Life Cycle

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState,
				R.layout.fragment_binding_sample_1,
				R.string.tag_fragment_bindingSample1);
	}

	// endregion

	// region Overrides

	@Override
	public void initializeValues() {
		super.initializeValues();

		viewModel.editText_FirstName.binding(viewModel.user.FirstNameProperty);
		viewModel.editText_LastName.binding(viewModel.user.LastNameProperty);
		viewModel.editText_Age.binding(viewModel.user.AgeProperty);

		viewModel.user.FirstNameProperty
				.addPropertyChangedListener(new IPropertyChangedListener<String>() {

					@Override
					public void onPropertyChanged(
							PropertyBinding<String> property) {
						viewModel.textView_FirstName.setText(property
								.getValue());
					}
				});
		viewModel.user.LastNameProperty
				.addPropertyChangedListener(new IPropertyChangedListener<String>() {

					@Override
					public void onPropertyChanged(
							PropertyBinding<String> property) {
						viewModel.textView_LastName.setText(property.getValue());
					}
				});
		viewModel.user.AgeProperty
				.addPropertyChangedListener(new IPropertyChangedListener<Integer>() {

					@Override
					public void onPropertyChanged(
							PropertyBinding<Integer> property) {
						viewModel.textView_Age.setText(property.getValue());
					}
				});
	}

	// endregion

	// region Get / Set

	@Override
	public VMBindingSample1Fragment getViewModel() {

		if (viewModel == null) {
			viewModel = new VMBindingSample1Fragment();
		}

		return viewModel;
	}

	// endregion

}
