package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 16.01.2017.
  */
class OverviewActivity extends Activity{

  val d = new Data

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.overview)

    val intent: Intent = getIntent
    val person_id: Int = intent.getExtras.get("person_id").asInstanceOf[Int]

    val db = Db(getApplicationContext())
    d.getDataIntoOverview(db, person_id)
  }

  def gotoPersoenlicheDaten(view:View): Unit ={
    val i = new Intent(this, classOf[PersoenlicheDatenActivity])
    startActivity(i)
  }

  def gotoAnmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AnmeldungActivity])
    startActivity(i)
  }

  def gotoHauptwohnsitz(view:View): Unit ={
    val i = new Intent(this, classOf[HauptwohnsitzActivity])
    startActivity(i)
  }

  def gotoAbmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AbmeldungActivity])
    startActivity(i)
  }

  def gotoErfolgreich(view:View): Unit ={
    val i = new Intent(this, classOf[ErfolgreichActivity])
    startActivity(i)
  }
}
