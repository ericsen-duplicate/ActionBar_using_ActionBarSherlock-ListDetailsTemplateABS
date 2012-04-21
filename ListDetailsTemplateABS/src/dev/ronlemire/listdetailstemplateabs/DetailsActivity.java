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

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class DetailsActivity extends SherlockFragmentActivity {
	public static final String TAG = "DetailsActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(DetailsActivity.TAG, "in DetailsActivity onCreate");
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// If the screen is now in landscape mode, it means
			// that our MainActivity is being shown with both
			// the titles and the text, so this activity is
			// no longer needed. Bail out and let the MainActivity
			// do all the work.
			finish();
			return;
		}

		if (getIntent() != null) {
			Bundle bundle = getIntent().getExtras();
			int index = bundle.getInt("index", 0);
			DetailsFragment df = DetailsFragment.newInstance(index);
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, df).commit();
			getSupportFragmentManager().executePendingTransactions();
		}
	}
}
