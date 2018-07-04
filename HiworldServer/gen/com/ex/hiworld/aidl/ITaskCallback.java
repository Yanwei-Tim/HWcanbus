/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\jxfWork\\EcProject\\HiworldServer\\src\\com\\ex\\hiworld\\aidl\\ITaskCallback.aidl
 */
package com.ex.hiworld.aidl;
public interface ITaskCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ex.hiworld.aidl.ITaskCallback
{
private static final java.lang.String DESCRIPTOR = "com.ex.hiworld.aidl.ITaskCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ex.hiworld.aidl.ITaskCallback interface,
 * generating a proxy if needed.
 */
public static com.ex.hiworld.aidl.ITaskCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ex.hiworld.aidl.ITaskCallback))) {
return ((com.ex.hiworld.aidl.ITaskCallback)iin);
}
return new com.ex.hiworld.aidl.ITaskCallback.Stub.Proxy(obj);
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
case TRANSACTION_getByte:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
byte[] _arg1;
_arg1 = data.createByteArray();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
this.getByte(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_getCmd:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int[] _arg1;
_arg1 = data.createIntArray();
int _arg2;
_arg2 = data.readInt();
java.lang.String _arg3;
_arg3 = data.readString();
this.getCmd(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_update:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int[] _arg1;
_arg1 = data.createIntArray();
float[] _arg2;
_arg2 = data.createFloatArray();
java.lang.String[] _arg3;
_arg3 = data.createStringArray();
this.update(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ex.hiworld.aidl.ITaskCallback
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
@Override public void getByte(int code, byte[] bytes, int size, java.lang.String strs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeByteArray(bytes);
_data.writeInt(size);
_data.writeString(strs);
mRemote.transact(Stub.TRANSACTION_getByte, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void getCmd(int code, int[] ints, int size, java.lang.String strs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(code);
_data.writeIntArray(ints);
_data.writeInt(size);
_data.writeString(strs);
mRemote.transact(Stub.TRANSACTION_getCmd, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//   void update(int updateCode, in int[] ints, int size);

@Override public void update(int updateCode, int[] ints, float[] flts, java.lang.String[] strs) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(updateCode);
_data.writeIntArray(ints);
_data.writeFloatArray(flts);
_data.writeStringArray(strs);
mRemote.transact(Stub.TRANSACTION_update, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_getByte = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getCmd = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_update = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void getByte(int code, byte[] bytes, int size, java.lang.String strs) throws android.os.RemoteException;
public void getCmd(int code, int[] ints, int size, java.lang.String strs) throws android.os.RemoteException;
//   void update(int updateCode, in int[] ints, int size);

public void update(int updateCode, int[] ints, float[] flts, java.lang.String[] strs) throws android.os.RemoteException;
}
