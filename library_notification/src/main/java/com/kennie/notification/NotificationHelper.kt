package com.kennie.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.kennie.library.notification.R

object NotificationHelper {

    /** 通知渠道-聊天消息(重要性级别-高：发出声音) */
    private val MESSAGE = NotificationUtil.Channel(
        channelId = "MESSAGE",
        name = BaseApplication.getContext().getString(R.string.channel_message),
        importance = NotificationManager.IMPORTANCE_DEFAULT
    )

    /** 通知渠道-@提醒消息(重要性级别-紧急：发出提示音，并以浮动通知的形式显示 & 锁屏显示 & 振动0.25s )*/
    private val MENTION = NotificationUtil.Channel(
        channelId = "MENTION",
        name = BaseApplication.getContext().getString(R.string.channel_mention),
        importance = NotificationManager.IMPORTANCE_HIGH,
        lockScreenVisibility = NotificationCompat.VISIBILITY_PUBLIC,
        vibrate = longArrayOf(0, 250)
    )

    /** 通知渠道-系统通知(重要性级别-中：无提示音) */
    private val NOTICE = NotificationUtil.Channel(
        channelId = "NOTICE",
        name = BaseApplication.getContext().getString(R.string.channel_notice),
        importance = NotificationManager.IMPORTANCE_LOW
    )

    /** 通知渠道-音视频通话(重要性级别-紧急：发出提示音，并以浮动通知的形式显示 & 锁屏显示 & 振动4s停2s再振动4s ) */
    private val CALL = NotificationUtil.Channel(
        channelId = "CALL",
        name = BaseApplication.getContext().getString(R.string.channel_call),
        importance = NotificationManager.IMPORTANCE_HIGH,
        lockScreenVisibility = NotificationCompat.VISIBILITY_PUBLIC,
        vibrate = longArrayOf(0, 4000, 2000, 4000),
        sound = Uri.parse("android.resource://" + BaseApplication.getContext().packageName + "/" + R.raw.iphone)
    )

    /**
     * 显示聊天消息
     * @param context 上下文
     * @param id      通知的唯一ID
     * @param title   标题
     * @param text    正文文本
     */
    fun notifyMessage(
        context: Context,
        id: Int,
        title: String?,
        text: String?
    ) {
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationUtil.createNotificationBuilder(
            context,
            MESSAGE,
            title,
            text,
            intent
        )

        // 默认情况下，通知的文字内容会被截断以放在一行。如果您想要更长的通知，可以使用 setStyle() 添加样式模板来启用可展开的通知。
        builder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(text)
        )

        NotificationUtil.notify(context, id, buildDefaultConfig(builder));
    }

    /**
     * 显示@提醒消息
     * @param context 上下文
     * @param id      通知的唯一ID
     * @param title   标题
     * @param text    正文文本
     */
    fun notifyMention(
        context: Context,
        id: Int,
        title: String?,
        text: String?
    ) {
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationUtil.createNotificationBuilder(
            context,
            MENTION,
            title,
            text,
            intent
        )

        // 默认情况下，通知的文字内容会被截断以放在一行。如果您想要更长的通知，可以使用 setStyle() 添加样式模板来启用可展开的通知。
        builder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(text)
        )

        NotificationUtil.notify(context, id, buildDefaultConfig(builder));
    }

    /**
     * 显示系统通知
     * @param context 上下文
     * @param id      通知的唯一ID
     * @param title   标题
     * @param text    正文文本
     */
    fun notifyNotice(
        context: Context,
        id: Int,
        title: String?,
        text: String?
    ) {
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationUtil.createNotificationBuilder(
            context,
            NOTICE,
            title,
            text,
            intent
        )

        NotificationUtil.notify(context, id, buildDefaultConfig(builder));
    }

    /**
     * 显示音视频通话
     * @param context 上下文
     * @param id      通知的唯一ID
     * @param title   标题
     * @param text    正文文本
     */
    fun notifyCall(
        context: Context,
        id: Int,
        title: String?,
        text: String?
    ) {
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationUtil.createNotificationBuilder(
            context,
            CALL,
            title,
            text,
            intent
        )

        NotificationUtil.notify(context, id, buildDefaultConfig(builder));
    }

    /**
     * 构建应用通知的默认配置
     * @param builder 构建器
     */
    private fun buildDefaultConfig(builder: NotificationCompat.Builder): Notification {
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        return builder.build()
    }
}