package com.Pisystems.dofphonebook.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.Pisystems.dofphonebook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by sagor on 4/26/2016.
 */
public class JsonController implements AsyncResponse {


    MyDBHandler db;
    final JSON asyncTask =new JSON();
    String serviceName="";
    String userInfoURL="http://service.digital-phonebook.com/userService.php";
    String areaCodeURL="http://service.digital-phonebook.com/areaCodeService.php";
    String areaInfoURL="http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt";
    String noticeURL="http://service.digital-phonebook.com/noticeService.php";
    Boolean userInfoRunning=false;
    Boolean areaCodeRunning=false;
    Boolean areaInfoRunning=false;
    Boolean noticeRunning=false;
    Bitmap img=null;
    String query="";
    String result="";
    public JsonController(String serviceName,String date,MyDBHandler db) {

        asyncTask.delegate = this;
        this.db=db;
        this.serviceName = serviceName;
        try {
            query= URLEncoder.encode(date,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void runService(String serviceName,String date,MyDBHandler db)
    {
        asyncTask.delegate = this;
        this.db=db;
        this.serviceName = serviceName;
        try {
            query= URLEncoder.encode(date,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(serviceName == "userinfo")
        {
            userInfoRunning=true;
            asyncTask.execute(userInfoURL + "?date=" + query);

        }
        else if(serviceName == "areacode")
        {
            areaCodeRunning=true;
            asyncTask.execute(areaCodeURL + "?date=" + query);
        }
        else if(serviceName == "areainfo")
        {
            areaInfoRunning=true;
            asyncTask.execute(areaInfoURL + "?date=" + query);
        }
        else if(serviceName == "notice")
        {
            noticeRunning=true;
            asyncTask.execute(noticeURL + "?date=" + query);
        }
    }

    @Override
    public void processFinish(String output) {
        result = output;
        if(serviceName == "userinfo")
        {
            User user = new User();
            try {
                JSONArray arrJsonArray = new JSONArray(output);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                    user.setUser_id(Integer.parseInt(jsonObject.optString("user_id").toString()));
                    user.setUser_name(jsonObject.optString("user_name_bng").toString());
                    user.setUser_name_eng(jsonObject.optString("user_name_eng").toString());
                    user.setDesignation(jsonObject.optString("designation").toString());
                    user.setArea_id(Integer.parseInt(jsonObject.optString("area_id").toString()));
                    user.setPhone1(jsonObject.optString("phone1").toString());
                    user.setPhone2(jsonObject.optString("phone2").toString());
                    user.setOffice_num(jsonObject.optString("lan").toString());
                    user.setEmail(jsonObject.optString("email").toString());
                    user.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                    user.setLast_update(jsonObject.optString("last_update").toString());
                    db.insertOrReplaceUSER(user);
                   /// System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }
           // db.updateSystemInfo("user_info");
            userInfoRunning=false;
        }
        else if(serviceName == "areacode")
        {
            System.out.println(output);
            areaCode cArea = new areaCode();
            try {
                JSONArray arrJsonArray = new JSONArray(output);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    JSONObject jsonObject = arrJsonArray.getJSONObject(i);
                    cArea.setAreaID(Integer.parseInt(jsonObject.optString("area_id").toString()));
                    cArea.setParentID(Integer.parseInt(jsonObject.optString("parent_id").toString()));
                    cArea.setAreaName(jsonObject.optString("area_name_bng").toString());
                    cArea.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                    cArea.setLast_update(jsonObject.optString("last_update").toString());
                    db.insertOrReplaceAreaCode(cArea);
                     //System.out.println("Node" + i + " : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
                }
            }
            catch (Exception e) {
            }
            //db.updateSystemInfo("area_code");
            areaCodeRunning=false;
        }
        else if(serviceName == "areainfo")
        {
            System.out.println("areainfo");
            areaInfoRunning=false;
        }
        else if(serviceName == "notice")
        {
            System.out.println("AT NOtice " + output);
            Notice notice = new Notice();
            NoticeIMG noticeIMG=new NoticeIMG();
            try {
                JSONArray arrJsonArray = new JSONArray(output);
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    JSONObject jsonObject = arrJsonArray.getJSONObject(i);

                    notice.setNotice_id(Integer.parseInt(jsonObject.optString("notice_id").toString()));
                    notice.setNotice_title(jsonObject.optString("notice_title").toString());
                    notice.setNotice_contains(jsonObject.optString("notice_contains").toString());
                    notice.setActive_stat(Integer.parseInt(jsonObject.optString("active_stat").toString()));
                    notice.setNotice_date_time(jsonObject.optString("post_date").toString());
                    notice.setRef_number(jsonObject.optString("ref_number").toString());
                    notice.setPost_date(jsonObject.optString("last_update").toString());


                    noticeIMG.setNotice_id(Integer.parseInt(jsonObject.optString("notice_id").toString()));

                    //System.out.println("PATH : " + jsonObject.optString("image_path").toString());


                   /* URL url = new URL(jsonObject.optString("image_path").toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();*/

                   /* final String Url = jsonObject.optString("image_path").toString();

                    Thread thread = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            try {
                                img = downloadImage(Url);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                    thread.join();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap =img;
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] photo = baos.toByteArray();
                    noticeIMG.setNotice_img(photo);
                    System.out.println("path : " + jsonObject.optString("image_path").toString()+"Node" + i + " : id= " + notice.getNotice_id() + "  Name= " + notice.getNotice_title() + "  Salary= " + notice.getNotice_contains() + " ");
*/
                    db.insertOrReplaceNotice(notice);
                   // break;

                }
            }
            catch (Exception e) {
            }
           // db.updateSystemInfo("notice");
            noticeRunning=false;
        }
        else
        {
            System.out.println("Done");
        }

    }



    public byte[] downloadImage22(String imgURL) throws Exception{

        URL url = new URL(imgURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setReadTimeout(10000);
        con.setConnectTimeout(10000);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            Bitmap b = BitmapFactory.decodeStream(con.getInputStream());
            b.compress(Bitmap.CompressFormat.JPEG,100,bos);
        } finally {
            con.disconnect();
        }

        return bos.toByteArray();
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
            System.out.println("downloadImage"+ e1.toString());
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
            System.out.println("downloadImage" + ex.toString());
        }
        return stream;
    }
}
