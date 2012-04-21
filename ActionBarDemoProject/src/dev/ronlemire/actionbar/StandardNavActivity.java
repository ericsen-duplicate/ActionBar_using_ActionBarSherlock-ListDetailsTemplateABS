package dev.ronlemire.actionbar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class StandardNavActivity extends SherlockFragmentActivity {
	private SherlockFragment details;
	private FragmentTransaction ft;
	private ActionBar bar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.standard_nav_activity);

		details = (SherlockFragment) getSupportFragmentManager()
				.findFragmentById(R.id.standardNavDetails);

		bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case android.R.id.home:
			details = Fragment1.newInstance(1);
			displayFragment(details);
			return true;
		}
		
		String itemTitle = item.getTitle().toString();

		if (itemTitle.equals("Action Fragment1")) {
			details = Fragment1.newInstance(1);
		} else if (itemTitle.equals("Action Fragment2")) {
			details = Fragment2.newInstance(2);
		} else if (itemTitle.equals("Action Fragment3")) {
			details = Fragment3.newInstance(3);
		} else if (itemTitle.equals("Invoke Fragment1")) {
			details = Fragment1.newInstance(1);
		} else if (itemTitle.equals("Invoke Fragment2")) {
			details = Fragment2.newInstance(2);
		} else if (itemTitle.equals("Invoke Fragment3")) {
			details = Fragment3.newInstance(3);
		}
		displayFragment(details);

		return true;
	}

	private void displayFragment(SherlockFragment details) {
		ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.standardNavDetails, details);
		ft.commit();
		getSupportFragmentManager().executePendingTransactions();
	}
}
