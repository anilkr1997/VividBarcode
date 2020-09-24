package com.dynamsoft.demo.dynamsoftbarcodereaderdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Aboutus extends AppCompatActivity {
    Button rateus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About Us");
        TextView rateus =  findViewById(R.id.rateus);
        TextView mail = (TextView) findViewById(R.id.mail);
        TextView website = (TextView) findViewById(R.id.website);


        website.setText("https://vividtechno.com");
        mail.setText("sales.vividtechno@gmail.com");
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://vividtechno.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.vividtechno.barcode.qrcode.reader");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


         mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"sales.vividtechno@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "User Enquiry From App: "+getResources().getString(R.string.app_name));
                i.putExtra(Intent.EXTRA_TEXT, "Dear Vivid Technical Support, I downloaded your App "+getResources().getString(R.string.app_name)+" and I have following query:");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                }

                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(Aboutus.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }

        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
