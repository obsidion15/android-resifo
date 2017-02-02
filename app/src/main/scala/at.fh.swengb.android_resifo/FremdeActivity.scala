package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, EditText, RadioButton, Spinner}

/**
  * Created by Martin on 19.01.2017.
  */
class FremdeActivity extends Activity{

  var db: Db = _
  var person_id = ""

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.reisedokument_fremde)
    db = Db(getApplicationContext)
    fillAllSpinner()
    val intent: Intent = getIntent
    person_id = intent.getStringExtra("person_id")
  }

  def saveData(view:View): Unit = {

    val art = findViewById(R.id.s_rdArt).asInstanceOf[Spinner].getSelectedItem.toString
    val nummer = findViewById(R.id.eT_rdNummer).asInstanceOf[EditText].getText.toString
    val rdTag = findViewById(R.id.s_rdTag).asInstanceOf[Spinner].getSelectedItem.toString
    val rdMonat = findViewById(R.id.s_rdMonat).asInstanceOf[Spinner].getSelectedItem.toString
    val rdJahr = findViewById(R.id.s_rdJahr).asInstanceOf[Spinner].getSelectedItem.toString
    val rdDatum = s"$rdTag.$rdMonat.$rdJahr"
    val behoerde = findViewById(R.id.eT_rdBehoerde).asInstanceOf[EditText].getText.toString
    val staat = findViewById(R.id.s_rdStaat).asInstanceOf[Spinner].getSelectedItem.toString

    val fremdDaten: FremdeDaten = FremdeDaten(person_id, art, nummer, rdDatum, behoerde, staat)

    val fremDao = db.mkFremDao()
    fremDao.insert(fremdDaten)
  }

  def gotoNext(view: View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[EntscheidungActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_rdArt).asInstanceOf[Spinner], Array("kompliziert", "..."))
    fillSpinner(findViewById(R.id.s_rdTag).asInstanceOf[Spinner], Array.range(1,31 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_rdMonat).asInstanceOf[Spinner], Array.range(1,31 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_rdJahr).asInstanceOf[Spinner], Array.range(1970,2015 + 1).reverse.map(x => x.toString))
    fillSpinner(findViewById(R.id.s_rdStaat).asInstanceOf[Spinner], Array("USA", "Deutschland", "..."))
  }

  def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
    val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
    spinner.setAdapter(adapter)
  }

}
