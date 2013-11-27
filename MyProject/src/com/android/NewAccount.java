package com.android;

import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewAccount extends Activity{
	SQLiteDatabase db1=null;
    Button sub;
    EditText edtAccNo,edtBank,edtBranch,edtAddr,edtPh,edtFax,edtIFSC,edtBal=null;
    Editable d1,d2,d3,d4,d5,d6,d7,d8,d9,d10=null;
    String type; 
    TextView edtxt,edtDob=null;
    
	public DatePickerDialog datepick=null;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	        edtxt=(TextView) findViewById(R.id.daye);
	        Button b1= (Button) findViewById(R.id.daypickbut);
	        try{
			b1.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v){
					String date=edtxt.getText().toString();
					if (date !=null && !date.equals(""))
					{
						StringTokenizer st= new StringTokenizer(date,"-");
						String day=st.nextToken();
						String month=st.nextToken();
						String year=st.nextToken();					
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
	        
	        db1=openOrCreateDatabase("bank.db", Context.MODE_PRIVATE, null);
	        edtAccNo=(EditText)findViewById(R.id.accno);
	        
	        edtDob=(TextView) findViewById(R.id.daye);
	        edtBank=(EditText)findViewById(R.id.bname);
	        edtBranch=(EditText)findViewById(R.id.brname);
	        edtAddr=(EditText)findViewById(R.id.bradd);
	        edtPh=(EditText)findViewById(R.id.brph);
	        edtFax=(EditText)findViewById(R.id.brfax);
	        edtIFSC=(EditText)findViewById(R.id.ifsc);
	        edtBal=(EditText)findViewById(R.id.bal);
	        sub=(Button)findViewById(R.id.submit);
	        
	        
	        
	        sub.setOnClickListener(new OnClickListener(){
	        @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				d1=edtAccNo.getText();
				String dd= edtDob.getText().toString();
				d4=edtBank.getText();
				d5=edtBranch.getText();
				d6=edtAddr.getText();
				d7=edtPh.getText();
				d8=edtFax.getText();
				d9=edtIFSC.getText();
				d10=edtBal.getText();
				
				
				
				try
				{
				
					db1.execSQL("CREATE TABLE IF NOT EXISTS Account_Details (AccountNo INTEGER(16) PRIMARY KEY, DateOfOpening DATE NOT NULL," +
							" Type TEXT CHECK((Type='CURRENT')OR(Type='SAVINGS')), BankName TEXT NOT NULL, BranchName TEXT NOT NULL, " +
							"BranchAddress TEXT NOT NULL, BranchPhno INTEGER, BranchFaxNo INTEGER, IFSCCode TEXT NOT NULL, ClearBalance INTEGER(10), " +
							"UnclearBalance INTEGER);");
					db1.execSQL("INSERT INTO Account_Details(AccountNo,DateOfOpening,Type,BankName,BranchName,BranchAddress,BranchPhno," +
							"BranchFaxNo,IFSCCode,ClearBalance) VALUES("+d1+",'"+dd+"','"+type+"','"+d4+"','"+d5+"'," +
							"'"+d6+"',"+d7+","+d8+",'"+d9+"',"+d10+");");
					Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
					Intent lis=new Intent(getApplicationContext(),ListAccount.class);
					startActivity(lis);
					finish();
				}
				
				catch(Exception e){
					System.out.println(e);
					Toast.makeText(getApplicationContext(), "Inadequate details..\nEnter Again", Toast.LENGTH_SHORT).show();
				}
			}});
	        
}
	 
	 public class DatePickHandler implements OnDateSetListener{
	        public void onDateSet(DatePicker view, int year, int month, int day) {
	        	int months = month+1;
	        	if((months<10)&&(day<10))
		            edtxt.setText(year + "-0" + (months) + "-0" + day);
		        	else if((months<10)&&(day>10))
			            edtxt.setText(year + "-0" + (months) + "-" + day);
		        	else if((months>10)&&(day<10))
			            edtxt.setText(year + "-" + (months) + "-0" + day);
		        	else
			            edtxt.setText(year + "-" + (months) + "-" + day);
	            datepick.hide();
	 }
}
	 
	 public void onRadioButtonClicked(View view){
			boolean checked = ((RadioButton) view).isChecked();
			
			//check if radio button is checked
			switch (view.getId()){
			case R.id.SavingsAc:
				if (checked)
					type= "SAVINGS";
				break;
			case R.id.CurrentAc:
				if (checked)
					type = "CURRENT";
				break;
			}
		}
}


