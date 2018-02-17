package samplesharedpreferences.com.showcontacts;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by neeraj on 16/2/18.
 */

public class sendmsg extends AppCompatActivity{
    EditText editText;
    TextView textView;
    Button button,button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.send_msg );

        editText=findViewById(R.id.edittext);
        textView=findViewById( R.id.text);
        button=findViewById(R.id.submit);
        //button1=findViewById(R.id.cancel);

        Intent intent= getIntent();
        final String number=intent.getStringExtra("number");
        final String name=intent.getStringExtra("name");
        textView.setText(name+"\n"+number);

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
               /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
                intent.putExtra("sms_body", msg);
                startActivity(intent);*/

                //TextView textView=msg.toString();
                ActivityCompat.requestPermissions(sendmsg.this,new String[]{Manifest.permission.SEND_SMS},1);


            try {

                Intent intent = new Intent( sendmsg.this, sendmsg.class );
                PendingIntent pi = PendingIntent.getActivity( sendmsg.this, 0, intent, 0 );

//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage( "9770828387", null, "hello javatpoint", pi, null );
            }catch (Exception e){
                e.printStackTrace();
            }


                   /* SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("7987001717", null, "hello", null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
*/




              /*  Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + number ) );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                  startActivity( intent );
*/

              /*  Intent intent=new Intent(getApplicationContext(),sendmsg.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(number, null, msg, null,null);

                Toast.makeText(getApplicationContext(), "Message Sent !"+name+number+msg,
                        Toast.LENGTH_LONG).show();*/


                // sendSMS(number, msg);

              /*  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + number));
                intent.putExtra("sms_body", msg);
                startActivity(intent);

*/

                //Toast.makeText( sendmsg.this, "hello"+number, Toast.LENGTH_SHORT ).show();
            }
        } );



    }

   /* private void sendSMS(String number, String msg) {



    }*/


}
