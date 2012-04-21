package dev.ronlemire.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class ListNavActivity extends SherlockFragmentActivity implements
		ActionBar.OnNavigationListener {
	// private TextView mSelected;
	//private String[] mFragments;
	private SherlockFragment details;
	private ActionBar bar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// setTheme(SampleList.THEME); //Used for theme switching in samples
		super.onCreate(savedInstanceState);

		setContentView(R.layout.list_nav_activity);
		// mSelected = (TextView)findViewById(R.id.text);

		//mFragments = getResources().getStringArray(R.array.list_fragments);

		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter
				.createFromResource(context, R.array.list_fragments,
						R.layout.sherlock_spinner_item);
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setListNavigationCallbacks(list, this);

		details = (SherlockFragment) getSupportFragmentManager()
				.findFragmentById(R.id.listNavDetails);
		
		bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			bar.setSelectedNavigationItem(0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		FragmentTransaction ft;

		switch (itemPosition) {
		case 0:
			details = Fragment1.newInstance(1);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.listNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
			break;
		case 1:
			details = Fragment2.newInstance(2);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.listNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
			break;
		case 2:
			details = Fragment3.newInstance(3);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.listNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
			break;
		default:
			break;
		}

		return false;
	}

}
