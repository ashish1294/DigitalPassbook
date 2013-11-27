package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AccDetails extends Activity{
	SQLiteDatabase db1=null;
	 
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main_acc);
		        Intent i =getIntent();
		        
		        //Recieving passed parameters
		        long accId=i.getLongExtra("id", 0);
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		        Cursor c=db1.rawQuery("SELECT * FROM Account_Details WHERE AccountNo="+accId+";",null);
		        c.moveToFirst();
		        
		        String Bankname=c.getString(c.getColumnIndex("BankName"));
		        TextView Bn=(TextView)findViewById(R.id.BNAME);
		        Bn.setText(Bankname);
		        
		        String Brname=c.getString(c.getColumnIndex("BranchName"));
		        TextView Brn=(TextView)findViewById(R.id.BRNAME);
		        Brn.setText(Brname);
		        
		        String Bradd=c.getString(c.getColumnIndex("BranchAddress"));
		        TextView Braddr=(TextView)findViewById(R.id.BRADDR);
		        Braddr.setText(Bradd);
		        
		        String Type=c.getString(c.getColumnIndex("Type"));
		        TextView ty=(TextView)findViewById(R.id.TYPE);
		        ty.setText(Type);
		        
		        String Ifsc=c.getString(c.getColumnIndex("IFSCCode"));
		        TextView ifs=(TextView)findViewById(R.id.IFSCC);
		        ifs.setText(Ifsc);
		        
		        String Doo=c.getString(c.getColumnIndex("DateOfOpening"));
		        TextView DOO=(TextView)findViewById(R.id.DOO);
		        DOO.setText(Doo);
		        
		        Long bnph=c.getLong(c.getColumnIndex("AccountNo"));
		        String acc=bnph.toString();
		       TextView Id=(TextView)findViewById(R.id.acc_no);
		       Id.setText(acc);
		       
		       Integer b=c.getInt(c.getColumnIndex("BranchFaxNo"));
		       String bnfax=b.toString();
		        TextView Bnfax=(TextView)findViewById(R.id.FAX);
		        Bnfax.setText(bnfax);
		        
		        Long ba=c.getLong(c.getColumnIndex("ClearBalance"));
		        String bal=ba.toString();
		        TextView Bal=(TextView)findViewById(R.id.BAL);
		        Bal.setText(bal);
		        
		        Long p=c.getLong(c.getColumnIndex("BranchPhno"));
		        String phn=p.toString();
		        TextView ph=(TextView)findViewById(R.id.PHNO);
		        ph.setText(phn);
		         
		        
		        Button bck=(Button)findViewById(R.id.back);
		        bck.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}});
		        
			 
			 }
			

}
