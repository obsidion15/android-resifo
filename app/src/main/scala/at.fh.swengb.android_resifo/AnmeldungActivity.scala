package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 15.01.2017.
  */
class AnmeldungActivity extends Activity{

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.anmeldung)
  }

  def gotoHauptwohnsitz(view:View): Unit ={
    val i = new Intent(this, classOf[HauptwohnsitzActivity])
    startActivity(i)
  }

  def goBack(view:View): Unit ={
    finish()
  }

}
