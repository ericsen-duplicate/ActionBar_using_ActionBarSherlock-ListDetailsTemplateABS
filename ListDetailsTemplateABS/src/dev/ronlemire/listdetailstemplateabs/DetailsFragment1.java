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
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class  DetailsFragment1 extends DetailsFragment {
	public static final String TAG = "DetailsFragment1";
	public static final String DF1_BROADCAST_INTENT = "dev.ronlemire.listdetailstemplate.DF1_BROADCAST_INTENT";
	
	@Override
    public void onInflate(Activity parentActivity, AttributeSet attrs, Bundle savedInstanceState) {
    	Log.v(DetailsFragment1.TAG,
    			"in DetailsFragment1 onInflate. AttributeSet contains:");
    	for(int i=0; i<attrs.getAttributeCount(); i++)
            Log.v(DetailsFragment1.TAG, "    " + attrs.getAttributeName(i) +
            		" = " + attrs.getAttributeValue(i));
    	super.onInflate(parentActivity, attrs, savedInstanceState);
    }

	@Override
    public void onAttach(Activity parentActivity) {
    	Log.v(DetailsFragment1.TAG, "in DetailsFragment1 onAttach; activity is: " +
    			parentActivity);
    	this.parentActivity = parentActivity;
    	super.onAttach(parentActivity);
    }

    @Override
    public void onCreate(Bundle myBundle) {
    	Log.v(DetailsFragment1.TAG, "in DetailsFragment1 onCreate. Bundle contains:");
    	if(myBundle != null) {
            for(String key : myBundle.keySet()) {
                Log.v(DetailsFragment1.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(DetailsFragment1.TAG, "    myBundle is null");
    	}
    	super.onCreate(myBundle);

    	mIndex = getArguments().getInt("index", 0);
		intent = new Intent(DF1_BROADCAST_INTENT);
    }

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
        Log.v(DetailsFragment1.TAG, "in DetailsFragment1 onCreateView. container = " +
        		container);

        // Don't tie this fragment to anything through the inflater. Android
        // takes care of attaching fragments for us. The container is only
        // passed in so we can know about the container where this View
        // hierarchy is going to go. If we're not going anywhere, don't
        // bother to create the view hierarchy and just return null.
        if(container == null) {
        	Log.v(DetailsFragment1.TAG, "container is null. No need to inflate.");
        	return null;
        }

        View v = inflater.inflate(R.layout.details_fragment1, container, false);
		TextView text1 = (TextView) v.findViewById(R.id.detailsFragment1);
		text1.setText("Details Fragment 1");
		
		return v;
	}

    @Override
    public void onActivityCreated(Bundle savedState) {
    	Log.v(DetailsFragment1.TAG,
    			"in DetailsFragment1 onActivityCreated. savedState contains:");
    	if(savedState != null) {
            for(String key : savedState.keySet()) {
                Log.v(DetailsFragment1.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(DetailsFragment1.TAG, "    savedState is null");
    	}
        super.onActivityCreated(savedState);
    }
}
