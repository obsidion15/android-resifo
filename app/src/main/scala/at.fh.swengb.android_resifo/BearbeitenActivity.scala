package at.fh.swengb.android_resifo

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.{AdapterView, ListView}

/**
  * Created by Martin on 15.01.2017.
  */
class BearbeitenActivity extends Activity{
  var db: Db = _

  override protected def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.bearbeiten)

    var myListView = findViewById(R.id.LV_bearbeiten).asInstanceOf[ListView]

    db = Db(getApplicationContext())
    var data = new Data()
    val list = data.getDataIntoList(db)

    var myAdapter = new PersonAdapter(this, list)
    myListView.setAdapter(myAdapter)


    myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
        //val selectedItem = myAdapter.getItem(position) + 1
        val intent = new Intent(view.getContext, classOf[OverviewActivity])
        intent.putExtra("person_id",position + 1)
        startActivity(intent)
      }
    })
  }

}
