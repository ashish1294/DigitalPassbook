package com.android;

import android.app.Activity;
import android.widget.TableLayout.LayoutParams;
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
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ListAccount extends Activity{
	SQLiteDatabase db1=null;
	Long k;
	int j;
	String j1;
	 TextView noDet;
	 
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main3);
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		        db1.execSQL("CREATE TABLE IF NOT EXISTS Account_Details (AccountNo INTEGER PRIMARY KEY, DateOfOpening DATE NOT NULL," +
						" Type TEXT CHECK((Type='CURRENT')OR(Type='SAVINGS')), BankName TEXT NOT NULL, BranchName TEXT NOT NULL, " +
						"BranchAddress TEXT NOT NULL, BranchPhno INTEGER, BranchFaxNo INTEGER, IFSCCode TEXT NOT NULL, ClearBalance INTEGER, " +
						"UnclearBalance INTEGER);");
		        Cursor c=db1.rawQuery("SELECT * FROM Account_Details ORDER BY DateOfOpening DESC;",null);
		        if(c!=null)
        			{if(c.moveToFirst())
	        	
	        do{
					
					String d4=c.getString(c.getColumnIndex("BankName"));
				final Long d1=c.getLong((c.getColumnIndex("AccountNo")));
				
					String temp=d1.toString();
					Button bt=new Button(ListAccount.this);
					ViewGroup tablelayout=(ViewGroup)findViewById(R.id.table3);
					bt.setText(temp);
										
					TextView tv=new TextView(ListAccount.this);
					tv.setText(d4);
					
					tv.setTextColor(Color.parseColor("#00CCFF"));
					tv.setGravity(Gravity.CENTER);
					bt.setBackgroundColor(Color.parseColor("#CCCCCC"));	
					bt.setPadding(0, 3, 0, 0);
					
					TableRow row;
					TableLayout.LayoutParams lp = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
					lp.setMargins(0, 10, 0, 0);
					
			        row = new TableRow( this );
			        tablelayout.addView( row );
			        row.setLayoutParams(lp);
			        int px=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,140,getResources().getDisplayMetrics());
			        int px1=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,getResources().getDisplayMetrics());
			        row.addView(tv,px,px1);
			        row.addView(bt,px,px1);
			        registerForContextMenu(bt);
					bt.setOnClickListener(new View.OnClickListener() {
						
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
					    Intent nextscreen=new Intent(getApplicationContext(),AccDetails.class);
						nextscreen.putExtra("id", d1);
						Log.e("List Screen",".");
		                startActivity(nextscreen);		
						}
					});
					
			j++;	}while(c.moveToNext());
	        }
		        if(j==0)
		        { noDet=new TextView(ListAccount.this);
				ViewGroup linearlayout=(ViewGroup)findViewById(R.id.newbutton);
				noDet.setText("No Accounts to view");
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
menu.add(0, v.getId(), 0, "Delete");  
menu.add(0, v.getId(), 0, "Update");
menu.add(0, v.getId(), 0, "View Transactions"); 
j1=((Button) v).getText().toString();
k=Long.parseLong(j1);}

public boolean onContextItemSelected(MenuItem item) {  
if(item.getTitle()=="Update")
{
Intent newscreen=new Intent(getApplicationContext(),Update.class);
newscreen.putExtra("id",k);
Log.e("List Screen",".");
startActivity(newscreen);
}  

else if(item.getTitle()=="View Transactions")
{
Intent newscr=new Intent(getApplicationContext(),ViewTransactions.class);
newscr.putExtra("id",k);
Log.e("List Screen",".");
startActivity(newscr);
}  
else if(item.getTitle()=="Delete")
{ AlertDialog.Builder adb=new AlertDialog.Builder(this);
adb.setMessage("Are you sure you want to delete this account?");
adb.setTitle("Delete");
adb.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		db1.execSQL("DELETE FROM Account_Details WHERE AccountNo="+k+";");
		db1.execSQL("DELETE FROM Transactions WHERE AccountNo="+k+";");
		finish();
		Intent same=new Intent(getApplicationContext(),ListAccount.class);
		startActivity(same);
		Toast.makeText(getApplicationContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
		
	}
});

adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
		
	}
});
adb.setIcon(R.drawable.accdel);
adb.show();}
else {return false;}  
return true;  
 }  
}