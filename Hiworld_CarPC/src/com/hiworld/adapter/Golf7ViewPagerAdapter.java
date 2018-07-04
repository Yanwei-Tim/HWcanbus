package com.hiworld.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Golf7ViewPagerAdapter extends FragmentPagerAdapter{

	private Fragment[] fragments;
	public Golf7ViewPagerAdapter(FragmentManager fm,Fragment[] fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		
		return fragments[position];
	}

	@Override
	public int getCount() {
		
		return fragments.length;
	}

}
