package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 02.02.2017.
  */
class EntscheidungActivity extends Activity{

  var person_id = 0

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.entscheidung)

    person_id = getIntent.getExtras.get("person_id").asInstanceOf[Int]
  }

  def gotoAnmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AnmeldungActivity])
    i.putExtra("person_id", person_id)
    finish()
    startActivity(i)
  }

  def gotoAbmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AbmeldungActivity])
    i.putExtra("person_id", person_id)
    finish()
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    val i = new Intent(this, classOf[PersoenlicheDatenActivity])
    i.putExtra("person_id", person_id)
    i.putExtra("update", "update")
    i.putExtra("update2", "update2")
    finish()
    startActivity(i)
  }
}
