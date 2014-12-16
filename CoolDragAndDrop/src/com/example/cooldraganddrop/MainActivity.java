package com.example.cooldraganddrop;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity implements CoolDragAndDropGridView.DragAndDropListener, SpanVariableGridView.OnItemClickListener,
		SpanVariableGridView.OnItemLongClickListener {

	ItemAdapter mItemAdapter;
	CoolDragAndDropGridView mCoolDragAndDropGridView;
	ImageView mTrash;
	Rect mTrashRect = new Rect();
	List<Item> mItems = new LinkedList<Item>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
		
		mCoolDragAndDropGridView = (CoolDragAndDropGridView) findViewById(R.id.coolDragAndDropGridView);
		mTrash = (ImageView)findViewById(R.id.ico_trash);
		for (int r = 0; r < 2; r++) {
			mItems.add(new Item(R.drawable.ic_local_search_airport_highlighted, 2, "Airport", "Heathrow"));
			mItems.add(new Item(R.drawable.ic_local_search_bar_highlighted, 1, "Bar", "Connaught Bar"));
			mItems.add(new Item(R.drawable.ic_local_search_drink_highlighted, 1, "Drink", "Tequila"));
			mItems.add(new Item(R.drawable.ic_local_search_eat_highlighted, 2, "Eat", "Sliced Steaks"));
			mItems.add(new Item(R.drawable.ic_local_search_florist_highlighted, 1, "Florist", "Roses"));
			mItems.add(new Item(R.drawable.ic_local_search_gas_station_highlighted, 2, "Gas station", "QuickChek"));
			mItems.add(new Item(R.drawable.ic_local_search_general_highlighted, 1, "General", "Service Station"));
			mItems.add(new Item(R.drawable.ic_local_search_grocery_store_highlighted, 3, "Grocery", "E-Z-Mart"));
			mItems.add(new Item(R.drawable.ic_local_search_pizza_highlighted, 2, "Pizza", "Pizza Hut"));
			mItems.add(new Item(R.drawable.ic_local_search_post_office_highlighted, 1, "Post office", "USPS"));
			mItems.add(new Item(R.drawable.ic_local_search_see_highlighted, 1, "See", "Tower Bridge"));
			mItems.add(new Item(R.drawable.ic_local_search_shipping_service_highlighted, 2, "Shipping service", "Celio*"));
		}

		mItemAdapter = new ItemAdapter(this, mItems);
		mCoolDragAndDropGridView.setAdapter(mItemAdapter);
		
		mCoolDragAndDropGridView.setScrollingStrategy(new SimpleScrollingStrategy(scrollView));
		mCoolDragAndDropGridView.setDragAndDropListener(this);
		mCoolDragAndDropGridView.setOnItemLongClickListener(this);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		mItemAdapter.notifyDataSetChanged();
		mTrash.getGlobalVisibleRect(mTrashRect); 
		Toast.makeText(getApplicationContext(), mTrash.getLeft()+":"+mTrash.getTop()+":"+mTrash.getRight()+":"+mTrash.getBottom(),
			     Toast.LENGTH_SHORT).show();
		mCoolDragAndDropGridView.setTrashRect(mTrashRect);
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		mCoolDragAndDropGridView.startDragAndDrop();

		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	public void onDragItem(int from) {

	}

	@Override
	public void onDraggingItem(int from, int to) {

	}

	@Override
	public void onDropItem(int from, int to) {
		if (from != to) {

			mItems.add(to, mItems.remove(from));
			mItemAdapter.notifyDataSetChanged();
		}

	}
	
	@Override
	public void onDeleteItem(int position){
		mItems.remove(position);
		mItemAdapter.notifyDataSetChanged();
		System.out.println("deleted:"+position);
	}

	@Override
	public boolean isDragAndDropEnabled(int position) {
		return true;
	}
	
	@Override
	public void isDeleteEnabled(Boolean flag){
		if(flag)
			mTrash.setBackgroundResource(R.color.rred);
		else
			mTrash.setBackgroundResource(R.color.hui);
	}
	


}
