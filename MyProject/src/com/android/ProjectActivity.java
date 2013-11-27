package com.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class ProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageButton btnNext=(ImageButton)findViewById(R.id.newacc);
        ImageButton listacc=(ImageButton)findViewById(R.id.accList);
        ImageButton rectrans=(ImageButton)findViewById(R.id.recTrans);
        ImageButton srchtrans=(ImageButton)findViewById(R.id.search);
        ImageButton support=(ImageButton)findViewById(R.id.support);
        ImageButton addtrans=(ImageButton)findViewById(R.id.addtrans);
        btnNext.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    		Intent nextscreen=new Intent(getApplicationContext(),NewAccount.class);
    		startActivity(nextscreen);
    		Log.e("New Account",".");
    		}
    	});
        
        
        listacc.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    		Intent nextscreen=new Intent(getApplicationContext(),ListAccount.class);
    		startActivity(nextscreen);
    		}
    	});   
        
        rectrans.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    		Intent nextscreen=new Intent(getApplicationContext(),Trans.class);
    		startActivity(nextscreen);
    		}
    	}); 
        
        	srchtrans.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    		Intent nextscreen=new Intent(getApplicationContext(),SrcTrans.class);
    		startActivity(nextscreen);
    		}
    	});
        	

        	addtrans.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    		Intent nextscreen=new Intent(getApplicationContext(),AddTrans.class);
    		startActivity(nextscreen);
    		}
    	});
        	
        	support.setOnClickListener(new View.OnClickListener() {
        		
        		@Override
        		public void onClick(View v) {
        			// TODO Auto-generated method stub
        		Intent nextscreen=new Intent(getApplicationContext(),support.class);
        		startActivity(nextscreen);
        		}
        	});
    }
}