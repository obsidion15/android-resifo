package at.fh.swengb.android_resifo

import android.database.Cursor

/**
  * Created by Martin on 20.01.2017.
  */
class Data {

  def fillDataIntoOverview(db: Db, i: Int):Unit = {
    fillPersDaten(db, i)
    fillAnmeldeDaten(db, i)
    fillHwsDaten(db, i)
    fillAbmeldeDaten(db, i)
  }

  def fillPersDaten(db: Db, i: Int): Unit = {
    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(db.getReadableDatabase.query("person", Array("person_id", "nachname", "vorname", "nachnameAlt", "geburtsdatum", "geburtsort", "geschlecht", "religion", "familienstand", "staatsangehoerigkeit"), null, null, null, null, null))

      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("person_id"))
            val nachname = c.getString(c.getColumnIndex("nachname"))
            val vorname = c.getString(c.getColumnIndex("vorname"))
            val nachnameAlt = c.getString(c.getColumnIndex("nachnameAlt"))
            val geburtsdatum = c.getString(c.getColumnIndex("geburtsdatum"))
            val geburtsort = c.getString(c.getColumnIndex("geburtsort"))
            val geschlecht = c.getString(c.getColumnIndex("geschlecht"))
            val religion = c.getString(c.getColumnIndex("religion"))
            val familienstand = c.getString(c.getColumnIndex("familienstand"))
            val staatsangehoerigkeit = c.getString(c.getColumnIndex("staatsangehoerigkeit"))
            println(s"ID($id): $nachname ($nachnameAlt) $vorname, geboren am $geburtsdatum in $geburtsort, Geschlecht: $geschlecht, $religion, $familienstand, $staatsangehoerigkeit")
          }
      }
    } finally {
      someCursor foreach (_.close())
    }
  }

  def fillAnmeldeDaten(db: Db, i: Int): Unit = {
    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(db.getReadableDatabase.query("anmeldung", Array("anmeldung_id", "person_id", "strasse", "hausnr", "stiege", "tuer", "plz", "ort", "bundesland", "zuzugAusAusland", "hauptwohnsitz", "unterkunftgeber"), null, null, null, null, null))

      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("anmeldung_id"))
            val p_id = c.getInt(c.getColumnIndex("person_id"))
            val strasse = c.getString(c.getColumnIndex("strasse"))
            val hausnr = c.getString(c.getColumnIndex("hausnr"))
            val stiege = c.getString(c.getColumnIndex("stiege"))
            val tuer = c.getString(c.getColumnIndex("tuer"))
            val plz = c.getString(c.getColumnIndex("plz"))
            val ort = c.getString(c.getColumnIndex("ort"))
            val bundesland = c.getString(c.getColumnIndex("bundesland"))
            val zuzugAusAusland = c.getString(c.getColumnIndex("zuzugAusAusland"))
            val hauptwohnsitz = c.getString(c.getColumnIndex("hauptwohnsitz"))
            val unterkunftgeber = c.getString(c.getColumnIndex("unterkunftgeber"))
            println(s"ID($p_id): wohnhaft in $strasse $hausnr, Stiege $stiege, Tür $tuer, $plz $ort, $bundesland; Zuzug aus Ausland $zuzugAusAusland; Hauptwohnsitz $hauptwohnsitz; Unterkunftgeber $unterkunftgeber")
          }
      }

    } finally {
      someCursor foreach (_.close())
    }
  }

  def fillHwsDaten(db: Db, i: Int): Unit = {
    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(db.getReadableDatabase.query("hauptsitz", Array("hauptsitz_id", "person_id", "strasse", "hausnr", "stiege", "tuer", "plz", "ort", "bundesland"), null, null, null, null, null))

      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("hauptsitz_id"))
            val p_id = c.getInt(c.getColumnIndex("person_id"))
            val strasse = c.getString(c.getColumnIndex("strasse"))
            val hausnr = c.getString(c.getColumnIndex("hausnr"))
            val stiege = c.getString(c.getColumnIndex("stiege"))
            val tuer = c.getString(c.getColumnIndex("tuer"))
            val plz = c.getString(c.getColumnIndex("plz"))
            val ort = c.getString(c.getColumnIndex("ort"))
            val bundesland = c.getString(c.getColumnIndex("bundesland"))
            println(s"ID($p_id): wohnhaft in $strasse $hausnr, Stiege $stiege, Tür $tuer, $plz $ort, $bundesland")
          }
      }

    } finally {
      someCursor foreach (_.close())
    }
  }

  def fillAbmeldeDaten(db: Db, i: Int): Unit = {
    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(db.getReadableDatabase.query("abmeldung", Array("abmeldung_id", "person_id", "strasse", "hausnr", "stiege", "tuer", "plz", "ort", "bundesland", "verzugInsAusland"), null, null, null, null, null))

      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("anmeldung_id"))
            val p_id = c.getInt(c.getColumnIndex("person_id"))
            val strasse = c.getString(c.getColumnIndex("strasse"))
            val hausnr = c.getString(c.getColumnIndex("hausnr"))
            val stiege = c.getString(c.getColumnIndex("stiege"))
            val tuer = c.getString(c.getColumnIndex("tuer"))
            val plz = c.getString(c.getColumnIndex("plz"))
            val ort = c.getString(c.getColumnIndex("ort"))
            val bundesland = c.getString(c.getColumnIndex("bundesland"))
            val verzugInsAusland = c.getString(c.getColumnIndex("verzugInsAusland"))
            println(s"ID($p_id): wohnhaft in $strasse $hausnr, Stiege $stiege, Tür $tuer, $plz $ort, $bundesland;Verzug aus Ausland $verzugInsAusland")
          }
      }

    } finally {
      someCursor foreach (_.close())
    }
  }

}
