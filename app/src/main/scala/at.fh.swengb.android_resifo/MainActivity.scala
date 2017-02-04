package at.fh.swengb.android_resifo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

/**
  * This Application helps entering data for a residence registration form.
  * The application consists of different screens which make it very easy to input the data.
  */

class MainActivity extends Activity {

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  def gotoImpressum(view: View): Unit ={
    val i = new Intent(this, classOf[ImpressumActivity])
    startActivity(i)
  }

  def gotoBearbeiten(view: View): Unit = {
    val i = new Intent(this, classOf[BearbeitenActivity])
    startActivity(i)
  }

  def gotoPersoenlicheDaten(view:View): Unit ={
    val i = new Intent(this, classOf[PersoenlicheDatenActivity])
    i.putExtra("person_id", 0)
    startActivity(i)
  }
}
