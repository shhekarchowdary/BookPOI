package com.arr.bookpoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText mUserName,mPassword;
    Button mLogin;

    HashMap<String,String> mUserData = new HashMap<>();
    public static String userLogged = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.etxtUserNAme);
        mPassword = findViewById(R.id.etxtPassword);
        mLogin = findViewById(R.id.btnLogin);

        fillData();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mUserName.getText().toString().trim().isEmpty()){
                    if(!mPassword.getText().toString().trim().isEmpty()){
                        String userName = mUserName.getText().toString().trim();
                        String password = mPassword.getText().toString().trim();
                        for(String usrName:mUserData.keySet()){
                            if(userName.equals(usrName) && password.equals(mUserData.get(usrName))) {
                                userLogged = userName;
                            }
                        }
                        if(userLogged != null){
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getBaseContext(),MainActivity2.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Enter User Name",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void fillData(){
        mUserData.put("User1","Password1");
        mUserData.put("User2","Password2");
        mUserData.put("User3","Password3");
    }


}