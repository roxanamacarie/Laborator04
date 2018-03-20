package com.example.student.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ContactsManagerActivity extends AppCompatActivity {

    public Button save, cancel, show;

    class MyListener implements View.OnClickListener {

        public void save() {

            String name = ((EditText)findViewById(R.id.nume)).getText().toString();
            String phone = ((EditText)findViewById(R.id.telefon)).getText().toString();
            String email = ((EditText)findViewById(R.id.email)).getText().toString();
            String company = ((EditText)findViewById(R.id.companie)).getText().toString();
            String website = ((EditText)findViewById(R.id.website)).getText().toString();



            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            if (name != null) {
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
            }
            if (phone != null) {
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
            }
            if (email != null) {
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
            }
            if (company != null) {
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
            }
            ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            if (website != null) {
                ContentValues websiteRow = new ContentValues();
                websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                contactData.add(websiteRow);
            }
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            startActivity(intent);

        }


        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.show :
                    LinearLayout ll = (LinearLayout) findViewById(R.id.showLayout);
                    if (ll.getVisibility() == View.VISIBLE)
                        ll.setVisibility(View.GONE);
                    else
                        ll.setVisibility(View.VISIBLE);

                    break;

                case R.id.save:
                    save();
                    break;

                case R.id.cancel:
                    finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        save = (Button)findViewById(R.id.save);
        cancel = (Button)findViewById(R.id.cancel);
        show = (Button)findViewById(R.id.show);

        MyListener ml = new MyListener();
        save.setOnClickListener(ml);
        cancel.setOnClickListener(ml);
        show.setOnClickListener(ml);

    }
}
