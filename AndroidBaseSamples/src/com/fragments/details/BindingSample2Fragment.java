package com.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbase.binding.interfaces.IPropertyChangedListener;
import com.androidbase.binding.properties.PropertyBinding;
import com.androidbase.fragments.BaseFragment;
import com.androidbasesamples.R;
import com.viewmodels.fragment.VMBindingSample2Fragment;

public class BindingSample2Fragment extends BaseFragment {

	// region Properties

	private VMBindingSample2Fragment viewModel;

	// endregion

	// region Life Cycle

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState,
				R.layout.fragment_binding_sample_2,
				R.string.tag_fragment_bindingSample2);
	}

	// endregion

	// region Overrides

	@Override
	public void initializeValues() {
		super.initializeValues();

		viewModel.spinner_People.setAdapter(viewModel.usersAdapter);

		viewModel.spinner_People.binding(viewModel.UserProperty);

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
	public VMBindingSample2Fragment getViewModel() {

		if (viewModel == null) {
			viewModel = new VMBindingSample2Fragment(getActivity());
		}

		return viewModel;
	}

	// endregion

}
