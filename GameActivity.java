package com.iot.excardgame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


public class GameActivity extends ActionBarActivity {
	private static final String TAG = "GameActivity";
	private TextView tvStage;
	private TextView tvName;
	private TextView tvTime;
	private TextView tvScore;
	private GridView gridView;

	DisplayMetrics metrics;
	private ImageAdaptor imageAdaptor;

	private int stage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);

		tvStage = (TextView) findViewById(R.id.tvStage);
		tvName = (TextView) findViewById(R.id.tvName);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvScore = (TextView) findViewById(R.id.tvScore);
		gridView = (GridView) findViewById(R.id.gridView);
		metrics = new DisplayMetrics();
		Log.i(TAG, metrics.widthPixels + "");

		Intent getintent = getIntent();
		stage = getintent.getIntExtra("stage", 0);
		tvStage.setText((stage + 1) +" 단계");
		tvName.setText(getIntent().getStringExtra("name"));

		initStage(stage);
		ItemClickListener itemClickListener = new ItemClickListener();
		gridView.setAdapter(imageAdaptor);
		gridView.setOnItemClickListener(itemClickListener);
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

	}

	private void initStage(int num) {
		int col = 0;
		switch (num) {
			case 0:
				col = 3;
				break;
			case 1:
				col = 4;
				break;
			case 2:
				col = 6;
				break;
		}
		this.gridView.setNumColumns(col);
		this.imageAdaptor = new ImageAdaptor(getApplicationContext(), metrics, (int)Math.pow(2, num) * 6);
	}

		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_game, menu);
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

	private void flipCard(View root, View back){
		View rootLayout = root;//findViewById(R.id.main_activity_root);
		View cardFace = back;//findViewById(R.id.main_activity_card_face);
		View cardBack = back;//findViewById(R.id.main_activity_card_back);

		FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

		if (cardFace.getVisibility() == View.GONE)
		{
			flipAnimation.reverse();
		}
		//rootLayout.startAnimation(flipAnimation);
		cardFace.startAnimation(flipAnimation);
	}

	public class ItemClickListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		}
	}
}
