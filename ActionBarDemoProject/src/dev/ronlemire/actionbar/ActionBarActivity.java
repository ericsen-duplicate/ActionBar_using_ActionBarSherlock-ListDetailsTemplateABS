package dev.ronlemire.actionbar;

import com.actionbarsherlock.app.SherlockListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActionBarActivity extends SherlockListActivity {
	private String[] listActivities;
	private int mCurCheckPosition = -1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

		listActivities = getResources().getStringArray(R.array.list_activities);        
        
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.list_titles,
				android.R.layout.simple_list_item_single_choice);
		setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setItemChecked(mCurCheckPosition, true);
    }

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		mCurCheckPosition = position;
		startActivity(new Intent(listActivities[position]));
	}
}