package com.example.sushi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBConnection extends SQLiteOpenHelper {
    final String SUSHI_TABLE="SUSHI";
    final String SUSHI_ID_TABLE="SUSHI_ID";
    final String SUSHI_NAME_TABLE="SUSHI_NAME";
    final String SUSHI_PHOTO_TABLE="SUSHI_PHOTO";
    final String SUSHI_COST_TABLE="SUSHI_COST";
    final String SUSHI_QUANTITY_TABLE="SUSHI_QUANTITY";


    public DBConnection(@Nullable Context context) {
        super(context, "sushi.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSushiCardTableStatement="CREATE TABLE "+SUSHI_TABLE+" ( "+SUSHI_ID_TABLE+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+SUSHI_NAME_TABLE+" TEXT, " + SUSHI_PHOTO_TABLE+" INTEGER, "+SUSHI_COST_TABLE+" INTEGER, "+ SUSHI_QUANTITY_TABLE+" INTEGER)";
        db.execSQL(createSushiCardTableStatement);

        ArrayList<SushiCard> sushi=new ArrayList<>();

        sushi.add(new SushiCard("Philadelphia Classic",R.mipmap.philadelphia,275,1));
        sushi.add(new SushiCard("Sake Tempura",R.mipmap.sake_tempura,100,1));
        sushi.add(new SushiCard("Ikura Maki",R.mipmap.ikura_maki,95,1));
        sushi.add(new SushiCard("Yakuza",R.mipmap.yakuza,120,1));

        sushi.forEach(s -> {
            ContentValues cv=new ContentValues();

            cv.put(SUSHI_NAME_TABLE,s.getName());
            cv.put(SUSHI_PHOTO_TABLE,s.getPhoto());
            cv.put(SUSHI_COST_TABLE,s.getCost());
            cv.put(SUSHI_QUANTITY_TABLE,s.getQuantity());

            db.insert(SUSHI_TABLE,null,cv);
        });
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<SushiCard> getSushi(){
        List<SushiCard> resultList=new ArrayList<>();

        String queryList="SELECT * FROM " + SUSHI_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryList,null);

        if(cursor.moveToFirst()){
            do {
                int sushiID=cursor.getInt(0);
                String sushiName=cursor.getString(1);
                int sushiPhoto=cursor.getInt(2);
                int sushiCost=cursor.getInt(3);
                int sushiQuantity=cursor.getInt(4);

                SushiCard newSushi=new SushiCard(sushiID,sushiName,sushiPhoto,sushiCost,sushiQuantity);
                resultList.add(newSushi);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultList;
    }

    public void addOneSushi(int id,int lastQuantity){
        String queryUpdate="UPDATE " +SUSHI_TABLE +  " SET "+ SUSHI_QUANTITY_TABLE +" = "+(lastQuantity+1) +" WHERE "+SUSHI_ID_TABLE+"="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(queryUpdate);
    }

    public void removeOneSushi(int id,int lastQuantity){
        String queryUpdate="UPDATE " +SUSHI_TABLE +  " SET "+ SUSHI_QUANTITY_TABLE +" = "+(lastQuantity<=1?0:(lastQuantity-1)) +" WHERE "+SUSHI_ID_TABLE+"="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(queryUpdate);
    }
}
