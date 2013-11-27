package com.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SrchResults extends Activity{
    /** Called when the activity is first created. */
	SQLiteDatabase db1=null;
	TextView noDet;
	String s,type,dat,t,m,transId,mode;
	Integer i;
	Intent in,er;
	int sz,siz,px,px1,px2,px3;
	ViewGroup tablelayout;
	Cursor c;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main13);
        siz=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
        sz=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
        px=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,65,getResources().getDisplayMetrics());
		 px1=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,getResources().getDisplayMetrics());
		 px2=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,110,getResources().getDisplayMetrics());
		 px3=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,33,getResources().getDisplayMetrics());
		
        
        in=getIntent();
        tablelayout=(ViewGroup)findViewById(R.id.restable);
        int i=in.getIntExtra("num", 0);
       if(i==1)
       {
    	transId=in.getStringExtra("transId");
       }
       else if(i==2) {mode=in.getStringExtra("mod");}
       else if(i==3){type=in.getStringExtra("typ");}
       else if(i==4){dat=in.getStringExtra("dat");}
       
        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS Transactions(TransactionId TEXT PRIMARY KEY," +
		 		"AccountNo INTEGER REFERENCES Account_Details ('AccountNo'),Dat DATE NOT NULL, Tym TIME NOT NULL," +
		 		"Mode TEXT CHECK ((Mode='Cheque')OR(Mode='Cash')OR(Mode='ATM')), Type TEXT CHECK ((Type='Debit')" +
		 		"OR(Type='Credit')),Amount INTEGER NOT NULL, RefNo INTEGER);");						
        if(!(transId==null))
        { try{
        	 c=db1.rawQuery("SELECT * FROM Transactions WHERE TransactionId='"+transId+"' ORDER BY Dat DESC;",null);
        }
        catch(Exception e){
        	Toast.makeText(getApplicationContext(), "Transaction Id not found" , Toast.LENGTH_SHORT).show();
		finish();
        }
        if(c.getCount()==0){Toast.makeText(getApplicationContext(), "Transaction Id not found" , Toast.LENGTH_SHORT).show();
		finish();}
       
        if(!(c==null))
        	{        
				if(c.moveToFirst())
			{	 m=c.getString(c.getColumnIndex("Mode"));
				t=c.getString(c.getColumnIndex("Type"));
				s=c.getString(c.getColumnIndex("Dat"));
			
				TextView tv=new TextView(SrchResults.this);
        	    Button bt=new Button(SrchResults.this);
        	    TextView tv1=new TextView(SrchResults.this);
				TextView tv2=new TextView(SrchResults.this);
				
				TableLayout.LayoutParams klp = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				klp.setMargins(0, 10, 0, 0);
				TableRow row;      
		        row = new TableRow( this );
		        row.setLayoutParams(klp);
		        tablelayout.addView( row );
		  
				tv.setText(s);
				tv.setTextSize(siz);
				bt.setText(transId);
			
				bt.setTextSize(sz);
				tv1.setText(t);
				tv2.setText(m);
				
				tv.setTextColor(Color.WHITE);
				tv1.setTextColor(Color.WHITE);
				tv1.setGravity(Gravity.CENTER);
				tv2.setTextColor(Color.WHITE);
				bt.setBackgroundColor(Color.parseColor("#CCCCCC"));
				bt.setGravity(Gravity.CENTER);
			    row.addView(tv,px,px1);
				row.addView(bt,px2,px3);
				row.addView(tv1);
				row.addView(tv2);
		        
		       
		        bt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
					nextscreen.putExtra("transid",transId );
					Log.e("Src Res",".");
	                startActivity(nextscreen);		
					}
				});
        		 
			}   }
       
        	}
        
    else if(!(mode==null))
        { try{
        	Cursor c1=db1.rawQuery("SELECT * FROM Transactions WHERE Mode='"+mode+"' ORDER BY Dat DESC;",null);
    		if(c1.getCount()==0)
    		{Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
    		finish();}
        	
        	
        	
        	if(c1.moveToFirst())
   do
    	{      
			final String trans=c1.getString(c1.getColumnIndex("TransactionId"));
			t=c1.getString(c1.getColumnIndex("Type"));
			s=c1.getString(c1.getColumnIndex("Dat"));
       		  		
			Button bt=new Button(SrchResults.this);
			TableLayout.LayoutParams klp1 = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			klp1.setMargins(0, 10, 0, 0);
			TableRow row;      
	        row = new TableRow( this );
	        row.setLayoutParams(klp1);
	        tablelayout.addView( row );
	        TextView tv1=new TextView(SrchResults.this);
			TextView tv2=new TextView(SrchResults.this);
	        TextView tv=new TextView(SrchResults.this);
	        
			tv.setText(s);
			tv.setTextSize(siz);
			tv1.setText(t);
			tv2.setText(mode);
			bt.setText(trans);
			bt.setTextSize(sz);
			
			tv.setTextColor(Color.WHITE);
			tv1.setTextColor(Color.WHITE);
			tv1.setGravity(Gravity.CENTER);
			tv2.setTextColor(Color.WHITE);
			bt.setBackgroundColor(Color.parseColor("#CCCCCC"));
			bt.setGravity(Gravity.CENTER);
			
	        row.addView(tv,px,px1);
			row.addView(bt,px2,px3);
			row.addView(tv1);
			row.addView(tv2);
			
			
			bt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
				nextscreen.putExtra("transid",trans );
				Log.e("Src Res",".");
                startActivity(nextscreen);		
				}
			});
			
			
	        
	       }while(c1.moveToNext());
    		
        }
        
        catch(Exception e) {Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
		finish();}
        }
        
        
        
        
        else if(!(type==null))
        { try{
        	Cursor c1=db1.rawQuery("SELECT * FROM Transactions WHERE Type='"+type+"' ORDER BY Dat DESC;",null);
    		if(c1.getCount()==0)
    		{Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
    		finish();}
        	
        	
        	
        	if(c1.moveToFirst())
   do
    	{      
			final String trans=c1.getString(c1.getColumnIndex("TransactionId"));
			t=c1.getString(c1.getColumnIndex("Mode"));
			s=c1.getString(c1.getColumnIndex("Dat"));
       		  		
			Button bt=new Button(SrchResults.this);
			TableLayout.LayoutParams klp2 = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			klp2.setMargins(0, 10, 0, 0);
			TableRow row;      
	        row = new TableRow( this );
	        row.setLayoutParams(klp2);
	        tablelayout.addView( row );
	        TextView tv2=new TextView(SrchResults.this);
			TextView tv1=new TextView(SrchResults.this);
	        TextView tv=new TextView(SrchResults.this);
			
	        tv.setText(s);
	        tv.setTextSize(siz);
	        bt.setText(trans);
	        bt.setTextSize(sz);
	        tv1.setText(type);
			tv2.setText(t);
			bt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
				nextscreen.putExtra("transid",trans );
				Log.e("Src Res",".");
                startActivity(nextscreen);		
				}
			});
			
			tv.setTextColor(Color.WHITE);
			tv1.setTextColor(Color.WHITE);
			tv1.setGravity(Gravity.CENTER);
			tv2.setTextColor(Color.WHITE);
			bt.setBackgroundColor(Color.parseColor("#CCCCCC"));
			bt.setGravity(Gravity.CENTER);
			
	        row.addView(tv,px,px1);
			row.addView(bt,px2,px3);
			row.addView(tv1);
			row.addView(tv2);
			
	       }while(c1.moveToNext());
    		
        }
        
        catch(Exception e) {Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
		finish();}
        }
        
        else if(!(dat==null))
        { try{
        	Cursor c1=db1.rawQuery("SELECT * FROM Transactions WHERE Dat='"+dat+"' ORDER BY Dat DESC;",null);
    		if(c1.getCount()==0)
    		{Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
    		finish();}
        	
        	
        	
        	if(c1.moveToFirst())
   do
    	{      
			final String trans=c1.getString(c1.getColumnIndex("TransactionId"));
			t=c1.getString(c1.getColumnIndex("Mode"));
			s=c1.getString(c1.getColumnIndex("Dat"));
			type=c1.getString(c1.getColumnIndex("Type"));
			
			Button bt=new Button(SrchResults.this);
			TextView tv=new TextView(SrchResults.this);
			TextView tv1=new TextView(SrchResults.this);
			TextView tv2=new TextView(SrchResults.this);
			
			TableLayout.LayoutParams klp3 = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			klp3.setMargins(0, 10, 0, 0);
			TableRow row;      
	        row = new TableRow( this );
	        row.setLayoutParams(klp3);
	        tablelayout.addView( row );
			
	        
			tv.setText(s);
			tv.setTextSize(siz);
			bt.setText(trans);
			bt.setTextSize(sz);
			tv1.setText(type);
			tv2.setText(t);
			
			tv.setTextColor(Color.WHITE);
			tv1.setTextColor(Color.WHITE);
			tv1.setGravity(Gravity.CENTER);
			tv2.setTextColor(Color.WHITE);
			bt.setBackgroundColor(Color.parseColor("#CCCCCC"));
			bt.setGravity(Gravity.CENTER);
			
	        row.addView(tv,px,px1);
			row.addView(bt,px2,px3);
			row.addView(tv1);
			row.addView(tv2);
			
			bt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			    Intent nextscreen=new Intent(getApplicationContext(),TransDetails.class);
				nextscreen.putExtra("transid",trans );
				Log.e("Src Res",".");
                startActivity(nextscreen);		
				}
			});
			}while(c1.moveToNext());
    		
        }
        
        catch(Exception e) {Toast.makeText(getApplicationContext(), "No Records found" , Toast.LENGTH_SHORT).show();
		finish();}
        }
        
        	}
    	
    	}
        
        	 
	





    
