 /*
 * Copyright 2011 The Android Open Source Project
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

package dev.ronlemire.listdetailstemplateabs;

import dev.ronlemire.listdetailstemplateabs.R;
import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewFragment extends ListFragment {
	public static final String TAG = "ListViewFragment";
	public static final String LIST_FRAGMENT_BROADCAST_INTENT = "dev.ronlemire.listdetailstemplate.LIST_FRAGMENT_BROADCAST_INTENT";
	public static final String LIST_ITEM_SELECTED = "listItemSelected";
	private Activity parentActivity = null;
	int mCurCheckPosition = 0;

	@Override
	public void onInflate(Activity parentActivity, AttributeSet attrs, Bundle bundle) {
		Log.v(ListViewFragment.TAG,
				"in ListViewFragment onInflate. AttributeSet contains:");
		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			Log.v(ListViewFragment.TAG, "    " + attrs.getAttributeName(i) + " = "
					+ attrs.getAttributeValue(i));
		}
		super.onInflate(parentActivity, attrs, bundle);
	}

	@Override
	public void onAttach(Activity parentActivity) {
		Log.v(ListViewFragment.TAG, "in ListViewFragment onAttach; activity is: "
				+ parentActivity);
		super.onAttach(parentActivity);
		this.parentActivity = parentActivity;
	}

	@Override
	public void onCreate(Bundle bundle) {
		Log.v(ListViewFragment.TAG, "in ListViewFragment onCreate. Bundle contains:");
		if (bundle != null) {
			for (String key : bundle.keySet()) {
				Log.v(ListViewFragment.TAG, "    " + key);
			}
		} else {
			Log.v(ListViewFragment.TAG, "    myBundle is null");
		}
		super.onCreate(bundle);
		if (bundle != null) {
			// Restore last state for checked position.
			mCurCheckPosition = bundle.getInt("curChoice", 0);
		}
	}

	@Override
	public View onCreateView(LayoutInflater myInflater, ViewGroup container,
			Bundle bundle) {
		Log.v(ListViewFragment.TAG, "in ListViewFragment onCreateView. container is "
				+ container);
		return super.onCreateView(myInflater, container, bundle);
	}

	@Override
	public void onActivityCreated(Bundle bundle) {
		Log.v(ListViewFragment.TAG,
				"in ListViewFragment onActivityCreated. bundle contains:");
		if (bundle != null) {
			for (String key : bundle.keySet()) {
				Log.v(ListViewFragment.TAG, "    " + key);
			}
		} else {
			Log.v(ListViewFragment.TAG, "    bundle is null");
		}
		super.onActivityCreated(bundle);

		refresh(mCurCheckPosition);
	}
	
	public void refresh(int index) {
		mCurCheckPosition = index;
		
		// Populate list with our static array of titles.
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				parentActivity, R.array.list_titles,
				android.R.layout.simple_list_item_single_choice);
		setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setItemChecked(mCurCheckPosition, true);
		lv.performItemClick(lv, mCurCheckPosition, lv.getItemIdAtPosition(mCurCheckPosition));		
	}

	@Override
	public void onSaveInstanceState(Bundle bundle) {
		Log.v(ListViewFragment.TAG, "in ListViewFragment onSaveInstanceState");
		super.onSaveInstanceState(bundle);
		bundle.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		Log.v(ListViewFragment.TAG, "in ListViewFragment onListItemClick. pos = "
				+ position);
		mCurCheckPosition = position;

		// Intent intent = new Intent(LIST_FRAGMENT_BROADCAST_INTENT);
		// intent.putExtra("name", "ron");
		// intent.putExtra("age", 66);
		// getActivity().sendBroadcast(intent);

		// Intent intent = new Intent(LIST_FRAGMENT_BROADCAST_INTENT);
		// Bundle extras = new Bundle();
		// extras.putString("name", "clara");
		// extras.putInt("age", 93);
		// intent.putExtras(extras);
		// getActivity().sendBroadcast(intent);

		Intent intent = new Intent(LIST_FRAGMENT_BROADCAST_INTENT);
		Bundle extras = new Bundle();
		extras.putInt(LIST_ITEM_SELECTED, mCurCheckPosition);
		intent.putExtras(extras);
		parentActivity.sendBroadcast(intent);
	}

}
