package com.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTransactions extends Activity{
	SQLiteDatabase db1=null;
	int j=0;
	TextView noDet;
	String j1;
	Cursor c;
	
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main6);
		        Intent in=getIntent();
		        c=null;
		        long accId=in.getLongExtra("id",0);
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		       try{  c=db1.rawQuery("SELECT * FROM Transactions WHERE AccountNo="+accId+" ORDER BY Dat DESC;",null);}
		       catch(Exception e){Toast.makeText(getApplicationContext(), "No Records found",Toast.LENGTH_SHORT).show();
		       finish();}
		        if(c!=null)
		        	{if(c.moveToFirst())
		        	
		        do{
						
					final String str=c.getString(c.getColumnIndex("TransactionId"));
						String s=c.getString(c.getColumnIndex("Dat"));
						Button bt=new Button(ViewTransactions.this);
						ViewGroup tablelayout=(ViewGroup)findViewById(R.id.table1);
						bt.setText(str);
					
						TextView tv=new TextView(ViewTransactions.this);
						tv.setText(s);
						bt.setId(++j);
						
						tv.setTextColor(Color.parseColor("#00CCFF"));
					
						
						bt.setBackgroundColor(Color.parseColor("#CCCCCC"));	
						bt.setPadding(0, 3, 0, 0);
						
						TableRow row;      
				        row = new TableRow( this );
				        
				        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
						lp.setMargins(0, 10, 0, 0);
				        row.setLayoutParams(lp);
				        
				        tablelayout.addView( row );
				        row.addView(tv,140,40);
				        row.addView(bt,130,40);
				        
						registerForContextMenu(bt);
						bt.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
						    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
							nextscreen.putExtra("transid",str );
							Log.e("View Trans",".");
			                startActivity(nextscreen);		
							}
						});
						
					}while(c.moveToNext());
			 }
		        if(j==0)
		        { noDet=new TextView(ViewTransactions.this);
				ViewGroup linearlayout=(ViewGroup)findViewById(R.id.viewtrans);
				noDet.setText("No Transactions to view");
				noDet.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
				noDet.setTextColor(Color.WHITE);
				noDet.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
				linearlayout.addView(noDet);
			 }
}
			 
			 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
				 super.onCreateContextMenu(menu, v, menuInfo);
				 menu.setHeaderTitle("Action");  
				 menu.setHeaderIcon(R.drawable.action);
				 menu.add(0, v.getId(), 0, "View");
				 menu.add(0, v.getId(), 0, "Delete");  
				 j1=((Button) v).getText().toString();
				 }

				 public boolean onContextItemSelected(MenuItem item) {  
				
				 if(item.getTitle()=="View")
				 {
				 Intent newscr=new Intent(getApplicationContext(),TransDetails.class);
				 newscr.putExtra("transid",j1);
				 Log.e("View Trans",".");
				 startActivity(newscr);
				 }  
				 else if(item.getTitle()=="Delete")
				 { AlertDialog.Builder adb=new AlertDialog.Builder(this);
				 adb.setMessage("Are you sure you want to delete this transaction?");
				 adb.setTitle("Delete");
				 adb.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				 	public void onClick(DialogInterface dialog, int which) {
				 		// TODO Auto-generated method stub
				 		Cursor c=db1.rawQuery("SELECT * FROM Transactions WHERE TransactionId='"+j1+"';",null);
				 		c.moveToFirst();
				 		String m=c.getString(c.getColumnIndex("Type"));
				 		Long amt=c.getLong(c.getColumnIndex("Amount"));
				 		Long ac=c.getLong(c.getColumnIndex("AccountNo"));
				 		if(m.equals("Credit")){
				 			 db1.execSQL("UPDATE Account_Details SET ClearBalance=ClearBalance-"+amt+" WHERE AccountNo="+ac+";");
				 		}
				 		else if(m.equals("Debit")){
				 			 db1.execSQL("UPDATE Account_Details SET ClearBalance=ClearBalance+"+amt+" WHERE AccountNo="+ac+";");
				 		}
				 		
				 		
				 		db1.execSQL("DELETE FROM Transactions WHERE TransactionId='"+j1+"';");
				 		finish();
				 		Intent same=new Intent(getApplicationContext(),ViewTransactions.class);
				 		same.putExtra("id", ac);
				 		startActivity(same);
				 		Toast.makeText(getApplicationContext(), "Transaction Deleted", Toast.LENGTH_SHORT).show();
				 		
				 	}
				 });

				 adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
				 	public void onClick(DialogInterface dialog, int which) {
				 		// TODO Auto-generated method stub
				 		
				 		
				 	}
				 });
				 adb.setIcon(R.drawable.transdel);
				 adb.show();}
				 else {return false;}  
				 return true;  
				  }  			 
			 
			 
			 
}