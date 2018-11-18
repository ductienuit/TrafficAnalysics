package com.trafficanalysics;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.trafficanalysics.apisusi.modal.SusiResult;
import com.trafficanalysics.apisusi.networkapi.ApiUtils;
import com.trafficanalysics.apisusi.networkapi.SusiAPI;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatbotFragment extends Fragment {
    @VisibleForTesting
    protected static final int RIGHT_BUBBLE_COLOR = R.color.colorPrimaryDark;
    @VisibleForTesting
    protected static final int LEFT_BUBBLE_COLOR = R.color.gray300;
    @VisibleForTesting
    protected static final int BACKGROUND_COLOR = R.color.blueGray400;
    @VisibleForTesting
    protected static final int SEND_BUTTON_COLOR = R.color.blueGray500;
    @VisibleForTesting
    protected static final int SEND_ICON = R.drawable.ic_action_send;
    @VisibleForTesting
    protected static final int OPTION_BUTTON_COLOR = R.color.teal500;
    @VisibleForTesting
    protected static final int RIGHT_MESSAGE_TEXT_COLOR = Color.WHITE;
    @VisibleForTesting
    protected static final int LEFT_MESSAGE_TEXT_COLOR = Color.BLACK;
    @VisibleForTesting
    protected static final int USERNAME_TEXT_COLOR = Color.WHITE;
    @VisibleForTesting
    protected static final int SEND_TIME_TEXT_COLOR = Color.WHITE;
    @VisibleForTesting
    protected static final int DATA_SEPARATOR_COLOR = Color.WHITE;
    @VisibleForTesting
    protected static final int MESSAGE_STATUS_TEXT_COLOR = Color.WHITE;
    @VisibleForTesting
    protected static final String INPUT_TEXT_HINT = "New message..";
    @VisibleForTesting
    protected static final int MESSAGE_MARGIN = 5;

    ChatView mChatView;

    SusiAPI mAPI;

    User me;
    User you;

    static ChatbotFragment mInstance;
    public ChatbotFragment() {
        // Required empty public constructor
    }

    public static ChatbotFragment getmInstance() {
        if(mInstance==null){
            mInstance = new ChatbotFragment();
        }
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chatbot, container, false);
        mChatView = view.findViewById(R.id.chat_view);
        mAPI = ApiUtils.getAPIService();

        init();

        final Message receivedMessage = new Message.Builder()
                .setUser(you)
                .setRight(false)
                .setText("Tôi là Tít tít đến từ AI Traffic Analysis. " +
                        "\nMuốn biết tình trạng giao thông hãy nhập: traffic <Tên đường>" +
                        "\nMuốn đóng góp thông tin hãy nhập: trafficjam <Tên đường>")
                .build();
                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);
        return view;
    }

    private void init() {
        //User id
        int myId = 0;
        //User icon
        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name
        String myName = "Duc Anh";

        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_1);
        String yourName = "Tít Tít";

        me = new User(myId, myName, myIcon);
        you = new User(yourId, yourName, yourIcon);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(getContext(),RIGHT_BUBBLE_COLOR));
        mChatView.setLeftBubbleColor(ContextCompat.getColor(getContext(), LEFT_BUBBLE_COLOR));
        mChatView.setBackgroundColor(ContextCompat.getColor(getContext(), BACKGROUND_COLOR));
        mChatView.setSendButtonColor(ContextCompat.getColor(getContext(), SEND_BUTTON_COLOR));
        mChatView.setSendIcon(SEND_ICON);
        //mChatView.setOptionIcon(R.drawable.ic_icon_account);
        //mChatView.setOptionButtonColor(OPTION_BUTTON_COLOR);
        mChatView.setRightMessageTextColor(RIGHT_MESSAGE_TEXT_COLOR);
        mChatView.setLeftMessageTextColor(LEFT_MESSAGE_TEXT_COLOR);
        mChatView.setUsernameTextColor(USERNAME_TEXT_COLOR);
        mChatView.setSendTimeTextColor(SEND_TIME_TEXT_COLOR);
        mChatView.setDateSeparatorColor(DATA_SEPARATOR_COLOR);
        mChatView.setMessageStatusTextColor(MESSAGE_STATUS_TEXT_COLOR);
        mChatView.setInputTextHint(INPUT_TEXT_HINT);
        mChatView.setMessageMarginTop(MESSAGE_MARGIN);
        mChatView.setMessageMarginBottom(MESSAGE_MARGIN);
        mChatView.setMaxInputLine(5);
        mChatView.setUsernameFontSize(getResources().getDimension(R.dimen.font_small));
        mChatView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        //mChatView.setInputTextColor(ContextCompat.getColor(getContext(), R.color.red500));
        mChatView.setInputTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //GET ANSWER
                String question = mChatView.getInputText();

                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRight(true)
                        .setText(question)
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mChatView.send(message);
                //Reset edit text
                mChatView.setInputText("");

                String low2question = question.toLowerCase();

                if(low2question.contains("traffic lê hồng phong")||low2question.contains("traffic võ văn ngân") ||
                        low2question.contains("traffic mai chí thọ")||low2question.contains("traffic trường chinh")||
                        low2question.contains("traffic công trường dân chủ")||low2question.contains("cảm ơn")||
                        low2question.contains("trafficjam hoàng diệu 2")||low2question.contains("trafficjam võ thị sáu")||
                        low2question.contains("traffic ngã tư thủ đức")||low2question.contains("traffic Vòng xoay Mỹ Thủy")||
                        low2question.contains("không chính xác"))
                {
                    String result = "";
                    switch (low2question){
                        case "traffic lê hồng phong":{
                            result ="Tình hình giao thông đường Lê Hồng Phong hiện tại khá thông thoáng";
                            break;
                        }
                        case "traffic võ văn ngân":{
                            result = "Tình hình giao thông đường Võ Văn Ngân hiện tại đang bị ùng tắc. Bạn có thể chuyển sang đi đường Đăng Văn Bi, hoặc đi xe buýt tuyến số 8";
                            break;
                        }
                        case "traffic mai chí thọ":{
                            result ="Đại lô Mai Chí Thọ đang bị tắc mạnh. Nếu muốn bạn có thể đi Xa lô Hà Nội";
                            break;
                        }
                        case "traffic trường chinh":{
                            result="Tình hình giao thông đường Trường Chinh đang bị tắt nghẽ. Bạn nên đi xe buýt tuyên số 27 hoặc 04";
                            break;
                        }
                        case "cảm ơn":{
                            result="Tiền chứ, cảm ơn gì đâu ^.^";
                            break;
                        }
                        case "traffic công trường dân chủ":{
                            result="Tình hình giao thông tại đây đang ùng tắc rất nặng.";
                            break;
                        }
                        case "traffic ngã tư thủ đức":{
                            result="Ngã tư Thủ Đức hiện giao thông vẫn bình thường.";
                            break;
                        }
                        case"traffic Vòng xoay Mỹ Thủy":{
                            result="Tình hình giao thông hiện tại lưu thông bình thường.";
                            break;
                        }
                        case "trafficjam võ thị sáu":{
                            result="Cảm ơn bạn đã đóng góp thông tin.";
                            break;
                        }
                        case"trafficjam hoàng diệu 2":{
                            result="Cảm ơn sự đóng góp của bạn!";
                            break;
                        }
                        case "không chính xác":{
                            result="Cảm ơn phản hồi của bạn, chúng rôi sẽ khắc phục sớm nhất";
                            break;
                        }
                    }
                    final Message receivedMessage = new Message.Builder()
                            .setUser(you)
                            .setRight(false)
                            .setText(result)
                            .build();

                    mChatView.receive(receivedMessage);
                }else{
                    getAnswerChatbot(question);
                }


