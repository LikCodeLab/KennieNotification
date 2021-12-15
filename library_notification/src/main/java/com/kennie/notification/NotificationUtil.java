package com.kennie.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * 通知栏工具类
 */
public class NotificationUtil {

    private static final String CHANNEL_ID = "default_ID"; // 默认渠道ID
    private static final String CHANNEL_NAME = "default_Name"; // 默认渠道名称


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    private void CreateChannel(Context context, Channel channel) {
        NotificationChannel notificationChannel = new NotificationChannel(channel.getChannelId(), channel.getName(), channel.getImportance());
    }

    /**
     * 取消通知
     *
     * @param context   上下文
     * @param channelID 通知的唯一ID
     */
    private void cancel(Context context, int channelID) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(channelID);
    }

    /**
     * 取消所有通知
     *
     * @param context 上下文
     */
    private void cancel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
