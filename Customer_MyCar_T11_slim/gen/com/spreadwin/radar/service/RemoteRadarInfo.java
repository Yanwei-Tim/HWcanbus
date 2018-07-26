/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\jxfWork\\EcProject\\Customer_MyCar_T11_slim\\src\\com\\spreadwin\\radar\\service\\RemoteRadarInfo.aidl
 */
package com.spreadwin.radar.service;
public interface RemoteRadarInfo extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.spreadwin.radar.service.RemoteRadarInfo
{
private static final java.lang.String DESCRIPTOR = "com.spreadwin.radar.service.RemoteRadarInfo";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.spreadwin.radar.service.RemoteRadarInfo interface,
 * generating a proxy if needed.
 */
public static com.spreadwin.radar.service.RemoteRadarInfo asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.spreadwin.radar.service.RemoteRadarInfo))) {
return ((com.spreadwin.radar.service.RemoteRadarInfo)iin);
}
return new com.spreadwin.radar.service.RemoteRadarInfo.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getEdogDistance:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getEdogDistance();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getEdogType:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getEdogType();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getPointEntity:
{
data.enforceInterface(DESCRIPTOR);
com.spreadwin.radar.service.PointEntity _result = this.getPointEntity();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.spreadwin.radar.service.RemoteRadarInfo
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public int getEdogDistance() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEdogDistance, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getEdogType() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEdogType, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public com.spreadwin.radar.service.PointEntity getPointEntity() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.spreadwin.radar.service.PointEntity _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPointEntity, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.spreadwin.radar.service.PointEntity.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getEdogDistance = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getEdogType = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getPointEntity = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public int getEdogDistance() throws android.os.RemoteException;
public int getEdogType() throws android.os.RemoteException;
public com.spreadwin.radar.service.PointEntity getPointEntity() throws android.os.RemoteException;
}
