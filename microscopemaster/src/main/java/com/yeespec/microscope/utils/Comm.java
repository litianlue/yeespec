package com.yeespec.microscope.utils;import android.app.Activity;import android.app.Dialog;import android.content.Context;import android.graphics.Bitmap;import android.graphics.Canvas;import android.graphics.Paint;import android.graphics.PorterDuff;import android.graphics.PorterDuffXfermode;import android.graphics.Rect;import android.graphics.RectF;import android.util.DisplayMetrics;import android.util.Log;import android.widget.Toast;import com.yeespec.R;import java.util.Locale;/** * Created by Mr.Wen on 2016/11/30. */public class Comm {  //  public static int SCREEN_HEIGHT = 0;  //  public static int SCREEN_WIDTH = 0;    static int doubleClickCount = 0;    static long doubleClickFirstClick = 0L;    static long doubleLastClick = 0L; //   public static final boolean isDEBUG = true;    public static void Log(String paramString) {        Log.i(LogHelper.TAG, paramString);    }    /*public static void adaptScreenOption(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {        DisplayMetrics localDisplayMetrics = new DisplayMetrics();        paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);        SCREEN_HEIGHT = localDisplayMetrics.heightPixels;        SCREEN_WIDTH = localDisplayMetrics.widthPixels;    }*/    public static String byteArray2String(byte[] bytArr, int pos, int len, boolean isHex) {        StringBuffer strBuf = new StringBuffer();        if (isHex) {            for (int i = pos; i < (pos + len); i++) {                String tmp = Integer.toHexString(bytArr[i] & 0xFF).toUpperCase(Locale.CHINA);                if (tmp.length() == 1) {                    tmp = "0" + tmp;                    strBuf.append(tmp + " ");                    return strBuf.substring(0, strBuf.length());                }            }        }        return new String(bytArr, pos, len);    }    public static void debug(String paramString) {        System.out.println(paramString);    }    /* public static String getTime(String format) {         String data = null;         if (format.equals("yyyy-MM-dd HH:mm:ss"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date());         else if (format.equals("yyyyMMddHHmmss"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date());         else if (format.equals("yyMMddHHmmss"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date()).substring(2);         else if (format.equals("yyyyMMddHHmmssSSS"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date());         else if (format.equals("yyyy-MM-dd HH:mm"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date());         else if (format.equals("yyyy-MM-dd"))             data = new SimpleDateFormat(format, Locale.CHINA).format(new Date());         else if (format.equals("ago_7_SS"))             data = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date(new Date().getTime() - 604800000L));         else if (format.equals("ago_7"))             data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(new Date().getTime() - 604800000L));         else if (format.equals("ago_7_S"))             data = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(new Date(new Date().getTime() - 604800000L));         return data;     } */    /*public static String getTime(String format, long milSeconds, String type) {        SimpleDateFormat sDateFormat = null;        String date = null;        if (format.equals("yyyy-MM-dd HH:mm:ss")) {            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        } else if (format.equals("yyyyMMddHHmmss"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        else if (format.equals("yyMMddHHmmss"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds)).substring(2);        else if (format.equals("yyyyMMddHHmmssSSS"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        else if (format.equals("yyyy-MM-dd HH:mm"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        else if (format.equals("yyyy-MM-dd"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        else if (format.equals("yy-MM-dd HH:mm"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        else if (format.equals("yy-MM-dd HH:mm:ss"))            date = new SimpleDateFormat(format, Locale.CHINA).format(new Date(milSeconds));        if ((type != null) && (type.equals("url"))) {            date = date.replaceAll(" ", "%20");        }        return date;    }*/    public static boolean isDoubleClick(Context paramContext, int paramInt, boolean paramBoolean) {        if ((doubleClickFirstClick != 0L) && (System.currentTimeMillis() - doubleClickFirstClick > paramInt))            doubleClickCount = 0;        doubleClickCount += 1;        if (doubleClickCount == 1) {            doubleClickFirstClick = System.currentTimeMillis();            if (paramBoolean)                Toast.makeText(paramContext, paramContext.getResources().getString(R.string.isExit), Toast.LENGTH_SHORT).show();            return false;        }        if (doubleClickCount == 2) {            doubleLastClick = System.currentTimeMillis();            if (doubleLastClick - doubleClickFirstClick < paramInt) {                doubleClickCount = 0;                doubleClickFirstClick = 0L;                doubleLastClick = 0L;                return true;            }        }        doubleClickCount = 0;        doubleClickFirstClick = 0L;        doubleLastClick = 0L;        return false;    }    /* public static boolean isNetWork(Context context) {         //        boolean bool = false;         //        context = (ConnectivityManager) context.getSystemService("connectivity");         //        if (context.getActiveNetworkInfo() != null)         //            bool = context.getActiveNetworkInfo().isAvailable();         //        return bool;         boolean flag = false;         ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);         if (connManager.getActiveNetworkInfo() != null) {             flag = connManager.getActiveNetworkInfo().isAvailable();         }         return flag;     } */    /*public static boolean isRunningApp(Context context, String packageName) {        //        ActivityManager am = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100).iterator();        //        List<ActivityManager.RunningTaskInfo> list  = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100).iterator();        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);        ActivityManager.RunningTaskInfo localRunningTaskInfo;        while (list.iterator().hasNext()) {            ActivityManager.RunningTaskInfo info = (ActivityManager.RunningTaskInfo) list.iterator().next();            if ((info.topActivity.getPackageName().equals(packageName)) && (info.baseActivity.getPackageName().equals(packageName))) {                // TODO: 2016/12/1 编译出错 :            }        }        return false;    }    public static boolean isValidSDKVersion() {        boolean bool = false;        try {            int i = Integer.valueOf(Build.VERSION.SDK_INT).intValue();            if (i > 12)                bool = true;            return bool;        } catch (NumberFormatException localNumberFormatException) {            localNumberFormatException.printStackTrace();        }        return false;    }    public static void openNetSetting(Context paramContext) {        AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();        localAlertDialog.setTitle(paramContext.getString(R.string.info_net));        localAlertDialog.setMessage(paramContext.getString(R.string.err_noNet));        localAlertDialog.setButton(-1, paramContext.getString(R.string.isOk), new DialogInterface.OnClickListener() {            // ERROR //            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {                // TODO: 2016/12/1 反编译出错 :            }        });        localAlertDialog.setButton(-2, paramContext.getString(R.string.isCancel), new DialogInterface.OnClickListener() {            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {                paramAnonymousDialogInterface.dismiss();            }        });        showDialog(localAlertDialog);    }*/    /*public static void showDialog(AlertDialog paramAlertDialog) {        if (paramAlertDialog.isShowing())            paramAlertDialog.dismiss();        paramAlertDialog.setCanceledOnTouchOutside(false);        paramAlertDialog.show();    }    public static void showDialog(ProgressDialog paramProgressDialog) {        if (paramProgressDialog.isShowing())            paramProgressDialog.dismiss();        paramProgressDialog.setCanceledOnTouchOutside(false);        paramProgressDialog.show();    }    public static void showDialog(Context paramContext, int paramInt1, int paramInt2, int paramInt3) {        AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();        localAlertDialog.setIcon(paramInt1);        localAlertDialog.setTitle(paramContext.getText(paramInt2));        localAlertDialog.setMessage(paramContext.getText(paramInt3));        localAlertDialog.setButton(-1, paramContext.getString(R.string.isOk), new DialogInterface.OnClickListener() {            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {                paramAnonymousDialogInterface.dismiss();            }        });        showDiaog(localAlertDialog);    }    public static void showDialog(Context paramContext, int paramInt, String paramString1, String paramString2) {        AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).create();        localAlertDialog.setIcon(paramInt);        localAlertDialog.setTitle(paramString1);        localAlertDialog.setMessage(paramString2);        localAlertDialog.setButton(-1, paramContext.getString(R.string.isOk), new DialogInterface.OnClickListener() {            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {                paramAnonymousDialogInterface.dismiss();            }        });        showDiaog(localAlertDialog);    }*/    public static void showDiaog(Dialog paramDialog) {        if (paramDialog.isShowing())            paramDialog.dismiss();        paramDialog.setCanceledOnTouchOutside(false);        paramDialog.show();    }/*    public Bitmap toRoundBitmap(Bitmap bitmap) {        int width = bitmap.getWidth();        int height = bitmap.getHeight();        float roundPx;        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;        if (width <= height) {            roundPx = width / 2;            top = 0;            bottom = width;            left = 0;            right = width;            height = width;            dst_left = 0;            dst_top = 0;            dst_right = width;            dst_bottom = width;        } else {            roundPx = height / 2;            float clip = (width - height) / 2;            left = clip;            right = width - clip;            top = 0;            bottom = height;            width = height;            dst_left = 0;            dst_top = 0;            dst_right = height;            dst_bottom = height;        }        Bitmap output = Bitmap.createBitmap(width,                height, Bitmap.Config.ARGB_8888);        Canvas canvas = new Canvas(output);        final int color = 0xff424242;        final Paint paint = new Paint();        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);        final RectF rectF = new RectF(dst);        paint.setAntiAlias(true);        canvas.drawARGB(0, 0, 0, 0);        paint.setColor(color);        //        paint.setColor(0x12434878);        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));        canvas.drawBitmap(bitmap, src, dst, paint);        return output;       *//* int i = bitmap.getWidth();        int j = bitmap.getHeight();        float f6;        float f1;        float f4;        float f5;        float f3;        if (i <= j) {            f6 = i / 2;            f1 = i;            f4 = 0.0F;            f5 = i;            j = i;            f3 = i;        }        for (float f2 = i; ; f2 = j) {            Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);            Canvas localCanvas = new Canvas(localBitmap);            Paint localPaint = new Paint();            Rect localRect1 = new Rect((int) f4, (int) 0.0F, (int) f5, (int) f1);            Rect localRect2 = new Rect((int) 0.0F, (int) 0.0F, (int) f3, (int) f2);            RectF localRectF = new RectF(localRect2);            localPaint.setAntiAlias(true);            localCanvas.drawARGB(0, 0, 0, 0);            localPaint.setColor(0x12434878);            localCanvas.drawRoundRect(localRectF, f6, f6, localPaint);            localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));            localCanvas.drawBitmap(bitmap, localRect1, localRect2, localPaint);            return localBitmap;            f6 = j / 2;            f1 = (i - j) / 2;            f4 = f1;            f5 = i - f1;            f1 = j;            i = j;            f3 = j;*//*    }*/}