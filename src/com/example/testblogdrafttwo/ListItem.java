package com.example.testblogdrafttwo;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Draft对应一行数据，修改和增加操作
 * @author liuxiuquan
 * 2014年6月20日
 */
public class ListItem extends Activity {
	/**转发自微博的ID输入框*/
	private EditText fromblogidEdit;
	/**发博人的ID输入框*/
	private EditText puseridEdit;
	/**微博的内容输入框*/
	private EditText pconEdit;
	/**指标信息输入框*/
	private EditText zbEdit;
	/**微博的at信息 @对象输入框*/
	private EditText atnameEdit;
	/**微博的at信息ID @id 输入框*/
	private EditText atidEdit;
	/**话题信息：名称 输入框*/
	private EditText htnameEdit;
	/**话题信息：ID 输入框*/
	private EditText htidEdit;
	/**案例信息 输入框*/
	private EditText anlikeyEdit;
	Bundle bunde;
	/**发送并返回按键*/
	Button send_btn;
	/**更新并回退按键*/
	Button update_btn;
	/**当前的context:等效于ListItem.this*/
	Activity context;
	/**主键*/
	private String key_id;
	/**数据库工具类*/
	private TodoDB draftDB;
	/**时间格式*/
	private SimpleDateFormat sDateFormat;
	/**调用DraftDemo的activity传来的*/
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.draft_detail);
		intent=this.getIntent();
		sDateFormat = new SimpleDateFormat("MM-dd hh:mm:ss");
		context = ListItem.this;
		initId();
		setIdClick();
	}

	/**
	 * 给组件增加监听事件
	 */
	private void setIdClick() {
		send_btn.setOnClickListener(onClickListener);
		update_btn.setOnClickListener(onClickListener);
	}

	/**
	 * 监听系统的返回键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ConfirmDelete();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	};

	/**是否保存为草稿对话框*/
	private void ConfirmDelete() {
		new AlertDialog.Builder(context)
				.setMessage("是否保存草稿箱？")
				.setPositiveButton("确定",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(context, "确认保存",
										Toast.LENGTH_SHORT).show();
								update();
							}
						})
				.setNegativeButton("取消",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(context, "放弃保存",
										Toast.LENGTH_SHORT).show();
								//更改startActivity为返回上一个activity
								context.setResult(RESULT_OK, intent);
								context.finish();
							}
						}).show();
	}

	/**
	 * 创建监听器
	 */
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.send_btn:
				Toast.makeText(context, "点击发送", Toast.LENGTH_SHORT).show();
				submit();
				break;
			case R.id.update_btn:
				Toast.makeText(context, "点击更新", Toast.LENGTH_SHORT).show();
				update();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 提交
	 */
	private void submit() {
		if (null == key_id) {
			Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
			return;
		}
		// TODO_LXQ 发送
		draftDB.delete(Integer.parseInt(key_id));
		Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
		//更改startActivity为返回上一个activity
		context.setResult(RESULT_OK, intent);
		context.finish();
	}

	/**
	 * 保存
	 */
	protected void save() {
		String fromblogid = fromblogidEdit.getText().toString();
		String puserid = puseridEdit.getText().toString();
		String pcon = pconEdit.getText().toString();
		String zb = zbEdit.getText().toString();
		String atname = atnameEdit.getText().toString();
		String atid = atidEdit.getText().toString();
		String htname = htnameEdit.getText().toString();
		String htid = htidEdit.getText().toString();
		String anlikey = anlikeyEdit.getText().toString();

		if (pcon.equals("")) {
			Toast.makeText(this, "内容字段不能为空，请确认后重试", Toast.LENGTH_SHORT).show();
			return;
		}
		/**添加时间*/
		String time = sDateFormat.format(new java.util.Date());
		draftDB.insert(fromblogid, puserid, pcon, zb, atname, atid, htname,
				htid, anlikey, time);
		Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();
		//更改startActivity为返回上一个activity
		context.setResult(RESULT_OK, intent);
		context.finish();
	}

	/**
	 * 更新
	 */
	protected void update() {
		// 主键为空进行保存
		if (null == key_id) {
			Toast.makeText(context, "提交保存", Toast.LENGTH_SHORT).show();
			save();
			return;
		}
		// 主键不为空进行更新
		String fromblogid = fromblogidEdit.getText().toString();
		String puserid = puseridEdit.getText().toString();
		String pcon = pconEdit.getText().toString();
		String zb = zbEdit.getText().toString();
		String atname = atnameEdit.getText().toString();
		String atid = atidEdit.getText().toString();
		String htname = htnameEdit.getText().toString();
		String htid = htidEdit.getText().toString();
		String anlikey = anlikeyEdit.getText().toString();
		// 内容字段不能为空
		if (pcon.equals("")) {
			Toast.makeText(this, "内容字段不能为空，请确认后重试", Toast.LENGTH_SHORT).show();
			return;
		}
		// 更新时间
		String time = sDateFormat.format(new java.util.Date());
		draftDB.update(Integer.parseInt(key_id), fromblogid, puserid, pcon, zb,
				atname, atid, htname, htid, anlikey, time);
		Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
		//更改startActivity为返回上一个activity
		context.setResult(RESULT_OK, intent);
		context.finish();
	}

	/**
	 * 初始化组件
	 */
	private void initId() {
		// 输入框
		fromblogidEdit = (EditText) findViewById(R.id.fromblogid);
		puseridEdit = (EditText) findViewById(R.id.puserid);
		pconEdit = (EditText) findViewById(R.id.pcon);
		zbEdit = (EditText) findViewById(R.id.zb);
		atnameEdit = (EditText) findViewById(R.id.atname);
		atidEdit = (EditText) findViewById(R.id.atid);
		htnameEdit = (EditText) findViewById(R.id.htname);
		htidEdit = (EditText) findViewById(R.id.htid);
		anlikeyEdit = (EditText) findViewById(R.id.anlikey);
		// 按键
		send_btn = (Button) findViewById(R.id.send_btn);
		update_btn = (Button) findViewById(R.id.update_btn);
		// 使用的bunde
		Bundle bunde = this.getIntent().getExtras();
		if (null != bunde) {
			DraftBean draftBean = (DraftBean) bunde
					.getSerializable("draftBean");
			Log.v("ListItem draftBean.getPcon()", draftBean.getPcon());
			key_id = draftBean.getKey_id();
			fromblogidEdit.setText(draftBean.getFromblogid());
			puseridEdit.setText(draftBean.getPuserid());
			pconEdit.setText(draftBean.getPcon());
			zbEdit.setText(draftBean.getZb());
			atnameEdit.setText(draftBean.getAtname());
			atidEdit.setText(draftBean.getAtid());
			htnameEdit.setText(draftBean.getHtname());
			htidEdit.setText(draftBean.getHtid());
			anlikeyEdit.setText(draftBean.getAnlikey());
		}
		// 数据库工具类
		draftDB = new TodoDB(this);
	}

}
