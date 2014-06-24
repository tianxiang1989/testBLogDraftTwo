package com.example.testblogdrafttwo;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * draft列表
 * @author liuxiuquan
 * 2014年6月22日
 */
public class DraftDemo extends Activity implements
		AdapterView.OnItemClickListener {
	/**数据库工具类*/
	private TodoDB draftDB;
	private Cursor mCursor;
	/**微博列表*/
	private ListView draftList;
	/**长按删除的主键号*/
	String deleteIndex;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_REFRESH = Menu.FIRST + 1;
	private Activity context;

	/**Called when the activity is starting*/
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = DraftDemo.this;
		// 初始化组件，添加事件
		setUpViews();
		// 设置列表空的提示信息
		setEmptyView();
	}

	/**
	 * 设置列表空的提示信息
	 */
	public void setEmptyView() {
		TextView emptyView = new TextView(this);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		emptyView.setText("列表为空！");
		emptyView.setTextSize(30);
		emptyView.setVisibility(View.GONE);
		ViewGroup viewGroup = (ViewGroup) draftList.getParent();
		viewGroup.addView(emptyView);
		draftList.setEmptyView(emptyView);
	}

	private DraftListAdapter myAdapter;

	/**
	 * 1 初始化组件 2  增加点击事件
	 */
	public void setUpViews() {
		draftDB = new TodoDB(this);
		mCursor = draftDB.select();
		draftList = (ListView) findViewById(R.id.draftlist);
		// draftList.setAdapter(new DraftListAdapter(this, mCursor));
		myAdapter = new DraftListAdapter(this, mCursor);
		draftList.setAdapter(myAdapter);

		draftList.setOnItemClickListener(this);
		draftList.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mCursor.moveToPosition(arg2);
				deleteIndex = mCursor.getString(0);
				ConfirmDelete();
				return true;
			}
		});
	}

	/**确认删除对话框*/
	private void ConfirmDelete() {
		new AlertDialog.Builder(context)
				.setMessage("真的要删除吗？")
				.setPositiveButton("确定",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(context, "确认删除",
										Toast.LENGTH_SHORT).show();
								draftDB.delete(Integer.parseInt(deleteIndex));
								mCursor.requery();
								draftList.invalidateViews();
								myAdapter.notifyDataSetChanged();
							}
						})
				.setNegativeButton("取消",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(context, "取消删除",
										Toast.LENGTH_SHORT).show();
							}
						}).show();
	}

	/**菜单内容*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_ADD, 0, "ADD");
		menu.add(Menu.NONE, MENU_REFRESH, 0, "REFRESH");
		return true;
	}

	/**menu事件*/
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ADD:
			add();
			break;
		case MENU_REFRESH:
			refresh();
			break;
		}
		return true;
	}

	/**
	 * 刷新列表
	 */
	private void refresh() {
		mCursor.requery();
		draftList.invalidateViews();
		myAdapter.notifyDataSetChanged();
	}

	/**
	 * 进入增加操作
	 */
	public void add() {
		Toast.makeText(this, "点击add，进入增加列表", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.setClass(DraftDemo.this, ListItem.class);
		// startActivity(intent);
		startActivityForResult(intent, 0);
	}

	/**每一个item的点击事件*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "点击了位置" + position, Toast.LENGTH_SHORT).show();
		// move到对应的行
		mCursor.moveToPosition(position);
		Intent intent = new Intent();
		intent.setClass(DraftDemo.this, ListItem.class);
		Bundle bundle = new Bundle();
		DraftBean draftBean = new DraftBean();
		draftBean = new DraftBean(mCursor);
		bundle.putSerializable("draftBean", draftBean);
		intent.putExtras(bundle);
		// startActivity(intent);
		startActivityForResult(intent, 0);
	}

	public class DraftListAdapter extends BaseAdapter {
		private Cursor mCursor;
		private LayoutInflater mInflater;

		public DraftListAdapter(Context context, Cursor cursor) {
			this.mInflater = LayoutInflater.from(context);
			mCursor = cursor;
		}

		@Override
		public int getCount() {
			return mCursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			mCursor.moveToPosition(position);

			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.draft_single, null);
				holder.img = (LinearLayout) convertView
						.findViewById(R.id.leftSend);
				holder.content_text = (TextView) convertView
						.findViewById(R.id.content_text);
				holder.time_text = (TextView) convertView
						.findViewById(R.id.time_text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// 左下角显示的内容
			StringBuffer sb = new StringBuffer();
			sb.append(mCursor.getString(1)).append(mCursor.getString(2))
					.append(mCursor.getString(3)).append(mCursor.getString(4))
					.append(mCursor.getString(5)).append(mCursor.getString(6))
					.append(mCursor.getString(7)).append(mCursor.getString(8))
					.append(mCursor.getString(9));
			String sbString = sb.toString();
			holder.content_text.setText(sbString);
			holder.time_text.setText(mCursor.getString(10));
			// img的onClick中会用到
			holder.img.setTag(mCursor.getString(0));
			holder.img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String index = v.getTag().toString();
					Toast.makeText(DraftDemo.this, "发送" + index,
							Toast.LENGTH_SHORT).show();
					submit(index);

				}
			});

			return convertView;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Log.v("DraftDemo", "进入onActivityResult");
		if (resultCode == RESULT_OK) {
			mCursor.requery();
			draftList.invalidateViews();
			myAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 提交
	 */
	private void submit(String index) {
		if (null == index) {
			return;
		}
		// TODO_LXQ 发送
		draftDB.delete(Integer.parseInt(index));
		mCursor.requery();
		draftList.invalidateViews();
		myAdapter.notifyDataSetChanged();
	}

	/**DraftListAdapter中用到*/
	public final class ViewHolder {
		public LinearLayout img;
		public TextView content_text;
		public TextView time_text;
	}
}