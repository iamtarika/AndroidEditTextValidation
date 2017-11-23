package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Option1Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name1);
        etPwd = (EditText) findViewById(R.id.et_pwd1);
        etEmail = (EditText) findViewById(R.id.et_email1);
        etPhone = (EditText) findViewById(R.id.et_phone1);
    }

    private void initView(){
        findViewById(R.id.btn_validate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEditText()&&validateEditPass()&&validateEditEmail()&&validateEditPhone()) {
                     Toast.makeText(Option1Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                    // SnackBar?

                }else {
                    Toast.makeText(Option1Activity.this, "บ่ผ่านเด้อ กรอกข้อมูลใหม่เด้อคร๊า", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This can be ignored
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This can be ignored
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditText(); // OR validation can be specific (only for this EditText)

            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditPass();

            }
        });

       etPhone.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               validateEditPhone();
           }
       });
       etEmail.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               validateEditEmail();
           }
       });

    }

    // To validate all EditTexts
    private boolean validateEditText(){
        boolean isValidatedName = true;
        String regexpName = "^[A-Z]+[\\D]+";
        if (etName.getText().toString().length() == 0) {
            etName.setError("โปรดระบุชื่อของคุณ");
            isValidatedName = false;
        }else if(!etName.getText().toString().trim().matches(regexpName)){
            etName.setError("ตัวอักษรแรกต้องเป็นตัวใหญ่");
            isValidatedName = false;
        }

        return isValidatedName;
    }


    private boolean validateEditPass(){
        boolean isValidatedPass = true;
        if (etPwd.getText().toString().length() == 0) {
            etPwd.setError("โปรดระบุรหัสผ่าน");
            isValidatedPass = false;
        }
        else if(etPwd.getText().toString().matches("[a-zA-Z ]+")){
            etPwd.setError("รหัสผ่านต้องเป็นตัวเลขเท่านั้น");
            isValidatedPass = false;

        }

            return isValidatedPass;
    }


    private boolean validateEditPhone(){
        boolean isValidatedPhone = true;
        if (etPhone.getText().toString().length() == 0 ||etPhone.getText().toString().length() <=9 ||etPhone.getText().toString().length() > 10) {
            etPhone.setError("คุณใส่เบอร์โทรศัพท์ไม่ถูกต้อง");
            isValidatedPhone = false;
        }
        return isValidatedPhone;
    }

    private boolean validateEditEmail(){
        boolean isValidatedEmail = true;
        String regexpEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!etEmail.getText().toString().trim().matches(regexpEmail)){
            etEmail.setError("ใส่อีเมลผิดแล้วจ้า");
            isValidatedEmail =false;
        }


        return isValidatedEmail;
    }


}
