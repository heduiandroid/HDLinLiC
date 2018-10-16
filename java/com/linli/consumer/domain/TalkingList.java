package com.linli.consumer.domain;

/**
 * Created by Administrator on 2016/6/10.
 */
public class TalkingList {
    private Integer mIntegerUser;//对话框的用户头像
    private String mTextViewUserName;//对话的用户名
    private String mTextViewTalkingRecord;//对话的聊天记录


    public TalkingList(Integer integerUser, String textViewUserName, String textViewTalkingRecord) {
        mIntegerUser = integerUser;
        mTextViewUserName = textViewUserName;
        mTextViewTalkingRecord = textViewTalkingRecord;
    }

    public TalkingList(Integer integerUser, String textViewTalkingRecord) {
        mIntegerUser = integerUser;
        mTextViewTalkingRecord = textViewTalkingRecord;
    }

    public Integer getIntegerUser() {
        return mIntegerUser;
    }

    public void setIntegerUser(Integer integerUser) {
        mIntegerUser = integerUser;
    }

    public String getTextViewUserName() {
        return mTextViewUserName;
    }

    public void setTextViewUserName(String textViewUserName) {
        mTextViewUserName = textViewUserName;
    }

    public String getTextViewTalkingRecord() {
        return mTextViewTalkingRecord;
    }

    public void setTextViewTalkingRecord(String textViewTalkingRecord) {
        mTextViewTalkingRecord = textViewTalkingRecord;
    }
}
