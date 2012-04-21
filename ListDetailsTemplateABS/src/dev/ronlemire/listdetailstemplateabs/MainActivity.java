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

import com.actionbarsherlock.app.SherlockFragmentActivity;

import dev.ronlemire.listdetailstemplateabs.R;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity {
	public static final String TAG = "MainActivity";
	private int listItemSelected = 0;
	private TextView messageTextView;

	private ListReceiver listReceiver;
	private IntentFilter listFilter;
	private MessageFromDF1Receiver df1Receiver;
	private MessageFromDF2Receiver df2Receiver;
	private IntentFilter df1Filter;
	private IntentFilter df2Filter;
	String[] titlesArray;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "in MainActivity onCreate");
		super.onCreate(savedInstanceState);
		FragmentManager.enableDebugLogging(true);
		setContentView(R.layout.main);

		messageTextView = (TextView) this.findViewById(R.id.etMessages);
		messageTextView.setEnabled(false);
		messageTextView.setTextColor(Color.parseColor("#FFFFFF")); // white

		listReceiver = new ListReceiver();
		listFilter = new IntentFilter(
				ListViewFragment.LIST_FRAGMENT_BROADCAST_INTENT);

		df1Receiver = new MessageFromDF1Receiver();
		df1Filter = new IntentFilter(DetailsFragment1.DF1_BROADCAST_INTENT);

		df2Receiver = new MessageFromDF2Receiver();
		df2Filter = new IntentFilter(DetailsFragment2.DF2_BROADCAST_INTENT);

		if (savedInstanceState != null) {
			messageTextView.setText(savedInstanceState.getString("message"));
		}

		registerReceiver(listReceiver, listFilter);
		registerReceiver(df1Receiver, df1Filter);
		registerReceiver(df2Receiver, df2Filter);

		titlesArray = getResources().getStringArray(R.array.list_titles);
	}

	// *****************************************************************************
	// Action Bar
	// *****************************************************************************
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		this.getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			refreshList(0);
			// Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
			break;

		case R.id.menu_refresh:
			refreshList(0);
			// Toast.makeText(this, "Fake refreshing...",
			// Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void refreshList(int index){
		ListViewFragment lf = (ListViewFragment) getSupportFragmentManager()
				.findFragmentById(R.id.listOptions);
		lf.refresh(index);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(listReceiver);
		unregisterReceiver(df1Receiver);
		unregisterReceiver(df2Receiver);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("message", messageTextView.getText().toString());
	}

	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
		messageTextView.setText(inState.getSerializable("message").toString());
	}

	// *****************************************************************************
	// BroadcastReceivers
	// *****************************************************************************
	class ListReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// String ron = intent.getStringExtra("name");
			// Integer age = intent.getIntExtra("age", 0);

			// Bundle extras = intent.getExtras();
			// String ron = extras.getString("name");
			// Integer age = extras.getInt("age");

			Bundle extras = intent.getExtras();
			listItemSelected = extras
					.getInt(ListViewFragment.LIST_ITEM_SELECTED);
			messageTextView.setText(titlesArray[listItemSelected]);

			showDetails(listItemSelected);
		}
	}

	class MessageFromDF1Receiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = (String) intent
					.getSerializableExtra(DetailsFragment1.OUT_MESSAGE_KEY);
			messageTextView.setText(message);
		}
	}

	class MessageFromDF2Receiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = (String) intent
					.getSerializableExtra(DetailsFragment2.OUT_MESSAGE_KEY);
			messageTextView.setText(message);
		}
	}

	// *****************************************************************************
	// Helper methods
	// *****************************************************************************

	private boolean isMultiPane() {
		return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	// private boolean isTablet() {
	// Display display = getWindowManager().getDefaultDisplay();
	// DisplayMetrics displayMetrics = new DisplayMetrics();
	// display.getMetrics(displayMetrics);
	//
	// int width = displayMetrics.widthPixels / displayMetrics.densityDpi;
	// int height = displayMetrics.heightPixels / displayMetrics.densityDpi;
	//
	// double screenDiagonal = Math.sqrt(width * width + height * height);
	// return (screenDiagonal >= 8.5);
	// }

	private void showDetails(int index) {
		Log.v(TAG, "in MainActivity showDetails(" + index + ")");

		if (isMultiPane()) {
			// Check what fragment is shown, replace if needed.
			DetailsFragment df = (DetailsFragment) getSupportFragmentManager()
					.findFragmentById(R.id.details);
			if (df == null || df.getShownIndex() != index) {
				// Make new fragment to show this selection.
				df = DetailsFragment.newInstance(index);

				// Execute a transaction, replacing any existing
				// fragment inside the frame with the new one.
				Log.v(TAG, "about to run FragmentTransaction...");
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				// ft.setCustomAnimations(R.animator.fragment_open_enter,
				// R.animator.fragment_open_exit);
				// ft.setCustomAnimations(R.animator.bounce_in_down,
				// R.animator.slide_out_right);
				// ft.setCustomAnimations(R.animator.fade_in,
				// R.animator.fade_out);
				// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.replace(R.id.details, df);
				// ft.addToBackStack(TAG);
				ft.commit();
				getSupportFragmentManager().executePendingTransactions();
			}
		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(this, DetailsActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}
}
