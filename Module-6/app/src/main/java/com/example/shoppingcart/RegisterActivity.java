package com.example.shoppingcart;

import android.app.ProgressDialog;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.HashMap;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccount;
    private EditText Inputname, Inputphonenumber, Inputpassword;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createAccount = (Button) findViewById(R.id.register_btn);
        Inputname = (EditText) findViewById(R.id.register_name_input);
        Inputphonenumber = (EditText) findViewById(R.id.register_number_input);
        Inputpassword = (EditText) findViewById(R.id.register_password_input);
        loading = new ProgressDialog(this);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = Inputname.getText().toString();
        String phone = Inputphonenumber.getText().toString();
        String password = Inputpassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter the number", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("create account");
            loading.setMessage("Please wait, while we are checking");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            validate(name, phone, password);
        }
    }

    private void validate(final String name, final String phone, final String password) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("name", name);
                    userdataMap.put("password", password);

                    rootref.child("Users").child(phone).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Congrats", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
                else {
                    Toast.makeText(RegisterActivity.this, "This "+ phone + " already exists.", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    Toast.makeText(RegisterActivity.this, "This "+ phone + " Please try another phone number", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
