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

    db.getWritableDatabase().insert("abmeldung", null, cv)
  }

  def gotoErfolgreich(view:View): Unit ={
    val i = new Intent(this, classOf[ErfolgreichActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

}
