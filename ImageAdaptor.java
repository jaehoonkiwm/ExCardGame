package com.iot.excardgame;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Hooni on 2015. 6. 8..
 */
public class ImageAdaptor extends BaseAdapter{
	private static final String TAG = "ImageAdaptor";
	private static final int BACK_IMAGE = R.drawable.backimg;
	private static int rowWidth;
	private static int colWidth;
	private int row;
	private int col;
	private Context context;
	private DisplayMetrics mMetrics;

	private ImageView imageView;
	private int[] images;
	private int[] frontImages;
	private int[] resourceImages = {R.drawable.ddolgie, R.drawable.ddungee, R.drawable.hochi,
			R.drawable.shaechomi, R.drawable.drago, R.drawable.yorongee,
			R.drawable.macho, R.drawable.mimi, R.drawable.mongchi,
			R.drawable.kiki, R.drawable.kangdari, R.drawable.jjingjjingee};

	public ImageAdaptor(Context applicationContext, DisplayMetrics metrics, int cntCard) {
		this.images = new int[cntCard];
		this.frontImages = new int[cntCard];

		initRandomImages(cntCard);
		for (int i = 0; i != images.length; ++i) {
			this.images[i] = BACK_IMAGE;
			//this.isClicked[i] = false;
			//this.isOpened[i] = false;
		}

		context = applicationContext;
		mMetrics = metrics;

		this.images = new int[cntCard];
	}

	private void initRandomImages(int cnt){
		int cntImage = cnt / 2;                              // 필요한 카드개수 1단계 3, 2단계 6, 3단계 12
		ArrayList allImages = new ArrayList();

		for (int i = 0; i < resourceImages.length; ++i)     // array를 arraylist로 변경
			allImages.add(resourceImages[i]);
		Collections.shuffle(allImages);                      // 이미지 섞기

		Object [] tmp =  allImages.toArray(new Object[12]);
		for(int i = 0; i < resourceImages.length; ++i)       // 섞인 이미지 다시 배열에 추가
			resourceImages[i] = Integer.parseInt(tmp[i].toString());

		ArrayList imageIndex = new ArrayList();                 // index 랜덤 생성
		for (int i = 0; i < cnt; ++i)
			imageIndex.add(i);
		Collections.shuffle(imageIndex);

		for (int i = 0; i < cnt; ++i)                           // frontimage 생성
			frontImages[(int)imageIndex.get(i)] = resourceImages[i % cntImage];

		setRowCol(cnt);
	}

	private void setRowCol(int cnt){            // 카드 개수에 따라 열과 행을 설정
		if (cnt == 6){
			this.row = 2;
			this.col = 3;
		} else if (cnt == 12){
			this.row = 3;
			this.col = 4;
		} else if (cnt == 24){
			this.row = 4;
			this.col = 6;
		}
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		rowWidth = mMetrics.widthPixels / row;
		colWidth = mMetrics.heightPixels / col;
		Log.i(TAG, "getView "+rowWidth +" "+colWidth);
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(rowWidth - 50, colWidth - 50));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setPadding(3, 3, 3, 3);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(images[position]);
		return imageView;
	}
}
