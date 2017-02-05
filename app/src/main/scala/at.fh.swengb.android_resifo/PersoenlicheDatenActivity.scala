package at.fh.swengb.android_resifo

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.view.View
import android.widget._

/**
  * Created by Martin on 15.01.2017.
  */
class PersoenlicheDatenActivity extends Activity{

  var db: Db = _
  var person_id = 0
  val d = new Data

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.persoenliche_daten)

    person_id = getIntent.getExtras.get("person_id").asInstanceOf[Int]

    fillAllSpinner()

    db = Db(getApplicationContext)
    val dataMap = d.fillPersDaten(db, person_id)
    fillDataInTextView(dataMap, person_id)
  }

  def fillDataInTextView(personData: Map[Int, Any], person_id: Int) : Unit = {
    val arrGeburtsdatum = personData(person_id).asInstanceOf[Person].getGeburtsdatum()
    val geschlecht = personData(person_id).asInstanceOf[Person].getGeschlecht()
    val religion = personData(person_id).asInstanceOf[Person].getReligion()
    val familienstand = personData(person_id).asInstanceOf[Person].getFamilienstand()
    val staatsgehoerigkeit = personData(person_id).asInstanceOf[Person].getStaatsangehoerigkeit()

    findViewById(R.id.eT_nachname).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getNachname())
    findViewById(R.id.eT_vorname).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getVorname())
    findViewById(R.id.eT_nachnameVorher).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getNachnameAlt())

    //findViewById(R.id.rB_gebDatum).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getGeburtsdatum())
    findViewById(R.id.eT_gebOrt).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getGeburtsort())

    if(geschlecht == "m") {
      findViewById(R.id.rB_m).asInstanceOf[RadioButton].setChecked(true)
      findViewById(R.id.rB_w).asInstanceOf[RadioButton].setChecked(false)
    } else {
      findViewById(R.id.rB_m).asInstanceOf[RadioButton].setChecked(false)
      findViewById(R.id.rB_w).asInstanceOf[RadioButton].setChecked(true)
    }

    if(religion == "röm-kath") {
      findViewById(R.id.s_religion).asInstanceOf[Spinner].setSelection(0)
    } else if(religion == "andere") {
      findViewById(R.id.s_religion).asInstanceOf[Spinner].setSelection(1)
    }

    if(familienstand == "ledig") {
      findViewById(R.id.s_famStand).asInstanceOf[Spinner].setSelection(0)
    } else if(familienstand == "verheiratet") {
      findViewById(R.id.s_famStand).asInstanceOf[Spinner].setSelection(1)
    } else if(familienstand == "geschieden") {
      findViewById(R.id.s_famStand).asInstanceOf[Spinner].setSelection(2)
    } else if(familienstand == "...") {
      findViewById(R.id.s_famStand).asInstanceOf[Spinner].setSelection(3)
    }

    if(staatsgehoerigkeit == "Österreich") {
      findViewById(R.id.s_staat).asInstanceOf[Spinner].setSelection(0)
    } else if (staatsgehoerigkeit == "Deutschland") {
      findViewById(R.id.s_staat).asInstanceOf[Spinner].setSelection(1)
    } else if (staatsgehoerigkeit == "...") {
      findViewById(R.id.s_staat).asInstanceOf[Spinner].setSelection(2)
    }
  }

  def saveData(view:View): Unit = {

    val nachname = findViewById(R.id.eT_nachname).asInstanceOf[EditText].getText.toString
    val vorname = findViewById(R.id.eT_vorname).asInstanceOf[EditText].getText.toString
    val nachnameVorher = findViewById(R.id.eT_nachnameVorher).asInstanceOf[EditText].getText.toString
    val gebTag = findViewById(R.id.s_gebTag).asInstanceOf[Spinner].getSelectedItem.toString
    val gebMonat = findViewById(R.id.s_gebMonat).asInstanceOf[Spinner].getSelectedItem.toString
    val gebJahr = findViewById(R.id.s_gebJahr).asInstanceOf[Spinner].getSelectedItem.toString
    var gebDatum = s"$gebTag.$gebMonat.$gebJahr"
    val gebOrt = findViewById(R.id.eT_gebOrt).asInstanceOf[EditText].getText.toString
    val rb_m = findViewById(R.id.rB_m).asInstanceOf[RadioButton]
    val geschlecht = if (rb_m.isChecked == true) "m" else "w"
    val religion = findViewById(R.id.s_religion).asInstanceOf[Spinner].getSelectedItem.toString
    val famStand = findViewById(R.id.s_famStand).asInstanceOf[Spinner].getSelectedItem.toString
    val staat = findViewById(R.id.s_staat).asInstanceOf[Spinner].getSelectedItem.toString

    gebDatum = checkDate(gebTag, gebMonat, gebJahr)

    val persDaten: PersoenlicheDaten = PersoenlicheDaten(nachname, vorname, nachnameVorher, gebDatum, gebOrt, geschlecht, religion, famStand, staat)

    val persDao = db.mkPersDao()
    persDao.insert(persDaten)
  }

  def fetchPersonId(): Int = {

    var someCursor: Option[Cursor] = None
    var id = 0

    try {
      someCursor = Option(db.getReadableDatabase.query("person", Array("person_id", "nachname", "vorname", "nachnameAlt", "geburtsdatum", "geburtsort", "geschlecht", "religion", "familienstand", "staatsangehoerigkeit"), null, null, null, null, null))
      someCursor match {
        case None =>
          System.err.println("Could not execute query due to some reason")
          id
        case Some(c) =>
          while (c.moveToNext()) {
            val pers_id = c.getInt(c.getColumnIndex("person_id"))
            id += pers_id
          }
          id
      }
    } finally {
      someCursor foreach (_.close())
    }
  }

  def updateData(view: View): Unit = {
    val nachname = findViewById(R.id.eT_nachname).asInstanceOf[EditText].getText.toString
    val vorname = findViewById(R.id.eT_vorname).asInstanceOf[EditText].getText.toString
    val nachnameVorher = findViewById(R.id.eT_nachnameVorher).asInstanceOf[EditText].getText.toString
    val gebTag = findViewById(R.id.s_gebTag).asInstanceOf[Spinner].getSelectedItem.toString
    val gebMonat = findViewById(R.id.s_gebMonat).asInstanceOf[Spinner].getSelectedItem.toString
    val gebJahr = findViewById(R.id.s_gebJahr).asInstanceOf[Spinner].getSelectedItem.toString
    var gebDatum = s"$gebTag.$gebMonat.$gebJahr"
    val gebOrt = findViewById(R.id.eT_gebOrt).asInstanceOf[EditText].getText.toString
    val rb_m = findViewById(R.id.rB_m).asInstanceOf[RadioButton]
    val geschlecht = if (rb_m.isChecked == true) "m" else "w"
    val religion = findViewById(R.id.s_religion).asInstanceOf[Spinner].getSelectedItem.toString
    val famStand = findViewById(R.id.s_famStand).asInstanceOf[Spinner].getSelectedItem.toString
    val staat = findViewById(R.id.s_staat).asInstanceOf[Spinner].getSelectedItem.toString

    gebDatum = checkDate(gebTag, gebMonat, gebJahr)

    val persDaten: PersoenlicheDaten = PersoenlicheDaten(nachname, vorname, nachnameVorher, gebDatum, gebOrt, geschlecht, religion, famStand, staat)

    val persDao = db.mkPersDao()
    persDao.deleteById(person_id)
    persDao.insert(persDaten)
  }

  def gotoNext(view:View): Unit ={
    val check: String = getIntent.getExtras.get("update").asInstanceOf[String]
    if (check == "update") {
      updateData(view)
      finish()
      val i = new Intent(this, classOf[ErfolgreichActivity])
      startActivity(i)
    }
    else {
      saveData(view)
      val i = new Intent(this, classOf[EntscheidungActivity])
      i.putExtra("person_id", fetchPersonId())
      startActivity(i)
    }
  }

  def goBack(view:View): Unit = finish()

  def fillAllSpinner(): Unit ={
    fillSpinner(findViewById(R.id.s_gebTag).asInstanceOf[Spinner], Array.range(1,31 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_gebMonat).asInstanceOf[Spinner], Array.range(1,12 + 1).map(x => x.toString))
    fillSpinner(findViewById(R.id.s_gebJahr).asInstanceOf[Spinner], Array.range(1970,2015 + 1).reverse.map(x => x.toString))
    fillSpinner(findViewById(R.id.s_religion).asInstanceOf[Spinner], Array("röm-kath","andere"))
    fillSpinner(findViewById(R.id.s_famStand).asInstanceOf[Spinner], Array("ledig","verheiratet","geschieden","..."))
    fillSpinner(findViewById(R.id.s_staat).asInstanceOf[Spinner], Array("Österreich", "Deutschland", "..."))

    def fillSpinner(spinner: Spinner, content: Array[String]): Unit ={
      val adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, content)
      spinner.setAdapter(adapter)
    }
  }

  def checkDate(day: String, month: String, year: String): String = {

    val date = s"$day.$month.$year"

    if (year.toInt % 4 == 0){
      if (month == "2") {
        if (day.toInt > 29) return s"29.2.$year"
      }
    }
    else {
      if (month == "2"){
        if (day.toInt > 28) return s"28.2.$year"
      }
      else if (month == "4" || month == "6" || month == "9" || month == "11") {
        if (day.toInt > 30) return s"30.$month.$year"
      }
    }
    date
  }
}
