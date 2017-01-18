package at.fh.swengb.android_resifo

import android.os.Bundle
import android.app.Activity
import android.content.{ContentValues, Intent}
import android.database.Cursor
import android.view.View
import android.widget.{EditText, RadioButton, Spinner}

/**
  * Created by Martin on 15.01.2017.
  */
class PersoenlicheDatenActivity extends Activity{

  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.persoenliche_daten)
    db = Db(getApplicationContext)
  }

  def saveData(view:View): Unit = {

    val eT_nachname = findViewById(R.id.eT_nachname).asInstanceOf[EditText]
    val eT_vorname = findViewById(R.id.eT_vorname).asInstanceOf[EditText]
    val eT_nachnameVorher = findViewById(R.id.eT_nachnameVorher).asInstanceOf[EditText]
    val s_gebTag = findViewById(R.id.s_gebTag).asInstanceOf[Spinner]
    val s_gebMonat = findViewById(R.id.s_gebMonat).asInstanceOf[Spinner]
    val s_gebJahr = findViewById(R.id.s_gebJahr).asInstanceOf[Spinner]
    val eT_gebOrt = findViewById(R.id.eT_gebOrt).asInstanceOf[EditText]
    val rb_m = findViewById(R.id.rB_m).asInstanceOf[RadioButton]
//    val rb_w = findViewById(R.id.rB_w).asInstanceOf[RadioButton]
    val s_religion = findViewById(R.id.s_religion).asInstanceOf[Spinner]
    val s_famStand = findViewById(R.id.s_famStand).asInstanceOf[Spinner]
    val s_staat = findViewById(R.id.s_staat).asInstanceOf[Spinner]

    val cv = new ContentValues()

    val nachname: String = eT_nachname.getText.toString
    Map("nachname" -> nachname) foreach {
      case (k, v) => cv.put(k, v)
    }

    val vorname: String = eT_vorname.getText.toString
    Map("vorname" -> vorname) foreach {
      case (k, v) => cv.put(k, v)
    }

    val nachnameAlt: String = eT_nachnameVorher.getText.toString
    Map("nachnameAlt" -> nachnameAlt) foreach {
      case (k, v) => cv.put(k, v)
    }

    val geburtsdatum: String = s_gebTag.getSelectedItem().toString() + "." + s_gebMonat.getSelectedItem().toString() + "." + s_gebJahr.getSelectedItem().toString()
    Map("geburtsdatum" -> geburtsdatum) foreach {
      case (k, v) => cv.put(k, v)
    }

    val geburtsort = eT_gebOrt.getText.toString
    Map("geburtsort" -> geburtsort) foreach {
      case (k, v) => cv.put(k, v)
    }

    val geschlecht: String = if (rb_m.isChecked == true) "m" else "w"
    Map("geschlecht" -> geschlecht) foreach {
      case (k, v) => cv.put(k, v)
    }

    val religion: String = s_religion.getSelectedItem().toString()
    Map("religion" -> religion) foreach {
      case (k, v) => cv.put(k, v)
    }

    val familienstand: String = s_famStand.getSelectedItem().toString()
    Map("familienstand" -> familienstand) foreach {
      case (k, v) => cv.put(k, v)
    }

    val staatsangehoerigkeit: String = s_staat.getSelectedItem().toString()
    Map("staatsangehoerigkeit" -> staatsangehoerigkeit) foreach {
      case (k, v) => cv.put(k, v)
    }

    db.getWritableDatabase().insert("person", null, cv)

    var someCursor: Option[Cursor] = None
    try {
      someCursor = Option(db.getReadableDatabase.query("person", Array("person_id", "nachname", "vorname", "nachnameAlt", "geburtsdatum", "geburtsort", "geschlecht", "religion", "familienstand", "staatsangehoerigkeit"), null, null, null, null, null))

      someCursor match {
        case None => System.err.println("Could not execute query due to some reason")
        case Some(c) =>
          while (c.moveToNext()) {
            val id = c.getInt(c.getColumnIndex("person_id"))
            val nachname = c.getString(c.getColumnIndex("nachname"))
            val vorname = c.getString(c.getColumnIndex("vorname"))
            val nachnameAlt = c.getString(c.getColumnIndex("nachnameAlt"))
            val geburtsdatum = c.getString(c.getColumnIndex("geburtsdatum"))
            val geburtsort = c.getString(c.getColumnIndex("geburtsort"))
            val geschlecht = c.getString(c.getColumnIndex("geschlecht"))
            val religion = c.getString(c.getColumnIndex("religion"))
            val familienstand = c.getString(c.getColumnIndex("familienstand"))
            val staatsangehoerigkeit = c.getString(c.getColumnIndex("staatsangehoerigkeit"))
            println(s"ID($id): $nachname ($nachnameAlt) $vorname, geboren am $geburtsdatum in $geburtsort, Geschlecht: $geschlecht, $religion, $familienstand, $staatsangehoerigkeit")
          }
      }

    } finally {
      someCursor foreach (_.close())
    }
  }

  def gotoAnmeldung(view:View): Unit ={
    saveData(view)
    val i = new Intent(this, classOf[AnmeldungActivity])
    startActivity(i)
  }

  def gotoMain(view:View): Unit ={
    val i = new Intent(this, classOf[MainActivity])
    startActivity(i)
  }

}
