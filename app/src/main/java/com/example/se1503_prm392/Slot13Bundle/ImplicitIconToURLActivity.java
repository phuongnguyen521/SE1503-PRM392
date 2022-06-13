package com.example.se1503_prm392.Slot13Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.se1503_prm392.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ImplicitIconToURLActivity extends AppCompatActivity {

    private ImageView ivVnExpress;
    private ImageView ivMessage;
    private ImageView ivPhone;
    private EditText etPhone;
    private EditText etContent;
    private EditText etPhoneTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_icon_to_urlactivity);

        ivVnExpress = findViewById(R.id.ivVnExpress);
        ivMessage = findViewById(R.id.ivMessage);
        ivPhone = findViewById(R.id.ivPhone);

        setData();

        ivVnExpress.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://vnexpress.net/"));
            startActivity(intent);
        });

        ivMessage.setOnClickListener(view -> {
            if (isNotType(true)){
                Toast.makeText(ImplicitIconToURLActivity.this,
                        "Please type both Send to and Content",
                        Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", etContent.getText().toString());
                intent.setData(Uri.parse("smsto:" + etPhone.getText().toString()));
                startActivity(intent);
            }
        });

        ivPhone.setOnClickListener(view -> {
            if (isNotType(false))
            {
                Toast.makeText(ImplicitIconToURLActivity.this,
                        "Please type Phone To",
                        Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + etPhoneTo.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private void setData()
    {
        ivVnExpress = findViewById(R.id.ivVnExpress);
        etPhone = findViewById(R.id.etPhone);
        etContent = findViewById(R.id.etContent);
        etPhoneTo = findViewById(R.id.etPhoneTo);
    }

    private boolean isNotType(boolean isSMS){
        if (isSMS){
            Log.d("AAA",etPhone.getText().toString() + etContent.getText().toString());
            boolean result = TextUtils.isEmpty(etPhone.getText().toString()) ||
                    TextUtils.isEmpty(etContent.getText().toString());
            return result;
        }
        return TextUtils.isEmpty(etPhoneTo.getText().toString());
    }
}