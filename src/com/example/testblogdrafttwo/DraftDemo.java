package com.example.testblogdrafttwo;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DraftDemo extends Activity implements
		AdapterView.OnItemClickListener {
	private TodoDB draftDB;
	private Cursor mCursor;
	// /**转发自微博的ID输入框*/
	// private EditText fromblogidEdit;
	// /**发博人的ID输入框*/
	// private EditText puseridEdit;
	// /**微博的内容输入框*/
	// private EditText pconEdit;
	// /**指标信息输入框*/
	// private EditText zbEdit;
	// /**微博的at信息 @对象输入框*/
	// private EditText atnameEdit;
	// /**微博的at信息ID @id 输入框*/
	// private EditText atidEdit;
	// /**话题信息：名称 输入框*/
	// private EditText htnameEdit;
	// /**话题信息：ID 输入框*/
	// private EditText htidEdit;
	// /**案例信息 输入框*/
	// private EditText anlikeyEdit;
	/**微博列表*/
	private ListView blogList;

	private int BLOG_KEY_ID = 0;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_DELETE = Menu.FIRST + 1;
	protected final static int MENU_UPDATE = Menu.FIRST + 2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setUpViews();
	}

	public void setUpViews() {
		draftDB = new TodoDB(this);
		mCursor = draftDB.select();
		//
		// fromblogidEdit = (EditText) findViewById(R.id.fromblogid);
		// puseridEdit = (EditText) findViewById(R.id.puserid);
		// pconEdit = (EditText) findViewById(R.id.pcon);
		// zbEdit = (EditText) findViewById(R.id.zb);
		// atnameEdit = (EditText) findViewById(R.id.atname);
		// atidEdit = (EditText) findViewById(R.id.atid);
		// htnameEdit = (EditText) findViewById(R.id.htname);
		// htidEdit = (EditText) findViewById(R.id.htid);
		// anlikeyEdit = (EditText) findViewById(R.id.anlikey);
		//
		blogList = (ListView) findViewById(R.id.bookslist);
		blogList.setAdapter(new BooksListAdapter(this, mCursor));
		blogList.setOnItemClickListener(this);
	}

	/**菜单内容*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_ADD, 0, "ADD");
		// menu.add(Menu.NONE, MENU_DELETE, 0, "DELETE");
		// menu.add(Menu.NONE, MENU_UPDATE, 0, "UPDATE");
		return true;
	}

	/**menu事件*/
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ADD:
			add();
			break;
		// case MENU_DELETE:
		// delete();
		// break;
		// case MENU_UPDATE:
		// update();
		// break;
		}
		return true;
	}

	public void add() {
		// String fromblogid = fromblogidEdit.getText().toString();
		// String puserid = puseridEdit.getText().toString();
		// String pcon = pconEdit.getText().toString();
		// String zb = zbEdit.getText().toString();
		// String atname = atnameEdit.getText().toString();
		// String atid = atidEdit.getText().toString();
		// String htname = htnameEdit.getText().toString();
		// String htid = htidEdit.getText().toString();
		// String anlikey = anlikeyEdit.getText().toString();
		//
		// if (pcon.equals("") ) {
		// Toast.makeText(this, "内容字段不能为空，请确认后重试", Toast.LENGTH_SHORT).show();
		// return;
		// }
		// /**添加时间*/
		// SimpleDateFormat sDateFormat = new SimpleDateFormat(
		// "MM-dd hh:mm:ss");
		// String time = sDateFormat.format(new java.util.Date());
		//
		// mBooksDB.insert(fromblogid, puserid, pcon, zb, atname, atid, htname, htid, anlikey, time);
		// mCursor.requery();
		// blogList.invalidateViews();
		//
		// fromblogidEdit.setText("");
		// puseridEdit.setText("");
		// pconEdit.setText("");
		// zbEdit.setText("");
		// atnameEdit.setText("");
		// atidEdit.setText("");
		// htnameEdit.setText("");
		// htidEdit.setText("");
		// anlikeyEdit.setText("");

		Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();

		// Log.v(this.getClass().getName().substring(0, this.getClass().getName().lastIndexOf('$')), "This is Verbose.");
		// Log.v(this.getClass().getName().lastIndexOf('$') > 0 ? this.getClass()
		// .getName()
		// .substring(0, this.getClass().getName().lastIndexOf('$'))
		// : this.getClass().getName(), "This is Verbose.");
		Log.v(getClassName(), "This is Verbose..");
		Log.d(Thread.currentThread().getStackTrace()[2].getMethodName(),
				"This is Debug.");

		Intent intent = new Intent();
		intent.setClass(DraftDemo.this, ListItem.class);
		/*new一个Bundle对象，并将要传递的数据传入*/
		// Bundle bundle = new Bundle();
		// bundle.putDouble("height",height);
		// bundle.putString("sex",sex);
		// //传递对象
		// UserBean us=new UserBean();
		// us.setUsername("tom");
		// us.setAge(17);
		// bundle.putSerializable("us", us);
		// /*将Bundle对象assign给Intent*/
		// intent.putExtras(bundle);
		/*调用Activity EX03_10_1*/
		startActivity(intent);
		// finish();
	}

	public String getClassName() {
		String className = this.getClass().getName().lastIndexOf('$') > 0 ? this
				.getClass().getName()
				.substring(0, this.getClass().getName().lastIndexOf('$'))
				: this.getClass().getName();
		return className;
	}

	// public void delete() {
	// if (BLOG_KEY_ID == 0) {
	// return;
	// }
	// mBooksDB.delete(BLOG_KEY_ID);
	// mCursor.requery();
	// blogList.invalidateViews();
	// fromblogidEdit.setText("");
	// puseridEdit.setText("");
	// pconEdit.setText("");
	// zbEdit.setText("");
	// atnameEdit.setText("");
	// atidEdit.setText("");
	// htnameEdit.setText("");
	// htidEdit.setText("");
	// anlikeyEdit.setText("");
	// Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
	// }

	// public void update() {
	// // Toast.makeText(this, "进入update()", Toast.LENGTH_SHORT).show();
	// String fromblogid = fromblogidEdit.getText().toString();
	// String puserid = puseridEdit.getText().toString();
	// String pcon = pconEdit.getText().toString();
	// String zb = zbEdit.getText().toString();
	// String atname = atnameEdit.getText().toString();
	// String atid = atidEdit.getText().toString();
	// String htname = htnameEdit.getText().toString();
	// String htid = htidEdit.getText().toString();
	// String anlikey = anlikeyEdit.getText().toString();
	// // 内容字段不能为空
	// if (pcon.equals("") ) {
	// Toast.makeText(this, "内容字段不能为空，请确认后重试", Toast.LENGTH_SHORT).show();
	// return;
	// }
	// SimpleDateFormat sDateFormat = new SimpleDateFormat(
	// "MM-dd hh:mm:ss");
	// String time = sDateFormat.format(new java.util.Date());
	// mBooksDB.update(BLOG_KEY_ID, fromblogid, puserid, pcon, zb, atname, atid, htname, htid, anlikey, time);
	// mCursor.requery();
	// blogList.invalidateViews();
	// fromblogidEdit.setText("");
	// puseridEdit.setText("");
	// pconEdit.setText("");
	// zbEdit.setText("");
	// atnameEdit.setText("");
	// atidEdit.setText("");
	// htnameEdit.setText("");
	// htidEdit.setText("");
	// anlikeyEdit.setText("");
	// Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
	// }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Toast.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
		//move到对应的行
		mCursor.moveToPosition(position);
		Intent intent = new Intent();
		intent.setClass(DraftDemo.this, ListItem.class);
		 /*new一个Bundle对象，并将要传递的数据传入*/
		Bundle bundle = new Bundle();
		DraftBean draftBean = new DraftBean();
		draftBean = new DraftBean(mCursor);

		bundle.putSerializable("draftBean", draftBean);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public class BooksListAdapter extends BaseAdapter {
		private Context mContext;
		private Cursor mCursor;

		public BooksListAdapter(Context context, Cursor cursor) {
			mContext = context;
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
			LinearLayout ll = null;
			ll = (LinearLayout) View.inflate(DraftDemo.this, R.layout.vlist2,
					null);
			mCursor.moveToPosition(position);
			TextView content_text = (TextView) ll
					.findViewById(R.id.content_text);
			TextView info_text = (TextView) ll.findViewById(R.id.time_text);
			// 左下角显示的内容
			StringBuffer sb = new StringBuffer();
			sb.append(mCursor.getString(1)).append(mCursor.getString(2))
					.append(mCursor.getString(3)).append(mCursor.getString(4))
					.append(mCursor.getString(5)).append(mCursor.getString(6))
					.append(mCursor.getString(7)).append(mCursor.getString(8))
					.append(mCursor.getString(9));
			String sbString = sb.toString();
			content_text.setText(sbString);
			info_text.setText(mCursor.getString(10));
			Log.d("--SQLiteDatabaseDemo getView--", "mCursor.getString(2)=="
					+ mCursor.getString(2));
			return ll;
		}

	}
}