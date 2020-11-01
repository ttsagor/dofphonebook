package com.Pisystems.dofphonebook.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Pisystems.dofphonebook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.spec.ECField;

/**
 * Created by sagor on 4/25/2016.
 */
public class JSON extends AsyncTask <String,String,String>{
    public AsyncResponse delegate = null;
    HttpURLConnection connection=null;
    BufferedReader bufferedReader=null;
    Boolean noticeService=false;
    Boolean sendFeedBack=false;
    Boolean userInfo=false;
    Boolean areaCode=false;
    Boolean areaInfo=false;
    Boolean designation=false;



    MyDBHandler db;
    Context mContex;
    int noticeID=0;
    @Override
    protected String doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null)
            {
                buffer.append(line);
            }
            return buffer.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.disconnect();
            }
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    Bitmap img=null;
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //System.out.println(s);
        int size=0;
        String noticeT="";

        if (noticeService) {
            try {
                Notice notice = new Notice();
                String lastUpdate = "";
                try {
                    JSONArray arrJsonArray = null;
                    arrJsonArray = new JSONArray(s);
                    size = 0;
                    for (int i = 0; i < arrJsonArray.length(); i++) {

                        try {
                            JSONObject jsonObject = null;

                            jsonObject = arrJsonArray.getJSONObject(i);

                            lastUpdate = jsonObject.optString("last_update").toString();
                            notice.setNotice_id(Integer.parseInt(jsonObject.optString("notice_id").toString()));
                            notice.setNotice_title(jsonObject.optString("notice_title").toString());
                            notice.setNotice_contains(jsonObject.optString("notice_content").toString());
                            notice.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                            notice.setNotice_date_time(jsonObject.optString("post_date").toString());
                            notice.setRef_number(jsonObject.optString("ref_number").toString());
                            notice.setPost_date(lastUpdate);
                            notice.setNotice_url(jsonObject.optString("notice_img").toString());
                            if (notice.getActive_stat() == 1) {
                                size++;
                                noticeT = notice.getNotice_title();
                                noticeID = notice.getNotice_id();
                            }
                            db.insertOrReplaceNotice(notice);
                        }catch (Exception e){}

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (lastUpdate != "" || !lastUpdate.isEmpty()) {
                    if(size>0)
                    {
                        if(size==1)
                        {
                            createNotification(mContex, noticeT, englishToBanglaNumber(String.valueOf(size)) + "টি নতুন নোটিশ", "ডিজিটাল ফোনবুকঃ নতুন নোটিশ",size);
                        }
                        else
                        {
                            createNotification(mContex, "ডিজিটাল ফোনবুকঃ নতুন নোটিশ", englishToBanglaNumber(String.valueOf(size)) + "টি নতুন নোটিশ", "ডিজিটাল ফোনবুক",size);
                        }
                    }
                    db.updateSystemInfoNotice("notice", lastUpdate);
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SystemInfo systemInfo = db.getSystemInfoJSON();
                final JSON asyncTask = new JSON();
                asyncTask.noticeService = true;
                asyncTask.db = db;
                asyncTask.mContex = mContex;
                // System.out.println(systemInfo.getNoticeLastUpdate());
                String date = "";
                try {
                    date = URLEncoder.encode(systemInfo.getNoticeLastUpdate(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //asyncTask.execute("http://aihub.com.bd/phonebook/api/get_notice_list" + "?last_update=" + date);
                //asyncTask.execute("http://service.digital-phonebook.com/noticeService.php" + "?date="+date);
            }
            catch (Exception e){}
            SplashActivity.noticComplete = true;
        }
        else if(sendFeedBack)
        {
            if(s!=null)
            {
                Toast.makeText(mContex, "ফিডব্যাকটি প্রেরণ সফল", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(mContex,"ফিডব্যাকটি প্রেরণ ব্যার্থ",Toast.LENGTH_LONG).show();
            }
        }
        else if(userInfo)
        {
            SplashActivity.userInfoComplete=false;
            User user = new User();
            String lastUpdate="";
            try {
                JSONArray arrJsonArray = new JSONArray(s);
                System.out.println("userinfo len:"+arrJsonArray.length());
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                        user.setUser_id(Integer.parseInt(jsonObject.optString("user_id").toString()));
                        user.setUser_name(jsonObject.optString("user_name_bng").toString());
                        user.setUser_name_eng(jsonObject.optString("user_name_eng").toString());
                        user.setDesignation(jsonObject.optString("desig_id").toString());
                        user.setArea_id(Integer.parseInt(jsonObject.optString("area_id").toString()));
                        user.setPhone1(jsonObject.optString("phone1").toString());
                        user.setPhone2(jsonObject.optString("phone2").toString());
                        user.setOffice_num(jsonObject.optString("lan").toString());
                        user.setEmail(jsonObject.optString("email").toString());
                        user.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                        lastUpdate = jsonObject.optString("last_update").toString();
                        user.setLast_update(jsonObject.optString("last_update").toString());
                        db.insertOrReplaceUSER(user);
                    }
                    catch (Exception e) {
                        System.out.println(arrJsonArray.getJSONObject(i).toString());
                        e.printStackTrace();
                    }
                    /// System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }
            if(lastUpdate!="")
            {
                db.updateSystemInfo("user_info",lastUpdate);
            }
            db.updateSystemInfoUpdating(MyDBHandler.COLUMN_userInfoUpdating, 0);
            //globalVariable.userInfoFlag=false;
            SplashActivity.userInfoComplete=true;
        }
        else if(areaCode)
        {
            SplashActivity.areaCodeComplete=false;
            areaCode cArea = new areaCode();
            String lastUpdate="";
            try {
                JSONArray arrJsonArray = new JSONArray(s);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                        cArea.setAreaID(Integer.parseInt(jsonObject.optString("area_id").toString()));
                        cArea.setParentID(Integer.parseInt(jsonObject.optString("parent_id").toString()));
                        cArea.setAreaName(jsonObject.optString("area_name_bng").toString());
                        cArea.setAreaNameEng(jsonObject.optString("area_name_eng").toString());
                        cArea.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                        cArea.setLast_update(jsonObject.optString("last_update").toString());
                        lastUpdate = jsonObject.optString("last_update").toString();
                        db.insertOrReplaceAreaCode(cArea);
                    }catch (Exception e){}
                    //System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }
            //System.out.println("LU:" + lastUpdate);
            if(lastUpdate!="")
            {
                db.updateSystemInfo("area_code",lastUpdate);
            }

            db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areacodeUpadting, 0);
            //System.out.println("areaCodeFlag "+ FragmentSettings.areaCodeFlag);
            //globalVariable.areaCodeFlag=false;
            // System.out.println("areaCodeFlag "+ FragmentSettings.areaCodeFlag);
            //db.updateSystemInfo("area_code");
            SplashActivity.areaCodeComplete=true;
        }
        else if(areaInfo)
        {
            SplashActivity.areaInfoComplete=false;
            area_info cArea = new area_info();
            String lastUpdate="";
            try {
                JSONArray arrJsonArray = new JSONArray(s);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                    cArea.setArea_id(Integer.parseInt(jsonObject.optString("area_id").toString()));
                    cArea.setArea_details(jsonObject.optString("area_details").toString());
                    cArea.setArea_location(jsonObject.optString("area_location").toString());
                    cArea.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                    cArea.setLast_update(jsonObject.optString("last_update").toString());
                    cArea.setAddress(jsonObject.optString("area_address_bn").toString());
                    lastUpdate = jsonObject.optString("last_update").toString();
                    db.insertOrReplaceAreaInfo(cArea);
                    //System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }

            if(lastUpdate!="")
            {
                db.updateSystemInfo("area_info",lastUpdate);
            }

            // System.out.println("areaInfoFlag "+ globalVariable.areaInfoFlag);
            //globalVariable.areaInfoFlag=false;
            // FragmentSettings.updatingDataFlag = false;

            // System.out.println("areaInfoFlag "+ FragmentSettings.areaInfoFlag);

            db.updateSystemInfoUpdating(MyDBHandler.COLUMN_areainfoUpadting, 0);
            SplashActivity.areaInfoComplete=true;
        }
        else if(designation)
        {
            SplashActivity.designationComplete=false;
            Designation designation = new Designation();
            String lastUpdate="";
            try {
                JSONArray arrJsonArray = new JSONArray(s);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                        designation.setDesign_id(Integer.parseInt(jsonObject.optString("desig_id").toString()));
                        designation.setDesign_en(jsonObject.optString("desig_en").toString());
                        designation.setDesign_bn(jsonObject.optString("desig_bn").toString());
                        designation.setDesign_rank(jsonObject.optString("desig_grade_en").toString());
                        designation.setLast_update(jsonObject.optString("create_date").toString());
                        designation.setActive_stats(Integer.parseInt(jsonObject.optString("status").toString()));
                        lastUpdate = jsonObject.optString("create_date").toString();
                        db.insertOrReplaceDesignation(designation);
                    }catch (Exception e){}
                    //System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }

            if(lastUpdate!="")
            {
                //System.out.println("LU:"+lastUpdate);
                db.updateSystemInfo("designation_info", lastUpdate);

            }
            db.updateSystemInfoUpdating(MyDBHandler.COLUMN_designUpdating,0);
            // globalVariable.designationFlag=false;
            SplashActivity.designationComplete=true;
        }
        else
        {
            delegate.processFinish(s);
        }
    }
    public static  Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream stream = null;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        try {
            stream = getHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
            stream.close();
        }
        catch (IOException e1) {
            e1.printStackTrace();
            //System.out.println("downloadImage"+ e1.toString());
        }
        return bitmap;
    }

    // Makes HttpURLConnection and returns InputStream

    public static  InputStream getHttpConnection(String urlString)  throws IOException {

        InputStream stream = null;
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();

        try {
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = httpConnection.getInputStream();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println("downloadImage" + ex.toString());
        }
        return stream;
    }


    public void createNotification(Context context,String msg,String msgText,String msgAlert,int notID)
    {
        Intent i = new Intent(context, MainActivity.class);
        System.out.println("notice id from pending: " +noticeID);
        if(notID==1)
        {
            i.putExtra("page_name", "singlenotice");
            i.putExtra("noticeID", noticeID);

        }
        else
        {
            i.putExtra("page_name", "notice");
        }
        PendingIntent notifiIntent = PendingIntent.getActivity(context, 0,
                i, PendingIntent.FLAG_CANCEL_CURRENT);

        try {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.dof)
                    .setContentTitle(msg)
                    .setTicker(msgAlert)
                    .setContentText(msgText);
            mBuilder.setContentIntent(notifiIntent);
            mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            mBuilder.setAutoCancel(true);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String englishToBanglaNumber(String number)
    {
        number = number.replace('1','১');
        number = number.replace('2','২');
        number = number.replace('3','৩');
        number = number.replace('4','৪');
        number = number.replace('5','৫');
        number = number.replace('6','৬');
        number = number.replace('7','৭');
        number = number.replace('8','৮');
        number = number.replace('9','৯');
        number = number.replace('0','০');
        return number;
    }
}

