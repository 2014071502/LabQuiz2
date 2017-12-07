package com.ramirez.labquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramirez.labquiz.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView iv_user;
    EditText et_user;
    EditText et_pass;
    Button btn_remme;
    Button btn_login;
    FileOutputStream fos;
    FileInputStream fis;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_user = (ImageView) findViewById(R.id.ivUser);
        et_user = (EditText) findViewById(R.id.etUser);
        et_pass = (EditText) findViewById(R.id.etPass);
        btn_remme = (Button) findViewById(R.id.btnRemMe);
        btn_login = (Button) findViewById(R.id.btnLogin);
        textview = (TextView) findViewById(R.id.textView);

    }
    public void saveStorage (View view) {
        String message = et_user.getText().toString();
        String message2 = et_pass.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.write(message2.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }
    public void act (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        String user = et_user.getText().toString();
        String pass = et_pass.getText().toString();
        if(buffer.toString().equals(user +""+pass)) {
         Intent intent = new Intent(this, activity2.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
        }
    }
}
