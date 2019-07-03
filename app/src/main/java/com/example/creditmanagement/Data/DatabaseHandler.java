package com.example.creditmanagement.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    public DatabaseHandler(Context ctx) {
        super(ctx, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTableQuery = "CREATE TABLE " + Constants.USER_TABLE_NAME + " ("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_NAME
                + " TEXT, " + Constants.KEY_EMAIL + " TEXT, " + Constants.KEY_CURRCREDIT
                + " INTEGER);";
        String createTransferTableQuery = "CREATE TABLE " + Constants.TRANSFER_TABLE_NAME + " ("
                + Constants.KEY_FROM_ID + " INTEGER, " + Constants.KEY_TO_ID + " INTEGER, "
                + Constants.KEY_CREDIT + " INTEGER);";
        sqLiteDatabase.execSQL(createUserTableQuery);
        sqLiteDatabase.execSQL(createTransferTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TRANSFER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME, user.getName());
        values.put(Constants.KEY_EMAIL, user.getEmail());
        values.put(Constants.KEY_CURRCREDIT, user.getCurrCredit());
        db.insert(Constants.USER_TABLE_NAME, null, values);
        Log.d("UserTBL", "Item inserted!");
    }

    public void addTransfer(Transfer transfer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_FROM_ID, transfer.getFromId());
        values.put(Constants.KEY_TO_ID, transfer.getToId());
        values.put(Constants.KEY_CREDIT, transfer.getCredit());
        db.insert(Constants.TRANSFER_TABLE_NAME, null, values);
        Log.d("TransferTBL", "Item inserted!");
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        User user = new User();
        Cursor cursor = db.query(Constants.USER_TABLE_NAME, new String[] {Constants.KEY_ID, Constants.KEY_NAME,
                        Constants.KEY_EMAIL, Constants.KEY_CURRCREDIT}, Constants.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            user.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_EMAIL)));
            user.setCurrCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_CURRCREDIT))));
        }
        return user;
    }

    public List<User> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> userList = new ArrayList<> ();
        Cursor cursor = db.query(Constants.USER_TABLE_NAME, new String[] {Constants.KEY_ID, Constants.KEY_NAME,
                        Constants.KEY_EMAIL, Constants.KEY_CURRCREDIT}, null, null, null,
                null, Constants.KEY_ID);

        if(cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Constants.KEY_EMAIL)));
                user.setCurrCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_CURRCREDIT))));
                userList.add(user);

            }while(cursor.moveToNext());
        }
        return userList;
    }

    public List<Transfer> getAllTransfers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Transfer> transferList = new ArrayList<>();
        Cursor cursor = db.query(Constants.TRANSFER_TABLE_NAME, new String[] {Constants.KEY_FROM_ID, Constants.KEY_TO_ID,
                Constants.KEY_CREDIT}, null, null, null, null, Constants.KEY_FROM_ID);
        if(cursor.moveToFirst()) {
            do {
                Transfer transfer = new Transfer();
                transfer.setFromId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_FROM_ID))));
                transfer.setToId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_TO_ID))));
                transfer.setCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_CREDIT))));
                transferList.add(transfer);

            }while (cursor.moveToNext());
        }
        return transferList;
    }

    public void updateCredit(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_CURRCREDIT, user.getCurrCredit());
        db.update(Constants.USER_TABLE_NAME, values, Constants.KEY_ID + "=?", new String[] {String.valueOf(user.getId())});
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.USER_TABLE_NAME, Constants.KEY_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void deletetransfer(int fid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TRANSFER_TABLE_NAME, Constants.KEY_FROM_ID + "=?", new String[] {String.valueOf(fid)});
        db.close();
    }

    public int getUserCount() {
        String countQuery = "SELECT * FROM " + Constants.USER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public int getTransferCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Constants.TRANSFER_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }



}
