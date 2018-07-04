/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\jxfWork\\ASProject\\MyApplication\\app\\src\\main\\aidl\\com\\ex\\hiworld\\aidl\\ITaskBinder.aidl
 */
package com.ex.hiworld.aidl;
public interface ITaskBinder extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements ITaskBinder
{
private static final String DESCRIPTOR = "com.ex.hiworld.aidl.ITaskBinder";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ex.hiworld.aidl.ITaskBinder interface,
 * generating a proxy if needed.
 */
public static ITaskBinder asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof ITaskBinder))) {
return ((ITaskBinder)iin);
}
return new Proxy(obj);
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
case TRANSACTION_sendByte:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
this.sendByte(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_cmd:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int[] _arg1;
_arg1 = data.createIntArray();
float[] _arg2;
_arg2 = data.createFloatArray();
String[] _arg3;
_arg3 = data.createStringArray();
this.cmd(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_sendCmd:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int[] _arg1;
_arg1 = data.createIntArray();
this.sendCmd(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_sendUpdate:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int[] _arg1;
_arg1 = data.createIntArray();
this.sendUpdate(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_registerCallback:
{
data.enforceInterface(DESCRIPTOR);
com.ex.hiworld.aidl.ITaskCallback _arg0;
_arg0 = com.ex.hiworld.aidl.ITaskCallback.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
this.registerCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_unregisterCallback:
{
data.enforceInterface(DESCRIPTOR);
com.ex.hiworld.aidl.ITaskCallback _arg0;
_arg0 = com.ex.hiworld.aidl.ITaskCallback.Stub.asInterface(data.readStrongBinder());
int _arg1;
_arg1 = data.readInt();
this.unregisterCallback(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements ITaskBinder
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
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void sendByte(int code, byte[] bytes) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeByteArray(bytes);
mRemote.transact(Stub.TRANSACTION_sendByte, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(cmdCode);
_data.writeIntArray(ints);
_data.writeFloatArray(flts);
_data.writeStringArray(strs);
mRemote.transact(Stub.TRANSACTION_cmd, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void sendCmd(int code, int[] ints) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeIntArray(ints);
mRemote.transact(Stub.TRANSACTION_sendCmd, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void sendUpdate(int code, int[] ints) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeIntArray(ints);
mRemote.transact(Stub.TRANSACTION_sendUpdate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void registerCallback(com.ex.hiworld.aidl.ITaskCallback cb, int code) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
_data.writeInt(code);
mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregisterCallback(com.ex.hiworld.aidl.ITaskCallback cb, int code) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
_data.writeInt(code);
mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_sendByte = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_cmd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_sendCmd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_sendUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
}
public void sendByte(int code, byte[] bytes) throws android.os.RemoteException;
public void cmd(int cmdCode, int[] ints, float[] flts, String[] strs) throws android.os.RemoteException;
public void sendCmd(int code, int[] ints) throws android.os.RemoteException;
public void sendUpdate(int code, int[] ints) throws android.os.RemoteException;
public void registerCallback(com.ex.hiworld.aidl.ITaskCallback cb, int code) throws android.os.RemoteException;
public void unregisterCallback(com.ex.hiworld.aidl.ITaskCallback cb, int code) throws android.os.RemoteException;
}
