package com.Pisystems.dofphonebook.activity;

/**
 * Created by sagor on 3/14/2016.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="dofPhonebook.db";
    public static final String TABLE_user = "user_info";
    public static final String TABLE_areaCode = "area_code";
    public static final String TABLE_favourite = "favourite";
    public static final String TABLE_areaInfo = "area_info";
    public static final String TABLE_notice = "notice";
    public static final String TABLE_systemInfo = "system_info";
    public static final String TABLE_noticeIMG="notice_img";
    public static final String TABLE_designation="designation_info";

    public static final String COLUMN_userInfoUpdating = "userinfo_updating";
    public static final String COLUMN_areacodeUpadting = "areacode_updating";
    public static final String COLUMN_areainfoUpadting = "areainfo_updating";
    public static final String COLUMN_designUpdating = "design_updating";

    public static final String COLUMN_designID = "design_id";
    public static final String COLUMN_designEN = "design_en";
    public static final String COLUMN_designBN = "design_bn";
    public static final String COLUMN_designRank = "design_rank";

    public static final String COLUMN_dataLoaded = "data_loaded";



    public static final String COLUMN_userID = "user_id";
    public static final String COLUMN_userName = "user_name";
    public static final String COLUMN_userDesignation = "designation";
    public static final String COLUMN_userPhone1 = "phone1";
    public static final String COLUMN_userPhone2 = "phone2";
    public static final String COLUMN_userOffice_num = "office_num";
    public static final String COLUMN_userEmail = "email";
    public static final String COLUMN_active_stat = "active_stat";
    public static final String COLUMN_Last_update = "last_update";
    public static final String COLUMN_userNameENG = "user_name_eng";

    public static final String COLUMN_areaID = "area_id";
    public static final String COLUMN_areaParentID = "parent_id";
    public static final String COLUMN_areaName = "area_name";
    public static final String COLUMN_areaNameEng = "area_name_eng";

    public static final String COLUMN_favouriteID = "favourite_id";

    public static final String COLUMN_area_detail = "area_details";
    public static final String COLUMN_area_location = "area_location";
    public static final String COLUMN_area_address = "area_address";

    public static final String COLUMN_noticeID = "notice_id";
    public static final String COLUMN_noticeTitle = "notice_title";
    public static final String COLUMN_noticeContains = "notice_contains";
    public static final String COLUMN_noticeURL = "notice_url";

    public static final String COLUMN_noticeImage = "notice_img";
    public static final String COLUMN_noticeDateTime = "notice_date_time";
    public static final String COLUMN_notice_seenStatus="notice_seen_status";
    public static final String COLUMN_refNumber = "ref_number";
    public static final String COLUMN_postDate = "last_update";
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_designation+" ( "+
                COLUMN_designID + " INTEGER PRIMARY KEY,"+
                COLUMN_designEN + " TEXT, "+
                COLUMN_designBN + " TEXT, "+
                COLUMN_designRank + " TEXT, "+
                COLUMN_active_stat + " INTEGER, "+
                COLUMN_Last_update + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        query = "CREATE TABLE " + TABLE_user+" ( "+
                COLUMN_userID + " INTEGER PRIMARY KEY, "+
                COLUMN_userName + " TEXT, "+
                COLUMN_userDesignation + " TEXT, "+
                COLUMN_areaID + " INTEGER, "+
                COLUMN_userPhone1 + " TEXT, "+
                COLUMN_userPhone2 + " TEXT, "+
                COLUMN_userOffice_num + " TEXT, "+
                COLUMN_userEmail + " TEXT, "+
                COLUMN_userNameENG+" TEXT,"+
                COLUMN_active_stat + " INTEGER, "+
                COLUMN_Last_update + " TEXT); ";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        query = "CREATE TABLE " + TABLE_areaCode+" ( "+
                COLUMN_areaID + " INTEGER PRIMARY KEY,"+
                COLUMN_areaParentID + " INTEGER, "+
                COLUMN_areaName + " TEXT, "+
                COLUMN_active_stat + " INTEGER, "+
                COLUMN_Last_update + " TEXT, "+
                COLUMN_areaNameEng + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        query = "CREATE TABLE " + TABLE_favourite+" ( "+
                COLUMN_favouriteID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_userID + " INTEGER UNIQUE);";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        query = "CREATE TABLE " + TABLE_areaInfo+" ( "+
                COLUMN_areaID + " INTEGER PRIMARY KEY, "+
                COLUMN_area_detail + " TEXT, "+
                COLUMN_area_location + " TEXT, "+
                COLUMN_active_stat + " INTEGER, "+
                COLUMN_Last_update + " TEXT, "+
                COLUMN_area_address + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        query = "CREATE TABLE " + TABLE_notice+" ( "+
                COLUMN_noticeID + " INTEGER PRIMARY KEY, "+
                COLUMN_noticeTitle + " TEXT, "+
                COLUMN_noticeContains + " TEXT, "+
                COLUMN_active_stat + " INTEGER, "+
                COLUMN_noticeDateTime + " TEXT," +
                COLUMN_notice_seenStatus + " INTEGER," +
                COLUMN_refNumber+" TEXT,"+
                COLUMN_postDate+" TEXT,"+
                COLUMN_noticeURL+" TEXT);";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        query = "CREATE TABLE " + TABLE_noticeIMG+" ( "+
                COLUMN_noticeID + " INTEGER PRIMARY KEY, "+
                COLUMN_noticeImage + " BLOB);";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        query = "CREATE TABLE " +  TABLE_systemInfo+" ( "+
                COLUMN_dataLoaded + " INTEGER PRIMARY KEY, "+
                COLUMN_userID + " TEXT, "+
                TABLE_user + " TEXT, "+
                TABLE_areaCode + " TEXT, "+
                TABLE_areaInfo + " TEXT, "+
                TABLE_notice + " TEXT, "+
                COLUMN_Last_update + " TEXT, "+
                TABLE_designation + " TEXT, "+
                COLUMN_userInfoUpdating + " INTEGER, "+
                COLUMN_areacodeUpadting + " INTEGER, "+
                COLUMN_areainfoUpadting + " INTEGER, "+
                COLUMN_designUpdating + " INTEGER );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
       //db.close() ;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_user);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_areaCode);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_areaInfo);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_favourite);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_notice);
        onCreate(db);
        //db.close();
    }

    public void insertDesignation(Designation designation)
    {
        try {
            ContentValues insertValues = new ContentValues();
            insertValues.put(COLUMN_designID, designation.getDesign_id());
            insertValues.put(COLUMN_designEN, designation.getDesign_en());
            insertValues.put(COLUMN_designBN, designation.getDesign_bn());
            insertValues.put(COLUMN_designRank, designation.getDesign_rank());
            insertValues.put(COLUMN_active_stat, designation.getActive_stats());
            insertValues.put(COLUMN_Last_update, designation.getLast_update());

            SQLiteDatabase db = getWritableDatabase();
            //db.isOpen()
            try {
                db.insert(TABLE_designation, null, insertValues);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertUser(User user)
    {
        try {
            ContentValues insertValues = new ContentValues();
            insertValues.put("user_id", user.getUser_id());
            insertValues.put("user_name", user.getUser_name());
            insertValues.put("designation", user.getDesignation());
            insertValues.put("area_id", user.getArea_id());
            insertValues.put("phone1", user.getPhone1());
            insertValues.put("phone2", user.getPhone2());
            insertValues.put("office_num", user.getOffice_num());
            insertValues.put("email", user.getEmail());
            insertValues.put("active_stat", user.getActive_stat());
            insertValues.put("last_update", user.getLast_update());
            insertValues.put("user_name_eng", user.getUser_name_eng());
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insert(TABLE_user, null, insertValues);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertArea(areaCode area)
    {
        try {
        ContentValues insertValues = new ContentValues();
        insertValues.put("area_id", area.getAreaID());
        insertValues.put("parent_id", area.getParentID());
        insertValues.put("area_name", area.getAreaName());
        insertValues.put("active_stat", area.getActive_stat());
        insertValues.put("last_update", area.getLast_update());
        insertValues.put(COLUMN_areaNameEng, area.getAreaNameEng());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_areaCode, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        }catch (Exception e){}
        //db.close();
    }

    public void insertSystem(SystemInfo cSystemInfo)
    {
        try{
        ContentValues insertValues = new ContentValues();
        insertValues.put("data_loaded", cSystemInfo.getDataLoaded());
        insertValues.put("user_id", cSystemInfo.getUserID());
        insertValues.put(TABLE_user, cSystemInfo.getUserLastUpdate());
        insertValues.put(TABLE_areaCode, cSystemInfo.getAreaCodeLastUpdate());
        insertValues.put(TABLE_areaInfo, cSystemInfo.getAreaInfoLastUpdate() );
        insertValues.put(TABLE_notice, cSystemInfo.getNoticeLastUpdate());
        insertValues.put(TABLE_designation, cSystemInfo.getDesignationLastUpdate());

        insertValues.put(COLUMN_userInfoUpdating, cSystemInfo.getUserinfoUpdating());
        insertValues.put(COLUMN_areacodeUpadting, cSystemInfo.getAreaCodeLastUpdate());
        insertValues.put(COLUMN_areainfoUpadting, cSystemInfo.getAreaInfoLastUpdate());
        insertValues.put(COLUMN_designUpdating, cSystemInfo.getDesignUpdating());

        insertValues.put("last_update", cSystemInfo.getLastUpdate());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_systemInfo, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        }catch (Exception e){}
        //db.close();
    }

    public void insertAreaInfo(area_info area)
    {
        try{
            ContentValues insertValues = new ContentValues();
            insertValues.put("area_id", area.getArea_id());
            insertValues.put("area_details", area.getArea_details());
            insertValues.put("area_location", area.getArea_location());
            insertValues.put("active_stat", area.getActive_stat());
            insertValues.put("last_update", area.getLast_update());
            insertValues.put(COLUMN_area_address, area.getAddress());

            SQLiteDatabase db = getWritableDatabase();
            try
            {
                db.insert(TABLE_areaInfo, null, insertValues);

            }
            catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertFavourite(Favourite favourite)
    {
        try {
            ContentValues insertValues = new ContentValues();
            insertValues.put("user_id", favourite.getUser_id());
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.insert(TABLE_favourite, null, insertValues);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }

    public boolean removeFavourite(int user_id)
    {
        try {
            String query = "DELETE FROM " + TABLE_favourite + " WHERE " + COLUMN_userID + "=" + user_id;
            SQLiteDatabase db = getWritableDatabase();
            try {
                return db.delete(TABLE_favourite, COLUMN_userID + "=" + String.valueOf(user_id), null) > 0;
            } catch (Exception e) {
                System.out.println("ERROR" + e.toString());
            }
        }catch (Exception e){}
        //db.close();
        return false;
    }

    public List<areaCode> getAreaCode(int nparentID)
    {
        List<areaCode> areaList = new ArrayList<areaCode>();
      try {
          SQLiteDatabase db = this.getWritableDatabase();
          String query = "SELECT * FROM " + TABLE_areaCode + " WHERE parent_id=" + nparentID + " AND " + COLUMN_active_stat + "=1";
          Cursor c = null;
          try {
              c = db.rawQuery(query, null);

          } catch (Exception e) {
              Log.e("ERROR", e.toString());
          }

          c.moveToFirst();

          do {
              areaCode cArea = new areaCode();
              cArea.setAreaID(c.getInt(0));
              cArea.setAreaName(c.getString(c.getColumnIndex("area_name")));
              cArea.setParentID(c.getColumnIndex("parent_id"));
              cArea.setActive_stat(c.getColumnIndex("active_stat"));
              cArea.setLast_update(c.getString(c.getColumnIndex("last_update")));
              areaList.add(cArea);
          } while (c.moveToNext());
      }catch (Exception e){}
        //db.close();
      return  areaList;
    }

    public ArrayList<Integer> getAreaCodeRec(int nparentID)
    {
        ArrayList<Integer> area = new ArrayList<Integer>();
        try {
            int count = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaCode + " WHERE parent_id=" + nparentID + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            //db.close();
            c.moveToFirst();
            if (c.getCount() == 0) {
                // System.out.println(nparentID+ " E:");
                area.add(nparentID);
                //db.close();
                return area;
            } else {
                List<areaCode> areaList = new ArrayList<areaCode>();
                do {
                    areaCode cArea = new areaCode();
                    cArea.setAreaID(c.getInt(0));
                    cArea.setAreaName(c.getString(c.getColumnIndex("area_name")));
                    cArea.setParentID(c.getColumnIndex("parent_id"));
                    cArea.setActive_stat(c.getColumnIndex("active_stat"));
                    cArea.setLast_update(c.getString(c.getColumnIndex("last_update")));
                    areaList.add(cArea);
                    area.add(cArea.getAreaID());

                    // System.out.println(cArea.getAreaID()+ " F:" +count);
                    // count+=numberOfRowsUser(cArea.getAreaID());
                    area.addAll(getAreaCodeRec(cArea.getAreaID()));
                } while (c.moveToNext());
                //db.close();
                return area;
            }
        }catch (Exception e){}
        return area;
    }

    public List<areaCode> getAreaCodeParent(int nparentID)
    {
        List<areaCode> areaList = new ArrayList<areaCode>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaCode + " WHERE area_id=" + nparentID + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                areaCode cArea = new areaCode();
                cArea.setAreaID(c.getInt(0));
                cArea.setAreaName(c.getString(c.getColumnIndex("area_name")));
                cArea.setParentID(c.getColumnIndex("parent_id"));
                cArea.setActive_stat(c.getColumnIndex("active_stat"));
                cArea.setLast_update(c.getString(c.getColumnIndex("last_update")));
                areaList.add(cArea);
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  areaList;
    }

    public int numberOfRows(int areaCode){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaCode + " WHERE parent_id=" + areaCode + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            //db.close();
            return c.getCount();
        }catch (Exception e){}
        return 0;
    }

    public int numberOfRowsUser(int areaCode){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_areaID + "=" + areaCode + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            //db.close();
            c.moveToFirst();
            if (c == null) {
                //db.close();
                return 0;
            }
        //db.close();
            return c.getCount();
        }catch (Exception e){}
        return 0;
    }



    public List<User> getAreaUser(int area_id)
    {
        List<User> userList=new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_areaID + "=" + area_id + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            String resultString = "";
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser);
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  userList;
    }


    public List<User> getDegUser(String deg)
    {
        List<User> userList = new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userDesignation + "='" + deg + "' AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            String resultString = "";
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser);
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  userList;
    }


    public User getUserByPhone1(String phone)
    {
        User cUser = new User();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userPhone1 + "='" + phone + "' AND " + COLUMN_userPhone1 + "!='' AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    cUser.setUser_id(c.getInt(0));
                    cUser.setUser_name(c.getString(1));
                    cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                    cUser.setArea_id(c.getInt(3));
                    cUser.setPhone1(c.getString(4));
                    cUser.setPhone2(c.getString(5));
                    cUser.setOffice_num(c.getString(6));
                    cUser.setEmail(c.getString(7));
                    cUser.setUser_name_eng(c.getString(8));
                    return cUser;
                } while (c.moveToNext());
            }
        }catch (Exception e){}
        //db.close();
        return  cUser;
    }

    public User getUserByPhone2(String phone)
    {
        User cUser = new User();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userPhone2 + "='" + phone + "' AND " + COLUMN_userPhone2 + "!='' AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    cUser.setUser_id(c.getInt(0));
                    cUser.setUser_name(c.getString(1));
                    cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                    cUser.setArea_id(c.getInt(3));
                    cUser.setPhone1(c.getString(4));
                    cUser.setPhone2(c.getString(5));
                    cUser.setOffice_num(c.getString(6));
                    cUser.setEmail(c.getString(7));
                    cUser.setUser_name_eng(c.getString(8));
                    return cUser;
                } while (c.moveToNext());
            }
        }catch (Exception e){}
        //db.close();
        return  cUser;
    }


    public List<String> getUserNumberByDes(String designation)
    {
        List<String> userList=new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userDesignation + "='" + designation + "' AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser.getPhone1());
                //System.out.println(cUser.getPhone1());
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  userList;
    }



    public List<String> getUserNumberByArea(ArrayList<Integer> area)
    {

        List<String> number=new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String condition = "";
            for (int i = 0; i < area.size(); i++) {
                if (i + 1 == area.size()) {
                    condition += " " + COLUMN_areaID + "=" + area.get(i) + " ";
                } else {
                    condition += " " + COLUMN_areaID + "=" + area.get(i) + " OR ";
                }
            }

            String query = "SELECT * FROM " + TABLE_user + " WHERE ( " + condition + " ) AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();

                List<String> userList = new ArrayList<String>();
                String resultString = "";
                do {
                    User cUser = new User();
                    cUser.setUser_id(c.getInt(0));
                    cUser.setUser_name(c.getString(1));
                    cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                    cUser.setArea_id(c.getInt(3));
                    cUser.setPhone1(c.getString(4));
                    cUser.setPhone2(c.getString(5));
                    cUser.setOffice_num(c.getString(6));
                    cUser.setEmail(c.getString(7));
                    cUser.setUser_name_eng(c.getString(8));
                    userList.add(cUser.getPhone1());
                    //System.out.println(cUser.getPhone1());
                } while (c.moveToNext());
                number.addAll(userList);
            }
        }catch (Exception e){}
        //db.close();
        return  number;
    }

    public List<String> getUserEmailByDes(String designation)
    {
        List<String> userList=new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userDesignation + "='" + designation + "' AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            String resultString = "";
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(c.getString(7));
                //System.out.println(cUser.getPhone1());
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  userList;
    }


    public List<String> getUserEmailByArea(ArrayList<Integer> area)
    {
        List<String> email=new ArrayList<String>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String condition = "";

            for (int i = 0; i < area.size(); i++) {
                if (i + 1 == area.size()) {
                    condition += " " + COLUMN_areaID + "=" + area.get(i) + " ";
                } else {
                    condition += " " + COLUMN_areaID + "=" + area.get(i) + " OR ";
                }
            }


            String query = "SELECT * FROM " + TABLE_user + " WHERE (" + condition + ") AND " + COLUMN_active_stat + "=1";

            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            if (c.getCount() > 0) {
                c.moveToFirst();
                //System.out.println(query);
                List<String> userList = new ArrayList<String>();
                do {
                    User cUser = new User();
                    cUser.setUser_id(c.getInt(0));
                    cUser.setUser_name(c.getString(1));
                    cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                    cUser.setArea_id(c.getInt(3));
                    cUser.setPhone1(c.getString(4));
                    cUser.setPhone2(c.getString(5));
                    cUser.setOffice_num(c.getString(6));
                    cUser.setEmail(c.getString(7));
                    cUser.setUser_name_eng(c.getString(8));
                    userList.add(cUser.getEmail());
                    //System.out.println(cUser.getPhone1());
                } while (c.moveToNext());
                email.addAll(userList);
            }
        }catch (Exception e){}
        //db.close();
        return  email;
    }


    public String getUserEmail(int user_id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userID + "=" + user_id + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();

            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                //db.close();
                return cUser.getEmail();
            } while (c.moveToNext());
        }catch (Exception e){}
        return  "";
    }


    public String getUsernumber(int user_id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userID + "=" + user_id + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();

            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                //db.close();
                return cUser.getPhone1();
            } while (c.moveToNext());
        }catch (Exception e){}
        return "";
    }

    public List<User> getFavouriteUser()
    {
        List<User> userList = new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + TABLE_user + ".* FROM " + TABLE_user + " INNER JOIN " + TABLE_favourite + "  ON " + TABLE_user + "." + COLUMN_userID + "=" + TABLE_favourite + ". " + COLUMN_userID;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser);
            } while (c.moveToNext());
            //db.close();
        }catch (Exception e){}
        return  userList;
    }

    public List<User> getSearchUser(String cond)
    {
        List<User> userList=new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_active_stat + " = 1 AND " + COLUMN_userNameENG + " LIKE '%" + cond + "%' OR " + COLUMN_userPhone1 + " LIKE '%" + cond + "%' OR " + COLUMN_userPhone2 + " LIKE '%" + cond + "%' OR " + COLUMN_userOffice_num + " LIKE '%" + cond + "%' OR " + COLUMN_userEmail + " LIKE '%" + cond + "%'";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    User cUser = new User();
                    cUser.setUser_id(c.getInt(0));
                    cUser.setUser_name(c.getString(1));
                    cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                    cUser.setArea_id(c.getInt(3));
                    cUser.setPhone1(c.getString(4));
                    cUser.setPhone2(c.getString(5));
                    cUser.setOffice_num(c.getString(6));
                    cUser.setEmail(c.getString(7));
                    cUser.setUser_name_eng(c.getString(8));
                    userList.add(cUser);
                } while (c.moveToNext());
            }
        }catch (Exception e){}
        //db.close();
        return  userList;
    }

    public HashMap<String, Integer> getDesignation()
    {
        HashMap<String, Integer> meMap=new HashMap<String, Integer>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + COLUMN_userDesignation + ",count(*) AS total FROM " + TABLE_user + " WHERE " + COLUMN_active_stat + "=1 " + " GROUP BY " + COLUMN_userDesignation;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();

            do {
                meMap.put(c.getString(0), c.getInt(1));
            } while (c.moveToNext());
        }catch (Exception e)
        {

        }
        //db.close();
        return  meMap;
    }

    public String getAreaName(int areaCode){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT area_name FROM " + TABLE_areaCode + " WHERE " + COLUMN_areaID + "=" + areaCode + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            //db.close();
            return c.getString(0);
        }catch (Exception e){}
        return "";
    }

    public List<User> getAreaUserByID(int user_id)
    {
        List<User> userList=new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_userID + "=" + user_id + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignation(getDesignationBN(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser);
            } while (c.moveToNext());
        }catch (Exception e){}
        //db.close();
        return  userList;
    }
    public int numberOfRowsFavourite(int userID){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_favourite + " WHERE " + COLUMN_userID + "=" + userID;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            //db.close();
            return c.getCount();
        }catch (Exception e){}
        return 0;
    }

    /*public int numberOfRowsUserArea(int area_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_user + " WHERE "+COLUMN_areaID+"="+area_id;
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        c.moveToFirst();
        //db.close();
        return c.getCount();
    }*/

    public int numberOfRowsFavouriteall(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_favourite;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            //db.close();
            return c.getCount();
        }catch (Exception e){}
        return 0;
    }

    public void insertNotice(Notice notice)
    {
        try {
            ContentValues insertValues = new ContentValues();
            insertValues.put("notice_id", notice.getNotice_id());
            insertValues.put("notice_title", notice.getNotice_title());
            insertValues.put("notice_contains", notice.getNotice_contains());
            insertValues.put("active_stat", notice.getActive_stat());
            insertValues.put("notice_date_time", notice.getNotice_date_time());
            insertValues.put("notice_seen_status", notice.getSeen_stat());
            insertValues.put(COLUMN_refNumber, notice.getRef_number());
            insertValues.put(COLUMN_postDate, notice.getPost_date());
            SQLiteDatabase db = getWritableDatabase();

            try {
                db.insert(TABLE_notice, null, insertValues);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){

        }
        //db.close();
    }

    public void insertNoticeIMG(NoticeIMG notice)
    {
        try {
            ContentValues insertValues = new ContentValues();

            insertValues.put(COLUMN_noticeID, notice.getNotice_id());
            insertValues.put(COLUMN_noticeImage, notice.getNotice_img());
            SQLiteDatabase db = getWritableDatabase();

            try {
                db.insert(TABLE_noticeIMG, null, insertValues);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }

    public List<Notice> getAllNotice()
    {
        List<Notice> allNotice = new ArrayList<Notice>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + COLUMN_noticeID + "," + COLUMN_noticeTitle + "," + COLUMN_noticeDateTime + "," + COLUMN_notice_seenStatus + "," + COLUMN_refNumber + "," + COLUMN_postDate + "," + COLUMN_noticeURL + " FROM " + TABLE_notice + " WHERE " + COLUMN_active_stat + "=1 ORDER BY " + COLUMN_postDate + " desc";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            System.out.println("dbgt:" + c.getCount());
            if (c.getCount() > 0) {

                c.moveToFirst();
                do {
                    Notice cNotice = new Notice();
                    cNotice.setNotice_id(c.getInt(0));
                    cNotice.setNotice_title(c.getString(1));
                    cNotice.setNotice_date_time(c.getString(2));
                    cNotice.setSeen_stat(c.getInt(3));
                    cNotice.setRef_number(c.getString(4));
                    cNotice.setPost_date(c.getString(5));
                    cNotice.setNotice_url(c.getString(6));
                    allNotice.add(cNotice);
                } while (c.moveToNext());
            }
            //db.close();
        }catch (Exception e){}
        return  allNotice;
    }

    public Notice geNotice(int noticeID)
    {
        Notice cNotice = new Notice();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_notice + " WHERE " + COLUMN_noticeID + "=" + noticeID;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    cNotice.setNotice_id(c.getInt(0));
                    cNotice.setNotice_title(c.getString(1));
                    cNotice.setNotice_contains(c.getString(2));
                    cNotice.setActive_stat(c.getInt(3));
                    cNotice.setNotice_date_time(c.getString(4));
                    cNotice.setSeen_stat(c.getInt(5));
                    cNotice.setRef_number(c.getString(6));
                    cNotice.setPost_date(c.getString(7));
                    cNotice.setNotice_url(c.getString(8));
                } while (c.moveToNext());
                //db.close();
            }
        }catch (Exception e){}
        return cNotice;
    }



   /* public NoticeIMG getNoticeIMG(int noticeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_noticeIMG + " WHERE " + COLUMN_noticeID + "=" + noticeID;
        Cursor c = null;
        try {
            c = db.rawQuery(query, null);

        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        NoticeIMG cNotice = new NoticeIMG();

        if (c.getCount() > 0) {
            c.moveToFirst();


            do {

                cNotice.setNotice_id(c.getInt(0));
                cNotice.setNotice_img(c.getBlob(1));
            } while (c.moveToNext());
            //db.close();
            return cNotice;
        }
       //db.close();
        return cNotice;
    }*/

    public void updateNoticeSeen(int noticeID)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues newValues = new ContentValues();
            newValues.put(COLUMN_notice_seenStatus, 1);
            try {
                db.update(TABLE_notice, newValues, COLUMN_noticeID + "=" + noticeID, null);

            } catch (Exception e) {
                System.out.println("ERROR " + e.toString());
            }
        }catch (Exception e){}
       //db.close();
    }

    public String getAreaLocation(int area_id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaInfo + " WHERE " + COLUMN_areaID + "=" + area_id + " AND " + COLUMN_active_stat + "=1";

            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();

            do {
                area_info cArea = new area_info();
                cArea.setArea_id(c.getInt(0));
                cArea.setArea_details(c.getString(1));
                cArea.setArea_location(c.getString(2));
                cArea.setActive_stat(c.getInt(3));
                cArea.setLast_update(c.getString(4));
                cArea.setAddress(c.getString(5));
                //db.close();
                return cArea.getArea_location();
            } while (c.moveToNext());
        }catch (Exception e){}
        return "";
    }

    public SystemInfo getSystemInfo()
    {
        SystemInfo cSystemInfo = new SystemInfo();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_systemInfo + " WHERE 1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            if (c.getCount() > 0) {
                c.moveToFirst();

                do {
                    cSystemInfo.setDataLoaded(c.getInt(0));
                    cSystemInfo.setUserID(c.getInt(1));
                    cSystemInfo.setUserLastUpdate(c.getString(2));
                    cSystemInfo.setAreaCodeLastUpdate(c.getString(3));
                    cSystemInfo.setAreaInfoLastUpdate(c.getString(4));
                    cSystemInfo.setNoticeLastUpdate(c.getString(5));
                    cSystemInfo.setLastUpdate(c.getString(6));
                    cSystemInfo.setDesignationLastUpdate(c.getString(7));
                    cSystemInfo.setUserinfoUpdating(c.getInt(8));
                    cSystemInfo.setAreacodeUpdating(c.getInt(9));
                    cSystemInfo.setAreainfoUpdating(c.getInt(10));
                    cSystemInfo.setDesignUpdating(c.getInt(11));
                } while (c.moveToNext());
            }
            //db.close();
        }
        catch (Exception e){}
        return  cSystemInfo;
    }

    public SystemInfo getSystemInfoJSON()
    {
        SystemInfo cSystemInfo = new SystemInfo();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_systemInfo + " WHERE 1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            if (c.getCount() > 0) {
                c.moveToFirst();

                do {
                    cSystemInfo.setDataLoaded(c.getInt(0));
                    cSystemInfo.setUserID(c.getInt(1));
                    cSystemInfo.setUserLastUpdate(c.getString(2));
                    cSystemInfo.setAreaCodeLastUpdate(c.getString(3));
                    cSystemInfo.setAreaInfoLastUpdate(c.getString(4));
                    cSystemInfo.setNoticeLastUpdate(c.getString(5));
                    cSystemInfo.setLastUpdate(c.getString(6));
                    cSystemInfo.setDesignationLastUpdate(c.getString(7));
                    cSystemInfo.setUserinfoUpdating(c.getInt(8));
                    cSystemInfo.setAreacodeUpdating(c.getInt(9));
                    cSystemInfo.setAreainfoUpdating(c.getInt(10));
                    cSystemInfo.setDesignUpdating(c.getInt(11));
                } while (c.moveToNext());
            }
            db.close();
        }
        catch (Exception e){}
        return  cSystemInfo;
    }

    public area_info getAreaInfo(int area_id)
    {
        area_info cArea = new area_info();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaInfo + " WHERE " + COLUMN_areaID + "=" + area_id;
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    cArea.setArea_id(c.getInt(0));
                    cArea.setArea_details(c.getString(1));
                    cArea.setAddress(c.getString(5));
                    return cArea;
                } while (c.moveToNext());
            }
        }
        catch (Exception e){}
        //db.close();
        return  cArea;
    }

    public int getParentID(int area_id)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaCode + " WHERE area_id=" + area_id + " AND " + COLUMN_active_stat + "=1";
            //infoCodeSystem.out.println(query);
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            int parent_id = 0;
            do {
                parent_id = c.getInt(1);
            } while (c.moveToNext());
            //db.close();
            return parent_id;
        }catch (Exception e){}
        return 0;
    }

    public void insertOrReplaceUSER(User user)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_userID, user.getUser_id());
            contentValues.put(COLUMN_userName, user.getUser_name());
            contentValues.put(COLUMN_userNameENG, user.getUser_name_eng());
            contentValues.put(COLUMN_userDesignation, user.getDesignation());
            contentValues.put(COLUMN_areaID, user.getArea_id());
            contentValues.put(COLUMN_userPhone1, user.getPhone1());
            contentValues.put(COLUMN_userPhone2, user.getPhone2());
            contentValues.put(COLUMN_userOffice_num, user.getOffice_num());
            contentValues.put(COLUMN_userEmail, user.getEmail());
            contentValues.put(COLUMN_active_stat, user.getActive_stat());
            contentValues.put(COLUMN_Last_update, user.getLast_update());

            try {
                db.insert(TABLE_user, null, contentValues);

            } catch (Exception e) {

            }
            try {
                db.update(TABLE_user, contentValues, COLUMN_userID + "=" + user.getUser_id(), null);
            } catch (Exception e) {

            }
        }catch (Exception e){}
        //db.close();
        //System.out.println(" : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
        //db.insertWithOnConflict(TABLE_user, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }


    public void insertOrReplaceAreaCode(areaCode cArea)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_areaID, cArea.getAreaID());
            contentValues.put(COLUMN_areaParentID, cArea.getParentID());
            contentValues.put(COLUMN_areaName, cArea.getAreaName());
            contentValues.put(COLUMN_active_stat, cArea.getActive_stat());
            contentValues.put(COLUMN_Last_update, cArea.getLast_update());
            contentValues.put(COLUMN_areaNameEng, cArea.getAreaNameEng());
            try {
                db.insert(TABLE_areaCode, null, contentValues);

            } catch (Exception e) {

            }
            try {
                db.update(TABLE_areaCode, contentValues, COLUMN_areaID + "=" + cArea.getAreaID(), null);
            } catch (Exception e) {

            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertOrReplaceAreaInfo(area_info cArea)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_areaID, cArea.getArea_id());
            contentValues.put(COLUMN_area_address, cArea.getAddress());
            contentValues.put(COLUMN_area_detail, cArea.getArea_details());
            contentValues.put(COLUMN_area_location, cArea.getArea_location());
            contentValues.put(COLUMN_active_stat, cArea.getActive_stat());
            contentValues.put(COLUMN_Last_update, cArea.getLast_update());
            try {
                db.insert(TABLE_areaInfo, null, contentValues);

            } catch (Exception e) {

            }
            try {
                db.update(TABLE_areaInfo, contentValues, COLUMN_areaID + "=" + cArea.getArea_id(), null);
            } catch (Exception e) {

            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertOrReplaceDesignation(Designation designation)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_designID, designation.getDesign_id());
            contentValues.put(COLUMN_designEN, designation.getDesign_en());
            contentValues.put(COLUMN_designBN, designation.getDesign_bn());
            contentValues.put(COLUMN_designRank, designation.getDesign_rank());
            contentValues.put(COLUMN_active_stat, designation.getActive_stats());
            contentValues.put(COLUMN_Last_update, designation.getLast_update());
            try {
                db.insert(TABLE_designation, null, contentValues);

            } catch (Exception e) {

            }
            try {
                db.update(TABLE_designation, contentValues, COLUMN_designID + "=" + designation.getDesign_id(), null);
            } catch (Exception e) {

            }
        }catch (Exception e){}
        //db.close();
    }

    public void insertOrReplaceNotice(Notice notice)    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_noticeID, notice.getNotice_id());
            contentValues.put(COLUMN_noticeTitle, notice.getNotice_title());
            contentValues.put(COLUMN_noticeContains, notice.getNotice_contains());
            contentValues.put(COLUMN_active_stat, notice.getActive_stat());
            contentValues.put(COLUMN_noticeDateTime, notice.getNotice_date_time());
            contentValues.put(COLUMN_refNumber, notice.getRef_number());
            contentValues.put(COLUMN_postDate, notice.getPost_date());
            contentValues.put(COLUMN_noticeURL, notice.getNotice_url());
            contentValues.put(COLUMN_notice_seenStatus, 0);

            //ContentValues contentValuesIMG = new ContentValues();
            //contentValuesIMG.put(COLUMN_noticeID, noticeIMG.getNotice_id());
            //contentValuesIMG.put(COLUMN_noticeImage, noticeIMG.getNotice_img());
            try {
                db.insert(TABLE_notice, null, contentValues);

            } catch (Exception e) {
                System.out.print(e.toString());
            }
            try {
                db.update(TABLE_notice, contentValues, COLUMN_noticeID + "=" + notice.getNotice_id(), null);
            } catch (Exception e) {
                System.out.print(e.toString());
            }

        /*try
        {
           // db.insert(TABLE_noticeIMG, null, contentValuesIMG);

        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }
        try
        {
           // db.update(TABLE_noticeIMG, contentValuesIMG, COLUMN_noticeID + "=" + notice.getNotice_id(), null);
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }*/
            //db.close();
            //System.out.println(" : id= " + user.getUser_id() + "  Name= " + user.getUser_name_eng()+"  Salary= " +user.getPhone1() +" ");
            //db.insertWithOnConflict(TABLE_user, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        }catch (Exception e){}
    }


    public String getDesignationBN(int design_id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + COLUMN_designBN + " FROM " + TABLE_designation + " WHERE " + COLUMN_designID + "=" + design_id + " AND " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            //db.close();
            return c.getString(0);
        }catch (Exception e){}
        return "";
    }

    public void updateSystemInfo(String columnName,String date)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            //System.out.println("TIme:" + currentTime);

            //user_info
            try {
                db.execSQL("UPDATE " + TABLE_systemInfo + " SET " + columnName + "='" + date + "'");

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            db.close();
        }catch (Exception e){}
    }

    public void updateSystemInfoUpdating(String columnName,int value)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();
            try {
                db.execSQL("UPDATE " + TABLE_systemInfo + " SET " + columnName + "=" + value + "");

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            db.close();
        }catch (Exception e){}
    }

    public void updateSystemInfoNotice(String columnName,String date)
    {
        try {
            SQLiteDatabase db = getWritableDatabase();

            try {
                db.execSQL("UPDATE " + TABLE_systemInfo + " SET " + columnName + "='" + date + "'");

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }catch (Exception e){}
        //db.close();
    }


    public ArrayList<User> getAllUser()
    {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_user + " WHERE " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                User cUser = new User();
                cUser.setUser_id(c.getInt(0));
                cUser.setUser_name(c.getString(1));
                cUser.setDesignationID(Integer.parseInt(c.getString(2)));
                cUser.setDesignation(getDesignationBNArray(Integer.parseInt(c.getString(2))));
                cUser.setArea_id(c.getInt(3));
                cUser.setPhone1(c.getString(4));
                cUser.setPhone2(c.getString(5));
                cUser.setOffice_num(c.getString(6));
                cUser.setEmail(c.getString(7));
                cUser.setUser_name_eng(c.getString(8));
                userList.add(cUser);
            } while (c.moveToNext());
            //db.close();

        }catch (Exception e){}
        return userList;
    }


    public ArrayList<Designation> getAllDesignation()
    {
        ArrayList<Designation> designationList = new ArrayList<Designation>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_designation + " WHERE " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                Designation cDesignation = new Designation();
                cDesignation.setDesign_id(c.getInt(0));
                cDesignation.setDesign_en(c.getString(1));
                cDesignation.setDesign_bn(c.getString(2));
                cDesignation.setDesign_rank(c.getString(2));
                cDesignation.setActive_stats(c.getInt(3));
                cDesignation.setLast_update(c.getString(4));
                ;
                designationList.add(cDesignation);
            } while (c.moveToNext());
            //db.close();
        }catch (Exception E){}
        return  designationList;
    }

    public ArrayList<areaCode> getAllArea()
    {
        ArrayList<areaCode> areas = new ArrayList<areaCode>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_areaCode + " WHERE " + COLUMN_active_stat + "=1";
            Cursor c = null;
            try {
                c = db.rawQuery(query, null);

            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

            c.moveToFirst();
            do {
                areaCode cArea = new areaCode();
                cArea.setAreaID(c.getInt(0));
                cArea.setParentID(c.getInt(1));
                cArea.setAreaName(c.getString(2));
                cArea.setActive_stat(c.getInt(3));
                cArea.setLast_update(c.getString(4));
                cArea.setAreaNameEng(c.getString(5));
                areas.add(cArea);
            } while (c.moveToNext());
            //db.close();
        }catch (Exception E){}
        return  areas;
    }

    private String getDesignationBNArray(int designation_id)
    {
        for(Designation d : SplashActivity.allDesignation)
        {
            if(d.getDesign_id() == designation_id)
            {
                return d.getDesign_bn();
            }
        }
        return "";
    }
}
