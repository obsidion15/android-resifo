package at.fh.swengb.android_resifo

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}

/**
  * Created by Sabine on 16.01.2017.
  */
case class db(context: Context) extends SQLiteOpenHelper(context, "mydb", null, 1) {

  override def onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = ()

  override def onCreate(db: SQLiteDatabase): Unit = {
    //Tabelle Person
    db.execSQL("create table person (person_id INTEGER PRIMARY KEY ASC, nachname TEXT, vorname TEXT, " +
      "nachnameAlt TEXT, geburtsdatum NUMERIC, geburtsort TEXT, geschlecht TEXT, religion TEXT, " +
      "familienstand TEXT, staatsangehoerigkeit TEXT);");

    //Tabelle Anmeldung
    db.execSQL("create table anmeldung (anmeldung_id INTEGER PRIMARY KEY ASC, person_id INTEGER, " +
      "strasse TEXT, hausnr INTEGER, stiege INTERGER, tuer INTERGER, plz INTEGER, ort TEXT, " +
      "bundesland TEXT, zuzugAusAusland TEXT, hauptwohnsitz TEXT, unterkunftgeber TEXT); ");

    //Tabelle Hauptsitz
    db.execSQL("create table hauptsitz (hauptsitz_id INTEGER PRIMARY KEY ASC, person_id INTEGER, " +
      "strasse TEXT, hausnr INTEGER, stiege INTERGER, tuer INTERGER, plz INTEGER, ort TEXT, " +
      "bundesland TEXT); ");

    //Tabelle Abmeldung
    db.execSQL("create table abmeldung (abmeldung_id INTEGER PRIMARY KEY ASC, person_id INTEGER, " +
      "strasse TEXT, hausnr INTEGER, stiege INTERGER, tuer INTERGER, plz INTEGER, ort TEXT, " +
      "bundesland TEXT, verzugInsAusland TEXT);")
  }
}