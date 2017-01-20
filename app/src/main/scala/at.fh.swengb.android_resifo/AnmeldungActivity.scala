package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.{ContentValues, Intent}
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.{EditText, RadioButton, Spinner}

/**
  * Created by Martin on 15.01.2017.
  */
class AnmeldungActivity extends Activity{

  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.anmeldung)
    db = Db(getApplicationContext())
  }

  def saveData(view: View): Unit = {
    val eT_strasse = findViewById(R.id.eT_anStraße).asInstanceOf[EditText]
    val eT_hausnummer = findViewById(R.id.eT_anHausNr).asInstanceOf[EditText]
    val eT_stiege = findViewById(R.id.eT_anStiege).asInstanceOf[EditText]
    val eT_tuer = findViewById(R.id.eT_anTuer).asInstanceOf[EditText]
    val eT_plz = findViewById(R.id.eT_anPLZ).asInstanceOf[EditText]
    val eT_ort = findViewById(R.id.eT_anOrt).asInstanceOf[EditText]
    val s_bundesland = findViewById(R.id.s_anBundesland).asInstanceOf[Spinner]
    val rb_auslandJa = findViewById(R.id.rB_anAuslandJa).asInstanceOf[RadioButton]
    val rb_HWSJa = findViewById(R.id.rB_anHWSJa).asInstanceOf[RadioButton]
    val eT_nameUG = findViewById(R.id.eT_anNameUG).asInstanceOf[EditText]

    val cv = new ContentValues()

    val strasse: String = eT_strasse.getText.toString
    Map("strasse" -> strasse) foreach {
      case (k, v) => cv.put(k, v)
    }

    val hausnummer: String = eT_hausnummer.getText.toString
    Map("hausnr" -> hausnummer) foreach {
      case (k, v) => cv.put(k, v)
    }

    val stiege: String = eT_stiege.getText.toString
    Map("stiege" -> stiege) foreach {
      case (k, v) => cv.put(k, v)
    }

    val tuer = eT_tuer.getText.toString
    Map("tuer" -> tuer) foreach {
      case (k, v) => cv.put(k, v)
    }

    val plz = eT_plz.getText.toString
    Map("plz" -> plz) foreach {
      case (k, v) => cv.put(k, v)
    }

    val ort = eT_ort.getText.toString
    Map("ort" -> ort) foreach {
      case (k, v) => cv.put(k, v)
    }

    val bundesland: String = s_bundesland.getSelectedItem().toString()
    Map("bundesland" -> bundesland) foreach {
      case (k, v) => cv.put(k, v)
    }

    val zuzugAusAusland: String = if (rb_auslandJa.isChecked == true) "ja" else "nein"
    Map("zuzugAusAusland" -> zuzugAusAusland) foreach {
      case (k, v) => cv.put(k, v)
    }

    val hauptwohnsitz: String = if (rb_HWSJa.isChecked == true) "ja" else "nein"
    Map("hauptwohnsitz" -> hauptwohnsitz) foreach {
      case (k, v) => cv.put(k, v)
    }

    val unterkunftgeber: String = eT_nameUG.getText.toString
    Map("unterkunftgeber" -> unterkunftgeber) foreach {
      case (k, v) => cv.put(k, v)
    }

    db.getWritableDatabase().insert("anmeldung", null, cv)

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

  def gotoHauptwohnsitz(view:View): Unit ={
    val i = new Intent(this, classOf[HauptwohnsitzActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

}
