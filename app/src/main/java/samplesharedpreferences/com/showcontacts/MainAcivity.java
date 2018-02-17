package samplesharedpreferences.com.showcontacts;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainAcivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private final int SEND_REQUEST_CONCATS = 123;
    // ListView list;
    EditText editsearch;
    Button pop;
    //    SearchView sr;
    AllContactsAdapter contactsAdapter;

    List<ContactCardDeta> contactCardDetaList = new ArrayList();
    List<ContactCardDeta> filterdatalist = null;
    ContactCardDeta contactCardDeta;
    Context context;
    //Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        recyclerView = findViewById( R.id.rv );
      /*  pop=findViewById( R.id.pop);
        pop.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(MainAcivity.this, v);

                // popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
                popup.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        return true;
                    }
                } );


                popup.show();//showing popup menu
            }



        } );*/

       /* button = findViewById( R.id.callb );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String num = "9770828387";
                String phone = "9770828387";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);


            }
        } );*/



        //  recyclerView.setItemAnimator();

         /* recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    public AdapterView parent;

                    @Override
                    public void onItemClick(View view, int position) {

                       // int i=(int) view.getTag();
                       // String pos=Integer.toString(i);


                        Toast.makeText(MainAcivity.this, "hello", Toast.LENGTH_SHORT).show();
*//*
                        List< ContactCardDeta> f=new ArrayList<>();
                   void     String number=view.toString();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);

                        callIntent.setData(Uri.parse(number));*//*


                       addpopupmenu();



                        if (ActivityCompat.checkSelfPermission(MainAcivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;

                        }
                        //startActivity(callIntent);






                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        Toast.makeText(MainAcivity.this, "Hello Click me", Toast.LENGTH_SHORT).show();

                        // do whatever
                    }
                })
        );

*/


        editsearch=findViewById(R.id.edsearch);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<ContactCardDeta> filist=new ArrayList<>();
                final String name = editsearch.getText().toString();
                for(int i=0;i<contactCardDetaList.size();i++) {
                    ContactCardDeta cardDeta = contactCardDetaList.get(i);
                    String name1 = cardDeta.getContactName();
                    if(name1.toLowerCase().contains(name)){
                        filist.add(cardDeta);
                    }else if(name1.startsWith(name)){
                        filist.add(cardDeta);

                    }
                    contactsAdapter.setContactCardDetaList(filist);

                    recyclerView.setAdapter(contactsAdapter);
                    contactsAdapter.notifyDataSetChanged();



                }



            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

 if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            getAllContacts();
//            getFilter();
        } else {
            requestLocationPermission();
        }


    }

 /*   private void addpopupmenu() {
      //  String number=null;
        for (int i = 0; i < contactCardDetaList.size(); i++) {
            ContactCardDeta cardDeta = contactCardDetaList.get(i);
          String number = cardDeta.getContactNumber();


            Toast.makeText(this, "hello "+number, Toast.LENGTH_SHORT).show();

        }

    }*/



    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.READ_CONTACTS)) {


        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    SEND_REQUEST_CONCATS);
        }

    }

    private void getAllContacts() {


        ContentResolver contentResolver;

        contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contactCardDeta = new ContactCardDeta();
                    contactCardDeta.setContactName(name);
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            new String[]{id},
                            null
                    );
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactCardDeta.setContactNumber(phoneNumber);
                    }
                    phoneCursor.close();
                    Cursor emailCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?",
                            new String[]{id},
                            null
                    );

                    while (emailCursor.moveToNext()) {
                        String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

                    }
                    contactCardDetaList.add(contactCardDeta);


                }

            }

            contactsAdapter = new AllContactsAdapter(contactCardDetaList, getApplicationContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(contactsAdapter);
//            recyclerView.notifyAll();
            //getFilter();




        }
    }

}



        /*   recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   if (position == 0) {
                       Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
                       startActivityForResult(myIntent, 0);
                   }

               }
           });*/

          /*  recyclerView.setAdapter(new ContentAdaptr(items, new ContentAdapter.OnItemClickListener() {
                @Override public void onItemClick(ContentItem item) {
                    Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
                }
            }));*/

        /*  recyclerView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int pos = recyclerView.indexOfChild(v);
                  String s =Integer.toString(pos);
                  //Toast.makeText(getContext(),"Click",Toast.LENGTH_LONG).show();
                  Log.d(s, "onClick: 1");
              }
          });
*/

        /*    recyclerView.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.indexOfChild(v);
                String s =Integer.toString(pos);
                Toast.makeText(MainAcivity.this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                Log.d(s, "onClick: 1");

            }
        });*/









/*
    @Override
    public Filter getFilter() {
        return new Filter() {
            List<ContactCardDeta> filterdata = new ArrayList<>();
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults results= new FilterResults();

                if(constraint!=null && constraint.length()>0) {
                    constraint = constraint.toString().toUpperCase();


                    for (int i = 0; i < filterdatalist.size(); i++) {
                        if (filterdata.get(i).getContactName().toUpperCase().contains(constraint)) {
                           // filterdata.add(filterdatalist.get(i).getContactName());
                          *//*  BabyDetailsData babydata = new BabyDetailsData(mStringFilterList.get(i)
                                    .getBabyname(), mStringFilterList.get(i)
                                    .getBabypicture());*//*

                          ContactCardDeta condeta=new ContactCardDeta(filterdatalist.get(i)
                          .getContactName(),filterdatalist.get(i).getContactNumber());

                          filterdata.add(condeta);


                        }

                    }
                    results.count = filterdata.size();

                    results.values = filterdata;
                }
                    else{
                        results.count=filterdatalist.size();
                        results.values=filterdatalist;
                    }

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    filterdatalist = (ArrayList<ContactCardDeta>) results.values;
                    contactsAdapter = new AllContactsAdapter(contactCardDetaList, (Context) filterdatalist);
                   // recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(contactsAdapter);
              //  Log.d(constraint, "publishResults: ");
                contactsAdapter.notifyDataSetChanged();
              //  Log.d("hello","hi");

            }


        };
    }*/


/*




    Filter.FilterResults results=new Filter.FilterResults();




        }else
        {
        results.count=filterList.size();
        results.values=filterList;

        }


        return results;
        }

@Override
protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.players= (ArrayList<Player>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
        }
        }
/*  button=findViewById(R.id.call);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               *//* String number=edittext1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
*//*
                Toast.makeText(MainAcivity.this, "Hello", Toast.LENGTH_SHORT).show();

            }
        });*/

 /*       recyclerView.setOnClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String cities = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainAcivity.this, cities, Toast.LENGTH_LONG).show();
                    }
                }
        );*/





 /* private void searchContacts(String name) {
        List<ContactCardDeta> flist=new ArrayList<>();
        for(int i=0;i<contactCardDetaList.size();i++){
            ContactCardDeta cardDeta = contactCardDetaList.get(i);
          String name1= cardDeta.getContactName();
            if(name1.toLowerCase().startsWith(name)){
                flist.add(cardDeta);
            }else if(name1.startsWith(name)){
                flist.add(cardDeta);

            }


        }
        contactsAdapter.setContactCardDetaList(flist);

        recyclerView.setAdapter(contactsAdapter);
        contactsAdapter.notifyDataSetChanged();



    }*/

// button=findViewById(R.id.search);
       /* button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name=editsearch.getText().toString();
                searchContacts(name);
            }
        });*/






