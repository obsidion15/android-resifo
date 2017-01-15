package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * Created by Martin on 15.01.2017.
  */
class HauptwohnsitzActivity extends Activity{

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.hauptwohnsitz)
  }

  def gotoAnmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AnmeldungActivity])
    startActivity(i)
  }

  def gotoAbmeldung(view:View): Unit ={
    val i = new Intent(this, classOf[AbmeldungActivity])
    startActivity(i)
  }

}
