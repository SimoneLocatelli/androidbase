package com.viewmodels.fragment;

import com.androidbase.viewmodels.BaseViewModel;

public class VMHomeDetailFragment extends BaseViewModel {

	// region Properties

	private String currentFragmentHeader;

	public String getCurrentFragmentHeader() {
		return currentFragmentHeader;
	}

	public void setCurrentFragmentHeader(String currentFragmentHeader) {
		this.currentFragmentHeader = currentFragmentHeader;
	}

	// endregion

}
