package at.fh.swengb.android_resifo

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View

/**
  * Created by Martin on 15.01.2017.
  */
class PersoenlicheDatenActivity extends Activity{

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.persoenliche_daten)
  }

  def gotoAnmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AnmeldungActivity])
    startActivity(i)
  }

  def gotoMain(view:View): Unit ={
    val i = new Intent(this, classOf[MainActivity])
    startActivity(i)
  }

}
