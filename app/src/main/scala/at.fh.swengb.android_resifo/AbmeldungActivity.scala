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
class AbmeldungActivity extends Activity{

  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.abmeldung)
    db = Db(getApplicationContext())
  }

  def saveData(view: View): Unit = {
    val eT_strasse = findViewById(R.id.eT_abStraße).asInstanceOf[EditText]
    val eT_hausnummer = findViewById(R.id.eT_abHausNr).asInstanceOf[EditText]
    val eT_stiege = findViewById(R.id.eT_abStiege).asInstanceOf[EditText]
    val eT_tuer = findViewById(R.id.eT_abTuer).asInstanceOf[EditText]
    val eT_plz = findViewById(R.id.eT_abPLZ).asInstanceOf[EditText]
    val eT_ort = findViewById(R.id.eT_abOrt).asInstanceOf[EditText]
    val s_bundesland = findViewById(R.id.s_abBundesland).asInstanceOf[Spinner]
    val rb_auslandJa = findViewById(R.id.rB_abAuslandJa).asInstanceOf[RadioButton]

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

    val verzugInsAusland: String = if (rb_auslandJa.isChecked == true) "ja" else "nein"
    Map("verzugInsAusland" -> verzugInsAusland) foreach {
      case (k, v) => cv.put(k, v)
    }

    db.getWritableDatabase().insert("person", null, cv)

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

  def gotoErfolgreich(view:View): Unit ={
    val i = new Intent(this, classOf[ErfolgreichActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

}
