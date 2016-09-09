package com.example.skillsofmdas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

public class splashActivity extends Activity{
	
	AnimationDrawable AniFrame;
	
	public MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		
		
		Thread logotimer = new Thread(){
			public void run(){
				
				try{
					//mp = MediaPlayer.create(splashActivity.this, R.raw.droid);
					//mp.start();
					int timer;
					for(timer=0;timer<=4000;timer=timer+100)
					{
						sleep(100);
						//AniFrame.start();
					}
					
					//startActivity(new Intent("com.example.skillsofmdas.MainActivity"));
					Intent mInHome = new Intent(splashActivity.this, mainActivity.class);
					splashActivity.this.startActivity(mInHome);
					splashActivity.this.finish();
					
				}
				catch(InterruptedException e){
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				finally{
					finish();
				}
			
			}
		
		};
		
		logotimer.start();
	}
}
