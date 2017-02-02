package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, EditText, Spinner}

/**
  * Created by Martin on 15.01.2017.
  */
class HauptwohnsitzActivity extends Activity{

  var db: Db = _
  val intent: Intent = getIntent
  val person_id = intent.getStringExtra("person_id")

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.hauptwohnsitz)
    db = Db(getApplicationContext())
    fillAllSpinner()
  }

  def saveData(view: View): Unit = {
    val strasse = findViewById(R.id.eT_hwsStraße).asInstanceOf[EditText].getText.toString
    val hausnummer = findViewById(R.id.eT_hwsHausNr).asInstanceOf[EditText].getText.toString
    val stiege = findViewById(R.id.eT_hwsStiege).asInstanceOf[EditText].getText.toString
    val tuer = findViewById(R.id.eT_hwsTuer).asInstanceOf[EditText].getText.toString
    val plz = findViewById(R.id.eT_hwsPLZ).asInstanceOf[EditText].getText.toString
    val ort = findViewById(R.id.eT_hwsOrt).asInstanceOf[EditText].getText.toString
    val bundesland = findViewById(R.id.s_hwsBundesland).asInstanceOf[Spinner].getSelectedItem().toString()

    val hwsDaten: HauptwohnsitzDaten = HauptwohnsitzDaten(strasse, hausnummer, stiege, tuer, plz, ort, bundesland)

    val hwsDao = db.mkHwsDao()
    hwsDao.insert(hwsDaten)
  }

  def gotoNext(view:View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[ErfolgreichActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_hwsBundesland).asInstanceOf[Spinner], Array("Steiermark", "Kärnten", "Burgenland", "Tirol", "Vorarlberg", "Salzburg", "Niederösterreich", "Oberösterreich", "Wien"))
  }

  def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
    val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
    spinner.setAdapter(adapter)
  }

}
