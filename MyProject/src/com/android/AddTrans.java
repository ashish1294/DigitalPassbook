package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrans extends Activity{
    /** Called when the activity is first created. */
	SQLiteDatabase db1=null;
	EditText e;
	String s;
	int key,n=0;
	Long acc;
	Cursor c;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main15);
        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
        key=0;
       Button b=(Button)findViewById(R.id.sb);
       
        b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					key=0;
					e=(EditText)findViewById(R.id.edt);
					String s=e.getText().toString();
					
					try {c=db1.rawQuery("SELECT AccountNo from Account_Details;", null);
		        	if(c.moveToFirst());
					do{
				
						Long Ac=c.getLong(0);
						String c=Ac.toString();
			        	  	if(s.equals(c)){
			        	  		
			        	  	key=1;
			        	  	
			        	  	Intent newscreen=new Intent(getApplicationContext(),Update.class);
			        		newscreen.putExtra("id",Ac);
			        		Log.e("Add Trans",".");
			        		
			        		startActivity(newscreen);
			        		}
			        	
			        }while(c.moveToNext());
					}
					
					catch(Exception e){Toast.makeText(getApplicationContext(), "No Records found", Toast.LENGTH_SHORT).show();
					 n=1;}
				if((key==0)&&(n==0))
				{
					Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_SHORT).show();	
				}
				}});
        	
	}
}