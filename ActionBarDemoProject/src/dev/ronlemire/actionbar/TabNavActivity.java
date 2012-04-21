package dev.ronlemire.actionbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class TabNavActivity extends SherlockFragmentActivity implements
		ActionBar.TabListener {
	private SherlockFragment details;
	private ActionBar bar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_nav_activity);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for (int i = 1; i <= 3; i++) {
			ActionBar.Tab tab = getSupportActionBar().newTab();
			tab.setText("Tab " + i);
			tab.setTabListener(this);
			getSupportActionBar().addTab(tab);
		}

		details = (SherlockFragment) getSupportFragmentManager()
				.findFragmentById(R.id.tabNavDetails);
		
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
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		String tabText = tab.getText().toString();
		if (tabText.equals("Tab 1")) {
			details = Fragment1.newInstance(1);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.tabNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
		} else if (tabText.equals("Tab 2")) {
			details = Fragment2.newInstance(2);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.tabNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
		} else if (tabText.equals("Tab 3")) {
			details = Fragment3.newInstance(3);
			ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.tabNavDetails, details);
			ft.commit();
			getSupportFragmentManager().executePendingTransactions();
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
