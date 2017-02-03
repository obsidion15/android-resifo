package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.{ArrayAdapter, EditText, RadioButton, Spinner}

import scala.util.matching.Regex

/**
  * Created by Martin on 19.01.2017.
  */
class FremdeActivity extends Activity{

  var db: Db = _
  var person_id = 0

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.reisedokument_fremde)
    db = Db(getApplicationContext)
    fillAllSpinner()
    val intent: Intent = getIntent
    person_id = intent.getExtras.get("person_id").asInstanceOf[Int]
  }

  def saveData(view:View): Unit = {

    val art = findViewById(R.id.s_rdArt).asInstanceOf[Spinner].getSelectedItem.toString
    val nummer = findViewById(R.id.eT_rdNummer).asInstanceOf[EditText].getText.toString
    val rdTag = findViewById(R.id.s_rdTag).asInstanceOf[Spinner].getSelectedItem.toString
    val rdMonat = findViewById(R.id.s_rdMonat).asInstanceOf[Spinner].getSelectedItem.toString
    val rdJahr = findViewById(R.id.s_rdJahr).asInstanceOf[Spinner].getSelectedItem.toString
    var rdDatum = s"$rdTag.$rdMonat.$rdJahr"
    val behoerde = findViewById(R.id.eT_rdBehoerde).asInstanceOf[EditText].getText.toString
    val staat = findViewById(R.id.s_rdStaat).asInstanceOf[Spinner].getSelectedItem.toString

    rdDatum = checkDate(rdDatum, rdJahr, rdMonat)

    val fremdDaten: FremdeDaten = FremdeDaten(person_id, art, nummer, rdDatum, behoerde, staat)

    val fremDao = db.mkFremDao()
    fremDao.insert(fremdDaten)
  }

  def gotoNext(view: View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[EntscheidungActivity])
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

    def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
      val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
      spinner.setAdapter(adapter)
    }
  }

  def checkDate(date: String, year: String, month: String): String = {

    val regSchaltjahr: Regex = "((30|31)\\..?2\\.\\d\\d\\d\\d)".r
    val regFeb: Regex = "((29|30|31)\\..?2\\.\\d\\d\\d\\d)".r
    val regRest: Regex = "(31\\.(.?4|.?6|.?9|11)\\.\\d\\d\\d\\d)".r

    if (year.toInt % 4 == 0) date match {
      case regSchaltjahr() => s"28.2.$year"
      case regRest() => s"30.$month.$year"
      case _ => date
    }
    else date match {
      case regFeb() => s"28.2.$year"
      case regRest() => s"30.$month.$year"
      case _ => date
    }
    date
  }
}
