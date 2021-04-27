package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            //수정
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int start=editText.getSelectionStart();
                int end=editText.getSelectionEnd();

                String str=editText.getText().toString();
                str.substring(start,end);

                switch(item.getItemId()){
                    case android.R.id.shareText:
                        //intent action, category 지정
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        if(editText.getText().toString().startsWith("http")){
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            //주소 지정
                            intent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
                        }
                        else{
                            intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
                            intent.putExtra("calculate",editText.getText().toString());
                        }
                        startActivity(intent);
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }
}
