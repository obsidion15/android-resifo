package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, EditText, RadioButton, Spinner}

/**
  * Created by Martin on 15.01.2017.
  */
class AbmeldungActivity extends Activity{

  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.abmeldung)
    db = Db(getApplicationContext())
    fillAllSpinner()
  }

  def saveData(view: View): Unit = {
    val strasse = findViewById(R.id.eT_abStraße).asInstanceOf[EditText].getText.toString
    val hausnummer = findViewById(R.id.eT_abHausNr).asInstanceOf[EditText].getText.toString
    val stiege = findViewById(R.id.eT_abStiege).asInstanceOf[EditText].getText.toString
    val tuer = findViewById(R.id.eT_abTuer).asInstanceOf[EditText].getText.toString
    val plz = findViewById(R.id.eT_abPLZ).asInstanceOf[EditText].getText.toString
    val ort = findViewById(R.id.eT_abOrt).asInstanceOf[EditText].getText.toString
    val bundesland = findViewById(R.id.s_abBundesland).asInstanceOf[Spinner].getSelectedItem().toString()
    val rb_auslandJa = findViewById(R.id.rB_abAuslandJa).asInstanceOf[RadioButton]
    val ausland = if (rb_auslandJa.isChecked == true) "ja" else "nein"

    val abmeldeDaten: AbmeldeDaten = AbmeldeDaten(strasse, hausnummer, stiege, tuer, plz, ort, bundesland, ausland)

    val abmDao = db.mkAbmDao()
    abmDao.insert(abmeldeDaten)
  }

  def gotoErfolgreich(view:View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[ErfolgreichActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_abBundesland).asInstanceOf[Spinner], Array("Steiermark", "Kärnten", "Burgenland", "Tirol", "Vorarlberg", "Salzburg", "Niederösterreich", "Oberösterreich", "Wien"))
  }

  def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
    val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
    spinner.setAdapter(adapter)
  }
}
