package com.android;


import java.util.Calendar;
import java.util.StringTokenizer;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Update extends Activity{
		 Editable trId,amnt,trnsref;
		 SQLiteDatabase db1=null;
		 Long accId,amount;
	
		 
		 TextView tv_date, tv_time;
		 public DatePickerDialog datepick=null;
		 public TimePickerDialog timepick=null;
		 
		 Spinner Mode;
		 ArrayAdapter<String> adapter1;
		 String Modes[] = {"ATM","Cash","Cheque"},mode,trnstype,ddate,ttime,A;
			 @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main4);
		        
		        tv_date = (TextView) findViewById(R.id.date);
		        tv_time = (TextView) findViewById(R.id.time);
		        
		        Button dpick = (Button) findViewById(R.id.daypickb);
		        Button tpick = (Button) findViewById(R.id.timepickb);
		        
		        try{
					dpick.setOnClickListener(new OnClickListener(){
						
						@Override
						public void onClick(View v){
							String date=tv_date.getText().toString();
							if (date !=null && !date.equals(""))
							{
								StringTokenizer st= new StringTokenizer(date,"-");
								String year=st.nextToken();
								String month=st.nextToken();
								String day=st.nextToken();					
								datepick = new DatePickerDialog(v.getContext(),(OnDateSetListener) new DatePickHandler(), Integer.parseInt(year),Integer.parseInt(month)-1, Integer.parseInt(day));
			                } 
							else 
							{
			                    datepick = new DatePickerDialog(v.getContext(), (OnDateSetListener) new DatePickHandler(), Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			                }
			                datepick.show();
						}
					});
			        }
			        catch(Exception e){
			        	Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
			        }
		        
		        try {
		    		tpick.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){
		    				String time=tv_time.getText().toString();
		    				if (time !=null && !time.equals(""))
		    				{
		    					StringTokenizer st= new StringTokenizer(time,":");
		    					String timeHour=st.nextToken();
		    					String timeMinute=st.nextToken();
		    					
		    					timepick = new TimePickerDialog(v.getContext(),(OnTimeSetListener) new TimePickHandler(), Integer.parseInt(timeHour),Integer.parseInt(timeMinute), true);
		                    } 
		    				else 
		    				{
		                        timepick = new TimePickerDialog(v.getContext(), new TimePickHandler(), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
		                    }

		                    timepick.show();
		    			}
		    			
		    		});
		        }
		    	catch (Exception e){
		    	Toast.makeText(getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT).show();
		        }

		        		        
		        Mode = (Spinner) findViewById(R.id.spinmode);
				adapter1 = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,Modes);			
				adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				Mode.setAdapter(adapter1);
		        
				Mode.setOnItemSelectedListener(new OnItemSelectedListener(){
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						mode=Modes[arg2];
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {		
					}
				});
		        
		        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
		        Intent i =getIntent();
		        //Recieving passed parameters
		        accId=i.getLongExtra("id", 0);
		        String a=accId.toString();
		        TextView t=(TextView)findViewById(R.id.ac);
		        t.setText(a);	
		       
		       
		       Button sub=(Button)findViewById(R.id.add_rec);
		        sub.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try{ EditText tr_id =(EditText)findViewById(R.id.trnsc_id);
					        trId=tr_id.getText();		      
					    EditText trref=(EditText)findViewById(R.id.trnsc_ref_no);
					        trnsref=trref.getText();
						 EditText amt=(EditText)findViewById(R.id.amount);
						 A=amt.getText().toString();
						 amount=Long.parseLong(A);
						 amnt=amt.getText();
					      
						 ddate= tv_date.getText().toString();
					     ttime= tv_time.getText().toString();}
						catch(Exception e){System.out.println(e);
						Toast.makeText(getApplicationContext(),"Inadequate Details", Toast.LENGTH_SHORT).show();}
					     
					    			     
					     
						 try
						 { db1.execSQL("CREATE TABLE IF NOT EXISTS Transactions(TransactionId TEXT PRIMARY KEY," +
							 		"AccountNo INTEGER REFERENCES Account_Details ('AccountNo'),Dat DATE NOT NULL, Tym TIME NOT NULL," +
							 		"Mode TEXT CHECK ((Mode='Cheque')OR(Mode='Cash')OR(Mode='ATM')), Type TEXT CHECK ((Type='Debit')" +
							 		"OR(Type='Credit')),Amount INTEGER NOT NULL, RefNo INTEGER);");						
							 
										 
							 if(trnstype=="Credit")
							 {
								 
								 
								
						 db1.execSQL("INSERT INTO Transactions VALUES('"+trId+"','"+accId+"','"+ddate+"','"+ttime+"','"+mode+"'," +
									"'"+trnstype+"',"+amnt+","+trnsref+");");
						 Toast.makeText(getApplicationContext(),"Account Updated", Toast.LENGTH_SHORT).show();
						 Intent i=new Intent(getApplicationContext(),Trans.class);
						 startActivity(i);
						 db1.execSQL("UPDATE Account_Details SET ClearBalance=ClearBalance+"+amount+" WHERE AccountNo="+accId+";");}
						 
						 else if(trnstype=="Debit")
						 {Cursor c=db1.rawQuery("SELECT * FROM Account_Details WHERE AccountNo="+accId+";",null);
							 c.moveToFirst();
							 Long i=c.getLong(c.getColumnIndex("ClearBalance"));
							 if(amount<=i)
							 {
								 db1.execSQL("INSERT INTO Transactions VALUES('"+trId+"','"+accId+"','"+ddate+"','"+ttime+"','"+mode+"'," +
											"'"+trnstype+"',"+amnt+","+trnsref+");");
								 Toast.makeText(getApplicationContext(),"Account Updated", Toast.LENGTH_SHORT).show();
								 Intent in=new Intent(getApplicationContext(),Trans.class);
								 finish();
								 startActivity(in);
							 db1.execSQL("UPDATE Account_Details SET ClearBalance=ClearBalance-"+amount+" WHERE AccountNo="+accId+";");}
							 
							 else {
								 Toast.makeText(getApplicationContext(), "Not Sufficient Balance", Toast.LENGTH_SHORT).show();
							 }
								 }
						 
						
							 }
						 catch(Exception e){
								System.out.println(e);
								Toast.makeText(getApplicationContext(),"Inadequate Details", Toast.LENGTH_SHORT).show();
							}
						 }
					});
		        }

			 
			 public class DatePickHandler implements OnDateSetListener{
			        public void onDateSet(DatePicker view, int year, int month, int day) {
			        	
			        	int months = month+1;
			        	if((months<10)&&(day<10))
			            tv_date.setText(year + "-0" + (months) + "-0" + day);
			        	else if((months<10)&&(day>10))
				            tv_date.setText(year + "-0" + (months) + "-" + day);
			        	else if((months>10)&&(day<10))
				            tv_date.setText(year + "-" + (months) + "-0" + day);
			        	else
				            tv_date.setText(year + "-" + (months) + "-" + day);
			        	datepick.hide();
			        }
			 }
			 
			 private class TimePickHandler implements OnTimeSetListener
			 {

			        
			        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			            tv_time.setText(hourOfDay + ":" + minute);
			            timepick.hide();

			 }
		}
			 
			 public void onRadioButtonClick(View view){
					boolean checked = ((RadioButton) view).isChecked();
					
					//check if radio button is checked
					switch (view.getId()){
					case R.id.Credit:
						if (checked)
							trnstype= "Credit";
						break;
					case R.id.Debit:
						if (checked)
							trnstype = "Debit";
						break;
					}
				}
}