package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Option3Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option3);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name3);
        etPwd = (EditText) findViewById(R.id.et_pwd3);
        etEmail = (EditText) findViewById(R.id.et_email3);
        etPhone = (EditText) findViewById(R.id.et_phone3);
    }

    private void initView(){
        // OnClickListener
        findViewById(R.id.btn_validate3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.btn_validate3) {
                    if(validateEditText()&&validateEditPass()&&validateEditEmail()&&validateEditPhone()) {
                        Toast.makeText(Option3Activity.this ,"OK ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Option3Activity.this , "ใส่ข้อมูลผิด จงแก้ไข", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // TextChangedListener
        etName.addTextChangedListener(new TextValidator(etName) {
            @Override
            public void validate(TextView textView, String text) {
              validateEditText();
            }
        });

        etPwd.addTextChangedListener(new TextValidator(etPwd) {
            @Override
            public void validate(TextView textView, String text) {
               validateEditPass();

            }
        });

       etEmail.addTextChangedListener(new TextValidator(etEmail) {
           @Override
           public void validate(TextView textView, String text) {
               validateEditEmail();
           }
       });
       etPhone.addTextChangedListener(new TextValidator(etPhone) {
           @Override
           public void validate(TextView textView, String text) {
              validateEditPhone();
           }
       });
    }


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
        if (etPwd.getText().toString().length() ==0) {
            etPwd.setError("โปรดระบุรหัสผ่าน");
            isValidatedPass = false;
        }else if(etPwd.getText().toString().length() <=3){
            etPwd.setError("รหัสมีความยาวสั้นเกินไป");
            isValidatedPass = false;
        }
        else if(!etPwd.getText().toString().matches("[0-9]+")){
            etPwd.setError("รหัสผ่านต้องเป็นตัวเลขเท่านั้น");
            isValidatedPass = false;

        }else if(etPwd.getText().toString().length() >= 20){
            etPwd.setError("มีความยาวมากเกินไป");
            isValidatedPass = false;

        }

        return isValidatedPass;
    }


    private boolean validateEditPhone(){
        boolean isValidatedPhone = true;
        if (etPhone.getText().toString().length() == 0 ||etPhone.getText().toString().length() <=9 ||etPhone.getText().toString().length() > 10) {
            etPhone.setError("คุณใส่เบอร์โทรศัพท์ไม่ถูกต้อง");
            isValidatedPhone = false;
        }else if(!etPhone.getText().toString().trim().matches("^[0]+[0-9]+")){
            etPhone.setError("ต้องขึ้นต้นด้วย0");
            isValidatedPhone = false;
        }
        return isValidatedPhone;
    }

    private boolean validateEditEmail(){
        boolean isValidatedEmail = true;
        String regexpEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!etEmail.getText().toString().trim().matches(regexpEmail)){
            etEmail.setError("ใส่อีเมลผิดค่ะ");
            isValidatedEmail =false;
        }


        return isValidatedEmail;
    }
}
