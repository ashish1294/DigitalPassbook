package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransDetails extends Activity{
	SQLiteDatabase db1=null;
	Button bt;
	 
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main5);
		        
		        Intent i =getIntent();
		        //Recieving passed parameters
		        String transId=i.getStringExtra("transid");
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		        Cursor c=db1.rawQuery("SELECT * FROM Transactions WHERE TransactionId='"+transId+"';",null);
		        c.moveToFirst();
		        bt=(Button)findViewById(R.id.back);
		        TextView Bn=(TextView)findViewById(R.id.TRANSID);
		        Bn.setText(transId);
		        
		        String dt=c.getString(c.getColumnIndex("Dat"));
		        TextView Brn=(TextView)findViewById(R.id.TRANSDATE);
		        Brn.setText(dt);
		        
		        String tym=c.getString(c.getColumnIndex("Tym"));
		        TextView Braddr=(TextView)findViewById(R.id.TRANSTIME);
		        Braddr.setText(tym);
		        
		        String trnsType=c.getString(c.getColumnIndex("Type"));
		        TextView ty=(TextView)findViewById(R.id.TRANSTYPE);
		        ty.setText(trnsType);
		        
		        String mod=c.getString(c.getColumnIndex("Mode"));
		        TextView ifs=(TextView)findViewById(R.id.MODE);
		        ifs.setText(mod);
		        		                
		        Long bnph=c.getLong(c.getColumnIndex("AccountNo"));
		        String acc=bnph.toString();
		        TextView Id=(TextView)findViewById(R.id.ACC);
		        Id.setText(acc);
		       
		        Long b=c.getLong(c.getColumnIndex("Amount"));
		        String bnfax=b.toString();
		        TextView Bnfax=(TextView)findViewById(R.id.AMNT);
		        Bnfax.setText(bnfax);
		        
		        Integer ba=c.getInt(c.getColumnIndex("RefNo"));
		        String bal=ba.toString();
		        TextView Bal=(TextView)findViewById(R.id.REF);
		        Bal.setText(bal);
		        bt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					finish();	
					}
				});     
		            }

}
