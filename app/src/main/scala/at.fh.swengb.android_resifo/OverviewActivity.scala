package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

/**
  * Created by Martin on 16.01.2017.
  */
class OverviewActivity extends Activity{

  var db: Db = _
  val d = new Data
  var person_id: Int = 0

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.overview)

    val intent: Intent = getIntent
    person_id = intent.getExtras.get("person_id").asInstanceOf[Int]

    db = Db(getApplicationContext())
    val dataMap = d.getDataIntoOverview(db, person_id)
    fillDataInTextView(dataMap)
  }

  def fillDataInTextView(dataMap: Map[String, Map[Int, Any]]): Unit = {
      val personData = dataMap("person")
//      val anmeldungData = dataMap("anmeldung")
      val hauptwohnsitzData = dataMap("hauptwohnsitz")
//      val abmeldungData = dataMap("abmeldung")

      //Personen Daten
      findViewById(R.id.eT_nachname).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getNachname())
      findViewById(R.id.eT_vorname).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getVorname())
      findViewById(R.id.eT_nachnameVorher).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getNachnameAlt())
      findViewById(R.id.rB_gebDatum).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getGeburtsdatum())
      findViewById(R.id.eT_gebOrt).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getGeburtsort())
      findViewById(R.id.rB_geschlecht).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getGeschlecht())
      findViewById(R.id.s_religion).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getReligion())
      findViewById(R.id.s_familienstand).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getFamilienstand())
      findViewById(R.id.s_staat).asInstanceOf[TextView].setText(personData(person_id).asInstanceOf[Person].getStaatsangehoerigkeit())
/*
      //Anmeldung Daten
      findViewById(R.id.eT_anStraße).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getStrasse())
      findViewById(R.id.eT_anHausNr).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getHausnr())
      findViewById(R.id.eT_anStiege).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getStiege())
      findViewById(R.id.eT_anTuer).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getTuer())
      findViewById(R.id.eT_anPLZ).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getPlz())
      findViewById(R.id.eT_anOrt).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getOrt())
      findViewById(R.id.s_anBundesland).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getBundesland())
      findViewById(R.id.rB_anAusland).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getZuzugAusAusland())
      findViewById(R.id.rB_anHWS).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getHauptwohnsitz())
      findViewById(R.id.eT_anNameUG).asInstanceOf[TextView].setText(anmeldungData(person_id).asInstanceOf[Anmeldung].getUnterkunftgeber())
*/
      //Hauptwohnsitz Daten
      findViewById(R.id.eT_hwsStraße).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getStrasse())
      findViewById(R.id.eT_hwsHausNr).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getHausnr())
      findViewById(R.id.eT_hwsStiege).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getStiege())
      findViewById(R.id.eT_hwsTuer).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getTuer())
      findViewById(R.id.eT_hwsPLZ).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getPlz())
      findViewById(R.id.eT_hwsOrt).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getOrt())
      findViewById(R.id.s_hwsBundesland).asInstanceOf[TextView].setText(hauptwohnsitzData(person_id).asInstanceOf[Hauptwohnsitz].getBundesland())
/*
      //Abmeldung Daten
      findViewById(R.id.eT_abStraße).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getStrasse())
      findViewById(R.id.eT_abHausNr).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getHausnr())
      findViewById(R.id.eT_abStiege).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getStiege())
      findViewById(R.id.eT_abTuer).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getTuer())
      findViewById(R.id.eT_abPLZ).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getPlz())
      findViewById(R.id.eT_abOrt).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getOrt())
      findViewById(R.id.s_abBundesland).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getBundesland())
      findViewById(R.id.rB_abAusland).asInstanceOf[TextView].setText(abmeldungData(person_id).asInstanceOf[Abmeldung].getVerzugAusAusland())
*/
  }


  def gotoPersoenlicheDaten(view:View): Unit ={
    val i = new Intent(this, classOf[PersoenlicheDatenActivity])
    i.putExtra("person_id", person_id)
    i.putExtra("update", "update")
    startActivity(i)
  }
/*
  def gotoAnmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AnmeldungActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }
*/
  def gotoHauptwohnsitz(view:View): Unit ={
    val i = new Intent(this, classOf[HauptwohnsitzActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }
/*
  def gotoAbmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AbmeldungActivity])
    i.putExtra("person_id", person_id)
    startActivity(i)
  }
*/
  def goBack(view:View): Unit ={
    val i = new Intent(this, classOf[MainActivity])
    startActivity(i)
  }
}
