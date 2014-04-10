/*
 * Copyright 2013 Simone Locatelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidbase.adapters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidbase.app.ABApplication;
import com.androidbase.dotnet.alias.CS;
import com.androidbase.injectors.ViewInjector;
import com.androidbase.viewmodels.BaseNotify;

public abstract class ABaseAdapter<T> extends BaseAdapter {

	// region Properties

	protected int resourceLayout;
	protected List<T> items = Collections.emptyList();

	// endregion

	// region Constructors

	public ABaseAdapter(int resourceLayout) {
		super();
		this.resourceLayout = resourceLayout;
	}

	public ABaseAdapter(int resourceLayout, List<T> items) {
		super();
		this.resourceLayout = resourceLayout;
		this.items = items;
	}

	// endregion

	// region Methods

	@Override
	public int getCount() {

		if (items == null) {
			return 0;
		}

		return items.size();
	}

	@Override
	public T getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void updateItems(List<T> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	public void updateItems(T[] items) {
		updateItems(Arrays.asList(items));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = ABApplication.getStaticLayoutInflater().inflate(
					resourceLayout, parent, false);

			BaseNotify viewModel = getViewModel();

			ViewInjector.inject(convertView, viewModel);

			convertView.setTag(viewModel);
		}

		initializeItem(position, (BaseNotify) convertView.getTag(),
				getItem(position));

		return convertView;
	}

	protected <V extends View> V findViewById(View view, int id) {
		V item = CS.as(view.findViewById(id));

		return item;
	}

	protected abstract BaseNotify getViewModel();

	protected abstract void initializeItem(int position,
			BaseNotify inputViewModel, T item);

	// endregion

}
