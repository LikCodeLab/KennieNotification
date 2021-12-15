package com.kennie.notification;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import java.util.Arrays;

public class Channel implements Parcelable {

    @NonNull
    private String channelId; // 唯一渠道ID

    @NonNull
    private String name; // 用户可见名称

    private int importance; // 重要性级别

    private String description; // 描述(可为空)

    @NotificationCompat.NotificationVisibility
    private int lockScreenVisibility = NotificationCompat.VISIBILITY_SECRET; // 锁定屏幕公开范围

    private Long[] vibrate; // 震动模式

    private Uri sound; // 声音

    @NonNull
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(@NonNull String channelId) {
        this.channelId = channelId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLockScreenVisibility() {
        return lockScreenVisibility;
    }

    public void setLockScreenVisibility(int lockScreenVisibility) {
        this.lockScreenVisibility = lockScreenVisibility;
    }

    public Long[] getVibrate() {
        return vibrate;
    }

    public void setVibrate(Long[] vibrate) {
        this.vibrate = vibrate;
    }

    public Uri getSound() {
        return sound;
    }

    public void setSound(Uri sound) {
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId='" + channelId + '\'' +
                ", name='" + name + '\'' +
                ", importance=" + importance +
                ", description='" + description + '\'' +
                ", lockScreenVisibility=" + lockScreenVisibility +
                ", vibrate=" + Arrays.toString(vibrate) +
                ", sound=" + sound +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.channelId);
        dest.writeString(this.name);
        dest.writeInt(this.importance);
        dest.writeString(this.description);
        dest.writeInt(this.lockScreenVisibility);
        dest.writeArray(this.vibrate);
        dest.writeParcelable(this.sound, flags);
    }

    public void readFromParcel(Parcel source) {
        this.channelId = source.readString();
        this.name = source.readString();
        this.importance = source.readInt();
        this.description = source.readString();
        this.lockScreenVisibility = source.readInt();
        this.vibrate = (Long[]) source.readArray(Long[].class.getClassLoader());
        this.sound = source.readParcelable(Uri.class.getClassLoader());
    }

    public Channel() {
    }

    protected Channel(Parcel in) {
        this.channelId = in.readString();
        this.name = in.readString();
        this.importance = in.readInt();
        this.description = in.readString();
        this.lockScreenVisibility = in.readInt();
        this.vibrate = (Long[]) in.readArray(Long[].class.getClassLoader());
        this.sound = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
