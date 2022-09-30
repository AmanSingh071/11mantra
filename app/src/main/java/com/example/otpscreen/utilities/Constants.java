package com.example.otpscreen.utilities;

import java.util.HashMap;

public class Constants {
    public static final String REMOTE_MSG_AUTHORIZATION="Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE="Content-Type";

    public static final String REMOTE_MSG_TYPE="type";
    public static final String REMOTE_MSG_INVITATION="invitation";
    public static final String REMOTE_MSG_MEETING_TYPE="meetingtype";
    public static final String REMOTE_MSG_INVITER_TOKEN="invitertoken";
    public static final String REMOTE_MSG_DATA="data";
    public static final String REMOTE_MSG_REGISTRATION_IDS="registration_ids";
    public static final String REMOTE_MSG_INVITATION_RESPONSE="invitationResponse";
    public static final String REMOTE_MSG_INVITATION_ACCEPTED="accepted";
    public static final String REMOTE_MSG_INVITATION_REJECTED="rejected";
    public static final String REMOTE_MSG_INVITATION_CANCELLED="cancelled";
    public static final String REMOTE_MSG_INVITATION_MEETING_ROOM="meetingroom";
    public static final String REMOTE_MSG_INVITATION_MEETING_ROOM2="meetingroom2";
    public static final String REMOTE_MSG_INVITATION_MEETING_NAME="meetingname";
    public static final String REMOTE_MSG_INVITATION_MEETING_IMAGE="meetingimage";
    public static final String REMOTE_MSG_INVITATION_MEETING_TYPE="video";







    public static HashMap<String ,String>getRemoteMessageHeaders(){
        HashMap<String,String>headers=new HashMap<>();
        headers.put(
                Constants.REMOTE_MSG_AUTHORIZATION,
                "key=AAAAqP-i9bM:APA91bG_G__oH7o3C9DluVotVLrZ0HekntsLHznzrJuCSnmr1bu62-mVu0A7Y-RYat_I-2BuxyhbvxNihu55s1HKkH03x-PH_ZbttxjsfOp9AHfEMdw1737xMRm9_6bP6-lOF6CnU-g2"
        );
        headers.put(Constants.REMOTE_MSG_CONTENT_TYPE,"application/json");
        return headers;
    }
}
