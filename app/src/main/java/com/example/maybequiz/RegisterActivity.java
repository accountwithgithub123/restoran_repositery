package com.example.maybequiz;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etuname,etemail,etpass1,etpass2;
    Button btnRegister, ivbtng, ivbtnf;
    RadioGroup rdgroup;
    RadioButton rdbtn;
    Spinner sp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializer();
        btnRegister.setOnClickListener(view -> {
            validateFunction();
        });

    }

    private void validateFunction() {
        String uname = "",email="",pass1="",pass2="";
        uname = etuname.getText().toString().trim();
        email = etemail.getText().toString().trim();
        pass1 = etpass1.getText().toString().trim();
        pass2 = etpass2.getText().toString().trim();
        rdbtn = findViewById(rdgroup.getCheckedRadioButtonId());
        if (uname.isEmpty()){
            etuname.setError("Fill this out!");
            etuname.requestFocus();
        }
        else if  (email.isEmpty()){
            etemail.setError("Fill this out!");
            etemail.requestFocus();
        }
        else if  (pass1.isEmpty()){
            etpass1.setError("Fill this out!");
            etpass1.requestFocus();
        }
        else if  (pass1.length()<4){
            etpass1.setError("Atleast 4 character password");
            etpass1.requestFocus();
        }
        else if  (pass2.isEmpty()){
            etpass2.setError("Please confirm password!");
            etpass2.requestFocus();
        }
        else if  (!pass2.equals(pass1)){
            etpass2.setError("Password doesn't match!");
            etpass2.requestFocus();
        }
        else if  (rdbtn==null){
            Toast.makeText(this, "Select gender!", Toast.LENGTH_SHORT).show();
            rdgroup.requestFocus();
        }
        else{
            Log.d(TAG, "validateFunction: " + uname + "\nEmail" + email +
                    "\nPassword : " + pass2 + "\nGender" + rdbtn.getText().toString() + "\nCountry :" + sp.getSelectedItem().toString());
            Toast.makeText(this,  "validateFunction: " + uname + "\nEmail" + email +
                    "\nPassword : " + pass2 + "\nGender" + rdbtn.getText().toString() + "\nCountry :" + sp.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.prog_dialog);
            dialog.show();
            register_modal modal = new register_modal(uname,email,pass1,rdbtn.getText().toString(),sp.getSelectedItem().toString());
            String finalEmail = email;
            String finalPass = pass1;
            FirebaseDatabase.getInstance().getReference("Users")
                    .push().setValue(modal).addOnSuccessListener(unused -> {
                        auth.createUserWithEmailAndPassword(finalEmail, finalPass)
                                .addOnSuccessListener(authResult -> {
                                    Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                })
                                .addOnFailureListener(e -> {
                                    Log.e(TAG, "FireBaseException: " + e.getMessage() );
                                    dialog.dismiss();
                                });
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "FireBaseException: " + e.getMessage() );
                        dialog.dismiss();
                    });
        }

    }

    private void initializer() {
        etuname = findViewById(R.id.etusrName);
        etemail = findViewById(R.id.etemail);
        etpass1 = findViewById(R.id.etpass);
        etpass2 = findViewById(R.id.etconfirmpass);
        btnRegister = findViewById(R.id.regbtn);
        ivbtnf = findViewById(R.id.btnface);
        ivbtng = findViewById(R.id.btngoogle);
        rdgroup = findViewById(R.id.rdgroup);
        sp = findViewById(R.id.cntry);
        auth = FirebaseAuth.getInstance();
    }
}