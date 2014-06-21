package com.example.testblogdrafttwo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
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
	 * 创建监听器
	 */
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(context, "进入点击判断", Toast.LENGTH_SHORT).show();
			switch (v.getId()) {
			case R.id.send_btn:
				Toast.makeText(context, "点击发送", Toast.LENGTH_SHORT).show();
				
				break;
			case R.id.update_btn:
				Toast.makeText(context, "点击更新", Toast.LENGTH_SHORT).show();
				
				break;
			default:
				break;
			}
		}
	};

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
	}

}
