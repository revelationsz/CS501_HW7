package com.example.w7_ch5_p25;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "user";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_USER + "(" + EMAIL;
        sqlCreate += " text, " + FIRSTNAME;
        sqlCreate += " text, " + LASTNAME + " text )" ;

        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db,
                           int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_USER );
        // Re-create tables
        onCreate( db );
    }

    public void insert( user user ) {
        System.out.println(user);
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_USER;
        sqlInsert += " values('" + user.getEmail();
        sqlInsert += "', '" + user.getFirstName() + "', '" + user.getLastName() + "' )";
        System.out.println(sqlInsert);
        db.execSQL( sqlInsert );
        db.close( );
    }
//
//    public void deleteById( int id ) {
//        SQLiteDatabase db = this.getWritableDatabase( );
//        String sqlDelete = "delete from " + TABLE_USER;
//        sqlDelete += " where " + EMAIL + " = " + id;
//
//        db.execSQL( sqlDelete );
//        db.close( );
//    }

//    public void updateById( int id, String name, double price ) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String sqlUpdate = "update " + TABLE_USER;
//        sqlUpdate += " set " + FIRSTNAME + " = '" + name + "', ";
//        sqlUpdate += LASTNAME + " = '" + price + "'";
//        sqlUpdate += " where " + EMAIL + " = " + id;
//
//        db.execSQL( sqlUpdate );
//        db.close( );
//    }

    public ArrayList<String> selectAll( ) {
        String sqlQuery = "select * from " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<String> allUsers = new ArrayList<String>( );
        while( cursor.moveToNext( ) ) {
            user curr = new user( cursor.getString( 0 ) ,
                    cursor.getString( 1 ), cursor.getString( 2 ));
            allUsers.add( curr.getEmail() );
        }
        db.close( );
        return allUsers;
    }

    public user selectByEmail( String email ) {
        String sqlQuery = "select * from " + TABLE_USER;
        sqlQuery += " where " + EMAIL + " = '" +email +"'";

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        user curr = null;
        System.out.println(cursor);
        if( cursor.moveToFirst( ) )
            curr = new user(  cursor.getString( 0 ) ,
                    cursor.getString( 1 ), cursor.getString( 2 ) );
        System.out.println(curr);

        return curr;
    }
}