package com.example.lifeofnote.ui.width;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lifeofnote.R;
import com.example.lifeofnote.callback.DialogInputCallback;

public class InputDialog extends Dialog {

    private ImageView ivClose;
    private TextView tvConfig;
    private EditText etInput;
    private TextView tvInputGropress;
    private DialogInputCallback dialogInputCallback;

    public void setDialogInputCallback(DialogInputCallback dialogInputCallback) {
        this.dialogInputCallback = dialogInputCallback;
    }

    public InputDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_input);
        setCanceledOnTouchOutside(true);
    }

    public void showDialog() {
        Window window = getWindow();
        window.setWindowAnimations(R.style.style_dialog);
        window.setBackgroundDrawableResource(R.color.tou_min);
        WindowManager.LayoutParams wl = window.getAttributes();
        //设置弹窗位置
        wl.gravity = Gravity.CENTER;
        wl.width = WindowManager.LayoutParams.MATCH_PARENT;
        wl.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wl);
        if (ivClose == null) {
            initViews();
        }
        show();
    }

    private void initViews() {
        ivClose = findViewById(R.id.iv_close);
        tvConfig = findViewById(R.id.tv_config);
        etInput = findViewById(R.id.et_input);
        tvInputGropress = findViewById(R.id.tv_input_progress);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tvConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInputCallback.inputCallBack(etInput.getText().toString());
                dismiss();
            }
        });
        tvConfig.setClickable(false);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0) {
                    tvConfig.setClickable(false);
                    tvConfig.setTextColor(getContext().getResources().getColor(R.color.tv_config_80));
                } else {
                    tvConfig.setClickable(true);
                    tvConfig.setTextColor(getContext().getResources().getColor(R.color.tv_config_100));
                }
                tvInputGropress.setText(editable.toString().length() + "/20");
            }
        });
    }
}
