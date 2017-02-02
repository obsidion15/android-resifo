package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, EditText, RadioButton, Spinner}

/**
  * Created by Martin on 15.01.2017.
  */
class AnmeldungActivity extends Activity{

  var db: Db = _
  var person_id = ""

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.anmeldung)
    db = Db(getApplicationContext())
    fillAllSpinner()
    val intent: Intent = getIntent
    person_id = intent.getStringExtra("person_id")
  }

  val rb_auslandJa = findViewById(R.id.rB_anAuslandJa).asInstanceOf[RadioButton]
  val rb_HWSJa = findViewById(R.id.rB_anHWSJa).asInstanceOf[RadioButton]

  def saveData(view: View): Unit = {
    val strasse = findViewById(R.id.eT_anStraße).asInstanceOf[EditText].getText.toString
    val hausnummer = findViewById(R.id.eT_anHausNr).asInstanceOf[EditText].getText.toString
    val stiege = findViewById(R.id.eT_anStiege).asInstanceOf[EditText].getText.toString
    val tuer = findViewById(R.id.eT_anTuer).asInstanceOf[EditText].getText.toString
    val plz = findViewById(R.id.eT_anPLZ).asInstanceOf[EditText].getText.toString
    val ort = findViewById(R.id.eT_anOrt).asInstanceOf[EditText].getText.toString
    val bundesland = findViewById(R.id.s_anBundesland).asInstanceOf[Spinner].getSelectedItem().toString()
    val ausland = if (rb_auslandJa.isChecked == true) "ja" else "nein"
    val hws = if (rb_HWSJa.isChecked == true) "ja" else "nein"
    val nameUG = findViewById(R.id.eT_anNameUG).asInstanceOf[EditText].getText.toString

    val anmeldeDaten: AnmeldeDaten = AnmeldeDaten(person_id, strasse, hausnummer, stiege, tuer, plz, ort, bundesland, ausland, hws, nameUG)

    val anmDao = db.mkAnmDao()
    anmDao.insert(anmeldeDaten)
  }

  def gotoNext(view:View): Unit ={
    saveData(view)
    val i = if (rb_HWSJa.isChecked == false) new Intent(this, classOf[HauptwohnsitzActivity]) else new Intent(this, classOf[ErfolgreichActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_anBundesland).asInstanceOf[Spinner], Array("Steiermark", "Kärnten", "Burgenland", "Tirol", "Vorarlberg", "Salzburg", "Niederösterreich", "Oberösterreich", "Wien"))
  }

  def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
    val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
    spinner.setAdapter(adapter)
  }

}
