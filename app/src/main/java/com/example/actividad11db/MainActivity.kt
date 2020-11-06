package com.example.actividad11db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numeroEditText: EditText = findViewById(R.id.editTextNumber)
        val nombreEditText: EditText = findViewById(R.id.editTextName)
        val direccionEditText: EditText = findViewById(R.id.editTextAddress)
        val telefonoEditText: EditText = findViewById(R.id.editTextPhone)
        val correoEditText: EditText = findViewById(R.id.editTextTextEmailAddress)
        val addButton: Button = findViewById(R.id.Agregar)
        val numeroSearchButton: Button = findViewById(R.id.BuscarClienteNumero)
        val nombreSearchButton: Button = findViewById(R.id.BuscarClienteNombre)
        val deleteButton: Button = findViewById(R.id.BorrarCliente)

        addButton.setOnClickListener() {
            val admin = DBclientes(this,"admin", null, 1)
            val bd = admin.writableDatabase
            val register = ContentValues()
            register.put("numero", numeroEditText.text.toString())
            register.put("nombre", nombreEditText.text.toString())
            register.put("direccion", direccionEditText.text.toString())
            register.put("telefono",telefonoEditText.text.toString())
            register.put("correo",correoEditText.text.toString())
            bd.insert("clientes",null, register)
            bd.close()
            numeroEditText.setText("")
            nombreEditText.setText("")
            direccionEditText.setText("")
            telefonoEditText.setText("")
            correoEditText.setText("")
            Toast.makeText(this, "Se agrego el articulo a la base de datos los datos", Toast.LENGTH_SHORT).show()
        }
        numeroSearchButton.setOnClickListener() {
            val admin = DBclientes(this, "admin", null, 1)
            val bd = admin.writableDatabase
            val row = bd.rawQuery("select nombre, direcion, telefono, correo  from clientes where numero='${numeroEditText.text}'", null)
            if (row.moveToFirst()) {
                nombreEditText.setText(row.getString(0))
                direccionEditText.setText(row.getString(1))
                telefonoEditText.setText(row.getString(2))
                correoEditText.setText(row.getString(3))
            } else
                Toast.makeText(this, "No existe clientes con dicho numero",  Toast.LENGTH_SHORT).show()
            bd.close()
        }
        nombreSearchButton.setOnClickListener {
            val admin = DBclientes(this, "admin", null, 1)
            val bd = admin.writableDatabase
            val row = bd.rawQuery("select nombre, direcion, telefono, correo  from clientes where nombre='${nombreEditText.text}'", null)
            if (row.moveToFirst()) {
                numeroEditText.setText(row.getString(0))
                direccionEditText.setText(row.getString(1))
                telefonoEditText.setText(row.getString(2))
                correoEditText.setText(row.getString(3))
            } else
                Toast.makeText(this, "No existe clientes con dicho numero",  Toast.LENGTH_SHORT).show()
            bd.close()
        }

        deleteButton.setOnClickListener {
            val admin = DBclientes(this, "admin", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("clientes", "numero='${numeroEditText.text}'", null)
            bd.close()
            numeroEditText.setText("")
            nombreEditText.setText("")
            direccionEditText.setText("")
            telefonoEditText.setText("")
            correoEditText.setText("")
            if (cant == 1)
                Toast.makeText(this, "Se borró el artículo con dicho numero", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "No existe un artículo con dicho numero", Toast.LENGTH_SHORT).show()
        }

    }
}