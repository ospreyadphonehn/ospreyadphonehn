package osprey_adphone_hn.cellcom.com.cn.activity.dhb;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import osprey_adphone_hn.cellcom.com.cn.R;
import osprey_adphone_hn.cellcom.com.cn.activity.csh.CshFragmentActivity;
import osprey_adphone_hn.cellcom.com.cn.activity.jsh.JshFragmentActivity;
import osprey_adphone_hn.cellcom.com.cn.activity.main.WebViewActivity;
import osprey_adphone_hn.cellcom.com.cn.activity.setting.GrzlActivity;
import osprey_adphone_hn.cellcom.com.cn.activity.setting.GwcActivity;
import osprey_adphone_hn.cellcom.com.cn.bean.UserInfo;
import osprey_adphone_hn.cellcom.com.cn.bean.UserInfoComm;
import osprey_adphone_hn.cellcom.com.cn.net.FlowConsts;
import osprey_adphone_hn.cellcom.com.cn.net.HttpHelper;
import osprey_adphone_hn.cellcom.com.cn.util.BitMapUtil;
import osprey_adphone_hn.cellcom.com.cn.util.SharepreferenceUtil;
import osprey_adphone_hn.cellcom.com.cn.widget.Rotate3DAnimation;
import osprey_adphone_hn.cellcom.com.cn.widget.jazzyviewpager.JazzyViewPager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cellcom.com.cn.net.CellComAjaxHttp;
import cellcom.com.cn.net.CellComAjaxParams;
import cellcom.com.cn.net.CellComAjaxResult;
import cellcom.com.cn.net.base.CellComHttpInterface;
import cellcom.com.cn.util.Des3;

/**
 * 
 * @author 个人中心
 * 
 */
