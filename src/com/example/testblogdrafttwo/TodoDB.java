package com.example.testblogdrafttwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * draft工具类
 * @author liuxiuquan
 * 2014年6月22日
 */
public class TodoDB extends SQLiteOpenHelper {
	/**数据库名称*/
	private final static String DATABASE_NAME = "BLOG.db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "blog_draft";
	/**自增主键*/
	public final static String KEY_ID = "key_id";
	/**转发自微博的ID*/
	public final static String FROMBLOGID = "fromblogid";
	/**发博人的ID*/
	public final static String PUSERID = "puserid";
	/**微博的内容*/
	public final static String PCON = "pcon";
	/**指标信息*/
	public final static String ZB = "zb";
	/**微博的at信息 @对象*/
	public final static String ATNAME = "atname";
	/**微博的at信息ID @id*/
	public final static String ATID = "atid";
	/**话题信息：名称*/
	public final static String HTNAME = "htname";
	/**话题信息：ID*/
	public final static String HTID = "htid";
	/**案例信息*/
	public final static String ANLIKEY = "anlikey";
	/**保存时间*/
	public final static String TIME = "time";

	public TodoDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 创建table
	// CREATE TABLE books_table
	// (key_id INTEGER primary key autoincrement, //自增主键
	// fromblogid text, //转发自微博的ID
	// puserid text, //发博人的ID
	// pcon text, //微博的内容
	// zb text, //指标信息
	// atname text, //微博的at信息 @对象
	// atid text, //微博的at信息ID @id
	// htname text, //话题信息：名称
	// htid text, //话题信息：ID
	// anlikey text, //案例信息
	// time text //保存时间
	// );
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID
				+ " INTEGER primary key autoincrement, " + FROMBLOGID
				+ " text, " + PUSERID + " text, " + PCON + " text, " + ZB
				+ " text, " + ATNAME + " text, " + ATID + " text, " + HTNAME
				+ " text, " + HTID + " text, " + ANLIKEY + " text, " + TIME
				+ " text" + ");";
		Log.d("BooksDB onCreate", "sql==" + sql);
		db.execSQL(sql);
	}

	/**更新*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	/**查询全部*/
	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	/**根据主键查询某一条记录，暂未用到 */
	public Cursor selectById(String id) {
		SQLiteDatabase db = this.getReadableDatabase();
		// Cursor cursor = db.rawQuery("select * from person where "
		// + "name like ? and age=?", new String[]{"%传智%", "4"});
		Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where "
				+ KEY_ID + "=?", new String[] { id });
		// Log.v("cursor.getString(0)", cursor.getString(0));

		return cursor;
	}

	// fromblogid text, //转发自微博的ID
	// puserid text, //发博人的ID
	// pcon text, //微博的内容
	// zb text, //指标信息
	// atname text, //微博的at信息 @对象
	// atid text, //微博的at信息ID @id
	// htname text, //话题信息：名称
	// htid text, //话题信息：ID
	// anlikey text, //案例信息
	// time text //保存时间
	/**增加操作*/
	public long insert(String fromblogid, String puserid, String pcon,
			String zb, String atname, String atid, String htname, String htid,
			String anlikey, String time) {
		SQLiteDatabase db = this.getWritableDatabase();
		/* ContentValues */
		ContentValues cv = new ContentValues();
		cv.put(FROMBLOGID, fromblogid);
		cv.put(PUSERID, puserid);
		cv.put(PCON, pcon);
		cv.put(ZB, zb);
		cv.put(ATNAME, atname);
		cv.put(ATID, atid);
		cv.put(HTNAME, htname);
		cv.put(HTID, htid);
		cv.put(ANLIKEY, anlikey);
		cv.put(TIME, time);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	/**删除操作*/
	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = KEY_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	/**修改操作*/
	public void update(int id, String fromblogid, String puserid, String pcon,
			String zb, String atname, String atid, String htname, String htid,
			String anlikey, String time) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = KEY_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };

		ContentValues cv = new ContentValues();
		cv.put(FROMBLOGID, fromblogid);
		cv.put(PUSERID, puserid);
		cv.put(PCON, pcon);
		cv.put(ZB, zb);
		cv.put(ATNAME, atname);
		cv.put(ATID, atid);
		cv.put(HTNAME, htname);
		cv.put(HTID, htid);
		cv.put(ANLIKEY, anlikey);
		cv.put(TIME, time);
		db.update(TABLE_NAME, cv, where, whereValue);
	}
}