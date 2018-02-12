package samplesharedpreferences.com.showcontacts;

/**
 * Created by neeraj on 12/2/18.
 */

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAcivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            getAllContacts();
        } else {
            requestLocationPermission();
        }



    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!

        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    123);
        }

    }

    private void getAllContacts() {


        List<ContactVO> contactVOList=new ArrayList();
        ContactVO contactVO;



        ContentResolver contentResolver=getContentResolver();
        Cursor cursor=contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        if(cursor.getCount()>0){
            while (cursor.moveToNext()){

                int hasPhoneNumber=Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if(hasPhoneNumber>0){
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contactVO=new ContactVO();
                    contactVO.setContactName(name);
                    Cursor phoneCursor=contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                            new String[]{id},
                            null
                    );
                    if (phoneCursor.moveToNext()){
                        String phoneNumber=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactVO.setContactNumber(phoneNumber);
                    }
                    phoneCursor.close();
                    Cursor emailCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID+"=?",
                            new String[]{id},
                            null
                    );

                    while (emailCursor.moveToNext()){
                        String emailId=emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

                    }
                    contactVOList.add(contactVO);


                }

            }

            AllContactsAdapter contactsAdapter=new AllContactsAdapter(contactVOList,getApplicationContext());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(contactsAdapter);
        }
    }

}