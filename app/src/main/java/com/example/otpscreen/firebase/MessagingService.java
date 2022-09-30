package com.example.otpscreen.firebase;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.otpscreen.activities.chatcallIncoming;
import com.example.otpscreen.activities.incomingcall;
import com.example.otpscreen.utilities.Constants;
import com.example.otpscreen.utilities.Constants2;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

Boolean chat=false;
    Boolean call=false;
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);


    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String type = remoteMessage.getData().get(Constants.REMOTE_MSG_TYPE);
        String type2 = remoteMessage.getData().get(Constants2.REMOTE_MSG_TYPE);
        String typevideo = remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_MEETING_TYPE);
        String typechat = remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_MEETING_TYPE);


            if (type2.equals(Constants2.REMOTE_MSG_INVITATION) && typevideo .equals("chat")) {


                Intent intent2 = new Intent(MessagingService.this, chatcallIncoming.class);
                intent2.putExtra(
                        Constants2.REMOTE_MSG_INVITER_TOKEN, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITER_TOKEN)
                );
                intent2.putExtra(
                        Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM)
                );
                intent2.putExtra(
                        Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM2, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_MEETING_ROOM2)
                );
                intent2.putExtra(
                        Constants2.REMOTE_MSG_INVITATION_MEETING_NAME, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_MEETING_NAME)
                );
                intent2.putExtra(
                        Constants2.REMOTE_MSG_INVITATION_MEETING_IMAGE, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_MEETING_IMAGE)
                );
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);

            } else if (type2.equals(Constants2.REMOTE_MSG_INVITATION_RESPONSE)) {

                Intent intent = new Intent(Constants2.REMOTE_MSG_INVITATION_RESPONSE);
                intent.putExtra(Constants2.REMOTE_MSG_INVITATION_RESPONSE, remoteMessage.getData().get(Constants2.REMOTE_MSG_INVITATION_RESPONSE));
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

            }

       else if(type2!=null)   {
            if (type.equals(Constants.REMOTE_MSG_INVITATION) && typechat.equals("call") ) {


                Intent intent = new Intent(MessagingService.this, incomingcall.class);
                intent.putExtra(
                        Constants.REMOTE_MSG_INVITER_TOKEN, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITER_TOKEN)
                );
                intent.putExtra(
                        Constants.REMOTE_MSG_INVITATION_MEETING_ROOM, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_MEETING_ROOM)
                );
                intent.putExtra(
                        Constants.REMOTE_MSG_INVITATION_MEETING_ROOM2, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_MEETING_ROOM2)
                );
                intent.putExtra(
                        Constants.REMOTE_MSG_INVITATION_MEETING_NAME, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_MEETING_NAME)
                );
                intent.putExtra(
                        Constants.REMOTE_MSG_INVITATION_MEETING_IMAGE, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_MEETING_IMAGE)
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } else if (type.equals(Constants.REMOTE_MSG_INVITATION_RESPONSE)) {


                Intent intent = new Intent(Constants.REMOTE_MSG_INVITATION_RESPONSE);
                intent.putExtra(Constants.REMOTE_MSG_INVITATION_RESPONSE, remoteMessage.getData().get(Constants.REMOTE_MSG_INVITATION_RESPONSE));
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);


            }
        }

    }
}
