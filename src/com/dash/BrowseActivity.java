package com.dash;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.dash.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BrowseActivity extends ListActivity {
	private ArrayAdapter<DeviceDisplay> listAdapter;
	public static ProgressDialog dialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Search the network
		searchNetwork();
		
		// show processing dialog on startup
		dialog = ProgressDialog.show(this, "",getResources().getString(R.string.scan_devices), true);

		listAdapter = new ArrayAdapter(this, R.layout.devicelisting);
		ListView lv = getListView();

		// Set list view header
		TextView tv = new TextView(this);
		tv.setTextColor(Color.rgb(184, 188, 247));
		tv.setText("Handheld Devices");
		lv.addHeaderView(tv);

		setListAdapter(listAdapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ListView lv2 = (ListView) arg0;

				// DeviceDisplay dd = (DeviceDisplay)
				// lv2.getItemAtPosition(arg2);

			}
		});

	}

	private void displayUnavailabilityMessage() {
		Toast.makeText(this, "This device does not support Remote",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, R.string.search_lan).setIcon(
				android.R.drawable.ic_menu_search);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			searchNetwork();
			break;
		}
		return false;
	}

	protected void searchNetwork() {
		new SniffNetwork().execute();
	}

	private class DeviceDisplay {

	}

}
