package samplesharedpreferences.com.showcontacts;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ContactViewHolder> {


    private List<ContactCardDeta> contactCardDetaList;
    private Context mContext;

    public void setContactCardDetaList(List<ContactCardDeta> contactCardDetaList) {
        this.contactCardDetaList = contactCardDetaList;
    }

    public AllContactsAdapter(List<ContactCardDeta> contactCardDetaList, Context mContext) {
        this.contactCardDetaList = contactCardDetaList;
        this.mContext = mContext;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( mContext ).inflate( R.layout.single_contact_view, null );
        ContactViewHolder contactViewHolder = new ContactViewHolder( view );
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {
        final ContactCardDeta contactCardDeta = contactCardDetaList.get( position );
        holder.tvContactName.setText( contactCardDeta.getContactName() );
        holder.tvPhoneNumber.setText( contactCardDeta.getContactNumber() );

        holder.msg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phone = contactCardDeta.getContactNumber();
                String name = contactCardDeta.getContactName();


                //  Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + phone ) );
                // intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                //  mContext.startActivity( intent );
                Intent intent = new Intent( mContext, sendmsg.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.putExtra( "number", phone );
                intent.putExtra( "name", name );
                mContext.startActivity( intent );


            }
        } );


     /*  holder.msg.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               *//*PopupMenu popup = new PopupMenu(mContext, v);

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

*//*


           }

       } );
*/
        holder.call.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = contactCardDeta.getContactNumber();
                try {

//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Intent intent = new Intent( Intent.ACTION_CALL, Uri.fromParts( "tel", phone, null ) );
                    intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    if (ActivityCompat.checkSelfPermission( mContext, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mContext.startActivity( intent );


                } catch (Exception e) {
                    e.printStackTrace();
                }

               /* Intent intent=new Intent(Intent.ACTION_CALL );
                intent.setData(Uri.parse(number));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;

                }
                mContext.startActivity(intent);*/

//                int position=(Integer) v.getTag();
                Toast.makeText( mContext, "hello" + position, Toast.LENGTH_SHORT ).show();
            }
        } );

       /* holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = (Integer) v.getTag();
                Toast.makeText(v.getContext(), "Row " + position + " was clicked!", Toast.LENGTH_SHORT).show();

            }
        });
*/
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=contactCardDeta.getContactNumber();
              // String uri = number.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(number));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;

                }
               mContext.startActivity(intent);

            */ /*  String uri=number.trim();
               Intent intent=new Intent(Intent.ACTION_CALL);
               intent.setData(Uri.parse( uri ));
                if (ActivityCompat.checkSelfPermission(AllContactsAdapter.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;

                }
               startActivity(intent);*/
               /* Intent intent= new Intent(mContext, MainAcivity.class);
                intent.putExtra("number",number);
                mContext.startActivity(intent);
*/





              /* Intent intent=new Intent(Intent.ACTION_DIAL));
                intent.setData(Uri.parse(number));
                startActivity(intent);
                */

               /* Intent i = new Intent(AllContactsAdapter.this, Call.class);
                String num=number;
                i.putExtra("number", num);*/
/*
               Toast.makeText(view.getContext(), "Recycle Click" + position+number, Toast.LENGTH_SHORT).show();
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return contactCardDetaList.size();
    }




    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView ivContactImage;
        TextView tvContactName;
        TextView tvPhoneNumber;
        Button call;
        Button msg;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ivContactImage =  itemView.findViewById(R.id.ivContactImage);
            tvContactName =  itemView.findViewById(R.id.tvContactName);
            tvPhoneNumber =  itemView.findViewById(R.id.tvPhoneNumber);
           call = itemView.findViewById(R.id.call);
           msg=itemView.findViewById(R.id.msg);


        }
    }
}



