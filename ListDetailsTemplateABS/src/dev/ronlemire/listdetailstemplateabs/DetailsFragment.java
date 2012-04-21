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

import com.actionbarsherlock.app.SherlockFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailsFragment extends SherlockFragment {
	public static final String TAG = "DetailsFragment";
	public static final String OUT_MESSAGE_KEY = "OutMessage";
	public static final String IN_MESSAGE_KEY = "InMessage";
	protected int mIndex = 0;
	protected Intent intent;
	protected Activity parentActivity = null;

	// DetailsFragment Factory Method
	public static DetailsFragment newInstance(int index) {
        Log.v(DetailsFragment.TAG, "in DetailsFragment newInstance(" + index + ")");

        DetailsFragment df = new DetailsFragment();
		switch (index) {
		case 0:
			df = new DetailsFragment1();
			break;
		case 1:
			df = new DetailsFragment2();
			break;
		default:
			break;
		}        
        
        // Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		df.setArguments(args);
		return df;
	}
	
	public static DetailsFragment newInstance(Bundle bundle) {
		int index = bundle.getInt("index", 0);
        return newInstance(index);
	}
	
	protected int getShownIndex() {
    	return mIndex;
    }

    @Override
    public void onStart() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onStart", mIndex));
    	super.onStart();
    }

    @Override
    public void onResume() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onResume", mIndex));
    	super.onResume();
    }

    @Override
    public void onPause() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onPause", mIndex));
    	super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onSaveInstanceState", mIndex));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onStop", mIndex));
    	super.onStop();
    }

    @Override
    public void onDestroyView() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onDestroyView, view = ", mIndex) +
    			getView());
    	super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onDestroy", mIndex));
    	super.onDestroy();
    }

    @Override
    public void onDetach() {
    	Log.v(DetailsFragment.TAG, String.format("in DetailsFragment onDetach", mIndex));
    	super.onDetach();
    }
    
	// *****************************************************************************
	// Helper Methods
	// *****************************************************************************
	protected void SendMessage(String message) {
		intent.putExtra(OUT_MESSAGE_KEY, message);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		parentActivity.sendBroadcast(intent);	
	}


}
