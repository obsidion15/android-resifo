package at.fh.swengb.android_resifo

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget._

/**
  * Created by Martin on 15.01.2017.
  */
class PersoenlicheDatenActivity extends Activity{

  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.persoenliche_daten)
    db = Db(getApplicationContext)
    fillAllSpinner()
  }

  def saveData(view:View): Unit = {

    val nachname = findViewById(R.id.eT_nachname).asInstanceOf[EditText].getText.toString
    val vorname = findViewById(R.id.eT_vorname).asInstanceOf[EditText].getText.toString
    val nachnameVorher = findViewById(R.id.eT_nachnameVorher).asInstanceOf[EditText].getText.toString
    val gebTag = findViewById(R.id.s_gebTag).asInstanceOf[Spinner].getSelectedItem.toString
    val gebMonat = findViewById(R.id.s_gebMonat).asInstanceOf[Spinner].getSelectedItem.toString
    val gebJahr = findViewById(R.id.s_gebJahr).asInstanceOf[Spinner].getSelectedItem.toString
    val gebDatum = s"$gebTag.$gebMonat.$gebJahr"
    val gebOrt = findViewById(R.id.eT_gebOrt).asInstanceOf[EditText].getText.toString
    val rb_m = findViewById(R.id.rB_m).asInstanceOf[RadioButton]
    val geschlecht = if (rb_m.isChecked == true) "m" else "w"
    val religion = findViewById(R.id.s_religion).asInstanceOf[Spinner].getSelectedItem.toString
    val famStand = findViewById(R.id.s_famStand).asInstanceOf[Spinner].getSelectedItem.toString
    val staat = findViewById(R.id.s_staat).asInstanceOf[Spinner].getSelectedItem.toString

    val persDaten: PersoenlicheDaten = PersoenlicheDaten(nachname, vorname, nachnameVorher, gebDatum, gebOrt, geschlecht, religion, famStand, staat)

    val persDao = db.mkPersDao()
    persDao.insert(persDaten)
  }

  def gotoNext(view:View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[AnmeldungActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_gebTag).asInstanceOf[Spinner], Array.range(1,31 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_gebMonat).asInstanceOf[Spinner], Array.range(1,12 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_gebJahr).asInstanceOf[Spinner], Array.range(1950,2015 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_religion).asInstanceOf[Spinner], Array("röm-kath","andere"))
    fillSpinner(findViewById(R.id.s_famStand).asInstanceOf[Spinner], Array("ledig","verheiratet","geschieden","..."))
    fillSpinner(findViewById(R.id.s_staat).asInstanceOf[Spinner], Array("Österreich", "Deutschland", "..."))
  }

  def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
    val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
    spinner.setAdapter(adapter)
  }
}
