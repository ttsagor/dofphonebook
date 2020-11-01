package com.Pisystems.dofphonebook.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UpdateData extends Service {
    public UpdateData() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        {
            MyDBHandler db1 = new MyDBHandler(getApplicationContext(),null,null,1);
            MyDBHandler db2 = new MyDBHandler(getApplicationContext(),null,null,1);
            MyDBHandler db3 = new MyDBHandler(getApplicationContext(),null,null,1);
            MyDBHandler db4 = new MyDBHandler(getApplicationContext(),null,null,1);

            final SystemInfo cSystemInfo = db1.getSystemInfo();
            final JSON userInfo =new JSON();


            //user Ifo
            userInfo.userInfo=true;
            userInfo.db=db1;
            userInfo.mContex=getApplicationContext();
            //System.out.println("are user" + cSystemInfo.getUserLastUpdate());
            String date="";
            String lastUpdate="";

            if(cSystemInfo.getUserLastUpdate()==null)
            {
                lastUpdate="2010-04-01 23:00:00";
            }
            else
            {
                lastUpdate=cSystemInfo.getUserLastUpdate();
            }
            try {
                date= URLEncoder.encode(lastUpdate, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("http://aihub.com.bd/phonebook/api/get_contact_list" + "?last_update=" + date);
            userInfo.execute("http://aihub.com.bd/phonebook/api/get_contact_list" + "?last_update=" + date);


            ///--area code
            final JSON areaCode =new JSON();
            areaCode.areaCode=true;
            areaCode.db=db2;
            areaCode.mContex=getApplicationContext();
           // System.out.println("are code"+cSystemInfo.getAreaCodeLastUpdate());
            date="";
            lastUpdate="";

            if(cSystemInfo.getAreaCodeLastUpdate()==null)
            {
                lastUpdate="2010-04-01 23:00:00";
            }
            else
            {
                lastUpdate=cSystemInfo.getAreaCodeLastUpdate();
            }
            try {
                date= URLEncoder.encode(lastUpdate, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            areaCode.execute("http://aihub.com.bd/phonebook/api/get_areacode" + "?last_update=" + date);


            //area info
            final JSON areaInfo =new JSON();
            areaInfo.areaInfo=true;
            areaInfo.db=db3;
            areaInfo.mContex=getApplicationContext();
            //System.out.println("are info"+cSystemInfo.getAreaInfoLastUpdate());
            date="";
            lastUpdate="";

            if(cSystemInfo.getAreaInfoLastUpdate()==null)
            {
                lastUpdate="2010-04-01 23:00:00";
            }
            else
            {
                lastUpdate=cSystemInfo.getAreaInfoLastUpdate();
            }
            try {
                date= URLEncoder.encode(lastUpdate, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            areaInfo.execute("http://aihub.com.bd/phonebook/api/get_areainfo" + "?last_update=" + date);

            //designation
            final JSON designation =new JSON();
            designation.designation=true;
            designation.db=db4;
            designation.mContex=getApplicationContext();
            //System.out.println("are info"+cSystemInfo.getDesignationLastUpdate());
            date="";
            lastUpdate="";

            if(cSystemInfo.getDesignationLastUpdate()==null)
            {
                lastUpdate="2010-04-01 23:00:00";
            }
            else
            {
                lastUpdate=cSystemInfo.getDesignationLastUpdate();
            }
            try {
                date= URLEncoder.encode(lastUpdate, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            designation.execute("http://aihub.com.bd/phonebook/api/get_designation" + "?last_update=" + date);
        }
        return START_NOT_STICKY ;
    }
}
