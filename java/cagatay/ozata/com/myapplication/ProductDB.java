package hw2.ozata.com.myapplication;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    public static final String TABLE_NAME="contact";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_TYPE = "type";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE "+TABLE_NAME+" ("+FIELD_ID+" number, "+FIELD_NAME+" text, "+FIELD_EMAIL+" text, "+FIELD_TYPE+" text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;


    public static List<Product> getAllContact(DatabaseHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        //Cursor cursor  db.getAllRecordsMethod2("SELECT * FROM "+TABLE_NAME, null)
        List<Product> data=new ArrayList<>();
        Product acontact = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);

            acontact= new Product(id, name,email );
            data.add(acontact);
        }
        return data;
    }
    public static List<Product> findContact(DatabaseHelper db, String key){

        String where = FIELD_NAME+" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        List<Product> data=new ArrayList<>();
        Product acontact = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);

            acontact= new Product(id, name,email );
            data.add(acontact);
        }
        return data;
    }
    public static long insertContact(DatabaseHelper db, int id, String name, String email){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_EMAIL, email);

        long res = db.insert(TABLE_NAME,contentValues);
        return res;
    }
    public static boolean updateContact(DatabaseHelper db, int id, String name, String email){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_EMAIL, email);
        String where = FIELD_ID +" = "+id;
        boolean res = db.update(TABLE_NAME,contentValues,where);

        return res;
    }
    public static boolean deleteContact(DatabaseHelper db, int id){
        String where = FIELD_ID +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }

}