public class DhbGrzxActivity extends Fragment {
	private Activity act;
	private ImageView video_img;
	private FinalBitmap finalBitmap;
	private TextView tv_fgg, tv_yyk, tv_jfk, tv_hfk, tv_cxzs, tv_zbsh, tv_shjf,
			tv_zxgw;
	private LinearLayout ll_zhcz, ll_fgg, ll_yyk, ll_jfk, ll_hfk, ll_cxzs,
			ll_zbsh, ll_gwc;
	FinalBitmap bitmap;
	JazzyViewPager jvp;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		if (activity instanceof DhbFragmentActivity) {
			act = (DhbFragmentActivity) activity;
		} else if (activity instanceof JshFragmentActivity) {
			act = (JshFragmentActivity) activity;
		} else {
			act = (CshFragmentActivity) activity;
		}
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.os_dhb_grzx_activity1, container, false);
		initView1(v, savedInstanceState);
		initViewData();
		initListener1();
		return v;
	}

	private void initViewData() {

		String uid = SharepreferenceUtil.readString(act,
				SharepreferenceUtil.fileName, "uid", "");
		String viplevel = SharepreferenceUtil.readString(act,
				SharepreferenceUtil.fileName, "viplevel" + uid, "");
		if (TextUtils.isEmpty(viplevel)) {
		} else {
			if ((Float.parseFloat(viplevel) + "").contains(".0")) {
				viplevel = Integer.parseInt(viplevel) + "";
			} else {
				viplevel = Float.parseFloat(viplevel) + "";
			}
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	/**
	 * 初始化控件
	 */
	private void initView(View v, Bundle savedInstanceState) {
		tv_fgg = (TextView) v.findViewById(R.id.tv_fgg);
		tv_yyk = (TextView) v.findViewById(R.id.tv_yyk);
		tv_jfk = (TextView) v.findViewById(R.id.tv_jfk);
		tv_hfk = (TextView) v.findViewById(R.id.tv_hfk);
		tv_cxzs = (TextView) v.findViewById(R.id.tv_cxzs);
		tv_zbsh = (TextView) v.findViewById(R.id.tv_zbsh);
		tv_shjf = (TextView) v.findViewById(R.id.tv_shjf);
		tv_zxgw = (TextView) v.findViewById(R.id.tv_zxgw);

	}

	private void initView1(View v, Bundle savedInstanceState) {
		video_img = (ImageView) v.findViewById(R.id.video_img);
		ll_zhcz = (LinearLayout) v.findViewById(R.id.ll_zhcz);
		ll_fgg = (LinearLayout) v.findViewById(R.id.ll_fgg);
		ll_yyk = (LinearLayout) v.findViewById(R.id.ll_yyk);
		ll_jfk = (LinearLayout) v.findViewById(R.id.ll_jfk);
		ll_hfk = (LinearLayout) v.findViewById(R.id.ll_hfk);
		ll_cxzs = (LinearLayout) v.findViewById(R.id.ll_cxzs);
		ll_zbsh = (LinearLayout) v.findViewById(R.id.ll_zbsh);
		ll_gwc = (LinearLayout) v.findViewById(R.id.ll_gwc);
		jvp = (JazzyViewPager) v.findViewById(R.id.jazzy_viewpager_os_dhb_grzx);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Rotate3DAnimation rotation_zhcz = new Rotate3DAnimation(-90, 0,
						ll_zhcz.getWidth() / 2, ll_zhcz.getHeight() / 2, 0.0f,
						false);
				rotation_zhcz.setDuration(500);
				rotation_zhcz.setFillAfter(true);
				ll_zhcz.startAnimation(rotation_zhcz);
				ll_zhcz.setVisibility(View.VISIBLE);

				Rotate3DAnimation rotation_hfk = new Rotate3DAnimation(-90, 0,
						ll_hfk.getWidth() / 2, ll_hfk.getHeight() / 2, 0.0f,
						false);
				rotation_hfk.setDuration(500);
				rotation_hfk.setFillAfter(true);
				ll_hfk.startAnimation(rotation_hfk);
				ll_hfk.setVisibility(View.VISIBLE);

				Rotate3DAnimation rotation_yyk = new Rotate3DAnimation(-90, 0,
						ll_yyk.getWidth() / 2, ll_yyk.getHeight() / 2, 0.0f,
						false);
				rotation_yyk.setDuration(500);
				rotation_yyk.setFillAfter(true);
				ll_yyk.startAnimation(rotation_yyk);
				ll_yyk.setVisibility(View.VISIBLE);

				Rotate3DAnimation rotation_jfk = new Rotate3DAnimation(-90, 0,
						ll_jfk.getWidth() / 2, ll_jfk.getHeight() / 2, 0.0f,
						false);
				rotation_jfk.setDuration(500);
				rotation_jfk.setFillAfter(true);
				ll_jfk.startAnimation(rotation_jfk);
				ll_jfk.setVisibility(View.GONE);

				rotation_jfk.setInterpolator(new AccelerateInterpolator());
				// 设置监听
				rotation_jfk.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub

						Rotate3DAnimation rotation_fgg = new Rotate3DAnimation(
								-90, 0, ll_fgg.getWidth() / 2, ll_fgg
										.getHeight() / 2, 0.0f, false);
						rotation_fgg.setDuration(500);
						rotation_fgg.setFillAfter(true);
						ll_fgg.startAnimation(rotation_fgg);
						ll_fgg.setVisibility(View.GONE);

						Rotate3DAnimation rotation_cxzs = new Rotate3DAnimation(
								-90, 0, ll_cxzs.getWidth() / 2, ll_cxzs
										.getHeight() / 2, 0.0f, false);
						rotation_cxzs.setDuration(500);
						rotation_cxzs.setFillAfter(true);
						ll_cxzs.startAnimation(rotation_cxzs);
						ll_cxzs.setVisibility(View.VISIBLE);

						Rotate3DAnimation rotation_zbsh = new Rotate3DAnimation(
								-90, 0, ll_zbsh.getWidth() / 2, ll_zbsh
										.getHeight() / 2, 0.0f, false);
						rotation_zbsh.setDuration(500);
						rotation_zbsh.setFillAfter(true);
						ll_zbsh.startAnimation(rotation_zbsh);
						ll_zbsh.setVisibility(View.VISIBLE);

						Rotate3DAnimation rotation_gwc = new Rotate3DAnimation(
								-90, 0, ll_gwc.getWidth() / 2, ll_gwc
										.getHeight() / 2, 0.0f, false);
						rotation_gwc.setDuration(500);
						rotation_gwc.setFillAfter(true);
						ll_gwc.startAnimation(rotation_gwc);
						ll_gwc.setVisibility(View.VISIBLE);
					}
				});
			}
		}, 300);
	}

	/**
	 * 初始化监听
	 */
	private void initListener() {
		// 积分商城
		tv_fgg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxJfscActivity.class);
				startActivity(intent);
			}
		});

		// 银币库
		tv_yyk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxHfkActivity.class);
				startActivity(intent);
			}
		});

		// 积分库
		tv_jfk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxJfkActivity.class);
				startActivity(intent);
			}
		});

		// 亮币库
		tv_hfk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxHfkActivity.class);
				startActivity(intent);
			}
		});

		// 出行助手
		tv_cxzs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(act, "功能正在开发中...", Toast.LENGTH_SHORT).show();
			}
		});

		// 周边生活
		tv_zbsh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(act, "功能正在开发中...", Toast.LENGTH_SHORT).show();
			}
		});

		// 生活缴费
		tv_shjf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(act, "功能正在开发中...", Toast.LENGTH_SHORT).show();
			}
		});

		// 在线购物
		tv_zxgw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(act, "功能正在开发中...", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initListener1() {
		// 购物车
		ll_gwc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(act, GwcActivity.class);
				// startActivity(intent);
				Toast.makeText(DhbGrzxActivity.this.getActivity(), "功能正在开发中",
						Toast.LENGTH_SHORT).show();
			}
		});
		// 账号充值
		ll_zhcz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxZhczActivity.class);
				startActivity(intent);
			}
		});
		// 积分商城
		ll_fgg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(act, DhbGrzxJfscActivity.class);
				// startActivity(intent);
				Toast.makeText(DhbGrzxActivity.this.getActivity(), "功能正在开发中",
						Toast.LENGTH_SHORT).show();
			}
		});

		// 银币库
		ll_yyk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxYykActivity.class);
				startActivity(intent);
			}
		});

		// 积分库
		ll_jfk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(act, DhbGrzxJfkActivity.class);
				// startActivity(intent);
				Toast.makeText(DhbGrzxActivity.this.getActivity(), "功能正在开发中",
						Toast.LENGTH_SHORT).show();
			}
		});

		// 亮币库
		ll_hfk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, DhbGrzxHfkActivity.class);
				startActivity(intent);
			}
		});

		// 出行助手
		ll_cxzs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(act, "功能正在开发中...", Toast.LENGTH_SHORT).show();
				// Intent intent = new Intent(act,JshSafetyInfoActivity.class);
				// startActivity(intent);
			}
		});

		// 周边生活
		ll_zbsh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(act, WebViewActivity.class);
				intent.putExtra("url", "http://wap.dianping.com");
				startActivity(intent);
			}
		});

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		finalBitmap = FinalBitmap.create(act);
		bitmap = FinalBitmap.create(act);
		getGrzl();

	}

	// 获取个人资料
	protected void getGrzl() {
		final String uid = SharepreferenceUtil.readString(act,
				SharepreferenceUtil.fileName, "uid");
		CellComAjaxParams cellComAjaxParams = new CellComAjaxParams();
		cellComAjaxParams.put("uid", uid);
		HttpHelper.getInstances(act).send(FlowConsts.YYW_USERINFO,
				cellComAjaxParams, CellComAjaxHttp.HttpWayMode.POST,
				new CellComHttpInterface.NetCallBack<CellComAjaxResult>() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
					}

					@Override
					public void onFailure(Throwable t, String strMsg) {
						// TODO Auto-generated method stub
						super.onFailure(t, strMsg);
					}

					@Override
					public void onSuccess(CellComAjaxResult arg0) {
						// TODO Auto-generated method stub
						UserInfoComm userInfoComm = arg0.read(
								UserInfoComm.class,
								CellComAjaxResult.ParseType.GSON);
						String state = userInfoComm.getReturnCode();
						String msg = userInfoComm.getReturnMessage();
						if (!FlowConsts.STATUE_1.equals(state)) {
							return;
						}
						initValue(userInfoComm.getBody(), uid);
					}
				});
	}

	protected void initValue(UserInfo userInfo, String uid) {
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName,
				"headpicurl", userInfo.getHeadpicurl());
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName, "huafei",
				userInfo.getHuafei());
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName, "jifen",
				userInfo.getJifen());
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName, "usermom",
				userInfo.getUsermom());
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName,
				"username", userInfo.getUsername());
		SharepreferenceUtil.write(act, SharepreferenceUtil.fileName, "yinyuan",
				userInfo.getYinyuan());

		// 头像赋值
		if (!SharepreferenceUtil.readString(act, SharepreferenceUtil.fileName,
				"headpicurl", "").equals("")
				&& (SharepreferenceUtil.readString(act,
						SharepreferenceUtil.fileName, "headpicurl", "")
						.contains(".jpg") || SharepreferenceUtil.readString(
						act, SharepreferenceUtil.fileName, "headpicurl", "")
						.contains(".png"))) {

			finalBitmap.display(
					new ImageView(DhbGrzxActivity.this.getActivity()),
					SharepreferenceUtil.readString(act,
							SharepreferenceUtil.fileName, "headpicurl", ""));
		}
		if (userInfo.getViplevel() != null
				&& !userInfo.getViplevel().trim().equals("")
				&& !userInfo.getViplevel().trim().equals("0")) {

			SharepreferenceUtil.write(act, SharepreferenceUtil.fileName,
					"viplevel" + uid, userInfo.getViplevel());
		}

		bitmap.display(new ImageView(DhbGrzxActivity.this.getActivity()),
				CshFragmentActivity.meiti);

		BitMapUtil.getImgOpt(DhbGrzxActivity.this.getActivity(), bitmap, jvp,
				R.drawable.os_login_topicon);

	}

	public void reflesh() {
		// TODO Auto-generated method stub
		initViewData();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		act.onKeyDown(keyCode, event);
		return false;
	}
}