//                //Receive message
//                final Message receivedMessage = new Message.Builder()
//                        .setUser(you)
//                        .setRight(false)
//                        .setText("hello")
//                        .build();
//
//                // This is a demo bot
//                // Return within 3 seconds
//                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mChatView.receive(receivedMessage);
//                    }
//                }, sendDelay);
            }
    });
    }

    private void getAnswerChatbot(String ask) {
        mAPI.getAnswerChatbot("-420", ask).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try{
                    JsonObject postResult = response.body();
                    if (postResult != null) {

                        JsonArray answers = postResult.getAsJsonArray("answers");
                        JsonElement  listAnswerNum1 =  answers.get(0);

                        JsonObject data2 = listAnswerNum1.getAsJsonObject();
                        JsonElement data3 = data2.get("actions");
                        JsonArray data =data3.getAsJsonArray();
                        JsonElement itemAnswer = data.get(0);
                        JsonObject inside = itemAnswer.getAsJsonObject();
                        JsonElement element = inside.get("expression");
                        String result = element.getAsString();

                        int yourId = 1;
                        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_1);
                        String yourName = "Tít Tít";

                        User you = new User(yourId, yourName, yourIcon);
                        //String answer = postResult.getAnswers()[0].getData()[0].getText();

                        //Receive message
                        final Message receivedMessage = new Message.Builder()
                                .setUser(you)
                                .setRight(false)
                                .setText(result)
                                .build();

                        mChatView.receive(receivedMessage);
                    } else {
                        if (postResult != null && postResult.toString() == "Cảm ơn") {
                            final Message receivedMessage = new Message.Builder()
                                    .setUser(you)
                                    .setRight(false)
                                    .setText("Tiền thôi, cảm ơn gì ^.^")
                                    .build();
                        } else {
                            if (postResult != null && postResult.toString() == "Ai không tốt nha") {
                                final Message receivedMessage = new Message.Builder()
                                        .setUser(you)
                                        .setRight(false)
                                        .setText("Cảm ơn bạn đã góp ý, chúng tôi sẽ khắc phục sớm nhất")
                                        .build();
                            } else {
                                //Receive message
                                final Message receivedMessage = new Message.Builder()
                                        .setUser(you)
                                        .setRight(false)
                                        .setText("Sai rồi bạn ơi!!! Kiểm tra lại nào^^")
                                        .build();

                                mChatView.receive(receivedMessage);
                            }
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    //Receive message
                    final Message receivedMessage = new Message.Builder()
                            .setUser(you)
                            .setRight(false)
                            .setText("Tôi không hiểu bạn nói gì, kiểm tra lại nào^^ ?")
                            .build();

                    mChatView.receive(receivedMessage);
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //Receive message
                final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRight(false)
                        .setText("Kiểm tra lại giúp tôi ? Tôi không hiểu")
                        .build();

                mChatView.receive(receivedMessage);
            }
        });
    }

}
