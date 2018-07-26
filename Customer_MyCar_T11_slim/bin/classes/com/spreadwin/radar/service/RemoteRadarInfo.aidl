package com.spreadwin.radar.service;
import com.spreadwin.radar.service.PointEntity;
 interface RemoteRadarInfo {
	int getEdogDistance(); 	
	int getEdogType();
	PointEntity getPointEntity();
}
