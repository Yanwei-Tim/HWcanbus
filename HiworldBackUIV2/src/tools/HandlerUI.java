/**
 * ��Ȩ�������������Ƽ����޹�˾
 * ���:	 �»���
 * ���룺�������з���/Android��
 * ���ڣ�2015��1��1��
 */

package tools;

import android.os.Handler;
import android.os.Looper;

/**
 * ����ˢ��UI,������������ݴ���,��ľ��
 */
public class HandlerUI extends Handler {

	private static final HandlerUI INSTANCE = new HandlerUI();
	
	public static HandlerUI getInstance() {
		return INSTANCE;
	}
	
	private HandlerUI() { super(Looper.getMainLooper()); }
}
