package at.fh.swengb.android_resifo

import android.content.Context
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{BaseAdapter, TextView}

/**
  * Created by Sabine on 02.02.2017.
  */
class PersonAdapter(val context: Context, var listItems:Map[Int, List[Any]]) extends BaseAdapter{
  override def getCount: Int = {
    listItems.size
  }

  override def getItem(position: Int):List[Any] = {
    listItems(position)
  }

  override def getItemId(position: Int):Long = {
    position
  }

  def convertView(position: Int, view: View):View = {
    view.findViewById(R.id.textViewListName).asInstanceOf[TextView].setText(getItem(position + 1)(0).asInstanceOf[Person].getNachname() + " " + getItem(position + 1)(0).asInstanceOf[Person].getVorname())
    view.findViewById(R.id.eT_anStraße).asInstanceOf[TextView].setText(getItem(position + 1)(1).asInstanceOf[Hauptwohnsitz].getStrasse())
    view.findViewById(R.id.eT_anHausNr).asInstanceOf[TextView].setText(getItem(position + 1)(1).asInstanceOf[Hauptwohnsitz].getHausnr())
    view.findViewById(R.id.eT_anPLZ).asInstanceOf[TextView].setText(getItem(position + 1)(1).asInstanceOf[Hauptwohnsitz].getPlz())
    view.findViewById(R.id.eT_anOrt).asInstanceOf[TextView].setText(getItem(position + 1)(1).asInstanceOf[Hauptwohnsitz].getOrt())
    view
  }

  override def getView(position: Int, view: View, parent: ViewGroup):View = view match {
    case null => convertView(position, (LayoutInflater.from(context)).inflate(R.layout.row_item, null))
    case _ => convertView(position,view)
  }

}
