package com.bittsys.alex.customforms;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by alex on 20/12/15.
 */
public class CustomEditText extends LinearLayout {
    EditText input;
    ImageView icon;
    ImageView showPassword;
    TextView tvTitle;
    View root = null;
    LinearLayout content;
    private final String TYPE_CLASS_TEXT = "text";
    private final String TYPE_TEXT_VARIATION_PASSWORD ="password";
    private final String TYPE_CLASS_NUMBER ="number";
    private final int ICON_LEFT= 0;
    private final int ICON_RIGHT = 1;

    private static boolean passHide = false;
    private String titleText;
    private int iconAlignment =-1;
    private boolean inGroup;
    private String inputType;
    public CustomEditText(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.CustomEditText);
        inGroup = a.getBoolean(R.styleable.CustomEditText_hasBackground, true);
        inputType = a.getString(R.styleable.CustomEditText_custominputType);
        iconAlignment = a.getInt(R.styleable.CustomEditText_iconAlignment, 0);
        titleText = a.getString(R.styleable.CustomEditText_label);
        init();
        if(titleText!=null){
            int padding_in_dp = 10;  // 6 dps
            final float scale = getResources().getDisplayMetrics().density;
            int padding_in_px = (int) (padding_in_dp * scale + 0.5f);

            content.setPadding(padding_in_px,0,padding_in_px,0);
            tvTitle.setText(titleText);
        }else{
            tvTitle.setVisibility(View.GONE);
        }
        if(inputType!=null) {
            switch (inputType) {
                case TYPE_CLASS_TEXT:
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case TYPE_TEXT_VARIATION_PASSWORD:
                    input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;
                case TYPE_CLASS_NUMBER:
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
            }
        }else{
            input.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        final String tint = a.getString(R.styleable.CustomEditText_iconFocusColor);
        String color_ = a.getString(R.styleable.CustomEditText_iconColor);
        if(color_==null){
            color_="#999999";
        }
        final String color__=color_;
        input.setHint(a.getString(R.styleable.CustomEditText_inputHint));
        final int color = Color.parseColor(color_);
        icon.setColorFilter(color);

        if(inputType==null)
            inputType="";
        if(inputType.equals(TYPE_TEXT_VARIATION_PASSWORD)){
            showPassword.setColorFilter(color);
            showPassword.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //displayPopupWindow(input,context);
                    if (passHide) {
                        // show password
                        input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passHide = false;
                        int color = Color.parseColor(color__);
                        showPassword.setColorFilter(color);
                    } else {
                        // hide password
                        if (tint != null) {
                            int color = Color.parseColor(tint);
                            showPassword.setColorFilter(color);
                        }
                        input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passHide = true;
                    }
                }
            });
        }
        Drawable icon_ = a.getDrawable(R.styleable.CustomEditText_customIcon);
        icon.setImageDrawable(icon_);
        if(icon_==null){
            icon.setVisibility(View.GONE);
        }

            input.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        if (tint != null) {
                            int color = Color.parseColor(tint);
                            icon.setColorFilter(color);
                        }
                    } else {
                        int color2 = Color.parseColor(color__);
                        icon.setColorFilter(color2);

                    }
                }
            });

        //Don't forget this
        a.recycle();
    }
    private void displayPopupWindow(View anchorView,Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        PopupWindow popup = new PopupWindow(CustomEditText.this);
        View layout =inflater.inflate(R.layout.popup_content,null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView, 0, 0);

    }
    public void init(){
        if(inputType==null) {
        inputType = "text";
        }
            switch (inputType) {
                case TYPE_CLASS_TEXT:
                    //verificar si es derecho o izquierdo
                    if(iconAlignment==ICON_RIGHT){
                        root = inflate(getContext(), R.layout.item_custom_edit_text_input_right, this);
                    }else{
                        root = inflate(getContext(), R.layout.item_custom_edit_text_input, this);
                    }
                    break;
                case TYPE_TEXT_VARIATION_PASSWORD:
                        root = inflate(getContext(), R.layout.item_custom_edit_text_password, this);
                        showPassword = (ImageView) root.findViewById(R.id.item_input_show_password);
                    break;
                case TYPE_CLASS_NUMBER:
                    //verificar si es derecho o izquierdo
                    if(iconAlignment==ICON_RIGHT){
                        root = inflate(getContext(), R.layout.item_custom_edit_text_input_right, this);
                    }else{
                        root = inflate(getContext(), R.layout.item_custom_edit_text_input, this);
                    }
                    break;
            }




        if(inGroup){
            root.setBackgroundResource(R.drawable.item_input_style);
        }else{
            root.setBackgroundResource(R.drawable.item_input_style_group);
        }
        input = (EditText) root.findViewById(R.id.item_input_input);
        input.setHintTextColor(getResources().getColor(R.color.hintColor));
        icon = (ImageView) root.findViewById(R.id.item_input_icon);
        tvTitle = (TextView) root.findViewById(R.id.item_custom_edit_text_input_title);
        content = (LinearLayout) root.findViewById(R.id.item_custom_edit_text_input_content);
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public EditText getInput() {
        return input;
    }

    public void setInput(EditText input) {
        this.input = input;
    }
}