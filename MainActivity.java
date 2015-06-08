package com.iot.excardgame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
	public static final int GAME_REQUEST_CODE = 1000;

	EditText etName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etName = (EditText) findViewById(R.id.etName);
	}

	public void onButtonClicked(View v){
		Intent intent = null;
		switch (v.getId()){
			case R.id.btnStart:
				intent = new Intent(this, GameActivity.class);
				intent.putExtra("stage", 0);
				intent.putExtra("name", etName.getText().toString());
				startActivityForResult(intent, GAME_REQUEST_CODE);
				break;
			case R.id.btnScore:
				intent = new Intent(this, GameActivity.class);
				intent.putExtra("name", etName.getText().toString());
				startActivityForResult(intent, GAME_REQUEST_CODE);
				break;


		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
