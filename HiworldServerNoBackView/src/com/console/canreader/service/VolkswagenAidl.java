package com.console.canreader.service;

interface VolkswagenAidl {

	void cmd(int cmd, int[]... params);

	void update(int code, int[] ints, float[] flts, String[] atrs);

}
