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
import android.view.Gravity;
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

public class Trans extends Activity{
	SQLiteDatabase db1=null;
	TextView noDet;
	String s,j1;
	Long i;
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main7);
		        
		        try{
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		        Cursor c=db1.rawQuery("SELECT * FROM Transactions ORDER BY Dat DESC limit 5;",null);
		        
		        if(!(c==null))
		        {if(c.moveToFirst())
		        	
		        do{
						i=c.getLong(c.getColumnIndex("AccountNo"));
						String temp=i.toString();
					  final String  str=c.getString(c.getColumnIndex("TransactionId"));
						s=c.getString(c.getColumnIndex("Dat"));
						Button bt=new Button(Trans.this);
						ViewGroup tablelayout=(ViewGroup)findViewById(R.id.table2);
						bt.setText(str);
						int p=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
						int t=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
						bt.setTextSize(p);
						bt.setGravity(Gravity.CENTER);
						bt.setBackgroundColor(Color.parseColor("#CCCCCC"));
				
						TextView tv=new TextView(Trans.this);
						tv.setText(s);
						
						TextView tv1=new TextView(Trans.this);
						tv1.setText(temp);
						tv.setTextColor(Color.WHITE);
						tv.setTextSize(t);
						tv1.setGravity(Gravity.CENTER);
						tv1.setTextColor(Color.WHITE);
						tv1.setTextSize(t);
						TableRow row;
						TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
						
						lp.setMargins(0, 10, 0, 0);
				        row = new TableRow( this );
				        row.setLayoutParams(lp);
				        tablelayout.addView( row );
				        int px=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,120,getResources().getDisplayMetrics());
				        int px1=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,getResources().getDisplayMetrics());
				        int px2=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,68,getResources().getDisplayMetrics());
				        
				        
				        row.addView(tv,px2,30);
				        
				        row.addView(tv1,px,px1);
				        row.addView(bt,px,px1);
						registerForContextMenu(bt);
						bt.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
						    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
							nextscreen.putExtra("transid",str );
							Log.e("Rec Trans",".");
			                startActivity(nextscreen);		
							}
						});
						
					}while(c.moveToNext());
			 }
		     else
		     {  noDet=new TextView(Trans.this);
				ViewGroup linearlayout=(ViewGroup)findViewById(R.id.recent);
				noDet.setText("No Transactions to view");
				noDet.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				noDet.setTextColor(Color.WHITE);
				noDet.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
				linearlayout.addView(noDet);
			  }
		        }
		        catch (Exception e){
		        	noDet=new TextView(Trans.this);
					ViewGroup linearlayout=(ViewGroup)findViewById(R.id.recent);
					noDet.setText("No Transactions to view");
					noDet.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					noDet.setTextColor(Color.WHITE);
					noDet.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
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
				 Log.e("Trans",".");
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
				 		Intent same=new Intent(getApplicationContext(),Trans.class);
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