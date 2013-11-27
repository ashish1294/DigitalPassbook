package com.android;

import java.util.Calendar;
import java.util.StringTokenizer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SrcTrans extends Activity {
	 Spinner sp;
	 Intent in;
	 EditText et,edt;
	 TextView tv,edtxt,txt;
	 int p,p1;
	 View view;
	 LinearLayout mainLayout;
	 public DatePickerDialog datepick=null;
	    ArrayAdapter<String> adapter;
	    String numbers[]={"Transaction Id","Date","Mode","Type"},mode,trnstype,dat=null;
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.main8);
	        
	        mainLayout =(LinearLayout)findViewById(R.id.LayoutTrans);
	        p=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,20,getResources().getDisplayMetrics());
	        p1=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,270,getResources().getDisplayMetrics());
	        tv=(TextView)findViewById(R.id.textView1);
	        in=new Intent(getApplicationContext(),SrchResults.class);
	        sp=(Spinner)findViewById(R.id.spinner1);
	        adapter= new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,numbers);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp.setAdapter(adapter);
	        
	        sp.setOnItemSelectedListener(new OnItemSelectedListener(){
	        	
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
	        		if(sp.getSelectedItem().toString().equals("Transaction Id")){
	        		if(view!=null)
	        		view.setVisibility(View.GONE);
	        		view=getLayoutInflater().inflate(R.layout.main9, mainLayout, false) ;
	        		Button b=(Button)view.findViewById(R.id.sbmt);
	        		
	        		if(txt!=null)
	        		{txt.setVisibility(View.GONE);}
	        	 txt=new TextView(SrcTrans.this);
		        		txt.setText("Enter Transaction Id");
		        		txt.setTextColor(Color.YELLOW);
		        		txt.setTextSize(p);
		        		mainLayout.addView(txt,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	        		
	        		if(edt!=null)
	        		{edt.setVisibility(View.GONE);}
	        		edt=new EditText(SrcTrans.this);
	        		edt.setTextColor(Color.WHITE);
	        		edt.requestFocus();
	           		mainLayout.addView(edt,p1,LayoutParams.WRAP_CONTENT);
	        		b.setOnClickListener(new View.OnClickListener() {
	        		    		
	        		    		@Override
	        		    		public void onClick(View v) {
	        		    			// TODO Auto-generated method stub
	        		    		
	        		    		
	        		    			 String s=edt.getText().toString();
	        		    			 int flag=0;
	        		    			 for(int i=0; i<s.length();i++)
	        		    			 {
	        		    				 if(Character.isWhitespace(s.charAt(i)))
	        		    				 {
	        		    					 flag=1; break;
	        		    				 }
	        		    			 }
	        		    			 
	        		    			 if (s.length()==0)
	        		    				 flag=1;
	        		    			 if (!(s.trim()==s))
	        		    				 flag=1;
	        		    			 
	        		    			 if(!(flag==1))
	        	        			{
	        	        			in.putExtra("transId", s);
	        	        			in.putExtra("num", 1);
	        	        			startActivity(in);}
	        	        			else
	        	        			{ Toast.makeText(getApplicationContext(),"Invalid Transaction Id", Toast.LENGTH_SHORT).show();}
	        		    		
	        		    		}});
	        			
	        		}
	        		else if(numbers[arg2].equals("Mode")){
	        			txt.setVisibility(View.GONE);
	        			edt.setVisibility(View.GONE);
	        			if(view!=null)
		        			view.setVisibility(View.GONE);
	        			view=getLayoutInflater().inflate(R.layout.main10, mainLayout, false) ;
	        			
	        			Button b=(Button)view.findViewById(R.id.sbmt1);
	        			 b.setOnClickListener(new View.OnClickListener() {
	        		    		
	        		    		@Override
	        		    		public void onClick(View v) {
	        		    			// TODO Auto-generated method stub
	        		    			if(!(mode==null))
	        		    			{in.putExtra("mod", mode);
	        		    			in.putExtra("num", 2);
	        		    			startActivity(in);
	        		    			}
	        		    			else {Toast.makeText(getApplicationContext(),"Select a mode", Toast.LENGTH_SHORT).show();}
	        		    		
	        		    		}});
	        		
	        		}
	        		
	        		else if(numbers[arg2].equals("Type")){
	        			txt.setVisibility(View.GONE);
	        			edt.setVisibility(View.GONE);
	        			if(view!=null)
		        			view.setVisibility(View.GONE);
	        			view=getLayoutInflater().inflate(R.layout.main11, mainLayout, false) ;
	        			
	        			Button b=(Button)view.findViewById(R.id.sbmt2);
	        			 b.setOnClickListener(new View.OnClickListener() {
	        		    		
	        		    		@Override
	        		    		public void onClick(View v) {
	        		    			// TODO Auto-generated method stub
	        		    		
	        		    			
	        		    			if(trnstype!=null)
	        		    			{in.putExtra("typ",trnstype );
	        		    			in.putExtra("num", 3);
	        		    			startActivity(in);
	        		    			}
	        		    			else {Toast.makeText(getApplicationContext(),"Select a type", Toast.LENGTH_SHORT).show();}
	        		    		
	        		    		
	        		    		}});
	        			
	        			
	        		}
	        		
	        		else if(numbers[arg2].equals("Date")){
	        			txt.setVisibility(View.GONE);
	        			edt.setVisibility(View.GONE);
	        			if(view!=null)
		        	    view.setVisibility(View.GONE);
	        			view=getLayoutInflater().inflate(R.layout.main12, mainLayout, false);
	        			Button b=(Button)view.findViewById(R.id.sbmt3);
  	        			 b.setOnClickListener(new View.OnClickListener() {
  	        		    		
  	        		    		@Override
  	        		    		public void onClick(View v) {
  	        		    			// TODO Auto-generated method stub
  	        		    			dat=edtxt.getText().toString();  	        		    			
  	        		    			if((dat==dat.trim()))
	        		    			{in.putExtra("dat", dat);
	        		    			in.putExtra("num", 4);
	        		    			startActivity(in);
	        		    			}
	        		    			else {Toast.makeText(getApplicationContext(),"Select a date", Toast.LENGTH_SHORT).show();}
	        		    		
  	        		    		
  	        		    		}});
       		        
	        			
	        			 edtxt=(TextView) view.findViewById(R.id.day1);
	        		        Button b1= (Button) view.findViewById(R.id.daypickbutt);
	        		        try{
	        				b1.setOnClickListener(new OnClickListener(){
	        					
	        					@Override
	        					public void onClick(View v){
	        				 String date=edtxt.getText().toString();
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
	        		        }
	        		
	         mainLayout.addView(view);
	     
				
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Nothing selected", Toast.LENGTH_LONG).show();
				}
	        });
	     }
	    
	   

	       

public void onRadioButtonClick(View view){
		boolean checked = ((RadioButton) view).isChecked();
		
		//check if radio button is checked
		switch (view.getId()){
		case R.id.cash:
			if (checked)
				mode= "Cash";
			break;
		case R.id.cheque:
			if (checked)
				mode = "Cheque";
			break;
			
		case R.id.atm:
			if (checked)
				mode = "ATM";
			break;
		}
	}


public void onButtonClick(View view){
		boolean checked = ((RadioButton) view).isChecked();
		
		//check if radio button is checked
		switch (view.getId()){
		case R.id.Cred:
			if (checked)
				trnstype= "Credit";
			break;
		case R.id.Deb:
			if (checked)
				trnstype = "Debit";
			break;
		}
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


}