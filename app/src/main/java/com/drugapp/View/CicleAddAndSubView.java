package com.drugapp.View;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.drugapp.R;

import java.math.BigDecimal;

public class CicleAddAndSubView extends LinearLayout implements View.OnClickListener{

    public static final int TYPE_SUBTRACT = 0;//减
    public static final int TYPE_ADD = 1;//加
    private static final int DEFAULT_NUM=0;//默认num值

    private View mLayoutView;
    private Context mContext;
    private ImageView mBtnAdd;//加按钮
    private ImageView mBtnSub;//减按钮
    private TextView mTvCount;//数量显示
    private int mNum;
    private OnNumChangeListener mOnNumChangeListener;

    /**
     * 设置监听回调
     *
     * @param onNumChangeListener
     */
    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
        this.mOnNumChangeListener = onNumChangeListener;
    }

    public CicleAddAndSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutView=LayoutInflater.from(context).inflate(R.layout.add_sub_view, this);
        this.mContext=context;

        initView();
        initData();
        setListener();
    }

    private void initView(){
        mBtnAdd = (ImageView) mLayoutView.findViewById(R.id.btn_add);
        mBtnSub = (ImageView) mLayoutView.findViewById(R.id.btn_sub);
        mTvCount = (TextView) mLayoutView.findViewById(R.id.tv_count);

        setPadding(1, 1, 1, 1);
        //重新设置mBtnAdd，mBtnSub宽高，用来保证显示正方形
        setViewSize(mBtnAdd);
        setViewSize(mBtnSub);
    }

    private void initData(){
        setAddBtnImageResource(R.mipmap.ic_add_money);
        setSubBtnImageResource(R.mipmap.ic_sub_money);
        mNum=DEFAULT_NUM;
        setNum(mNum);//设置默认数量
    }

    private void setListener(){
        mBtnAdd.setOnClickListener(this);
        mBtnSub.setOnClickListener(this);
    }

    private void setViewSize(final View view){
        view.post(new Runnable(){
            public void run() {//这里获取宽高
                int width=view.getWidth();
                int height=view.getHeight();

                LayoutParams params= (LayoutParams) view.getLayoutParams();
                if(width<height){
                    params.height=width;
                }else if(width>height){
                    params.width=height;
                }
                view.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onClick(View v) {
        String countText = mTvCount.getText().toString();
        if (TextUtils.isEmpty(countText)) {
            mNum = DEFAULT_NUM;
            mTvCount.setText(String.valueOf(mNum));
            return;
        }
        switch(v.getId()){
            case R.id.btn_add://加号
                mNum = add(mNum,1);
                setNum(mNum);
                if (mOnNumChangeListener != null) {
                    mOnNumChangeListener.onNumChange(mLayoutView, TYPE_ADD, getNum());
                }
                break;
            case R.id.btn_sub://减号
                mNum = sub(mNum,1);
                setNum(mNum);
                if (mOnNumChangeListener != null) {
                    mOnNumChangeListener.onNumChange(mLayoutView, TYPE_SUBTRACT, getNum());
                }
                break;
            default:
                break;
        }
    }

    public static int add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).intValue();
    }

    public static int sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).intValue();
    }


    /**
     * 设置中间的距离
     *
     * @param distance
     */
    public void setMiddleDistance(int distance) {
        mTvCount.setPadding(distance, 0, distance, 0);
    }

    /**
     * 设置数量
     *
     * @param num
     */
    public void setNum(int num) {
        this.mNum = num;
        if (mNum > 0) {
            if(mNum>9){
                mNum=9;
                Toast.makeText(mContext, "最多购买9份哦", Toast.LENGTH_SHORT).show();
            }
            mBtnSub.setEnabled(true);
            mTvCount.setText(String.valueOf(mNum));
        } else {
            mBtnSub.setEnabled(false);
            mTvCount.setText("1");
            mNum=1;
        }
    }

    /**
     * 获取值
     *
     * @return
     */
    public int getNum() {
        String countText=mTvCount.getText().toString().trim();
        if (!TextUtils.isEmpty(countText)) {
            return add(Double.parseDouble(countText),0);
        } else {
            return DEFAULT_NUM;
        }
    }


    /**
     * 设置加号图片
     *
     * @param addBtnDrawable
     */
    public void setAddBtnImageResource(int addBtnDrawable) {
        mBtnAdd.setImageResource(addBtnDrawable);
    }

    /**
     * 设置减法图片
     *
     * @param subBtnDrawable
     */
    public void setSubBtnImageResource(int subBtnDrawable) {
        mBtnSub.setImageResource(subBtnDrawable);
    }

    /**
     * 设置加法减法的背景色
     *
     * @param addBtnColor
     * @param subBtnColor
     */
    public void setButtonBgColor(int addBtnColor, int subBtnColor) {
        mBtnAdd.setBackgroundColor(addBtnColor);
        mBtnSub.setBackgroundColor(subBtnColor);
    }

    public interface OnNumChangeListener {
        /**
         * 监听方法
         *
         * @param view
         * @param stype 点击按钮的类型
         * @param num   返回的数值
         */
        void onNumChange(View view, int stype, int num);
    }

}