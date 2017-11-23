package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Option2Activity extends AppCompatActivity implements TextWatcher, View.OnClickListener{

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name2);
        etPwd = (EditText) findViewById(R.id.et_pwd2);
        etEmail = (EditText) findViewById(R.id.et_email2);
        etPhone = (EditText) findViewById(R.id.et_phone2);
    }

    private void initView(){
        // OnClickListener
        findViewById(R.id.btn_validate2).setOnClickListener(this);

        // TextChangedListener
        etName.addTextChangedListener(this);
        etPwd.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }

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

        validateEditText();
        validateEditPass();
        validateEditEmail();
        validateEditPhone();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_validate2) {
            if(validateEditText()&&validateEditPass()&&validateEditEmail()&&validateEditPhone()) {
                Toast.makeText(this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "ข้อมูลผิดไม่ตรงตามฟอร์มลองใหม่นะ^^", Toast.LENGTH_SHORT).show();
            }
        }
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
            etEmail.setError("ใส่อีเมลผิดแล้วจ้า");
            isValidatedEmail =false;
        }


        return isValidatedEmail;
    }
}
