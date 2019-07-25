package com.example.claid;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.claid.R.drawable.edit_text_focuses_bg;

public class forget extends AppCompatActivity implements View.OnClickListener {
EditText editText_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget);
        editText_phone=findViewById(R.id.editText_phone);
        editText_phone.setOnClickListener((View.OnClickListener) this);
        editText_phone.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editText_phone.setBackgroundResource(edit_text_focuses_bg);
        if(editText_phone.getText().length()<=11){
            //Toast.makeText(forget.this, "max character length 11", Toast.LENGTH_SHORT).show();
        }
    }
});


    }



    @Override
    public void onClick(View v) {

       // editText_phone.setBackgroundResource(R.drawable.inp_cir_hover);

    }
}
