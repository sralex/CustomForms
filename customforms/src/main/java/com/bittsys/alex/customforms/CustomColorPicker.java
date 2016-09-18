package com.bittsys.alex.customforms;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by alex on 21/12/15.
 */
public class CustomColorPicker extends LinearLayout {
    View color;
    TextView tvTitle;
    TextView tvText;
    String title ;
    String text;
    boolean inGroup;
    LinearLayout content;
    public CustomColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.CustomColorPicker);
        inGroup = a.getBoolean(R.styleable.CustomColorPicker_hasBackground, true);
        init();
        title = a.getString(R.styleable.CustomColorPicker_label);
        text = a.getString(R.styleable.CustomColorPicker_android_text);
        tvText.setText(text);
        if(title!=null) {
            tvTitle.setText(title);
            int padding_in_dp = 10;  // 6 dps
            final float scale = getResources().getDisplayMetrics().density;
            int padding_in_px = (int) (padding_in_dp * scale + 0.5f);
            content.setPadding(padding_in_px, 0, padding_in_px, 0);
        }else{
            tvTitle.setVisibility(View.GONE);
        }
        int color  = a.getColor(R.styleable.CustomColorPicker_android_color,0xff000000);
        setColor(color);

        a.recycle();
    }

    public void setColor(int col){
        //PorterDuffColorFilter color_ = new PorterDuffColorFilter(col, PorterDuff.Mode.MULTIPLY);
        //color.getBackground().setColorFilter(color_);
        color.setBackgroundColor(col);
    }
    public void init(){
        View root = inflate(getContext(), R.layout.item_custom_color_picker, this);
        if(inGroup){
            root.setBackgroundResource(R.drawable.item_input_style);
        }else{
            root.setBackgroundResource(R.drawable.item_input_style_group);
        }
        color = (View) root.findViewById(R.id.item_custom_color_picker_color);
        tvTitle = (TextView) root.findViewById(R.id.item_custom_color_picker_title);
        content = (LinearLayout) root.findViewById(R.id.item_custom_color_picker_content);
        tvText = (TextView) root.findViewById(R.id.item_custom_color_picker_text);
    }

}
