package com.example.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Rejestracja extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Rejestracja.this, "Należy wpisać wszystkie pola!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(Rejestracja.this, "Witaj w aplikacji zdrowego trybu życia!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Menu.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Rejestracja.this, "Błędna rejestracja", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Rejestracja.this, "Użytkownik już istnieje! Zaloguj się", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Rejestracja.this, "Hasła nie pasują", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}
