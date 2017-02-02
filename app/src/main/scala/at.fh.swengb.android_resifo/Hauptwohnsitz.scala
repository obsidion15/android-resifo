package at.fh.swengb.android_resifo

/**
  * Created by Sabine on 02.02.2017.
  */
class Hauptwohnsitz {
  private var hauptsitz_id: Int = 0
  private var person_id: Int = 0
  private var strasse: String = ""
  private var hausnr: String = ""
  private var stiege: String = ""
  private var tuer: String = ""
  private var plz: String = ""
  private var ort: String = ""
  private var bundesland: String = ""

  def getHauptsitzId(): Int = hauptsitz_id
  def getPersonId(): Int = person_id
  def getStrasse(): String = strasse
  def getHausnr(): String = hausnr
  def getStiege(): String = stiege
  def getTuer(): String = tuer
  def getPlz(): String = plz
  def getOrt(): String = ort
  def getBundesland(): String = bundesland

  def setHauptsitzId(n: Int) = hauptsitz_id = n
  def setPersonId(n: Int) = person_id = n
  def setStrasse(n: String) = strasse = n
  def setHausnr(n: String) = hausnr = n
  def setStiege(n: String) = stiege = n
  def setTuer(n: String) = tuer = n
  def setPlz(n: String) = plz = n
  def setOrt(n: String) = ort = n
  def setBundesland(n: String) = bundesland = n

}
