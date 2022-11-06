package com.example.bd_com_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {

            //Criar BD
            //val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)

        //} catch (e: Exception) {
            //e.printStackTrace()

        //}

        //Criar Tabela
        val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)
        bd.execSQL("CREATE TABLE IF NOT EXISTS veiculo(nome INTEGER PRIMARY KEY AUTOINCREMENT, modelo VARCHAR, chassi VARCHAR, placa VARCHAR, ano INT (5))")

        //Inserir Dados
        bd.execSQL("INSERT INTO veiculo (nome, modelo, chassi, placa, ano) VALUES ('Palio', 'Fiat', '012345X','ABC123', 2 )")
        bd.execSQL("INSERT INTO veiculo (nome, modelo, chassi, placa, ano) VALUES ('Gol', 'Volkswagen', '98765X', 'DEF321', 2)")
        bd.execSQL("INSERT INTO veiculo (nome, modelo, chassi, placa, ano) VALUES ('HB20', 'Hyunday', '43210X', 'GHI431', 2)")

        //Recuperar
        val cursor =
            bd.rawQuery("SELECT nome, modelo, chassi, placa, ano FROM veiculo ORDER BY nome", null)
        bd.execSQL("UPDATE veiculo SET NOME = 'Palio' where modelo = 'Fiat'")

        //Indice das Tabelas
        val indiceNome = cursor!!.getColumnIndex("nome")
        val indiceModelo = cursor.getColumnIndex("modelo")
        val indiceChassi = cursor.getColumnIndex("chassi")
        val indicePlaca = cursor.getColumnIndex("placa")
        val indiceAno = cursor.getColumnIndex("ano")
        cursor.moveToFirst()
        while (cursor != null) {
            Log.i(
                "RESULTADO - NOME:", cursor.getString(indiceNome) + "MODELO: " +
                        cursor.getString(indiceModelo) + "CHASSI: " + cursor.getString(indiceChassi) +
                        "PLACA: " + cursor.getString(indicePlaca) + "ANO: " + cursor.getString(
                    indiceAno
                )
            )
            //Log.i("RESULTADO = MODELO:", cursor.getString(indiceModelo))
            //Log.i("RESULTADO = CHASSI:", cursor.getString(indiceChassi))
            //Log.i("RESULTADO = PLACA:", cursor.getString(indicePlaca))
            //Log.i("RESULTADO = ANO:", cursor.getString(indiceAno))
            cursor.moveToNext()
        }
    } catch (e: Exception){

    }
        //Apagar a Tabela
        //bd.execSQL("DROP TABLE veiculo")

        //bd.execSQL("DELETE FROM veiculo" )

    }
}

