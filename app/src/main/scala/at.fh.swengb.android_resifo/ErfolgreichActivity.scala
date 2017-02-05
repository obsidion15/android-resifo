package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 15.01.2017.
  */
class ErfolgreichActivity extends Activity{

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.aenderung_erfolgreich)
  }

  def gotoMain(view:View): Unit ={
    val i = new Intent(this, classOf[MainActivity])
    finish()
    startActivity(i)
  }

}
