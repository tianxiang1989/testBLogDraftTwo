package com.example.testblogdrafttwo;

import java.io.Serializable;

import android.database.Cursor;

/**
 * Draft实体类
 * @author liuxiuquan
 * 2014年6月21日
 */
public class DraftBean implements Serializable {
	/**数据库主键*/
	private String key_id;
	/**转发自微博的ID*/
	private String fromblogid;
	/**发博人的ID*/
	private String puserid;
	/**微博的内容*/
	private String pcon;
	/**指标信息*/
	private String zb;
	/**微博的at信息 @对象*/
	private String atname;
	/**微博的at信息ID @id*/
	private String atid;
	/**话题信息：名称*/
	private String htname;
	/**话题信息：ID*/
	private String htid;
	/**案例信息*/
	private String anlikey;
	/**保存时间*/
	private String time;

	public DraftBean() {
		super();
	}

	public DraftBean(Cursor mCursor) {
		key_id = mCursor.getString(0);
		fromblogid = mCursor.getString(1);
		puserid = mCursor.getString(2);
		pcon = mCursor.getString(3);
		zb = mCursor.getString(4);
		atname = mCursor.getString(5);
		atid = mCursor.getString(6);
		htname = mCursor.getString(mCursor.getColumnIndex("htname"));
		htid = mCursor.getString(8);
		anlikey = mCursor.getString(9);
		time = mCursor.getString(10);
	}

	public String getKey_id() {
		return key_id;
	}

	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	public String getFromblogid() {
		return fromblogid;
	}

	public void setFromblogid(String fromblogid) {
		this.fromblogid = fromblogid;
	}

	public String getPuserid() {
		return puserid;
	}

	public void setPuserid(String puserid) {
		this.puserid = puserid;
	}

	public String getPcon() {
		return pcon;
	}

	public void setPcon(String pcon) {
		this.pcon = pcon;
	}

	public String getZb() {
		return zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	public String getAtname() {
		return atname;
	}

	public void setAtname(String atname) {
		this.atname = atname;
	}

	public String getAtid() {
		return atid;
	}

	public void setAtid(String atid) {
		this.atid = atid;
	}

	public String getHtname() {
		return htname;
	}

	public void setHtname(String htname) {
		this.htname = htname;
	}

	public String getHtid() {
		return htid;
	}

	public void setHtid(String htid) {
		this.htid = htid;
	}

	public String getAnlikey() {
		return anlikey;
	}

	public void setAnlikey(String anlikey) {
		this.anlikey = anlikey;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
