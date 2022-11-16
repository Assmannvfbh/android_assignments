package com.example.androidassignments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MessageFragment extends Fragment {

    TextView message;
    TextView id;
    Button button;
    ChatWindowActivity chatWindowActivity;

    public MessageFragment() {}

    public MessageFragment(ChatWindowActivity chatWindowActivity) {
        this.chatWindowActivity = chatWindowActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        message = getView().findViewById(R.id.fragment_message);
        id = getView().findViewById(R.id.fragment_id);
        button = getView().findViewById(R.id.fragment_delete_button);
        button.setOnClickListener(new deleteListener());


        if(getArguments().getString("message") != null){
            message.setText(getArguments().getString("message"));
        }
        if(getArguments().getInt("id") != -1){
            String idText = String.valueOf(getArguments().getInt("id"));
            id.setText(idText);
        }
    }

    public class deleteListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id = getArguments().getInt("id");
            if(chatWindowActivity == null) {
                Intent data = new Intent();
                data.putExtra("id", id);
                getActivity().setResult(101, data);
                getActivity().finish();
            }
            else{
                chatWindowActivity.deleteMessage(id);
            }
        }
    }


}