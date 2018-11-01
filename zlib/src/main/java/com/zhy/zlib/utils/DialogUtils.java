package com.zhy.zlib.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.zlib.R;
import com.zhy.zlib.listener.ClickListener;

/**
 * Created by zhouyang on 2018/9/12.
 */

public class DialogUtils {
    private static Dialog dialog;

    /**
     * 普通提示弹框
     *
     * @param context      上下文
     * @param titleResId   标题
     * @param messageResId 内容
     * @param btuNameResId 按钮名
     * @param listener     回调监听
     */
    public static void showDialog(Context context, int titleResId, int messageResId, int btuNameResId, final ClickListener listener) {
        if (dialog == null) {
            View view = View.inflate(context, R.layout.dialog_common, null);
            dialog = new Dialog(context);
            view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.yes();
                }
            });
            if (titleResId == 0) {
                view.findViewById(R.id.dialog_title).setVisibility(View.GONE);
            } else {
                TextView tvTitle = view.findViewById(R.id.dialog_title);
                tvTitle.setText(context.getResources().getString(titleResId));
            }
            TextView tvMessage = view.findViewById(R.id.dialog_message);
            tvMessage.setText(context.getResources().getString(messageResId));
            TextView tvBtu = view.findViewById(R.id.tv_ok);
            tvBtu.setText(context.getResources().getString(btuNameResId));
            dialog.addContentView(view, new LinearLayout.LayoutParams(ScreenUtils.dip2px(context, 288), ScreenUtils.dip2px(context, 185)));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    dialog = null;
                }
            });
        }

        dialog.show();
    }

    public static void showDialog(Context context, String title, String message, String btuName, final ClickListener listener) {
        if (dialog == null) {
            View view = View.inflate(context, R.layout.dialog_common, null);
            dialog = new Dialog(context);
            view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.yes();
                }
            });
            if (TextUtils.isEmpty(title)) {
                view.findViewById(R.id.dialog_title).setVisibility(View.GONE);
            } else {
                TextView tvTitle = view.findViewById(R.id.dialog_title);
                tvTitle.setText(title);
            }
            TextView tvMessage = view.findViewById(R.id.dialog_message);
            tvMessage.setText(message);
            TextView tvBtu = view.findViewById(R.id.tv_ok);
            tvBtu.setText(btuName);
            dialog.addContentView(view, new LinearLayout.LayoutParams(ScreenUtils.dip2px(context, 288), ScreenUtils.dip2px(context, 185)));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    dialog = null;
                }
            });
        }

        dialog.show();
    }

    public static void showDoubleBtnDialog(Context context, String title, String message, final ClickListener listener) {
        if (dialog == null) {
            View view = View.inflate(context, R.layout.dialog_double_btn, null);
            dialog = new Dialog(context);
            view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.yes();

                }
            });
            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.no();
                }
            });
            if (TextUtils.isEmpty(title)) {
                view.findViewById(R.id.dialog_title).setVisibility(View.GONE);
            } else {
                TextView tvTitle = view.findViewById(R.id.dialog_title);
                tvTitle.setText(title);
            }
            TextView tvMessage = view.findViewById(R.id.dialog_message);
            tvMessage.setText(message);
            dialog.addContentView(view, new LinearLayout.LayoutParams(ScreenUtils.dip2px(context, 288), ScreenUtils.dip2px(context, 185)));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    dialog = null;
                }
            });
        }

        dialog.show();
    }

    /**
     * 双按钮提示弹框
     *
     * @param context      上下文
     * @param titleResId   标题
     * @param messageResId 内容
     * @param listener     回调监听
     */
    public static void showDoubleBtnDialog(Context context, int titleResId, int messageResId, final ClickListener listener) {
        if (dialog == null) {
            View view = View.inflate(context, R.layout.dialog_double_btn, null);
            dialog = new Dialog(context);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            view.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.yes();
                }
            });
            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) listener.no();
                }
            });
            if (titleResId == 0) {
                view.findViewById(R.id.dialog_title).setVisibility(View.GONE);
            } else {
                TextView tvTitle = view.findViewById(R.id.dialog_title);
                tvTitle.setText(context.getResources().getString(titleResId));
            }
            TextView tvMessage = view.findViewById(R.id.dialog_message);
            tvMessage.setText(context.getResources().getString(messageResId));
            dialog.addContentView(view, new LinearLayout.LayoutParams(ScreenUtils.dip2px(context, 288), ScreenUtils.dip2px(context, 185)));
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (listener != null) listener.onDismiss();
                    dialog = null;
                }
            });
        }

        dialog.show();
    }

}
