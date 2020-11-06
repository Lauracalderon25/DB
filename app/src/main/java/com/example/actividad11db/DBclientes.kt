package com.example.actividad11db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

 class DBclientes (context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase){
        db.execSQL("create table clientes(numero int primary key, nombre, direcion, telefono, correo  )")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){

    }
}